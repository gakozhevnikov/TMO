package com.kga.tmo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by home on 01.06.2018.
 */

public class ListCursorDB {
    /**Для фильтрации Log при отладки */private static final String tagLog = "MyLog";
    Context context;
    DBHelper mainDBHelper ;//Обязательно объявить
    SQLiteDatabase maindb;
    Cursor cursorDB;
    Cursor cursorDBEditText;
    /**Постонаая для запросы у курсора наименования всех столбцов*/
    public static final String ALL_COLUMNS = "allColumns";
    /**Уловие для загрузки только колонок для чек-листа*/
    public static final String COLUMNS_CHECK = "allColumns";
    /**Уловие для загрузки только rowid*/
    public static final String COLUMNS_ROWID = "rowid";
    /**Константа внеплановое*/
    public static final String UNPLANNED = "Внепланово";
    /***/
    ContentValues cv = new ContentValues();
    /***/
    List<String> row_sColumnsCheck = new ArrayList<>();
    /***/
    List<String> row_sRowID = new ArrayList<>();

    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();



    ListCursorDB(Context context){
        this.context=context;
        mainDBHelper =new DBHelper(context);//вот здесь обязательно нужен context (х.з. зачем), методом перебора с начала Context context = getActivity(); а затем добавлем в скобки context
        maindb=mainDBHelper.getReadableDatabase();
        maindb= mainDBHelper.getWritableDatabase();

        //Log.d(tagLog, "ListCursorDB.ListCursorDB,1:");

    }
    /**Метод формирующий запрос в БД с разными символами O в журнале ТО,O,V,+,-,*/
    String getModeSQLQuery (String mode){
        String modeSQLQuery = DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_LETTER +" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_PLANNED_DATE_OF_WORK +" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY +" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_EQUIPMENT_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_ACT+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_ACT_TIGHTNESS_OF_VALVES+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_STAMP_INSTALL+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_STAMP_JOURNAL_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_ACT_SYBERVISOR_ADMIN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_SCAN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_NOTE+" ='"+mode+"' OR "+
                //MDM mdm - монтаж демонтаж
                //public static final String COLUMN_NAME_MDM_="mdm_"; //
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_TITLE+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_ACT+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_ACT_TIGHTNESS_OF_VALVES+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_CMCSI+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_9_EQUIPMENT_SMAO_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_PPD_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_LIST_REGISTRATION_EQUIPMENT_PPD_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_ARM_PSP+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_CORRECTION_THERMOMETERS+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_HOURS_WORK_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_ACT_ENTER+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_ACT_OUTPUT+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_JOURNAL_EQUIPMENT_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_SCAN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_NOTE+" ='"+mode+"' OR "+
                //verification of a measuring instrument (VMI) - Поверка (РМГ 29)
                //public static final String COLUMN_NAME_VMI_="vmi_"; //
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_CERTIFICATE+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_JOURNAL_EQUIPMENT_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_STAMP_INSTALL+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_ARM_PSP+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_ACT_SYBERVISOR_ADMIN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_STAMP_JOURNAL_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_ACT_TIGHTNESS_OF_VALVES+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_LISTING_MF+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_SCAN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_NOTE+" ='"+mode+"' OR "+
                //crash - авария, отказ, инциндент 18 пунктов @string/vMINote
                //public static final String COLUMN_NAME_CRASH_="crash_"; //
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_DISMANTLING+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_MOUNTING+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_CRASH_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_SMAO_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_9_EQUIPMENT_SMAO_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_SYBERVISOR_ADMIN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ARM_PSP+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_CORRECTION_THERMOMETERS+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_LISTING_MF+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_HOURS_WORK_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_ACT_ENTER+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_ACT_OUTPUT+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_JOURNAL_EQUIPMENT_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_SCAN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ANALYSIS+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_NOTE+" ='"+mode+"' OR "+
                //storage - хранение
                //public static final String COLUMN_NAME_STORAGE_="storage_"; //
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_ACT_ENTER+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_ACT_OUTPUT+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_JOURNAL_EQUIPMENT_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_SCAN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_NOTE+" ='"+mode+"' OR "+
                //repair - ремонт
                //public static final String COLUMN_NAME_REPAIR_="repair_"; //
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_ACT+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_JOURNAL_EQUIPMENT_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_JOURNAL_REPAIR_ENTRY+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_SCAN+" ='"+mode+"' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_NOTE+" ='"+mode+"' OR "+
                //note - Общее примечание
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_NOTE+"='"+mode+"'";
        
        return modeSQLQuery;
    }
    /***/
    List<String> getListObject (String mode){
        List <String> list = new ArrayList<String>();
        //Log.d(tagLog, "ListCursorDB.getListObject,1:"+mainDBHelper);
        try {
            if (mode.equals(ModeView.LIST_SPINNER_MODE.get(0))){
                //Log.d(tagLog, "ListCursorDB.getListObject,2:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT}, null, null, null, null, null, null);
                //Log.d(tagLog, "ListCursorDB.getListObject,22:"+cursorDB);
            }else if (mode.equals(ModeView.LIST_SPINNER_MODE.get(1))){
                //Log.d(tagLog, "ListCursorDB.getListObject,3:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT},
                        DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY+ "=?", new String[]{"O"}, null, null, null, null);
                //Log.d(tagLog, "ListCursorDB.getListObject,32:"+cursorDB);
            }else if(mode.equals(ModeView.LIST_SPINNER_MODE.get(2))|mode.equals(ModeView.LIST_SPINNER_MODE.get(3))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(4))|mode.equals(ModeView.LIST_SPINNER_MODE.get(5))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(6))){
                //Log.d(tagLog, "ListCursorDB.getListObject,4:");
                cursorDB=maindb.rawQuery("SELECT DISTINCT "
                        + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT
                        + " FROM "
                        + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                        + " WHERE "
                        +getModeSQLQuery(mode),null);
                //Log.d(tagLog, "ListCursorDB.getListObject,5:"+cursorDB.isAfterLast());
            }
            if (!cursorDB.isAfterLast()) {
                list.add(context.getString(R.string.spinner_object_first));
                while (cursorDB.moveToNext()){
                    //Log.d(tagLog, "ListCursorDB.getListObject,6:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT)));
                    list.add(cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT)));
                }
            } else {
                list.add(context.getString(R.string.data_base_no_information));
            }
            //Log.d(tagLog, "ListCursorDB.getListObject,7:"+cursorDB.getPosition());
            cursorDB.close();
            //Log.d(tagLog, "ListCursorDB.getListObject,71:"+cursorDB.getPosition());
            return list;
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getListObject.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getListObject.catch,1", Toast.LENGTH_LONG).show();
            list.clear();
            return list;
        }
    }

    /***/
    List <String> getListMonth (String mode, String selectItem){
        List <String> list = new ArrayList<String>();
        cursorDB.close();
        //Log.d(tagLog, "ListCursorDB.getListMonth,1:"+cursorDB.isClosed());/
        try {
            if (mode.equals(ModeView.LIST_SPINNER_MODE.get(0))){
                //Log.d(tagLog, "ListCursorDB.getListMonth,2:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH}, null, null, null, null, null, null);
                //Log.d(tagLog, "ListCursorDB.getListMonth,22:"+cursorDB.isClosed());
            }else if (mode.equals(ModeView.LIST_SPINNER_MODE.get(1))){
                //Log.d(tagLog, "ListCursorDB.getListMonth,3:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH},
                        DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY+ "=?", new String[]{selectItem,"O"}, null, null, null, null);
                //Log.d(tagLog, "ListCursorDB.getListMonth,32:"+cursorDB);
            }else if(mode.equals(ModeView.LIST_SPINNER_MODE.get(2))|mode.equals(ModeView.LIST_SPINNER_MODE.get(3))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(4))|mode.equals(ModeView.LIST_SPINNER_MODE.get(5))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(6))){
                //Log.d(tagLog, "ListCursorDB.getListMonth,4:");
                cursorDB=maindb.rawQuery("SELECT DISTINCT "
                        + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH
                        + " FROM "
                        + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                        + " WHERE "
                        +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "= '"+selectItem+"' AND ("
                        +getModeSQLQuery(mode)+")",null);
                //Log.d(tagLog, "ListCursorDB.getListMonth,5:"+cursorDB.isAfterLast());
            }
            //Log.d(tagLog, "ListCursorDB.getListMonth,55:"+cursorDB.isAfterLast());
            if (!cursorDB.isAfterLast()) {
                list.add(context.getString(R.string.spinner_month_first));
                while (cursorDB.moveToNext()){
                    //Log.d(tagLog, "ListCursorDB.getListMonth,6:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH)));
                    list.add(cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH)));//СМОТРИ ВНИМАТЕЛЬНО ЧТО СЮДА ПИШЕШь ЕСЛИ не ТО ПОЛЕ ВНИСЁШЬ то БУДЕТ ОШИБКА Couldn't read row 1, col -1 from CursorWindow
                }
            } else {
                //Log.d(tagLog, "ListCursorDB.getListMonth,61:");
                list.add(context.getString(R.string.data_base_no_information));
            }
            //Log.d(tagLog, "ListCursorDB.getListMonth,7:"+list);
            cursorDB.close();
            return list;
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getListMonth.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getListMonth", Toast.LENGTH_LONG).show();
            list.clear();
            return list;
        }

    }

    /***/
    List <String> getListPeriod (String mode, String object, String month){
        List <String> list = new ArrayList<String>();
        //Log.d(tagLog, "ListCursorDB.getListPeriod,1:"+cursorDB.isClosed());
        try {
            if (mode.equals(ModeView.LIST_SPINNER_MODE.get(0))){
                //Log.d(tagLog, "ListCursorDB.getListPeriod,2:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD},
                        DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT + "=? AND "
                                + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH + "=?",
                        new String[]{object, month}, null, null, null, null);
                //Log.d(tagLog, "ListCursorDB.getListPeriod,22:"+cursorDB.isClosed());
            }else if (mode.equals(ModeView.LIST_SPINNER_MODE.get(1))){
                //Log.d(tagLog, "ListCursorDB.getListPeriod,3:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD},
                        DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY+ "=?", new String[]{object,month,"O"}, null, null, null, null);
                //Log.d(tagLog, "ListCursorDB.getListPeriod,32:"+cursorDB);
            }else if(mode.equals(ModeView.LIST_SPINNER_MODE.get(2))|mode.equals(ModeView.LIST_SPINNER_MODE.get(3))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(4))|mode.equals(ModeView.LIST_SPINNER_MODE.get(5))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(6))){
                //Log.d(tagLog, "ListCursorDB.getListPeriod,4:");
                cursorDB=maindb.rawQuery("SELECT DISTINCT "
                        + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD
                        + " FROM "
                        + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                        + " WHERE "
                        +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "= '"+object+"' AND "
                        +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "= '"+month+"' AND ("
                        +getModeSQLQuery(mode)+")",null);
                //Log.d(tagLog, "ListCursorDB.getListPeriod,5:"+cursorDB.isAfterLast());
            }
            //Log.d(tagLog, "ListCursorDB.getListPeriod,55:"+cursorDB.isAfterLast());
            if (cursorDB.isAfterLast()) {//!!!НА ДОСУГЕ ПОНЯТЬ Здесь в отличии от предыдущего метода пришлось реализовать такой алгоритм т.к. иногда курсор содержал пустое значение которое определялось только cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD))==null
                //Log.d(tagLog, "ListCursorDB.getListPeriod,6:");
                list.add(context.getString(R.string.data_base_no_information));
            } else {
                //Log.d(tagLog, "ListCursorDB.getListPeriod,7:");
                cursorDB.moveToFirst();
                if (cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD))==null){
                    //Log.d(tagLog, "ListCursorDB.getListPeriod,8:");
                    list.add(context.getString(R.string.data_base_no_information));
                }else {
                    //Log.d(tagLog, "ListCursorDB.getListPeriod,9:");
                    cursorDB.moveToFirst();
                    list.add(context.getString(R.string.spinner_period_first));
                    do{
                        //Log.d(tagLog, "ListCursorDB.getListPeriod,10:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD)));
                        list.add(cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD)));//СМОТРИ ВНИМАТЕЛЬНО ЧТО СЮДА ПИШЕШь ЕСЛИ не ТО ПОЛЕ ВНИСЁШЬ то БУДЕТ ОШИБКА Couldn't read row 1, col -1 from CursorWindow
                    }while (cursorDB.moveToNext());
                }

            }
            //Log.d(tagLog, "ListCursorDB.getListPeriod,7:"+list);
            cursorDB.close();
            return list;
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getListPeriod.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getListPeriod", Toast.LENGTH_LONG).show();
            list.clear();
            return list;
        }

    }

    /**период отсутствуют*/
    List <String> getListCategory (String mode, String object, String month){
        List <String> list = new ArrayList<String>();
        Log.d(tagLog, "ListCursorDB.getListCategory,1:"+cursorDB.isClosed());
        try {
            if (mode.equals(ModeView.LIST_SPINNER_MODE.get(0))){
                //Log.d(tagLog, "ListCursorDB.getListCategory,2:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY},
                        DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT + "=? AND "
                                + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH + "=?",
                        new String[]{object, month}, null, null, null, null);
                //Log.d(tagLog, "ListCursorDB.getListCategory,22:"+cursorDB.isClosed());
            }else if (mode.equals(ModeView.LIST_SPINNER_MODE.get(1))){//поправить
                //Log.d(tagLog, "ListCursorDB.getListCategory,3:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY},
                        DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY+ "=?", new String[]{object,month,"O"}, null, null, null, null);
                //Log.d(tagLog, "ListCursorDB.getListCategory,32:"+cursorDB);
            }else if(mode.equals(ModeView.LIST_SPINNER_MODE.get(2))|mode.equals(ModeView.LIST_SPINNER_MODE.get(3))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(4))|mode.equals(ModeView.LIST_SPINNER_MODE.get(5))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(6))){
                //Log.d(tagLog, "ListCursorDB.getListCategory,4:");
                cursorDB=maindb.rawQuery("SELECT DISTINCT "
                        + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY
                        + " FROM "
                        + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                        + " WHERE "
                        +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "= '"+object+"' AND "
                        +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "= '"+month+"' AND ("
                        +getModeSQLQuery(mode)+")",null);
                //Log.d(tagLog, "ListCursorDB.getListCategory,5:"+cursorDB.isAfterLast());
            }
            Log.d(tagLog, "ListCursorDB.getListCategory,55:"+cursorDB.isAfterLast());
            if (cursorDB.isAfterLast()) {
                //Log.d(tagLog, "ListCursorDB.getListCategory,6:");
                list.add(context.getString(R.string.data_base_no_information));
            } else {
                //Log.d(tagLog, "ListCursorDB.getListCategory,7:");
                cursorDB.moveToFirst();
                if (cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY))==null){
                    Log.d(tagLog, "ListCursorDB.getListCategory,8:");
                    list.add(context.getString(R.string.data_base_no_information));
                }else {
                    //Log.d(tagLog, "ListCursorDB.getListCategory,9:");
                    cursorDB.moveToFirst();
                    list.add(context.getString(R.string.spinner_category_first));
                    do{
                        Log.d(tagLog, "ListCursorDB.getListCategory,10:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY)));
                        list.add(cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY)));//СМОТРИ ВНИМАТЕЛЬНО ЧТО СЮДА ПИШЕШь ЕСЛИ не ТО ПОЛЕ ВНИСЁШЬ то БУДЕТ ОШИБКА Couldn't read row 1, col -1 from CursorWindow
                    }while (cursorDB.moveToNext());
                }

            }
            Log.d(tagLog, "ListCursorDB.getListCategory,7:"+list);
            cursorDB.close();
            return list;
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getListPeriod.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getListPeriod", Toast.LENGTH_LONG).show();
            list.clear();
            return list;
        }

    }

    /***/
    List <String> getListCategory (String mode, String object, String month, String period){
        List <String> list = new ArrayList<String>();
        //Log.d(tagLog, "ListCursorDB.getListPeriod2,1:"+cursorDB.isClosed());
        try {
            if (mode.equals(ModeView.LIST_SPINNER_MODE.get(0))){
                //Log.d(tagLog, "ListCursorDB.getListPeriod2,2:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY},
                        DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT + "=? AND "
                        +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH + "=? AND "
                                + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD + "=?",
                        new String[]{object, month,period}, null, null, null, null);
                //Log.d(tagLog, "ListCursorDB.getListPeriod2,22:"+cursorDB.isClosed());
            }else if (mode.equals(ModeView.LIST_SPINNER_MODE.get(1))){//поправить
                //Log.d(tagLog, "ListCursorDB.getListPeriod2,3:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY},
                        DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD+ "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY+ "=?",
                        new String[]{object,month, period,"O"}, null, null, null, null);
                //Log.d(tagLog, "ListCursorDB.getListPeriod2,32:"+cursorDB);
            }else if(mode.equals(ModeView.LIST_SPINNER_MODE.get(2))|mode.equals(ModeView.LIST_SPINNER_MODE.get(3))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(4))|mode.equals(ModeView.LIST_SPINNER_MODE.get(5))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(6))){
                //Log.d(tagLog, "ListCursorDB.getListPeriod2,4:");
                cursorDB=maindb.rawQuery("SELECT DISTINCT "
                        + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY
                        + " FROM "
                        + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                        + " WHERE "
                        +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "= '"+object+"' AND "
                        +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "= '"+month+"' AND "
                        +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD+ "= '"+period+"' AND ("
                        +getModeSQLQuery(mode)+")",null);
                //Log.d(tagLog, "ListCursorDB.getListPeriod2,5:"+cursorDB.isAfterLast());
            }
            //Log.d(tagLog, "ListCursorDB.getListPeriod2,55:"+cursorDB.isAfterLast());
            if (cursorDB.isAfterLast()) {
                //Log.d(tagLog, "ListCursorDB.getListPeriod2,6:");
                list.add(context.getString(R.string.data_base_no_information));
            } else {
                //Log.d(tagLog, "ListCursorDB.getListPeriod2,7:");
                cursorDB.moveToFirst();
                if (cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY))==null){
                    //Log.d(tagLog, "ListCursorDB.getListPeriod2,8:");
                    list.add(context.getString(R.string.data_base_no_information));
                }else {
                    //Log.d(tagLog, "ListCursorDB.getListPeriod2,9:");
                    cursorDB.moveToFirst();
                    list.add(context.getString(R.string.spinner_category_first));
                    do{
                        //Log.d(tagLog, "ListCursorDB.getListPeriod2,10:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY)));
                        list.add(cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY)));//СМОТРИ ВНИМАТЕЛЬНО ЧТО СЮДА ПИШЕШь ЕСЛИ не ТО ПОЛЕ ВНИСЁШЬ то БУДЕТ ОШИБКА Couldn't read row 1, col -1 from CursorWindow
                    }while (cursorDB.moveToNext());
                }
            }
            //Log.d(tagLog, "ListCursorDB.getListPeriod2,7:"+list);
            cursorDB.close();
            return list;
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getListPeriod2.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getListPeriod2", Toast.LENGTH_LONG).show();
            list.clear();
            return list;
        }

    }

    /***/
    List <String> getListTitle (String mode, String object, String month, String period, String category){
        List <String> list = new ArrayList<String>();
        //Log.d(tagLog, "ListCursorDB.getListTitle,1:"+cursorDB.isClosed());
        try {
            if (mode.equals(ModeView.LIST_SPINNER_MODE.get(0))){//Обычный режим
                //Log.d(tagLog, "ListCursorDB.getListTitle,2:");
                if (period.equals(context.getString(R.string.data_base_no_information))){
                    //Log.d(tagLog, "ListCursorDB.getListTitle,22:");
                    cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE},
                            DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY + "=?",
                            new String[]{object, month,category}, null, null, null, null);
                }else {
                    //Log.d(tagLog, "ListCursorDB.getListTitle,23:");
                    cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE},
                        DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT + "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH + "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD + "=? AND "
                                + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY + "=?",
                        new String[]{object, month,period,category}, null, null, null, null);
                }

                //Log.d(tagLog, "ListCursorDB.getListTitle,24:"+cursorDB.isClosed());
            }else if (mode.equals(ModeView.LIST_SPINNER_MODE.get(1))){//Режим "O журнала ТО"
                //Log.d(tagLog, "ListCursorDB.getListTitle,3:");
                if (period.equals(context.getString(R.string.data_base_no_information))){
                    //Log.d(tagLog, "ListCursorDB.getListTitle,31:");
                    cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE},
                            DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY+ "=?",
                            new String[]{object,month,category,"O"}, null, null, null, null);
                }else{
                    //Log.d(tagLog, "ListCursorDB.getListTitle,32:");
                    cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE},
                            DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD + "=? AND "
                                    + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY+ "=?",
                            new String[]{object,month,period,category,"O"}, null, null, null, null);
                }
                //Log.d(tagLog, "ListCursorDB.getListTitle,32:"+cursorDB);
            }else if(mode.equals(ModeView.LIST_SPINNER_MODE.get(2))|mode.equals(ModeView.LIST_SPINNER_MODE.get(3))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(4))|mode.equals(ModeView.LIST_SPINNER_MODE.get(5))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(6))){
                //Log.d(tagLog, "ListCursorDB.getListTitle,4:");
                if (period.equals(context.getString(R.string.data_base_no_information))){
                    //Log.d(tagLog, "ListCursorDB.getListTitle,5:"+cursorDB.isAfterLast());
                    cursorDB=maindb.rawQuery("SELECT DISTINCT "
                            + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE
                            + " FROM "
                            + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                            + " WHERE "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "= '"+object+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "= '"+month+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY+ "= '"+category+"' AND ("
                            +getModeSQLQuery(mode)+")",null);
                }
                else{
                    //Log.d(tagLog, "ListCursorDB.getListTitle,51:"+cursorDB.isAfterLast());
                    cursorDB=maindb.rawQuery("SELECT DISTINCT "
                            + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE
                            + " FROM "
                            + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                            + " WHERE "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "= '"+object+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "= '"+month+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD+ "= '"+period+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY+ "= '"+category+"' AND ("
                            +getModeSQLQuery(mode)+")",null);
                }
            }
            //Log.d(tagLog, "ListCursorDB.getListTitle,55:"+cursorDB.isAfterLast());
            if (cursorDB.isAfterLast()) {
                //Log.d(tagLog, "ListCursorDB.getListTitle,6:");
                list.add(context.getString(R.string.data_base_no_information));
            } else {
                //Log.d(tagLog, "ListCursorDB.getListTitle,7:");
                cursorDB.moveToFirst();
                if (cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE))==null){
                    //Log.d(tagLog, "ListCursorDB.getListTitle,8:");
                    list.add(context.getString(R.string.data_base_no_information));
                }else {
                    //Log.d(tagLog, "ListCursorDB.getListTitle,9:");
                    cursorDB.moveToFirst();
                    list.add(context.getString(R.string.spinner_title_first));
                    do{
                        //Log.d(tagLog, "ListCursorDB.getListTitle,10:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD)));
                        list.add(cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE)));//СМОТРИ ВНИМАТЕЛЬНО ЧТО СЮДА ПИШЕШь ЕСЛИ не ТО ПОЛЕ ВНИСЁШЬ то БУДЕТ ОШИБКА Couldn't read row 1, col -1 from CursorWindow
                    }while (cursorDB.moveToNext());
                }

            }
            //Log.d(tagLog, "ListCursorDB.getListTitle,7:"+list);
            cursorDB.close();
            return list;
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getListTitle.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getListTitle", Toast.LENGTH_LONG).show();
            list.clear();
            return list;
        }

    }

    /***/
    List <String> getListTitle (String view, String object){
        List <String> list = new ArrayList<String>();
        //Log.d(tagLog, "ListCursorDB.getListTitle2,1:"+cursorDB.isClosed());
        try {
            if (view.equals(ModeView.LIST_SPINNER_VIEW.get(2))&!object.equals(context.getString(R.string.data_base_no_information))//Внеплановое
                    &!object.equals(context.getString(R.string.spinner_title_first))){
                //Log.d(tagLog, "ListCursorDB.getListTitle2,2:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE},
                        DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT + "=?",
                        new String[]{object}, null, null, null, null);


                //Log.d(tagLog, "ListCursorDB.getListTitle2,3:"+cursorDB.isClosed());
            }
            //Log.d(tagLog, "ListCursorDB.getListTitle2,4:"+cursorDB.isAfterLast());
            if (cursorDB.isAfterLast()) {
                //Log.d(tagLog, "ListCursorDB.getListTitle2,5:");
                list.add(context.getString(R.string.data_base_no_information));
            } else {
                //Log.d(tagLog, "ListCursorDB.getListTitle2,6:");
                cursorDB.moveToFirst();
                if (cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE))==null){
                    //Log.d(tagLog, "ListCursorDB.getListTitle2,6:");
                    list.add(context.getString(R.string.data_base_no_information));
                }else {
                    //Log.d(tagLog, "ListCursorDB.getListTitle2,7:");
                    cursorDB.moveToFirst();
                    list.add(context.getString(R.string.spinner_title_first));
                    do{
                        //Log.d(tagLog, "ListCursorDB.getListTitle2,8:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE)));
                        list.add(cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE)));//СМОТРИ ВНИМАТЕЛЬНО ЧТО СЮДА ПИШЕШь ЕСЛИ не ТО ПОЛЕ ВНИСЁШЬ то БУДЕТ ОШИБКА Couldn't read row 1, col -1 from CursorWindow
                    }while (cursorDB.moveToNext());
                }

            }
            //Log.d(tagLog, "ListCursorDB.getListTitle,7:"+list);
            cursorDB.close();
            return list;
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getListTitle2.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getListTitle2", Toast.LENGTH_LONG).show();
            list.clear();
            return list;
        }

    }

    /***/
    ArrayList <StateVO> getListSerial (String mode, String object, String month, String period, String category, String title){
        ArrayList <StateVO> list = new ArrayList<StateVO>();
        //Log.d(tagLog, "ListCursorDB.getListSerial,1:"+cursorDB.isClosed());
        try {
            if (mode.equals(ModeView.LIST_SPINNER_MODE.get(0))){//Обычный режим
                //Log.d(tagLog, "ListCursorDB.getListSerial,2:");
                if (period.equals(context.getString(R.string.data_base_no_information))){
                    //Log.d(tagLog, "ListCursorDB.getListSerial,22:");
                    cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL},
                            DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE + "=?",
                            new String[]{object, month,category,title}, null, null, null, null);
                }else {
                    //Log.d(tagLog, "ListCursorDB.getListSerial,23:");
                    cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL},
                            DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY + "=? AND "
                                    + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE + "=?",
                            new String[]{object, month,period,category,title}, null, null, null, null);
                }

                //Log.d(tagLog, "ListCursorDB.getListSerial,24:"+cursorDB.isClosed());
            }else if (mode.equals(ModeView.LIST_SPINNER_MODE.get(1))){//Режим "O журнала ТО"
                //Log.d(tagLog, "ListCursorDB.getListSerial,3:");
                if (period.equals(context.getString(R.string.data_base_no_information))){
                    //Log.d(tagLog, "ListCursorDB.getListSerial,31:");
                    cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL},
                            DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY+ "=?",
                            new String[]{object,month,category,title,"O"}, null, null, null, null);
                }else{
                    //Log.d(tagLog, "ListCursorDB.getListSerial,32:");
                    cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL},
                            DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY+ "=?",
                            new String[]{object,month,period,category,title,"O"}, null, null, null, null);
                }
                //Log.d(tagLog, "ListCursorDB.getListSerial,32:"+cursorDB);
            }else if(mode.equals(ModeView.LIST_SPINNER_MODE.get(2))|mode.equals(ModeView.LIST_SPINNER_MODE.get(3))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(4))|mode.equals(ModeView.LIST_SPINNER_MODE.get(5))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(6))){
                //Log.d(tagLog, "ListCursorDB.getListSerial,4:");
                if (period.equals(context.getString(R.string.data_base_no_information))){
                    //Log.d(tagLog, "ListCursorDB.getListSerial,5:"+cursorDB.isAfterLast());
                    cursorDB=maindb.rawQuery("SELECT DISTINCT "
                            + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL
                            + " FROM "
                            + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                            + " WHERE "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "= '"+object+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "= '"+month+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY+ "= '"+category+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE+ "= '"+title+"' AND ("
                            +getModeSQLQuery(mode)+")",null);
                }
                else{
                    //Log.d(tagLog, "ListCursorDB.getListSerial,51:"+cursorDB.isAfterLast());
                    cursorDB=maindb.rawQuery("SELECT DISTINCT "
                            + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL
                            + " FROM "
                            + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                            + " WHERE "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "= '"+object+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "= '"+month+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD+ "= '"+period+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY+ "= '"+category+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE+ "= '"+title+"' AND ("
                            +getModeSQLQuery(mode)+")",null);
                }
            }
            //Log.d(tagLog, "ListCursorDB.getListSerial,55:"+cursorDB.isAfterLast());
            if (cursorDB.isAfterLast()) {
                //Log.d(tagLog, "ListCursorDB.getListSerial,6:");
                StateVO stateVO = new StateVO();
                stateVO.setTitle(context.getString(R.string.data_base_no_information));
                stateVO.setSelected(false);
                list.add(stateVO);
            } else {
                //Log.d(tagLog, "ListCursorDB.getListSerial,7:");
                cursorDB.moveToFirst();
                if (cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL))==null){
                    //Log.d(tagLog, "ListCursorDB.getListSerial,8:");
                    StateVO stateVO = new StateVO();
                    stateVO.setTitle(context.getString(R.string.data_base_no_information));
                    stateVO.setSelected(false);
                    list.add(stateVO);
                }else {
                    //Log.d(tagLog, "ListCursorDB.getListSerial,9:");
                    StateVO stateVO = new StateVO();
                    stateVO.setTitle(context.getString(R.string.spinner_serial_first));
                    stateVO.setSelected(false);
                    list.add(stateVO);
                    cursorDB.moveToFirst();
                    do{
                        //Log.d(tagLog, "ListCursorDB.getListSerial,10:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL)));
                        StateVO stateVODW = new StateVO();
                        stateVODW.setTitle(cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL)));
                        stateVODW.setSelected(false);
                        list.add(stateVODW);
                    }while (cursorDB.moveToNext());
                }

            }
            //Log.d(tagLog, "ListCursorDB.getListSerial,7:"+list);
            cursorDB.close();
            return list;
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getListSerial.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getListSerial", Toast.LENGTH_LONG).show();
            list.clear();
            return list;
        }

    }

    /***/
    ArrayList <StateVO> getListSerial (String view, String object, String month, String title){
        ArrayList <StateVO> list = new ArrayList<StateVO>();
        //Log.d(tagLog, "ListCursorDB.getListSerial2,1:"+cursorDB.isClosed());
        try {
            if (view.equals(ModeView.LIST_SPINNER_VIEW.get(2))&!object.equals(context.getString(R.string.data_base_no_information))
                    &!month.equals(context.getString(R.string.data_base_no_information))&!title.equals(context.getString(R.string.data_base_no_information))){//Внеплановый вид
                //Log.d(tagLog, "ListCursorDB.getListSerial2,2:");
                cursorDB = maindb.query(true, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                        new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL},
                        DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT + "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH + "=? AND "
                                +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE + "=?",
                        new String[]{object, month,title}, null, null, null, null);
                //Log.d(tagLog, "ListCursorDB.getListSerial2,3:");
            }
            //Log.d(tagLog, "ListCursorDB.getListSerial2,4:"+cursorDB.isAfterLast());
            if (cursorDB.isAfterLast()) {
                //Log.d(tagLog, "ListCursorDB.getListSerial2,5:");
                StateVO stateVO = new StateVO();
                stateVO.setTitle(context.getString(R.string.data_base_no_information));
                stateVO.setSelected(false);
                list.add(stateVO);
            } else {
                //Log.d(tagLog, "ListCursorDB.getListSerial2,6:");
                cursorDB.moveToFirst();
                if (cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL))==null){
                    //Log.d(tagLog, "ListCursorDB.getListSerial2,7:");
                    StateVO stateVO = new StateVO();
                    stateVO.setTitle(context.getString(R.string.data_base_no_information));
                    stateVO.setSelected(false);
                    list.add(stateVO);
                }else {
                    //Log.d(tagLog, "ListCursorDB.getListSerial2,8:");
                    StateVO stateVO = new StateVO();
                    stateVO.setTitle(context.getString(R.string.spinner_serial_first));
                    stateVO.setSelected(false);
                    list.add(stateVO);
                    cursorDB.moveToFirst();
                    do{
                        //Log.d(tagLog, "ListCursorDB.getListSerial2,9:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL)));
                        StateVO stateVODW = new StateVO();
                        stateVODW.setTitle(cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL)));
                        stateVODW.setSelected(false);
                        list.add(stateVODW);
                    }while (cursorDB.moveToNext());
                }

            }
            //Log.d(tagLog, "ListCursorDB.getListSerial2,10:"+list);
            cursorDB.close();
            return list;
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getListSerial2.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getListSerial2", Toast.LENGTH_LONG).show();
            list.clear();
            return list;
        }

    }

    /***/
    List<String> getRow_s (String mode, String object, String month, String period, String category, String title, List<String> listSerial,String conditionList){
        List<String> row_s = new ArrayList<>();
        row_s.clear();
        String stringQueryRow_s="";
        //Log.d(tagLog, "ListCursorDB.getRow_s.1:"+listSerial+":"+period+":"+category);
        Iterator iterator=listSerial.iterator();
        try {
            while (iterator.hasNext()){//получаем строку с серийными номерами для вставки в запросы
                if (stringQueryRow_s.equals("")){
                    stringQueryRow_s="'"+iterator.next().toString()+"'";
                }else{
                    stringQueryRow_s=stringQueryRow_s+", '"+iterator.next().toString()+"'";
                }
            }
            //Log.d(tagLog, "ListCursorDB.getRow_s.1:"+stringQueryRow_s);
            if (mode.equals(ModeView.LIST_SPINNER_MODE.get(0))){//Обычный режим
                //Log.d(tagLog, "ListCursorDB.getRow_s,2:");
                if (period.equals(context.getString(R.string.data_base_no_information))){
                    //Log.d(tagLog, "ListCursorDB.getRow_s,22:");
                    cursorDB = maindb.query(false, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID, "*"},//DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID - добавляется сюда для того чтобы получить столбец rowID, т.к. просто "*" его не выдает, видимо как в DataBaseContract создали столбцы такие они и выдаются, аналогично в SQLite Expert в Design в colums тоже отображаются только те которые созданы контрактором
                            DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL + " IN ("+stringQueryRow_s+")",
                            new String[]{object, month,category,title}, null, null, null, null);

                }else {
                    //Log.d(tagLog, "ListCursorDB.getRow_s,23:");

                    cursorDB = maindb.query(false, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID, "*"},
                            DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE + "=? AND "
                                    + DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL + " IN ("+stringQueryRow_s+")",
                            new String[]{object, month,period,category,title}, null, null, null, null);//
                    }

                //Log.d(tagLog, "ListCursorDB.getRow_s,24:"+cursorDB.isClosed());
            }else if (mode.equals(ModeView.LIST_SPINNER_MODE.get(1))){//Режим "O журнала ТО"
                //Log.d(tagLog, "ListCursorDB.getRow_s,3:");
                if (period.equals(context.getString(R.string.data_base_no_information))){
                    //Log.d(tagLog, "ListCursorDB.getRow_s,31:");
                    cursorDB = maindb.query(false, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID, "*"},
                            DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL+ " IN ("+stringQueryRow_s+") AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY+ "=?",
                            new String[]{object,month,category,title,"O"}, null, null, null, null);
                }else{
                    //Log.d(tagLog, "ListCursorDB.getRow_s,32:");
                    cursorDB = maindb.query(false, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID, "*"},
                            DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE + "=? AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL + " IN ("+stringQueryRow_s+") AND "
                                    +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY+ "=?",
                            new String[]{object,month,period,category,title,"O"}, null, null, null, null);
                }
                //Log.d(tagLog, "ListCursorDB.getRow_s,32:"+cursorDB);
            }else if(mode.equals(ModeView.LIST_SPINNER_MODE.get(2))|mode.equals(ModeView.LIST_SPINNER_MODE.get(3))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(4))|mode.equals(ModeView.LIST_SPINNER_MODE.get(5))
                    |mode.equals(ModeView.LIST_SPINNER_MODE.get(6))){
                //Log.d(tagLog, "ListCursorDB.getRow_s,4:");
                if (period.equals(context.getString(R.string.data_base_no_information))){
                    //Log.d(tagLog, "ListCursorDB.getRow_s,5:"+cursorDB.isAfterLast());
                    cursorDB=maindb.rawQuery("SELECT "+DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID+", * "//DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID добавлено по причинам описанным выше
                            + " FROM "
                            + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                            + " WHERE "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "= '"+object+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "= '"+month+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY+ "= '"+category+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE+ "= '"+title+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL+ " IN ("+stringQueryRow_s+") AND ("
                            +getModeSQLQuery(mode)+")",null);
                }
                else{
                    //Log.d(tagLog, "ListCursorDB.getRow_s,51:"+cursorDB.isAfterLast());
                    cursorDB=maindb.rawQuery("SELECT "+DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID+", * "
                            + " FROM "
                            + DataBaseContract.CheckListDBTableClass.TABLE_NAME
                            + " WHERE "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT+ "= '"+object+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH+ "= '"+month+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD+ "= '"+period+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY+ "= '"+category+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE+ "= '"+title+"' AND "
                            +DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL+ " IN ("+stringQueryRow_s+") AND ("
                            +getModeSQLQuery(mode)+")",null);
                }
            }
            //Log.d(tagLog, "ListCursorDB.getRow_s,55:"+cursorDB.isAfterLast());
            if (cursorDB.isAfterLast()) {
                //Log.d(tagLog, "ListCursorDB.getRow_s,6:");
                row_s.add(context.getString(R.string.data_base_no_information));
            } else {
                cursorDB.moveToFirst();
                //Log.d(tagLog, "ListCursorDB.getRow_s,7:");
                if (cursorDB.getString(cursorDB.getColumnIndex(COLUMNS_ROWID))==null){
                    //Log.d(tagLog, "ListCursorDB.getRow_s,8:");
                    row_s.add(context.getString(R.string.data_base_no_information));
                }else {
                    //Log.d(tagLog, "ListCursorDB.getRow_s,9:");
                    cursorDB.moveToFirst();

                    do{
                        //Log.d(tagLog, "ListCursorDB.getRow_s,10:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD)));
                        //Log.d(tagLog, "ListCursorDB.getRow_s,101:");
                        switch (conditionList){//в зависимости от условия возвращается определенный List
                            case COLUMNS_CHECK:
                                //Log.d(tagLog, "ListCursorDB.getRow_s,102:");
                                int ii=0;
                                for (String cn : cursorDB.getColumnNames()) {
                                    ii++;
                                    if (ii > 7&cursorDB.getPosition()<1) {//т.к. при наличии нескольких строк курсор перемещается повторно по этим же колонкам, то в строке с названиями (условие case COLUMNS_CHECK) появляются дубли, которые приводят к дублям в чек листе, чтобы это исключить после того позиция будет на последующих строках, то названия колонок добавляться не будут
                                        if (mode.equals(ModeView.LIST_SPINNER_MODE.get(0))|mode.equals(ModeView.LIST_SPINNER_MODE.get(1))){//Обычный режим
                                            row_s.add(cn);
                                        }else if(cursorDB.getString(cursorDB.getColumnIndex(cn))!=null) {
                                            //Log.d(tagLog, "ListCursorDB.getRow_s,103:"+row_s+": "+cursorDB.getString(cursorDB.getColumnIndex(cn)));
                                            if (cursorDB.getString(cursorDB.getColumnIndex(cn)).equals(mode)|cursorDB.getColumnName(cursorDB.getColumnIndex(cn)).equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY)){//ЗДЕСЬ определяется какие поля будут отображаться или происходит выборка
                                                row_s.add(cn);
                                            }
                                        }
                                        //Log.d(tagLog, "ListCursorDB.getRow_s,104:"+row_s);
                                    }
                                }
                                break;
                            case COLUMNS_ROWID:
                                row_s.add(cursorDB.getString(cursorDB.getColumnIndex(COLUMNS_ROWID)));
                                break;
                        }
                        //row_s.add(cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE)));//СМОТРИ ВНИМАТЕЛЬНО ЧТО СЮДА ПИШЕШь ЕСЛИ не ТО ПОЛЕ ВНИСЁШЬ то БУДЕТ ОШИБКА Couldn't read row 1, col -1 from CursorWindow
                    }while (cursorDB.moveToNext());
                }
            }
            //Log.d(tagLog, "ListCursorDB.getRow_s,11:"+row_s);
            cursorDB.close();

        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getRow_s.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getRow_s", Toast.LENGTH_LONG).show();
            row_s.clear();
            cursorDB.close();
            return row_s;
        }


        return row_s;
    }

    /***/
    void getNewRow_s (String object, String month, String title, List<String> listSerial){
        row_sRowID.clear(); row_sColumnsCheck.clear();
        String stringQueryRow_s="";
        //Log.d(tagLog, "ListCursorDB.getNewRow_s.1:"+listSerial);
        Iterator iterator=listSerial.iterator();
        try {
            while (iterator.hasNext()){//получаем строку с серийными номерами для вставки в запросы
                //Log.d(tagLog, "ListCursorDB.getNewRow_s.1.2:"+listSerial);
                cv.clear();
                cv.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_OBJECT,object);
                cv.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MONTH,month);
                cv.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD,UNPLANNED);
                cv.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CATEGORY,UNPLANNED);
                cv.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_TITLE,title);
                cv.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_SERIAL,iterator.next().toString());
                cv.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY,"O");
                if (stringQueryRow_s.equals("")){
                    //Log.d(tagLog, "ListCursorDB.getNewRow_s.1.21:");
                    stringQueryRow_s = "'"+Long.toString(maindb.insert(DataBaseContract.CheckListDBTableClass.TABLE_NAME,null,cv))+"'";
                    //stringQueryRow_s="'"+iterator.next().toString()+"'";
                }else{
                    //Log.d(tagLog, "ListCursorDB.getNewRow_s.1.22:");
                    stringQueryRow_s=stringQueryRow_s+", '"+Long.toString(maindb.insert(DataBaseContract.CheckListDBTableClass.TABLE_NAME,null,cv))+"'";
                }
            }
            //Log.d(tagLog, "ListCursorDB.getNewRow_s.2:"+stringQueryRow_s);

            cursorDB = maindb.query(false, DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                    new String[]{DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID, "*"},//DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID - добавляется сюда для того чтобы получить столбец rowID, т.к. просто "*" его не выдает, видимо как в DataBaseContract создали столбцы такие они и выдаются, аналогично в SQLite Expert в Design в colums тоже отображаются только те которые созданы контрактором
                     DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID + " IN ("+stringQueryRow_s+")",
                    new String[]{}, null, null, null, null);
            //Log.d(tagLog, "ListCursorDB.getNewRow_s,3:"+cursorDB.isAfterLast());
            if (cursorDB.isAfterLast()) {
                //Log.d(tagLog, "ListCursorDB.getNewRow_s,6:");
                row_sColumnsCheck.add(context.getString(R.string.data_base_no_information));
            } else {
                cursorDB.moveToFirst();
                //Log.d(tagLog, "ListCursorDB.getNewRow_s,7:");
                if (cursorDB.getString(cursorDB.getColumnIndex(COLUMNS_ROWID))==null){
                    //Log.d(tagLog, "ListCursorDB.getNewRow_s,8:");
                    row_sColumnsCheck.add(context.getString(R.string.data_base_no_information));
                }else {
                    //Log.d(tagLog, "ListCursorDB.getNewRow_s,9:");
                    cursorDB.moveToFirst();
                    do{
                        //Log.d(tagLog, "ListCursorDB.getNewRow_s,10:"+cursorDB.getString(cursorDB.getColumnIndex("rowid")));
                        row_sRowID.add(cursorDB.getString(cursorDB.getColumnIndex(COLUMNS_ROWID)));
                        int ii=0;
                        for (String cn : cursorDB.getColumnNames()) {
                            ii++;
                            if (ii > 7&cursorDB.getPosition()<1) {//т.к. при наличии нескольких строк курсор перемещается повторно по этим же колонкам, то в строке с названиями (условие case COLUMNS_CHECK) появляются дубли, которые приводят к дублям в чек листе, чтобы это исключить после того позиция будет на последующих строках, то названия колонок добавляться не будут
                                row_sColumnsCheck.add(cn);
                                //Log.d(tagLog, "ListCursorDB.getNewRow_s,104:"+row_sColumnsCheck);
                            }
                        }
                    }while (cursorDB.moveToNext());
                }
            }
            //Log.d(tagLog, "ListCursorDB.getNewRow_s,11:" +"rowid:"+row_sRowID+", columnCheck:"+row_sColumnsCheck);
            cursorDB.close();
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getRow_s.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getRow_s", Toast.LENGTH_LONG).show();
            row_sRowID.clear();
            row_sColumnsCheck.clear();
            cursorDB.close();
        }

    }
    /**Т.к. метод не позволяет возвращать два значения из getNewRow_s то получение перечня колонок и номера строк будут передоваться через эти методы*/
    List<String> getRow_sColumnsCheck(){
        return this.row_sColumnsCheck;
    }
    /**Т.к. метод не позволяет возвращать два значения из getNewRow_s то получение перечня колонок и номера строк будут передоваться через эти методы*/
    List<String> getRow_sRowID(){
        return this.row_sRowID;
    }

    /***/
    public String getContent(List<String> rowid, String column){
        //Log.d(tagLog, "ListCursorDB.getContent.1:");
        String content="";
        String stringRowid=getListToStringQuery(rowid);
        //Iterator iterator=rowid.iterator();//вместо используется метод  getListToStringQuery
        try {
            /*while (iterator.hasNext()){//получаем строку с серийными номерами для вставки в запросы //вместо используется метод  getListToStringQuery
                if (stringRowid.equals("")){
                    stringRowid="'"+iterator.next().toString()+"'";
                }else{
                    stringRowid=stringRowid+", '"+iterator.next().toString()+"'";
                }
            }*/
            //Log.d(tagLog, "ListCursorDB.getContent.2:"+stringRowid);
            cursorDBEditText =maindb.query(DataBaseContract.CheckListDBTableClass.TABLE_NAME,
                    new String[]{column},
                    "rowid IN ("+stringRowid+")",
                    new String[]{}, null, null, null, null);
            //Log.d(tagLog, "ListCursorDB.getContent.3:"+cursorDBEditText.getCount());
            if (cursorDBEditText.getCount()>1){
                //Log.d(tagLog, "ListCursorDB.getContent.4:");
                while (cursorDBEditText.moveToNext()) {
                    //Log.d(tagLog, "ListCursorDB.getContent.5:"+cursorDBEditText.getPosition());
                    for (String cn : cursorDBEditText.getColumnNames()) {

                        if (cursorDBEditText.getPosition()==0){

                            if (cursorDBEditText.getString(cursorDBEditText.getColumnIndex(cn))!=null) {
                                content = (cursorDBEditText.getString(cursorDBEditText.getColumnIndex(cn)));
                            } else {
                                content="";
                            }

                        }else if (cursorDBEditText.getPosition()>0) {
                            if (cursorDBEditText.getString(cursorDBEditText.getColumnIndex(cn))!=null) {
                                content =content+", "+(cursorDBEditText.getString(cursorDBEditText.getColumnIndex(cn)));
                            }else {
                                content=content+"";
                            }
                       }

                    }
                }
            }else if(cursorDBEditText.getCount()==1){
                //Log.d(tagLog, "ListCursorDB.getContent.6:");
                while (cursorDBEditText.moveToNext()) {
                    //Log.d(tagLog, "ListCursorDB.getContent.61:");
                    for (String cn : cursorDBEditText.getColumnNames()) {
                        //Log.d(tagLog, "ListCursorDB.getContent.62:"+cn+": "+cursorDBEditText.getString(cursorDBEditText.getColumnIndex(cn)));
                        content = cursorDBEditText.getString(cursorDBEditText.getColumnIndex(cn));
                    }
                }
            }
            //Log.d(tagLog, "ListCursorDB.getContent.7:"+content);
            if (content==null){
                content="";
            }
            //Log.d(tagLog, "ListCursorDB.getContent.8:"+content);
            cursorDBEditText.close();
            return content;
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getContent.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getContent", Toast.LENGTH_LONG).show();
            content=context.getString(R.string.toast_list_cursor_db) +": ListCursorDB.getContent";
            return content;
        }
    }

    /***/
    String getListToStringQuery(List<String> list){
        //Log.d(tagLog, "ListCursorDB.getListToStringQuery.1:");
        String result="";
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){//получаем строку с серийными номерами для вставки в запросы
            if (result.equals("")){
                result="'"+iterator.next().toString()+"'";
            }else{
                result=result+", '"+iterator.next().toString()+"'";
            }
        }
        return result;
    }

    /***/
    public void insertContentCellDB (List<String> rowId, String column, String contentCell){//Подумать о том чтобы завести убрать ветвления LIst с rowid из одного класса вдругие, а использовать его только по максимому в этом классе
        try {
            //Log.d(tagLog, "ListCursorDB.insertContentCellDB.1:");
            String stringRowId =getListToStringQuery(rowId);
            cv.clear();
            cv.put(column, contentCell);
            maindb.update(DataBaseContract.CheckListDBTableClass.TABLE_NAME, cv,
                    DataBaseContract.CheckListDBTableClass.COLUMN_NAME_ROWID+ " IN ("+stringRowId+")", new String[]{});
        }catch (Exception e){
            Log.d(tagLog, "ListCursorDB.insertContentCellDB.catch,1:"+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.insertContentCellDB", Toast.LENGTH_LONG).show();
        }
    }

    /**Метод возращает из БД дату поверки и местонахождение прибора
     * */
    Map<String,Map<String,String>> getVMIDateLocation (String title, List<String> listSerial){

        Map<String, Map<String,String>> SerialVMIDateLocation = new HashMap<String,Map<String,String>>();
        String serial ="";
        Iterator iterator=listSerial.iterator();


        Log.d(tagLog, "ListCursorDB.getVMIDateLocation,1:");

        try {

            if (!title.equals(null)|!listSerial.isEmpty()){
                Log.d(tagLog, "ListCursorDB.getVMIDateLocation,2:");
                while (iterator.hasNext()){//получаем строку с серийными номерами для вставки в запросы
                    Map<String,String> VMIDateLocation = new HashMap<String,String>();
                    serial=iterator.next().toString();

                    cursorDB = maindb.query(true, DataBaseContract.GraphDBTableClass.TABLE_NAME,
                            new String[]{DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI,DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION},
                            DataBaseContract.GraphDBTableClass.COLUMN_NAME_TITLE+ "=? AND "
                                    +DataBaseContract.GraphDBTableClass.COLUMN_NAME_SERIAL+ "=?", new String[]{title,serial}, null, null, null, null);
                    if (cursorDB.isAfterLast()) {
                        Log.d(tagLog, "ListCursorDB.getVMIDateLocation,3:");
                        VMIDateLocation.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI,context.getString(R.string.data_base_no_information));
                        VMIDateLocation.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION,context.getString(R.string.data_base_no_information));
                        serial=context.getString(R.string.data_base_no_information);
                    } else {
                        cursorDB.moveToFirst();
                        Log.d(tagLog, "ListCursorDB.getVMIDateLocation,4:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI) )+": "+cursorDB.getColumnName(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI)));
                        Log.d(tagLog, "ListCursorDB.getVMIDateLocation,5:"+cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION) )+": "+cursorDB.getColumnName(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION)));
                        if (cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI)) == null
                                & cursorDB.getColumnName(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI)).equals(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI)) {

                            VMIDateLocation.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI, "");
                        } else if (cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI)) != null
                                & cursorDB.getColumnName(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI)).equals(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI)) {
                            VMIDateLocation.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI, cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_DATE_VMI)));
                        }
                        if (cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION)) == null
                                & cursorDB.getColumnName(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION)).equals(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION)) {
                            VMIDateLocation.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION, "");
                        } else if (cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION)) != null
                                & cursorDB.getColumnName(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION)).equals(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION)) {
                            VMIDateLocation.put(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION, cursorDB.getString(cursorDB.getColumnIndex(DataBaseContract.GraphDBTableClass.COLUMN_NAME_LOCATION)));
                        }

                    }Log.d(tagLog, "ListCursorDB.getVMIDateLocation,6:"+VMIDateLocation);
                    SerialVMIDateLocation.put(serial,VMIDateLocation);
                    Log.d(tagLog, "ListCursorDB.getVMIDateLocation,7:"+SerialVMIDateLocation);
                }


            }


            cursorDB.close();
        } catch (Exception e) {
            Log.d(tagLog, "ListCursorDB.getVMIDateLocation: "+e);
            Toast.makeText(context, context.getString(R.string.toast_list_cursor_db)+": ListCursorDB.getVMIDateLocation", Toast.LENGTH_LONG).show();
            cursorDB.close();

        }
        return SerialVMIDateLocation;

    }

}
