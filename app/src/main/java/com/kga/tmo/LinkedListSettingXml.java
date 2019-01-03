package com.kga.tmo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
//Блок 0(н):

//Блок 0(к):
/**
 * Created by home on 24.03.2018.
 */
/**Класс представляет LinkedList в котором содержаться перечисление настроек на основе класса SettingXml.МОДИФИЦИРОВАТЬ ЧЕРЕЗ МАССИВ КОНСТАНТ, ЧТОБЫ ДАЛЕЕ БЫЛО удобнее добавлять новые или применть через перечисление массива.
 * @see SettingXml*/
public class LinkedListSettingXml extends LinkedList {
    //Блок 1(н):Объявление переменных
    /**Для фильтрации Log при отладки */
    private static final String tagLog = "MyLog";
    /***/
    static final String KEY_COUNT_OBJECT_SETTING = "Object";
    /***/
    static final String KEY_COUNT_TITLE_EQUIPMENT_SETTING = "CountTitleEquipment";
    /***/
    static final String KEY_COUNT_TYPES_TO_SETTING ="CountTypesTO";
    /***/
    static final String KEY_START_ROW_SETTING = "StartRow";
    /***/
    static final String KEY_END_ROW_SETTING = "EndRow";
    /***/
    static final String KEY_COLUMN_TITLE_SETTING = "ColumnTitle";
    /***/
    static final String KEY_COLUMN_SERIAL_SETTING  =   "ColumnSerial";
    /***/
    static final String KEY_COLUMN_JANUARY_SETTING  =   "ColumnJanuary";
    /***/
    static final String KEY_COLUMN_FEBRUARY_SETTING  =  "ColumnFebruary";
    /***/
    static final String KEY_COLUMN_MARCH_SETTING  =  "ColumnMarch";
    /***/
    static final String KEY_COLUMN_APRIL_SETTING  =  "ColumnApril";
    /***/
    static final String KEY_COLUMN_MAY_SETTING   =  "ColumnMay";
    /***/
    static final String KEY_COLUMN_JUNE_SETTING   =  "ColumnJune";
    /***/
    static final String KEY_COLUMN_JULE_SETTING   = "ColumnJuly";
    /***/
    static final String KEY_COLUMN_AUGUST_SETTING   =  "ColumnAugust";
    /***/
    static final String KEY_COLUMN_SEPTEMBER_SETTING  =  "ColumnSeptember";
    /***/
    static final String KEY_COLUMN_OCTOBER_SETTING   =  "ColumnOctober";
    /***/
    static final String KEY_COLUMN_NOVEMBER_SETTING  =   "ColumnNovember";
    /***/
    static final String KEY_COLUMN_DECEMBER_SETTING  =  "ColumnDecember";
    /***/
    static final String KEY_COLUMN_VMI_SETTING   = "ColumnVMI";
    /***/
    static final String KEY_COLUMN_LOCATION_SETTING  =  "ColumnLocation";
    /***/

    /***/

    /***/

    /**Контекс в который передаётся context
     * приложения из MainActivity в конструкторе
     * LinkedListSettingXml класса LinkedListSettingXml.
     * Передача контекста необходима для использования
     * текста из ресурса R.string, в котором храняться
     * текст для TextView настроек.
     * @see LinkedListSettingXml#LinkedListSettingXml(Context)*/
    Context contextllsxml;

    /**Создание переменной tmoFile необходимо для
     * работы с файлом Setting.xml методами:
     * @see TMOFile#isExistFileSetting()
     * @see TMOFile#readSetting()
     * @see TMOFile#deleteFileSetting()*/
    TMOFile tmoFile = new TMOFile();

    /**Карта - набор пар объектов ключ-значение:
     * имя тега в файле setting.xml, значение тега,
     * получаемые из метода TMO.readSetting*/
    Map<String, String> readHashMapSetting;

     /**Число параметров настроек.ВАЖНО! Если меняется
      * количесвто параметров то это число также должно
      * измениться иначе настройки не сформируются. Счёт идёт от 1.*/
     int countSetting =21;//переписать чтобы было автоматическое определение
    //Блок 1(к):Объявление переменных

    //Блок 2(н): Конструктор, методы add,greatLinkedListSettingXml,...Default,...FromFile
    /**Конструктор класса принимающий на вход context приложения из MainActivity. Передача context
     * необходима для передачи из ресурса приложения R.string текста для TextView при формировании
     * динамически Fragment Setting
     * @param context context приложения из MainActivity через Setting.onCreateView
     * @see Setting#onCreateView(LayoutInflater, ViewGroup, Bundle) */
    LinkedListSettingXml(Context context){
        this.contextllsxml = context;
    }
    /**Переопределенный метод для добавления в LinkedListSettingXml настроек класса SettingXml
     * @see SettingXml*/
    @Override
    public boolean add(Object o) {
        return super.add(o);
    }

