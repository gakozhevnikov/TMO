package com.kga.tmo;

/**
 * Created by home on 26.05.2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**Класс в котором будет передоваться данные из файла graph.xls в базу данных.
 *
 * */
public class GraphToDataBase {
    //Блок 1(н):Объявление переменных
    /***/
    private static final String tagLog = "MyLog";
    /**Контекст который передается из MainActivity через контруктор класса */
    Context context;
    /**Экземпляр класса DBHelper для работы с базой данных
     * @see DBHelper*/
    DBHelper mainDBHelper;
    /***/
    SQLiteDatabase maindb;
    /***/
    TMOFile tmoFile = new TMOFile();
    /***/
    File fileGraph = tmoFile.getFileGraph();
    /***/
    LinkedListSettingXml linkedListSettingXml;
    /***/
    Integer countObject;
    /***/
    Integer countTitleEquipment;
    /***/
    Integer countTypesTO;
    /***/
    Integer startRow;
    /***/
    Integer endRow;
    /***/
    Integer startCellTO;
    /***/
    Integer endCellTO;
    /***/
    Integer endCell;
    /***/
    Integer ColumnTitle;
    /***/
    Integer ColumnSerial;
    /***/
    Integer ColumnVMI;
    /***/
    Integer locationSetting;
    /***/
    Integer locationObjectSetting;
    /***/
    ArrayList<String> unUniqueEquipment = new ArrayList<>();//Создаем два ArrayList чтобы убрать дубли оборудования, с повторами.
    /***/
    ArrayList <String> uniqueEquipment = new ArrayList<>();//без повторов
    /***/
    ArrayList<String> unUniqueObject = new ArrayList<>();//Создаем два ArrayList чтобы убрать дубли , с повторами.
    /***/
    ArrayList <String> uniqueObject = new ArrayList<>();//без повторов
    /***/
    ArrayList<String> unUniqueTypes = new ArrayList<>();//Создаем два ArrayList чтобы убрать дубли , с повторами.
    /***/
    ArrayList <String> uniqueTypes = new ArrayList<>();//без повторов
    /***/
    ArrayList <String> unUniqueLocation = new ArrayList<>();
    /***/
    ArrayList <String> uniqueLocation = new ArrayList<>();
    /***/
    String locationObjectString ;
    /***/
    String locationString ;
    /**Карта необходима для того чтобы убирать повторные позиции. String - помещается название объекта. ArrayList<String> - помещается отсортированный и без повторов перечень позиций.*/
    Map<String, ArrayList<String>> mapObjectLocation = new HashMap<String, ArrayList<String>>();
    /***/
    ArrayList <String> addUpdateCell = new ArrayList<>();//для добавления графика в базу данных

    /***/
    //Для добавления в БД значений - Создать новую карту значений, где имена столбцов ключи(кривой перевод Create a new map of values, where column names are the keys )
    ContentValues mainValues = new ContentValues();

    //Блок 1(н):Объявление переменных