    /**Метод создающий по умолчанию перечисление настроек класса SettingXml в LinkedListSettingXml
     * переопределённым методом add класса LinkedListSettingXml.
     * @see LinkedListSettingXml#add(Object)
     * @see SettingXml*/
    void createLinkedListSettingXmlDefault (){
        add(new SettingXml(KEY_COUNT_OBJECT_SETTING,  contextllsxml.getString(R.string.textViewCountObject),"4"));
        add(new SettingXml(KEY_COUNT_TITLE_EQUIPMENT_SETTING,  contextllsxml.getString(R.string.textViewCountTitleEquipment),"42"));
        add(new SettingXml(KEY_COUNT_TYPES_TO_SETTING,contextllsxml.getString(R.string.textViewCountTypesTO),"3"));
        add(new SettingXml(KEY_START_ROW_SETTING,contextllsxml.getString(R.string.textViewStartRow),"15"));
        add(new SettingXml(KEY_END_ROW_SETTING,contextllsxml.getString(R.string.textViewEndRow),"426"));
        add(new SettingXml(KEY_COLUMN_TITLE_SETTING,contextllsxml.getString(R.string.textViewColumnTitle),"1"));
        add(new SettingXml(KEY_COLUMN_SERIAL_SETTING,contextllsxml.getString(R.string.textViewColumnSerial),"2"));
        add(new SettingXml(KEY_COLUMN_JANUARY_SETTING,contextllsxml.getString(R.string.textViewColumnJanuary),"3"));
        add(new SettingXml(KEY_COLUMN_FEBRUARY_SETTING,contextllsxml.getString(R.string.textViewColumnFebruary),"4"));
        add(new SettingXml(KEY_COLUMN_MARCH_SETTING,contextllsxml.getString(R.string.textViewColumnMarch),"5"));
        add(new SettingXml(KEY_COLUMN_APRIL_SETTING,contextllsxml.getString(R.string.textViewColumnApril),"6"));
        add(new SettingXml(KEY_COLUMN_MAY_SETTING,contextllsxml.getString(R.string.textViewColumnMay),"7"));
        add(new SettingXml(KEY_COLUMN_JUNE_SETTING,contextllsxml.getString(R.string.textViewColumnJune),"8"));
        add(new SettingXml(KEY_COLUMN_JULE_SETTING,contextllsxml.getString(R.string.textViewColumnJuly),"9"));
        add(new SettingXml(KEY_COLUMN_AUGUST_SETTING,contextllsxml.getString(R.string.textViewColumnAugust),"10"));
        add(new SettingXml(KEY_COLUMN_SEPTEMBER_SETTING,contextllsxml.getString(R.string.textViewColumnSeptember),"11"));
        add(new SettingXml(KEY_COLUMN_OCTOBER_SETTING,contextllsxml.getString(R.string.textViewColumnOctober),"12"));
        add(new SettingXml(KEY_COLUMN_NOVEMBER_SETTING,contextllsxml.getString(R.string.textViewColumnNovember),"13"));
        add(new SettingXml(KEY_COLUMN_DECEMBER_SETTING,contextllsxml.getString(R.string.textViewColumnDecember),"14"));
        add(new SettingXml(KEY_COLUMN_VMI_SETTING,contextllsxml.getString(R.string.textViewColumnVMI),"15"));
        add(new SettingXml(KEY_COLUMN_LOCATION_SETTING,contextllsxml.getString(R.string.textViewColumnLocation),"16"));//20
    }

    /**Метод создающий перечисление настроек класса SettingXml в LinkedListSettingXml
     * переопределённым методом add класса LinkedListSettingXml. Значения настроек (переменная value)
     * берётся из файла Setting.xml методом TMOFile.readSetting и передаётся в переменную
     * readHashMapSetting в методе greatLinkedListSettingXml класса LinkedListSettingXml.
     * @see SettingXml
     * @see LinkedListSettingXml#add(Object)
     * @see LinkedListSettingXml#createLinkedListSettingXml()
     * @see LinkedListSettingXml#readHashMapSetting */
    void createLinkedListSettingXmlFromFile (){
        add(new SettingXml(KEY_COUNT_OBJECT_SETTING,  contextllsxml.getString(R.string.textViewCountObject),readHashMapSetting.get(KEY_COUNT_OBJECT_SETTING)));
        add(new SettingXml(KEY_COUNT_TITLE_EQUIPMENT_SETTING,  contextllsxml.getString(R.string.textViewCountTitleEquipment),readHashMapSetting.get(KEY_COUNT_TITLE_EQUIPMENT_SETTING)));
        add(new SettingXml(KEY_COUNT_TYPES_TO_SETTING,contextllsxml.getString(R.string.textViewCountTypesTO),readHashMapSetting.get(KEY_COUNT_TYPES_TO_SETTING)));
        add(new SettingXml(KEY_START_ROW_SETTING,contextllsxml.getString(R.string.textViewStartRow),readHashMapSetting.get(KEY_START_ROW_SETTING)));
        add(new SettingXml(KEY_END_ROW_SETTING,contextllsxml.getString(R.string.textViewEndRow),readHashMapSetting.get(KEY_END_ROW_SETTING)));
        add(new SettingXml(KEY_COLUMN_TITLE_SETTING,contextllsxml.getString(R.string.textViewColumnTitle),readHashMapSetting.get(KEY_COLUMN_TITLE_SETTING)));
        add(new SettingXml(KEY_COLUMN_SERIAL_SETTING,contextllsxml.getString(R.string.textViewColumnSerial),readHashMapSetting.get(KEY_COLUMN_SERIAL_SETTING)));
        add(new SettingXml(KEY_COLUMN_JANUARY_SETTING,contextllsxml.getString(R.string.textViewColumnJanuary),readHashMapSetting.get(KEY_COLUMN_JANUARY_SETTING)));
        add(new SettingXml(KEY_COLUMN_FEBRUARY_SETTING,contextllsxml.getString(R.string.textViewColumnFebruary),readHashMapSetting.get(KEY_COLUMN_FEBRUARY_SETTING)));
        add(new SettingXml(KEY_COLUMN_MARCH_SETTING,contextllsxml.getString(R.string.textViewColumnMarch),readHashMapSetting.get(KEY_COLUMN_MARCH_SETTING)));
        add(new SettingXml(KEY_COLUMN_APRIL_SETTING,contextllsxml.getString(R.string.textViewColumnApril),readHashMapSetting.get(KEY_COLUMN_APRIL_SETTING)));
        add(new SettingXml(KEY_COLUMN_MAY_SETTING,contextllsxml.getString(R.string.textViewColumnMay),readHashMapSetting.get(KEY_COLUMN_MAY_SETTING)));
        add(new SettingXml(KEY_COLUMN_JUNE_SETTING,contextllsxml.getString(R.string.textViewColumnJune),readHashMapSetting.get(KEY_COLUMN_JUNE_SETTING)));
        add(new SettingXml(KEY_COLUMN_JULE_SETTING,contextllsxml.getString(R.string.textViewColumnJuly),readHashMapSetting.get(KEY_COLUMN_JULE_SETTING)));
        add(new SettingXml(KEY_COLUMN_AUGUST_SETTING,contextllsxml.getString(R.string.textViewColumnAugust),readHashMapSetting.get(KEY_COLUMN_AUGUST_SETTING)));
        add(new SettingXml(KEY_COLUMN_SEPTEMBER_SETTING,contextllsxml.getString(R.string.textViewColumnSeptember),readHashMapSetting.get(KEY_COLUMN_SEPTEMBER_SETTING)));
        add(new SettingXml(KEY_COLUMN_OCTOBER_SETTING,contextllsxml.getString(R.string.textViewColumnOctober),readHashMapSetting.get(KEY_COLUMN_OCTOBER_SETTING)));
        add(new SettingXml(KEY_COLUMN_NOVEMBER_SETTING,contextllsxml.getString(R.string.textViewColumnNovember),readHashMapSetting.get(KEY_COLUMN_NOVEMBER_SETTING)));
        add(new SettingXml(KEY_COLUMN_DECEMBER_SETTING,contextllsxml.getString(R.string.textViewColumnDecember),readHashMapSetting.get(KEY_COLUMN_DECEMBER_SETTING)));
        add(new SettingXml(KEY_COLUMN_VMI_SETTING,contextllsxml.getString(R.string.textViewColumnVMI),readHashMapSetting.get(KEY_COLUMN_VMI_SETTING)));
        add(new SettingXml(KEY_COLUMN_LOCATION_SETTING,contextllsxml.getString(R.string.textViewColumnLocation),readHashMapSetting.get(KEY_COLUMN_LOCATION_SETTING)));//20
        //Log.d(tagLog, "greatLinkedListSettingXmlFromFile:"+readHashMapSetting.toString());
    }