    /**Метод в котором данные из файла graph.xls переносяться в таблицу Equipment базы данных
     * @see DataBaseContract.EquipmentClass*/
    //Блок 2(к):Конструктор
    /**Конструктор класса в который передаётся контекст*/
    GraphToDataBase(Context context){

        this.context=context;
        //Log.d(tagLog, "GraphToDataBase.GraphToDataBase,Блок 1:"+" set:"+tmoFile.isExistFileSetting());
        linkedListSettingXml = new LinkedListSettingXml(context);
        //Log.d(tagLog, "GraphToDataBase.GraphToDataBase,Блок 2:"+" set:"+tmoFile.isExistFileSetting());
        mainDBHelper = new DBHelper(context);
        //Log.d(tagLog, "GraphToDataBase.GraphToDataBase,Блок 3:"+" set:"+tmoFile.isExistFileSetting());
        linkedListSettingXml.createLinkedListSettingXml();
        //Log.d(tagLog, "GraphToDataBase.GraphToDataBase,Блок 4:"+" set:"+tmoFile.isExistFileSetting());
        countObject = (Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_COUNT_OBJECT_SETTING)));
        countTitleEquipment =(Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_COUNT_TITLE_EQUIPMENT_SETTING)));
        startRow = (Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_START_ROW_SETTING)))-1;//-1 т.к. счет програмнно идёт от нуля, в таблице Excel от 1
        endRow=(Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_END_ROW_SETTING)))-1;
        startCellTO=(Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_COLUMN_JANUARY_SETTING)))-1;
        endCellTO =(Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_COLUMN_DECEMBER_SETTING)))-1;
        endCell =(Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_COLUMN_LOCATION_SETTING)))-1;
        countTypesTO=(Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_COUNT_TYPES_TO_SETTING)));
        ColumnTitle =(Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_COLUMN_TITLE_SETTING)))-1;
        ColumnSerial =(Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_COLUMN_SERIAL_SETTING)))-1;
        ColumnVMI =(Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_COLUMN_VMI_SETTING)))-1;
        locationSetting =(Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_COLUMN_LOCATION_SETTING)))-1;//повтор
        locationObjectSetting =(Integer.parseInt(linkedListSettingXml.getValueByKey(LinkedListSettingXml.KEY_COLUMN_TITLE_SETTING)))-1;//повтор
        maindb =mainDBHelper.getWritableDatabase();// Создаем базу данных. Если она не существует,то здесь она будет создана
        mainDBHelper.onUpdate(maindb);

    }
    //Блок 2(к):Конструктор

    //Блок 3(н):Метод наполнения в базе данных таблицы Equipment
    /**Метод наполнения в базе данных таблицы Equipment*/
    boolean Equipment (){
        //Log.d(tagLog, "GraphToDataBase.Equipment,Блок 4,1:");
        unUniqueEquipment.clear();
        mainValues.clear();
        boolean result;
        Integer rowInteger;
        Integer cellInteger=0;
        Integer i=0;
        try {
            //*открываем файл через openFileInput, при таком способе открывается файл в папке data/data/home.testv1/files
            //* через FileInputStream на работало, видимо какие-то изменения в java после обновления, поэтому сработало через openFileInput
            InputStream inp = new FileInputStream(fileGraph);
            Workbook wb = WorkbookFactory.create(inp);
            //берем первый лист
            Sheet sheet = wb.getSheetAt(0);
            for (rowInteger=startRow;rowInteger<=endRow;rowInteger++){
                //читаем первое поле (отсчет полей идет с нуля) т.е. по сути читаем второе - cell с 0, а Row с 1
                Row row = sheet.getRow(rowInteger);
                //читаем столбцы
                Cell cell = row.getCell(cellInteger);
                //Устанавливаем тип Cell String СРАЗУ т.к. если в файле Ексель будут цифры или ПУСТЫЕ то они не будут обрабатываться как String,
                // и код не будет выполняться или будет ошибка.
                cell.setCellType(Cell.CELL_TYPE_STRING);
                //Для Cell getStringCellValue().toString() именно .toString() обязательно т.к. если его убрать то будут возникать ошибки, к примеру при отсутствии в ячейки значения и без приведения к стринг значение будет восприниматься как null  и вызывать ошибку
                if(cell.getStringCellValue().toString().equals("")//Убираем разные не нужные вспомогательные слова которые попадаются по ходу чтения из файла
                        | cell.getStringCellValue().toString().equals("Объект:")){
                    continue;
                }
                else {
                    unUniqueEquipment.add(cell.getStringCellValue().toString());
                }
            }
            //Удаляем повторения из файла чтобы создать в БД таблицу с наименованиями оборудования, для этого удаляем повторяющиеся имена.
            uniqueEquipment=Sort(unUniqueEquipment);
            //заносим в БД "уникальные" наименования оборудования, т.е. без повторяющихся и сравниваем количество оборудования в файле и настройках
            //Log.d(tagLog, "GraphToDataBase.Equipment,Блок 3,2:"+uniqueEquipment.toString());
            if (countTitleEquipment ==uniqueEquipment.size()) {
                while (i < uniqueEquipment.size()) {//может быть надо (i<=uniqueEquipment.size()-1), -1 надо
                    //Log.d(tagLog, "GraphToDataBase.Equipment,Блок 3,3:"+i+" :"+uniqueEquipment.get(i));
                    mainValues.put(DataBaseContract.EquipmentClass.COLUMN_NAME_TITLE, uniqueEquipment.get(i));
                    //Log.d(tagLog, "GraphToDataBase.Equipment,Блок 3,4:"+i);
                    maindb.insert(DataBaseContract.EquipmentClass.TABLE_NAME, null, mainValues);
                    //Log.d(tagLog, "GraphToDataBase.Equipment,Блок 3,5:"+i);
                    i++;
                }
            }else if  (countTitleEquipment !=uniqueEquipment.size()) {//!= не равно
                Toast.makeText(context, context.getString(R.string.toast_graph_to_database_equipment)+ " :"+uniqueEquipment.size(), Toast.LENGTH_LONG).show();
            }
            inp.close();
            result=true;
        }
        catch(Exception e) {
            //Log.d(tagLog, "GraphToDataBase.Equipment,Блок 3,6:"+e);
            result=false;
        }

        return result;
    }
    //Блок 3(к):Метод наполнения в базе данных таблицы Equipment

    //Блок 4(н):Метод убирает повторения и сортирует
    /**Метод убирает повторения и сортирует*/
    ArrayList<String> Sort(ArrayList<String> unUnique){
        //Log.d(tagLog, "GraphToDataBase.Sort,Блок 4,5:"+unUnique.toString());
        List<String> listSort = new ArrayList<String>();// для сортировки в алфавитном порядке
        ArrayList<String> uniqueSort = new ArrayList<>();
        uniqueSort.clear();
        Set<String> sortSet =new HashSet<String>(unUnique);//убираем повторяющиеся элементы
        listSort.addAll(sortSet);// передаем массив для сортировки
        Collections.sort(listSort, new Comparator<String>() {//сортируем
            public int compare(String o1, String o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        uniqueSort.addAll(listSort); //передаем сортированный и без повторения массив
        //Log.d(tagLog, "GraphToDataBase.Sort,Блок 4,5:"+uniqueSort.toString());
        return uniqueSort;
    }
    //Блок 4(к):Метод убирает повторения и сортирует


    //Блок 5(н):Метод наполнения в базе данных таблицы object
    /**Метод наполнения в базе данных таблицы object*/
    boolean Object (){
        //Log.d(tagLog, "GraphToDataBase.Object,Блок 5,1:");
        unUniqueObject.clear();
        mainValues.clear();
        boolean result;
        Integer rowInteger;
        Integer cellInteger=0;
        Integer i=0;
        try {
            //*открываем файл через openFileInput, при таком способе открывается файл в папке data/data/home.testv1/files
            //* через FileInputStream на работало, видимо какие-то изменения в java после обновления, поэтому сработало через openFileInput
            InputStream inp = new FileInputStream(fileGraph);
            Workbook wb = WorkbookFactory.create(inp);
            //берем первый лист
            Sheet sheet = wb.getSheetAt(0);
            for (rowInteger=startRow;rowInteger<=endRow;rowInteger++){
                //читаем первое поле (отсчет полей идет с нуля) т.е. по сути читаем второе - cell с 0, а Row с 1
                Row row = sheet.getRow(rowInteger);
                //читаем столбцы
                Cell cell = row.getCell(cellInteger);
                //Устанавливаем тип Cell String СРАЗУ т.к. если в файле Ексель будут цифры или ПУСТЫЕ то они не будут обрабатываться как String,
                // и код не будет выполняться или будет ошибка.
                cell.setCellType(Cell.CELL_TYPE_STRING);
                //Для Cell getStringCellValue().toString() именно .toString() обязательно т.к. если его убрать то будут возникать ошибки, к примеру при отсутствии в ячейки значения и без приведения к стринг значение будет восприниматься как null  и вызывать ошибку
                if(cell.getStringCellValue().toString().equals("")){//Убираем разные не нужные вспомогательные слова которые попадаются по ходу чтения из файла
                    continue;
                }
                else if (cell.getStringCellValue().toString().equals("Объект:")) {
                    Cell cellObjectTitle = row.getCell(cellInteger+1);
                    cellObjectTitle.setCellType(Cell.CELL_TYPE_STRING);
                    unUniqueObject.add(cellObjectTitle.getStringCellValue().toString());
                }
            }
            //Удаляем повторения из файла чтобы создать в БД таблицу с наименованиями оборудования, для этого удаляем повторяющиеся имена.
            uniqueObject=Sort(unUniqueObject);
            //заносим в БД "уникальные" наименования оборудования, т.е. без повторяющихся и сравниваем количество оборудования в файле и настройках
            //Log.d(tagLog, "GraphToDataBase.Object,Блок 5,2:"+uniqueObject.toString());
            if (countObject ==uniqueObject.size()) {
                while (i < uniqueObject.size()) {
                    //Log.d(tagLog, "GraphToDataBase.Object,Блок 5,3:"+i+" :"+uniqueObject.get(i));
                    mainValues.put(DataBaseContract.Object.COLUMN_NAME_OBJECT, uniqueObject.get(i));
                    //Log.d(tagLog, "GraphToDataBase.Equipment,Блок 5,4:"+i);
                    maindb.insert(DataBaseContract.Object.TABLE_NAME, null, mainValues);
                    //Log.d(tagLog, "GraphToDataBase.Object,Блок 5,5:"+i);
                    i++;
                }
            }else if  (countObject !=uniqueObject.size()) {//!= не равно
                Toast.makeText(context, context.getString(R.string.toast_graph_to_database_object)+ " :"+uniqueObject.size(), Toast.LENGTH_LONG).show();
            }
            inp.close();
            result=true;
        }
        catch(Exception e) {
            //Log.d(tagLog, "GraphToDataBase.Equipment,Блок 3,6:"+e);
            result=false;
        }

        return result;
    }
    //Блок 5(к):Метод наполнения в базе данных таблицы object

    //Блок 6(н):Метод наполнения в базе данных таблицы TypesTO
    /**Метод наполнения в базе данных таблицы TypesTO*/
    boolean TypesTO (){
        //Log.d(tagLog, "GraphToDataBase.TypesTO,Блок 6,1:");
        unUniqueTypes.clear();
        mainValues.clear();
        boolean result;
        Integer rowInteger;
        Integer cellInteger=0;
        Integer i=0;
        try {
            //*открываем файл через openFileInput, при таком способе открывается файл в папке data/data/home.testv1/files
            //* через FileInputStream на работало, видимо какие-то изменения в java после обновления, поэтому сработало через openFileInput
            InputStream inp = new FileInputStream(fileGraph);
            Workbook wb = WorkbookFactory.create(inp);
            //берем первый лист
            Sheet sheet = wb.getSheetAt(0);
            for (cellInteger=startCellTO; cellInteger<=endCellTO;cellInteger++) {
                for (rowInteger=startRow;rowInteger<=endRow;rowInteger++){
                    //читаем первое поле (отсчет полей идет с нуля) т.е. по сути читаем второе - cell с 0, а Row с 1
                    Row row = sheet.getRow(rowInteger);
                    //читаем столбцы
                    Cell cell = row.getCell(cellInteger);
                    //Устанавливаем тип Cell String СРАЗУ т.к. если в файле Ексель будут цифры или ПУСТЫЕ то они не будут обрабатываться как String,
                    // и код не будет выполняться или будет ошибка.
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    //Для Cell getStringCellValue().toString() именно .toString() обязательно т.к. если его убрать то будут возникать ошибки, к примеру при отсутствии в ячейки значения и без приведения к стринг значение будет восприниматься как null  и вызывать ошибку
                    if(cell.getStringCellValue().toString().equals("")| cell.getStringCellValue().toString().equals("-")){//Убираем разные не нужные вспомогательные слова которые попадаются по ходу чтения из файла
                        continue;
                    }
                    else {
                        unUniqueTypes.add(cell.getStringCellValue().toString());
                    }
                    rowInteger++;
                }
            }
            //Удаляем повторения из файла чтобы создать в БД таблицу с наименованиями оборудования, для этого удаляем повторяющиеся имена.
            uniqueTypes=Sort(unUniqueTypes);
            //заносим в БД "уникальные" наименования оборудования, т.е. без повторяющихся и сравниваем количество оборудования в файле и настройках
            //Log.d(tagLog, "GraphToDataBase.TypesTO,Блок 6,2: countTypesTO:"+countTypesTO+" uniqueTypes:"+uniqueTypes);
            if (countTypesTO ==uniqueTypes.size()) {
                while (i < uniqueTypes.size()) {
                    //Log.d(tagLog, "GraphToDataBase.TypesTO,Блок 6,3:"+i+" :");
                    mainValues.put(DataBaseContract.TypesTOClass.COLUMN_NAME_TITLE, uniqueTypes.get(i));
                    //Log.d(tagLog, "GraphToDataBase.TypesTO,Блок 6,4:"+i);
                    maindb.insert(DataBaseContract.TypesTOClass.TABLE_NAME, null, mainValues);
                    //Log.d(tagLog, "GraphToDataBase.TypesTO,Блок 6,5:"+i);
                    i++;
                }
            }else if  (countTypesTO !=uniqueTypes.size()) {//!= не равно
                Toast.makeText(context, context.getString(R.string.toast_graph_to_database_type_to)+ " :"+uniqueTypes.size(), Toast.LENGTH_LONG).show();
            }
            inp.close();
            result=true;
        }
        catch(Exception e) {
            //Log.d(tagLog, "GraphToDataBase.Equipment,Блок 3,6:"+e);
            result=false;
        }

        return result;
    }
    //Блок 6(к):Метод наполнения в базе данных таблицы TypesTO

    //Блок 7(н):Метод наполнения в базе данных таблицы Location
    /**Метод наполнения в базе данных таблицы Position*/
    boolean Location (){
        unUniqueLocation.clear();
        uniqueLocation.clear();
        mapObjectLocation.clear();
        locationObjectString="";
        locationString="";
        mainValues.clear();
        boolean result;
        Integer rowInteger;
        Integer locationObjectTitleSetting = locationObjectSetting+1;
        try {
            //*открываем файл через openFileInput, при таком способе открывается файл в папке data/data/home.testv1/files
            //* через FileInputStream на работало, видимо какие-то изменения в java после обновления, поэтому сработало через openFileInput
            InputStream inp = new FileInputStream(fileGraph);
            Workbook wb = WorkbookFactory.create(inp);
            //берем первый лист
            Sheet sheet = wb.getSheetAt(0);
            //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,1:");
            for (rowInteger=startRow;rowInteger<=endRow;rowInteger++){
                mainValues.clear();//очищаем от предыдущих данных а иначе первая строка не будет вноситься в БД, т.к. структура будет смешана с предыдущей
                //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,2:");
                //читаем первое поле (отсчет полей идет с нуля) т.е. по сути читаем второе - cell с 0, а Row с 1
                Row row = sheet.getRow(rowInteger);
                //читаем столбцы
                //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,3:"+rowInteger);
                Cell cell= row.getCell(locationSetting);
                //Устанавливаем тип Cell String СРАЗУ т.к. если в файле Ексель будут цифры или ПУСТЫЕ то они не будут обрабатываться как String,
                // и код не будет выполняться или будет ошибка.
                Cell cellObject = row.getCell(locationObjectSetting);
                cellObject.setCellType(Cell.CELL_TYPE_STRING);
                Cell cellObjectTitle = row.getCell(locationObjectTitleSetting);
                cellObjectTitle.setCellType(Cell.CELL_TYPE_STRING);
                //
                //Для Cell getStringCellValue().toString() именно .toString() обязательно т.к. если его убрать то будут возникать ошибки, к примеру при отсутствии в ячейки значения и без приведения к стринг значение будет восприниматься как null  и вызывать ошибку
                if(cellObject.getStringCellValue().equals("Объект:")){
                    if (!locationObjectString.equals("")){
                        //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,31:"+unUniqueLocation);
                        uniqueLocation=Sort(unUniqueLocation);
                        mapObjectLocation.put(locationObjectString,uniqueLocation);
                        unUniqueLocation.clear();
                    }
                    locationObjectString=cellObjectTitle.getStringCellValue();
                }
                //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,32:");
                //Проводим проверку на пустые ячейки, т.к. если ячейка будет пустая то в дальнейшем будет возникать ошибка.
                if (cell!=null){
                    //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,33:");
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    locationString=cell.getStringCellValue();
                }
                else {
                    //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,34:");
                    locationString="";
                    continue;
                }
                if(locationString.equals("")| locationString.equals("-")){//Убираем разные не нужные вспомогательные слова которые попадаются по ходу чтения из файла
                    //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,35:");
                    locationString="";
                    continue;
                }
                else if(!locationObjectString.equals("")&&!locationString.equals(""))  {
                    //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,36:"+locationObjectString+" :"+locationString);
                    unUniqueLocation.add(locationString);
                    //unUniqueLocationObject.add(locationObjectString);
                }
                if (rowInteger == endRow){
                    uniqueLocation=Sort(unUniqueLocation);
                    mapObjectLocation.put(locationObjectString,uniqueLocation);
                }
                //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,5:"+mapObjectLocation);
            }
            for (String xObjectLocation:mapObjectLocation.keySet()){
                for (String yLocation:mapObjectLocation.get(xObjectLocation)) {
                    //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,6:"+xObjectLocation+": "+yLocation);
                    mainValues.put(DataBaseContract.Location.COLUMN_NAME_OBJECT, xObjectLocation);
                    mainValues.put(DataBaseContract.Location.COLUMN_NAME_LOCATION, yLocation);
                    //заносим в БД
                    //Log.d(tagLog, "GraphToDataBase.Location,Блок 7,7:"+mainValues);
                    if (maindb.insert(DataBaseContract.Location.TABLE_NAME, null, mainValues)==-1) {
                        Log.d(tagLog, "GraphToDataBase.Location,Блок 7,8:");
                        Toast.makeText(context, context.getString(R.string.toast_graph_to_database_location), Toast.LENGTH_LONG).show();
                        result=false;
                    break;
                    }
                }
            }
            inp.close();
            result=true;
        }
        catch(Exception e) {
            Log.d(tagLog, "GraphToDataBase.Location,Exception:"+e);
            result=false;
        }

        return result;
    }
    //Блок 7(к):Метод наполнения в базе данных таблицы Location

    //Блок 8(н):Метод наполнения в базе данных таблицы GraphDBTableClass
    /**Метод наполнения в базе данных таблицы GraphDBTableClass*/
    boolean GraphDBTable(){
        boolean result;
        Integer rowInteger;
        Integer cellInteger;
        String stringObject = "";
        Integer secondRow=0;
        String valueCell;
        //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,1:");
        try {
            InputStream inp = new FileInputStream(fileGraph);
            Workbook wb = WorkbookFactory.create(inp);
            //берем первый лист
            Sheet sheet = wb.getSheetAt(0);
            mainValues.clear();//очищаем от предыдущих данных, а иначе первая строка не будет вноситься в БД, т.к. структура будет смешана с предыдущей
            for (rowInteger=startRow;rowInteger<=endRow;rowInteger++){
                //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,2:"+rowInteger);
                for (cellInteger=ColumnTitle; cellInteger<=endCell;cellInteger++){
                    //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,3:"+"r:"+rowInteger+"c:" +cellInteger);
                    //читаем первое поле (отсчет полей идет с нуля) т.е. по сути читаем второе cell с 0, а Row с 1
                    Row row = sheet.getRow(rowInteger);
                    //читаем столбцы
                    //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,31:");
                    Cell cell= row.getCell(cellInteger);
                    valueCell=ReturnValueCell(cell);
                    /*Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,32:"+cellInteger);
                    cell.setCellType(Cell.CELL_TYPE_STRING);//Устанавливаем тип Cell String т.к.*/
                    // если в файле Ексель будут цифры то они не будут обрабатываться как String, и код не будет выполняться или будет ошибка.
                    //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,4:"+cellInteger);
                    if(valueCell.equals("Объект:"))
                    {
                        //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,41:");
                        stringObject ="Объект:";
                        //Log.d(tagLog, "1");
                        continue;
                    }
                    else if(cellInteger==1 & stringObject.equals("Объект:"))
                    {
                        //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,42:");
                        stringObject =valueCell;
                        //Log.d(tagLog, "2");
                        break;
                    }
                    else if ((valueCell.equals("")& (cellInteger.equals(ColumnTitle)|cellInteger.equals(ColumnSerial)|cellInteger.equals(ColumnVMI)|cellInteger.equals(locationSetting)))
                            | valueCell.equals("(системный блок, монитор, клавиатура, мышь)")
                            | valueCell.equals("КОНСЕРВАЦИЯ")
                            | valueCell.equals("(резервный)")){
                        //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,43:"+cellInteger);
                        if (secondRow==0&&(cellInteger.equals(ColumnVMI)|cellInteger.equals(locationSetting))){
                            //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,431:"+cellInteger);
                            addUpdateCell.add(null);
                            //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,432:"+cellInteger);
                        }
                        if (secondRow==0&&cellInteger.equals(ColumnTitle)){secondRow++;}//необходима для того, чтобы понимать, идет вторая строка дата
                        //cellInteger++;
                        continue;
                    }
                    else if (secondRow==1&&(cellInteger.equals(ColumnVMI)|cellInteger.equals(locationSetting))){
                        //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,432:"+cellInteger);
                        continue;
                    }
                    else if (secondRow==1&&(!cellInteger.equals(ColumnTitle)|!cellInteger.equals(ColumnSerial)|!cellInteger.equals(ColumnVMI)|!cellInteger.equals(locationSetting))){
                        //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,433:"+cellInteger+";"+locationSetting);
                        if (valueCell.equals("")) {
                            //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,4:"+cellInteger);
                            addUpdateCell.add(null);
                            //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,42:"+cellInteger);
                        } else {
                            addUpdateCell.add(valueCell);
                        }
                        continue;
                    }
                    else {
                        //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,44:"+cellInteger);
                        if (valueCell.equals("")) {
                            //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,45:"+cellInteger);
                            addUpdateCell.add(null);
                            //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,46:"+cellInteger);
                        } else {
                            addUpdateCell.add(valueCell);
                        }
                        //secondRow=0;
                        //rowInteger++;
                    }

                }
                //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,5:"+addUpdateCell+":"+secondRow+":"+addUpdateCell.size());
                if (secondRow==1&& addUpdateCell.size()==28) {
                    //заполнием базу
                    //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,6:");
                    int indexAddUpdateCell=0;
                    while (indexAddUpdateCell<addUpdateCell.size()){
                        //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,7:"+addUpdateCell.get(indexAddUpdateCell));
                        switch (indexAddUpdateCell) {//в зависимости от индекса передаем в нужный столбец данные
                            case 0:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_TITLE, addUpdateCell.get(indexAddUpdateCell));
                            case 1:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_SERIAL, addUpdateCell.get(indexAddUpdateCell));
                            case 2:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_JANUARY_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 3:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_FEBRUARY_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 4:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_MARCH_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 5:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_APRIL_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 6:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_MAY_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 7:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_JUNE_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 8:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_JULY_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 9:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_AUGUST_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 10:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_SEPTEMBER_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 11:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_OCTOBER_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 12:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_NOVEMBER_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 13:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DECEMBER_CATEGORY, addUpdateCell.get(indexAddUpdateCell));
                            case 14:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI, addUpdateCell.get(indexAddUpdateCell));
                            case 15:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION, addUpdateCell.get(indexAddUpdateCell));
                            case 16:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_JANUARY_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                            case 17:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_FEBRUARY_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                            case 18:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_MARCH_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                            case 19:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_APRIL_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                            case 20:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_MAY_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                            case 21:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_JUNE_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                            case 22:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_JULY_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                            case 23:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_AUGUST_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                            case 24:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_SEPTEMBER_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                            case 25:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_OCTOBER_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                            case 26:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_NOVEMBER_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                            case 27:
                                mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DECEMBER_TEN_DAY_PERIOD, addUpdateCell.get(indexAddUpdateCell));
                        }
                        indexAddUpdateCell++;
                    }
                    //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,8:");
                    mainValues.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_OBJECT, stringObject);
                    if (maindb.insert(DataBaseContract.GraphDBTableClass.TABLE_NAME, null, mainValues)==-1) {
                        //Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,9:");
                        Toast.makeText(context, context.getString(R.string.toast_graph_to_database_location), Toast.LENGTH_LONG).show();
                        result=false;
                        return result;
                    }
                    mainValues.clear();
                    secondRow=0;
                    addUpdateCell.clear();
                }
            }
            inp.close();
            result= true;
        } catch (Exception e) {
            e.printStackTrace();
            result= false;
            Log.d(tagLog, "GraphToDataBase.GraphDBTable,Блок 8,catch:"+e);
            return result;
        }
        return result;
    }
    //Блок 8(к):Метод наполнения в базе данных таблицы GraphDBTableClass

    //Блок 9(н):Метод проверки пустоты ячейки и возвращения значения
    /**Метод проверки пустоты ячейки и возвращения значения. Если в ячейки будет пустое значение то будет возникать ошибка java.lang.NullPointerException: Attempt to invoke interface method 'void org.apache.poi.ss.usermodel.Cell.setCellType(int)' on a null object reference
     * ДОРАБОТАТЬ С УЧЁТОМ РАЗНЫХ ТИПОВ ЯЧЕЕК чтобы не возникали ошибки*/
    String ReturnValueCell (Cell cell){
        //Log.d(tagLog, "GraphToDataBase.ReturnValueCell,Блок 9,:");
        String result;
        if (cell==null) {
            result="";
        } else {
            //Log.d(tagLog, "GraphToDataBase.ReturnValueCell,Блок 9,2,:"+ cell.getCellType()+":"+Cell.CELL_TYPE_NUMERIC);
            //Если формат ячейки Дата, то преобразуем дату в строку и передаем.
            if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC&&DateUtil.isCellDateFormatted(cell)){
                DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                //Log.d(tagLog, "GraphToDataBase.ReturnValueCell,Блок 9,3,:"+ formatter.format(cell.getDateCellValue()));
                result=formatter.format(cell.getDateCellValue());
                return result;
            }
            cell.setCellType(Cell.CELL_TYPE_STRING);//Устанавливаем тип Cell String т.к.
            // если в файле Ексель будут цифры то они не будут обрабатываться как String, и код не будет выполняться или будет ошибка.
            result=cell.getStringCellValue();
        }
        return result;
    }
    //Блок 9(к):Метод проверки пустоты ячейки и возвращения значения

    //Блок 10(н):Метод наполнения в базе данных таблицы graphDBTable
    /***/
    boolean CheckListDBTable(){
        boolean result;
        //ContentValues cvO = new ContentValues();
        mainValues.clear();
        try {
            //Log.d(tagLog, "GraphToDataBase.CheckListDBTable,Блок 10,1,:");
            //maindb = mainDBHelper.getWritableDatabase();
            String mounth = "1";
            String mounthCategory = new String();
            String mounthTenDayPeriod = new String();
            //maindb.execSQL(DataBaseContract.CheckListDBTableClass.DELETE_TABLE);//потом удалить
            //maindb.execSQL(DataBaseContract.CheckListDBTableClass.CREATE_TABLE);//потом удалить
            for (int intMounth =1; intMounth<=12;  intMounth++) {
                switch (intMounth) {//Присваиваем переменным атрибуты столбцов месяцев, номер месяца, категория в данном месяце, декада месяца.
                    case 1:
                        mounth=String.valueOf(intMounth);//Переводим из int в string
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_JANUARY_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_JANUARY_TEN_DAY_PERIOD;
                        break;
                    case 2:
                        mounth=String.valueOf(intMounth);
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_FEBRUARY_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_FEBRUARY_TEN_DAY_PERIOD;
                        break;
                    case 3:
                        mounth=String.valueOf(intMounth);
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_MARCH_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_MARCH_TEN_DAY_PERIOD;
                        break;
                    case 4:
                        mounth=String.valueOf(intMounth);
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_APRIL_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_APRIL_TEN_DAY_PERIOD;
                        break;
                    case 5:
                        mounth=String.valueOf(intMounth);
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_MAY_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_MAY_TEN_DAY_PERIOD;
                        break;
                    case 6:
                        mounth=String.valueOf(intMounth);
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_JUNE_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_JUNE_TEN_DAY_PERIOD;
                        break;
                    case 7:
                        mounth=String.valueOf(intMounth);
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_JULY_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_JULY_TEN_DAY_PERIOD;
                        break;
                    case 8:
                        mounth=String.valueOf(intMounth);
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_AUGUST_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_AUGUST_TEN_DAY_PERIOD;
                        break;
                    case 9:
                        mounth=String.valueOf(intMounth);
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_SEPTEMBER_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_SEPTEMBER_TEN_DAY_PERIOD;
                        break;
                    case 10:
                        mounth=String.valueOf(intMounth);
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_OCTOBER_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_OCTOBER_TEN_DAY_PERIOD;
                        break;
                    case 11:
                        mounth=String.valueOf(intMounth);
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_NOVEMBER_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_NOVEMBER_TEN_DAY_PERIOD;
                        break;
                    case 12:
                        mounth=String.valueOf(intMounth);
                        mounthCategory =DataBaseContract.GraphDBTableClass.COLUMN_NAME_DECEMBER_CATEGORY;
                        mounthTenDayPeriod =DataBaseContract.GraphDBTableClass.COLUMN_NAME_DECEMBER_TEN_DAY_PERIOD;
                        break;
                }

                maindb.execSQL("INSERT INTO " + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                        + " ("
                        + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT
                        + "," + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH
                        + "," + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE
                        + "," + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL
                        + "," + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY
                        + "," + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD
                        + ")"
                        + " SELECT "
                        + DataBaseContract.GraphDBTableClass.COLUMN_NAME_OBJECT
                        + ","+ mounth
                        + "," + DataBaseContract.GraphDBTableClass.COLUMN_NAME_TITLE
                        + "," + DataBaseContract.GraphDBTableClass.COLUMN_NAME_SERIAL
                        + "," + mounthCategory
                        + "," + mounthTenDayPeriod
                        + " FROM "
                        + DataBaseContract.GraphDBTableClass.TABLE_NAME
                        + " WHERE "
                        + mounthCategory
                        + " IS NOT NULL "
                        + " AND "
                        + mounthCategory
                        + " != ''"
                        + " ORDER BY "
                        +DataBaseContract.GraphDBTableClass.COLUMN_NAME_OBJECT
                        + "," + mounthTenDayPeriod
                        + "," + mounthCategory
                );
            }
            mainValues.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY, "O");//Добавляем О в столбец запись в журнале ТО, для отслеживания выполнено ТО или нет, т.к. если есть ТО то как минимум должна быть запись в журнале, и режиме О можно отслеживать выполнение ТО
            maindb.update(DataBaseContract.CheckListDBTableClass.TABLE_NAME, mainValues, null, null);
            result = true;

        }catch (Exception e){
            Log.d(tagLog, "GraphToDataBase.CheckListDBTable,Блок catch,1,:"+ e);
            result = false;
        }

        return result;
    }
    //Блок 10(к):

    //Блок 11(н):Метод последовательно запускающий методы наполнения таблиц
    /**Метод последовательно запускающий методы наполнения таблиц*/
    boolean MainMethod(){
        //Log.d(tagLog, "GraphToDataBase.MainMethod,Блок 11,1,:");
        boolean result;
        try {
            result=Equipment();
            if(result) {
                result=Object();
                //Log.d(tagLog, "GraphToDataBase.MainMethod,Блок 11,2,:"+result +" set:"+tmoFile.isExistFileSetting());
            }
            if(result) {
                result=TypesTO();
                //Log.d(tagLog, "GraphToDataBase.MainMethod,Блок 11,3,:"+result +" set:"+tmoFile.isExistFileSetting());
            }
            if(result) {
                result=Location();
                //Log.d(tagLog, "GraphToDataBase.MainMethod,Блок 11,4,:"+result +" set:"+tmoFile.isExistFileSetting());
            }
            if(result) {
                result=GraphDBTable();
                //Log.d(tagLog, "GraphToDataBase.MainMethod,Блок 11,5,:"+result +" set:"+tmoFile.isExistFileSetting());
            }
            if(result) {
                result=CheckListDBTable();
                //Log.d(tagLog, "GraphToDataBase.MainMethod,Блок 11,6,:"+result +" set:"+tmoFile.isExistFileSetting());
            }
            return result;
        } catch (Exception e) {
            //Log.d(tagLog, "GraphToDataBase.MainMethod,Блок 11,catch,:"+e);
            result=false;
            return result;
        }

    }

    //Блок 11(к):Метод последовательно запускающий методы наполнения таблиц
}