    /**Метод наполнения LinkedListSettingXml объектами класса SettingXml.
     * При отсутствии файла Setting.xml создается LinkedListSettingXml с применением метода
     * LinkedListSettingXml.greatLinkedListSettingXmlDefault
     * При наличии файла Setting.xml, проводиться сравнение полученных данных из файла Setting.xml.
     * Методом TMOFile.readSetting переменной readHashMapSetting передается Map: ключ - ключ настройки,
     * значение значение настройки. Далее проводиться сравнение значения countSetting с размером
     * readHashMapSetting. Если они равны то LinkedListSettingXml формируется методом
     * greatLinkedListSettingXmlFromFile с настройками из файла Setting.xml. Если он не равны,
     * то выводиться сообщение о изменении структуры файла Setting.xml, и файл Setting.xml удаляется
     * методом TMOFile.deleteFileSetting.<br/>
     * Файл Setting.xml создается при вызове MainActivity.onOptionsItemSelected (в case MENU_SETTING:)
     * с применением метода LinkedListSettingXml.greatLinkedListSettingXmlDefault, т.е. делается
     * изначальным согласно структуре метода LinkedListSettingXml.greatLinkedListSettingXmlDefault.
     * @see TMOFile#readSetting()
     * @see TMOFile#deleteFileSetting()
     * @see LinkedListSettingXml#readHashMapSetting
     * @see LinkedListSettingXml#createLinkedListSettingXmlFromFile
     * @see LinkedListSettingXml#createLinkedListSettingXmlDefault()
     * @see MainActivity#onOptionsItemSelected(MenuItem)*/
    void createLinkedListSettingXml (){
        //Log.d(tagLog, "LinkedListSettingXml.createLinkedListSettingXml, Блок 2,1:");
        try {
            if (!tmoFile.isExistFileSetting()){
                //Log.d(tagLog, "greatLinkedListSettingXml !isExistFileSetting:");
                this.createLinkedListSettingXmlDefault();

            }else if (tmoFile.isExistFileSetting()){
                readHashMapSetting= tmoFile.readSetting();
                //Log.d(tagLog, "greatLinkedListSettingXml isExist:" +readHashMapSetting.size());
                if (countSetting ==readHashMapSetting.size()){
                    //Log.d(tagLog, "greatLinkedListSettingXml s ==readHashMapSetting.size():" +(countSetting ==readHashMapSetting.size()));
                    this.createLinkedListSettingXmlFromFile();
                }else if (countSetting !=readHashMapSetting.size()) {
                    //Log.d(tagLog, "greatLinkedListSettingXml s !=readHashMapSetting.size():" +(countSetting ==readHashMapSetting.size())+", count:"+this.size()+", rhms:"+readHashMapSetting.size());
                    Toast.makeText(contextllsxml, R.string.msg_bad_setting, Toast.LENGTH_SHORT).show();
                    tmoFile.deleteFileSetting();
                    this.createLinkedListSettingXmlDefault();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*Iterator<SettingXml> iterator = listSettingXml.iterator();
        Log.d(tagLog, "greatLinkedListSettingXml2");
        while (iterator.hasNext()){
            SettingXml settingXml=iterator.next();
            Log.d(tagLog, "greatLinkedListSettingXml"+settingXml.getKey()+"-"+settingXml.getTextViewResourse()+"-"+settingXml.getValue());
        }*/
    }
    //Блок 2(к): Конструктор, методы add,greatLinkedListSettingXml,...Default,...FromFile


    //Блок 3(н):
    /***/
    String getValueByKey (String key){
        String Value = new String();
        //Log.d(tagLog, "LinkedListSettingXml.getValueByKey, Блок 3,1:"+super.toString());
        Iterator<SettingXml> iterator = super.iterator();
        //Log.d(tagLog, "LinkedListSettingXml.getValueByKey, Блок 3,2:"+iterator.toString()+" :"+ iterator.hasNext());
        while (iterator.hasNext()){

            SettingXml settingXml=iterator.next();
            //Log.d(tagLog, "LinkedListSettingXml.getValueByKey, Блок 3,2:"+settingXml);
            if (settingXml.getKey().equals(key)){

                Value= settingXml.getValue();
                //Log.d(tagLog, "LinkedListSettingXml.getValueByKey, Блок 3,3:"+Value);
            }
        }
        return Value;
    }
    //Блок 3(к):

}
