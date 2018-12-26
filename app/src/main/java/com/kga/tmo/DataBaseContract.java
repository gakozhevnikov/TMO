package com.kga.tmo;

import android.provider.BaseColumns;
/*реализуем методы создания и обслуживания базы данных и таблиц
    * */
//

/**
 * Created by home on 06.10.2016.
 * В этом класса создаем класс-контракт, в котором описываем структуру базы данных, в дальнейшем
 * если надо поменять что-то, то это будет проще как пишут в https://developer.android.com/training/basics/data-storage/databases.html
 */

public final class DataBaseContract {
    private static String SLASH_SEP="/";
    private static final String COMMA_SEP = ",";// просто запятая для добавления в запросы, так написано на сайте андройд
    public static final int DATABASE_VERSION = 1;
    public static TMOFile tmoFile = new TMOFile();
    public static final String DATABASE_NAME =tmoFile.getDirectoryTmo()+SLASH_SEP+"T(M)ODataBase";

    private static final String TYPE_TEXT = " TEXT";// тип данных в БД текст
    //private static final String TYPE_DATE = " NUMERIC";// тип данных в БД в котором будем записывать даты

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DataBaseContract(){}

    //Блок 1 (начало): Создаем класс Оборудование в котором описываем структуру имя таблицы и столбец наименование
    /**Класс-структура таблицы с оборудованием */
    public static abstract class EquipmentClass implements BaseColumns {
        public static final String TABLE_NAME = "equipment";
        //public static final String _ID = "id";//в приципе это лишнее но посмотрим, что получится,
        // это не обязательно, автоматически создается ID столбец с именем rowid
        public static final String COLUMN_NAME_TITLE ="title";
        public static final String COLUMN_NAME_TYPE_DESCRIPTION ="type_description";//описание типа
        public static final String COLUMN_NAME_VMI_PROCEDURE ="vmi_procedure";//методика поверки, verification of a measuring instrument (VMI) - Поверка (РМГ 29) 10 пунктов
        public static final String COLUMN_NAME_TECHNOLOGICAL_MAP ="technological_map";//технологическая карта
        public static final String COLUMN_NAME_CHECK_LIST_MAP ="check_list_map";//карта для чек-листа
        public static final String COLUMN_NAME_ADDITIONAL_MAP ="additional_map";//Дополнительная карта работ

        //создаем запрос для создания таблицы оборудования
        /*public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ("+
                EquipmentClass._ID + " INTEGER PRIMARY KEY"+
                COMMA_SEP+
                EquipmentClass.COLUMN_NAME_TITLE +TYPE_TEXT+" )";/Этот код был для создания столбца ID,
                но все равно создается автоматически столбец rowid, поэтому я его закомментировал*/
        public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ("+
                EquipmentClass.COLUMN_NAME_TITLE +TYPE_TEXT+ COMMA_SEP +
                EquipmentClass.COLUMN_NAME_TYPE_DESCRIPTION +TYPE_TEXT+ COMMA_SEP +
                EquipmentClass.COLUMN_NAME_VMI_PROCEDURE +TYPE_TEXT+ COMMA_SEP +
                EquipmentClass.COLUMN_NAME_TECHNOLOGICAL_MAP +TYPE_TEXT+ COMMA_SEP +
                EquipmentClass.COLUMN_NAME_CHECK_LIST_MAP +TYPE_TEXT+ COMMA_SEP +
                EquipmentClass.COLUMN_NAME_ADDITIONAL_MAP +TYPE_TEXT+
                " )";
        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + EquipmentClass.TABLE_NAME;

    }
    //Блок 1 (конец): Создаем класс Оборудование в котором описываем структуру имя таблицы и столбец наименование

    //Блок 2 (начало): Создаем класс GraphDBTable в котором описываем структуру, имя таблицы и столбецы: наименование,
    //за. номер, даты ТО и вид ТО по месяцам
    public static abstract class GraphDBTableClass implements BaseColumns {
        public static final String TABLE_NAME = "graphDBTable";
        //public static final String _ID = "id";//в приципе это лишнее но посмотрим, что получится,
        // это не обязательно, автоматически создается ID столбец с именем rowid
        public static final String COLUMN_NAME_OBJECT ="object";
        public static final String COLUMN_NAME_TITLE ="title";
        public static final String COLUMN_NAME_SERIAL ="serial";
        public static final String COLUMN_NAME_JANUARY_CATEGORY ="january_category";
        public static final String COLUMN_NAME_JANUARY_TEN_DAY_PERIOD ="january_ten_day_period";

        public static final String COLUMN_NAME_FEBRUARY_CATEGORY ="february_category";
        public static final String COLUMN_NAME_FEBRUARY_TEN_DAY_PERIOD ="february_ten_day_period";

        public static final String COLUMN_NAME_MARCH_CATEGORY ="march_category";
        public static final String COLUMN_NAME_MARCH_TEN_DAY_PERIOD ="march_ten_day_period";

        public static final String COLUMN_NAME_APRIL_CATEGORY ="april_category";
        public static final String COLUMN_NAME_APRIL_TEN_DAY_PERIOD ="april_ten_day_period";

        public static final String COLUMN_NAME_MAY_CATEGORY ="may_category";
        public static final String COLUMN_NAME_MAY_TEN_DAY_PERIOD ="may_ten_day_period";

        public static final String COLUMN_NAME_JUNE_CATEGORY ="june_category";
        public static final String COLUMN_NAME_JUNE_TEN_DAY_PERIOD ="june_ten_day_period";

        public static final String COLUMN_NAME_JULY_CATEGORY ="july_category";
        public static final String COLUMN_NAME_JULY_TEN_DAY_PERIOD ="july_ten_day_period";

        public static final String COLUMN_NAME_AUGUST_CATEGORY  ="august_category";
        public static final String COLUMN_NAME_AUGUST_TEN_DAY_PERIOD ="august_ten_day_period";

        public static final String COLUMN_NAME_SEPTEMBER_CATEGORY ="september_category";
        public static final String COLUMN_NAME_SEPTEMBER_TEN_DAY_PERIOD ="september_ten_day_period";

        public static final String COLUMN_NAME_OCTOBER_CATEGORY ="october_category";
        public static final String COLUMN_NAME_OCTOBER_TEN_DAY_PERIOD ="october_ten_day_period";

        public static final String COLUMN_NAME_NOVEMBER_CATEGORY ="november_category";
        public static final String COLUMN_NAME_NOVEMBER_TEN_DAY_PERIOD ="november_ten_day_period";

        public static final String COLUMN_NAME_DECEMBER_CATEGORY ="december_category";
        public static final String COLUMN_NAME_DECEMBER_TEN_DAY_PERIOD ="december_ten_day_period";

        public static final String COLUMN_NAME_DATE_VMI ="date_vmi";//дата поверки
        public static final String COLUMN_NAME_LOCATION ="location";//позиция
        public static final String COLUMN_NAME_CERTIFICATE_VMI ="certificate_vmi";//свидетельство о поверке

        //создаем запрос для создания таблицы оборудования
        /*public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ("+
                EquipmentClass._ID + " INTEGER PRIMARY KEY"+
                COMMA_SEP+
                EquipmentClass.COLUMN_NAME_TITLE +TYPE_TEXT+" )";/Этот код был для создания столбца ID,
                но все равно создается автоматически столбец rowid, поэтому я его закомментировал*/
        public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ("+
                GraphDBTableClass.COLUMN_NAME_OBJECT + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_TITLE + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_SERIAL + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_JANUARY_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_JANUARY_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_FEBRUARY_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_FEBRUARY_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_MARCH_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_MARCH_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_APRIL_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_APRIL_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_MAY_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_MAY_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_JUNE_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_JUNE_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_JULY_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_JULY_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_AUGUST_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_AUGUST_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_SEPTEMBER_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_SEPTEMBER_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_OCTOBER_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_OCTOBER_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_NOVEMBER_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_NOVEMBER_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_DECEMBER_CATEGORY + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_DECEMBER_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_DATE_VMI + TYPE_TEXT +COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_LOCATION + TYPE_TEXT + COMMA_SEP +
                GraphDBTableClass.COLUMN_NAME_CERTIFICATE_VMI + TYPE_TEXT +" )";

        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + GraphDBTableClass.TABLE_NAME;

    }
    //Блок 2 (конец): Создаем класс GraphDBTable

    //Блок 3 (начало): Создаем класс Вид ТО в котором описываем структуру имя таблицы и столбец наименование
    public static abstract class TypesTOClass implements BaseColumns {
        public static final String TABLE_NAME = "TypesTO";
        //public static final String _ID = "id";//в приципе это лишнее но посмотрим, что получится,
        // это не обязательно, автоматически создается ID столбец с именем rowid
        public static final String COLUMN_NAME_TITLE ="titleTypesTO";
        //создаем запрос для создания таблицы оборудования

        public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ("+
                TypesTOClass.COLUMN_NAME_TITLE +TYPE_TEXT+" )";
        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TypesTOClass.TABLE_NAME;

    }
    //Блок 3 (конец): Создаем класс Вид ТО в котором описываем структуру имя таблицы и столбец наименование

    //Блок 4 (начало): Создаем класс CheckListDBTable в котором описываем структуру, имя таблицы и столбецы: наименование,
    //за. номер, даты ТО и вид ТО по месяцам
    public static abstract class CheckListDBTableClass implements BaseColumns {
        public static final String TABLE_NAME = "checkListDBTable";
        public static final String COLUMN_NAME_ROWID = "rowid";//в приципе это лишнее но посмотрим, что получится,
        // это не обязательно, автоматически создается ID столбец с именем rowid
        public static final String COLUMN_NAME_OBJECT ="object";
        public static final String COLUMN_NAME_MONTH ="month";
        public static final String COLUMN_NAME_TEN_DAY_PERIOD ="ten_day_period";
        public static final String COLUMN_NAME_CATEGORY ="category";
        public static final String COLUMN_NAME_TITLE ="title";
        public static final String COLUMN_NAME_SERIAL ="serial";

        //maintenance MT mt - техническое обслуживание 12 пунктов
        //public static final String COLUMN_NAME_MT_="mt_"; //
        public static final String COLUMN_NAME_MT_LETTER ="mt_letter"; //факсограмма или письмо о согласовании работ
        public static final String COLUMN_NAME_MT_PLANNED_DATE_OF_WORK ="mt_planned_date_of_work"; //Планируемая дата работ
        public static final String COLUMN_NAME_MT_JOURNAL_MT_ENTRY ="mt_journal_mt_entry"; //journal_entry-запись в журнале технического обслуживания
        public static final String COLUMN_NAME_MT_JOURNAL_EQUIPMENT_ENTRY="mt_journal_equipment_entry"; //journal_equipment-формуляр, _entry запись в формуляре
        public static final String COLUMN_NAME_MT_ACT="mt_act"; //акт о ТО
        public static final String COLUMN_NAME_MT_ACT_TIGHTNESS_OF_VALVES="mt_act_tightness_of_valves";//tightness of valves - акт герметичности запорной арматуры при наработке ТПР
        public static final String COLUMN_NAME_MT_STAMP_INSTALL="mt_stamp_install"; //установка пломбы
        public static final String COLUMN_NAME_MT_STAMP_JOURNAL_ENTRY="mt_stamp_journal_entry"; //Запись в журнале регистрации снятия и установки пломб
        public static final String COLUMN_NAME_MT_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN="mt_journal_mt_entry_sybervisor_admin"; //Запись о входе на 3(администратор) уровень в Sybervisore
        public static final String COLUMN_NAME_MT_ACT_SYBERVISOR_ADMIN="mt_act_sybervisor_admin"; //Акт или запись в акте о входе на 3(администратор) уровень в Sybervisore
        public static final String COLUMN_NAME_MT_SCAN="mt_scan"; //Скан документов
        public static final String COLUMN_NAME_MT_NOTE="mt_note"; //Примечание

        //MDM mdm - монтаж демонтаж 17 пунктов
        //public static final String COLUMN_NAME_MDM_="mdm_"; //
        public static final String COLUMN_NAME_MDM_TITLE="mdm_title"; //наименование операции монтаж или демонтаж
        public static final String COLUMN_NAME_MDM_JOURNAL_ENTRY ="mdm_journal_entry"; //journal_entry-запись в журнале
        public static final String COLUMN_NAME_MDM_ACT="mdm_act"; //Акт
        public static final String COLUMN_NAME_MDM_ACT_TIGHTNESS_OF_VALVES="mdm_act_tightness_of_valves";//tightness of valves - акт герметичности запорной арматуры
        public static final String COLUMN_NAME_MDM_CMCSI="mdm_cmcsi"; //control of metrological characteristics-контроль метрологических характеристик СИ
        public static final String COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_ENTRY="mdm_journal_equipment_entry"; //journal_equipment-формуляр, _entry запись в формуляре
        public static final String COLUMN_NAME_MDM_JOURNAL_9_EQUIPMENT_SMAO_ENTRY="mdm_journal_9_equipment_smao_entry"; //journal_equipment-формуляр, _entry запись в формуляре, system for measuring amount of oil smao система измерения количества нефти, раздел 9
        public static final String COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_PPD_ENTRY="mdm_journal_equipment_ppd_entry"; //journal_equipment-формуляр, _entry запись в формуляре, pipe pusher device, труба толкатель устройство трубопоршневая установка ТПУ
        public static final String COLUMN_NAME_MDM_LIST_REGISTRATION_EQUIPMENT_PPD_ENTRY="mdm_list_registration_journal_equipment_ppd_entry"; //journal_equipment-формуляр, _entry запись в формуляре, pipe pusher device, труба толкатель устройство трубопоршневая установка ТПУ
        public static final String COLUMN_NAME_MDM_ARM_PSP="mdm_arm_psp"; //арм псп
        public static final String COLUMN_NAME_MDM_CORRECTION_THERMOMETERS="mdm_correction_thermometers"; //correction (РМГ29) thermometers - поправки термометров
        public static final String COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_HOURS_WORK_ENTRY="mdm_journal_equipment_hours_work_entry"; //the number of hours of work - отметка в формуляре оборудования о количестве часов работы
        public static final String COLUMN_NAME_MDM_STORAGE_ACT_ENTER="mdm_storage_act_enter"; //Акт ввода на хранение при демонтаже
        public static final String COLUMN_NAME_MDM_STORAGE_ACT_OUTPUT="mdm_storage_act_output"; //Акт вывода из хранения при монтаже
        public static final String COLUMN_NAME_MDM_STORAGE_JOURNAL_EQUIPMENT_ENTRY="mdm_storage_journal_equipment_entry"; //Запись в формуляре о хранении
        public static final String COLUMN_NAME_MDM_SCAN="mdm_scan"; //Скан документов
        public static final String COLUMN_NAME_MDM_NOTE="mdm_note"; //Примечание

        //verification of a measuring instrument (VMI) - Поверка (РМГ 29) 10 пунктов
        //public static final String COLUMN_NAME_VMI_="vmi_"; //
        public static final String COLUMN_NAME_VMI_CERTIFICATE="vmi_certificate"; //проверка данных свидетельства о поверке
        public static final String COLUMN_NAME_VMI_JOURNAL_EQUIPMENT_ENTRY="vmi_journal_equipment_entry"; // отметка о поверке в формуляре
        public static final String COLUMN_NAME_VMI_STAMP_INSTALL="vmi_stamp_install"; //установка пломбы
        public static final String COLUMN_NAME_VMI_ARM_PSP="vmi_arm_psp"; //введение данных о поверке в АРМ ПСП
        public static final String COLUMN_NAME_VMI_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN="vmi_journal_mt_entry_sybervisor_admin"; //Запись о входе на 3(администратор) уровень в Sybervisore
        public static final String COLUMN_NAME_VMI_ACT_SYBERVISOR_ADMIN="vmi_act_sybervisor_admin"; //Акт или запись в акте о входе на 3(администратор) уровень в Sybervisore
        public static final String COLUMN_NAME_VMI_STAMP_JOURNAL_ENTRY="vmi_stamp_journal_entry";// Оттиск клейма или пломбы на листе образцов в журнале снятия и установки пломб
        public static final String COLUMN_NAME_VMI_ACT_TIGHTNESS_OF_VALVES="vmi_act_tightness_of_valves"; //Акт герметичности запорной арматуры при поверки ТПР
        public static final String COLUMN_NAME_VMI_LISTING_MF="vmi_listing_mf";//Распечатка МФ ТПР после ввода коэффициентов
        public static final String COLUMN_NAME_VMI_SCAN="vmi_scan"; //Скан документов
        public static final String COLUMN_NAME_VMI_NOTE="vmi_note"; //Примечание

        //crash - авария, отказ, инциндент 20 пунктов @string/vMINote
        //public static final String COLUMN_NAME_CRASH_="crash_"; //
        public static final String COLUMN_NAME_CRASH_ACT="crash_act"; //Акт о сбое
        public static final String COLUMN_NAME_CRASH_ACT_DISMANTLING="crash_act_dismantling"; //Акт о снятии
        public static final String COLUMN_NAME_CRASH_ACT_MOUNTING="crash_act_mounting"; //Акт о установке
        public static final String COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY="crash_journal_mt_entry"; //Запись в журнале тех.обслуживания об аварии, сбое, отказа, инциндента
        public static final String COLUMN_NAME_CRASH_JOURNAL_CRASH_ENTRY="crash_journal_crash_entry"; //Запись в журнале отказов СИ СИКН об аварии, сбое, отказа, инциндента
        public static final String COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_SMAO_ENTRY="crash_journal_equipment_smao_entry"; //Запись об аварии в формуляре СИКН В РАЗДЕЛЕ УЧЕТА
        public static final String COLUMN_NAME_CRASH_JOURNAL_9_EQUIPMENT_SMAO_ENTRY="crash_journal_9_equipment_smao_entry"; //Запись об аварии в формуляре СИКН при замене
        public static final String COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_ENTRY="crash_journal_equipment_entry"; //Запись в формуляре оборудования
        public static final String COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN="crash_journal_mt_entry_sybervisor_admin"; //Запись о входе на 3(администратор) уровень в Sybervisore
        public static final String COLUMN_NAME_CRASH_ACT_SYBERVISOR_ADMIN="crash_act_sybervisor_admin"; //Акт или запись в акте о входе на 3(администратор) уровень в Sybervisore
        public static final String COLUMN_NAME_CRASH_ARM_PSP="crash_arm_psp"; //Замена оборудования в АРМ ПСП
        public static final String COLUMN_NAME_CRASH_CORRECTION_THERMOMETERS="crash_correction_thermometers"; //correction (РМГ29) thermometers - поправки термометров
        public static final String COLUMN_NAME_CRASH_LISTING_MF="crash_listing_mf";//Распечатка МФ
        public static final String COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_HOURS_WORK_ENTRY="crash_journal_equipment_hours_work_entry"; // отметка в формуляре оборудования о количестве часов работы
        public static final String COLUMN_NAME_CRASH_STORAGE_ACT_ENTER="crash_storage_act_enter"; //Акт ввода на хранение
        public static final String COLUMN_NAME_CRASH_STORAGE_ACT_OUTPUT="crash_storage_act_output"; //Акт вывода из хранения
        public static final String COLUMN_NAME_CRASH_STORAGE_JOURNAL_EQUIPMENT_ENTRY="crash_storage_journal_equipment_entry"; //Запись в формуляре о хранении
        public static final String COLUMN_NAME_CRASH_SCAN="crash_scan"; //Скан документов
        public static final String COLUMN_NAME_CRASH_ANALYSIS="crash_analysis"; //Скан документов
        public static final String COLUMN_NAME_CRASH_NOTE="crash_note"; //Примечание

        //storage - хранение
        //public static final String COLUMN_NAME_STORAGE_="storage_"; //
        public static final String COLUMN_NAME_STORAGE_ACT_ENTER="storage_act_enter"; //Акт ввода на хранение
        public static final String COLUMN_NAME_STORAGE_ACT_OUTPUT="storage_act_output"; //Акт вывода из хранения
        public static final String COLUMN_NAME_STORAGE_JOURNAL_EQUIPMENT_ENTRY="storage_journal_equipment_entry"; //Запись в формуляре о хранении
        public static final String COLUMN_NAME_STORAGE_SCAN="storage_scan"; //Скан документов
        public static final String COLUMN_NAME_STORAGE_NOTE="storage_note"; //Примечание

        //repair - ремонт
        //public static final String COLUMN_NAME_REPAIR_="repair_"; //
        public static final String COLUMN_NAME_REPAIR_ACT="repair_act"; //Акт о ремонте
        public static final String COLUMN_NAME_REPAIR_JOURNAL_EQUIPMENT_ENTRY="repair_journal_equipment_entry"; //Запись о ремонте в формуляре
        public static final String COLUMN_NAME_REPAIR_JOURNAL_REPAIR_ENTRY="repair_journal_repair_entry"; //Запись в журнале ремонтов оборудования
        public static final String COLUMN_NAME_REPAIR_SCAN="repair_scan"; //Скан документов
        public static final String COLUMN_NAME_REPAIR_NOTE="repair_note"; //

        //note - Общее примечание
        public static final String COLUMN_NAME_NOTE="note"; //Общее примечание


        //создаем запрос для создания таблицы оборудования
        /*public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ("+
                EquipmentClass._ID + " INTEGER PRIMARY KEY"+
                COMMA_SEP+
                EquipmentClass.COLUMN_NAME_TITLE +TYPE_TEXT+" )";/Этот код был для создания столбца ID,
                но все равно создается автоматически столбец rowid, поэтому я его закомментировал*/
        public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ("+
                CheckListDBTableClass.COLUMN_NAME_OBJECT + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MONTH + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_TEN_DAY_PERIOD + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CATEGORY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_TITLE + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_SERIAL + TYPE_TEXT + COMMA_SEP +
                //maintenance MT mt - техническое обслуживание
                CheckListDBTableClass.COLUMN_NAME_MT_LETTER + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MT_PLANNED_DATE_OF_WORK + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_EQUIPMENT_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MT_ACT + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MT_ACT_TIGHTNESS_OF_VALVES + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MT_STAMP_INSTALL + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MT_STAMP_JOURNAL_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MT_ACT_SYBERVISOR_ADMIN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MT_SCAN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MT_NOTE + TYPE_TEXT + COMMA_SEP +
                //MDM mdm - монтаж демонтаж
                CheckListDBTableClass.COLUMN_NAME_MDM_TITLE + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_ACT + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_ACT_TIGHTNESS_OF_VALVES + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_CMCSI + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_9_EQUIPMENT_SMAO_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_PPD_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_LIST_REGISTRATION_EQUIPMENT_PPD_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_ARM_PSP + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_CORRECTION_THERMOMETERS + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_HOURS_WORK_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_ACT_ENTER + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_ACT_OUTPUT + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_JOURNAL_EQUIPMENT_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_SCAN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_MDM_NOTE + TYPE_TEXT + COMMA_SEP +
                //verification of a measuring instrument (VMI) - Поверка (РМГ 29)
                CheckListDBTableClass.COLUMN_NAME_VMI_CERTIFICATE + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_VMI_JOURNAL_EQUIPMENT_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_VMI_STAMP_INSTALL + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_VMI_ARM_PSP + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_VMI_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_VMI_ACT_SYBERVISOR_ADMIN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_VMI_STAMP_JOURNAL_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_VMI_ACT_TIGHTNESS_OF_VALVES + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_VMI_LISTING_MF + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_VMI_SCAN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_VMI_NOTE + TYPE_TEXT + COMMA_SEP +
                //crash - авария, отказ, инциндент
                CheckListDBTableClass.COLUMN_NAME_CRASH_ACT + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_DISMANTLING + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_MOUNTING + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_CRASH_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_SMAO_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_9_EQUIPMENT_SMAO_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_SYBERVISOR_ADMIN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_ARM_PSP + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_CORRECTION_THERMOMETERS + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_LISTING_MF + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_HOURS_WORK_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_ACT_ENTER + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_ACT_OUTPUT + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_JOURNAL_EQUIPMENT_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_SCAN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_ANALYSIS + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_CRASH_NOTE + TYPE_TEXT + COMMA_SEP +
                //storage - хранение
                CheckListDBTableClass.COLUMN_NAME_STORAGE_ACT_ENTER + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_STORAGE_ACT_OUTPUT + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_STORAGE_JOURNAL_EQUIPMENT_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_STORAGE_SCAN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_STORAGE_NOTE + TYPE_TEXT + COMMA_SEP +
                //repair - ремонт
                CheckListDBTableClass.COLUMN_NAME_REPAIR_ACT + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_REPAIR_JOURNAL_EQUIPMENT_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_REPAIR_JOURNAL_REPAIR_ENTRY + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_REPAIR_SCAN + TYPE_TEXT + COMMA_SEP +
                CheckListDBTableClass.COLUMN_NAME_REPAIR_NOTE + TYPE_TEXT + COMMA_SEP +
                //note - Общее примечание
                CheckListDBTableClass.COLUMN_NAME_NOTE + TYPE_TEXT + " )";
        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + CheckListDBTableClass.TABLE_NAME;

        public static final String WHERE_O =CheckListDBTableClass.COLUMN_NAME_MT_LETTER +" = 'O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MT_PLANNED_DATE_OF_WORK +" = 'O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY +" = 'O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_EQUIPMENT_ENTRY+" = 'O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MT_ACT+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MT_ACT_TIGHTNESS_OF_VALVES+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MT_STAMP_INSTALL+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MT_STAMP_JOURNAL_ENTRY+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MT_ACT_SYBERVISOR_ADMIN+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MT_SCAN+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MT_NOTE+"='O' OR "+
                //MDM mdm - монтаж демонтаж
                //public static final String COLUMN_NAME_MDM_="mdm_"; //
                CheckListDBTableClass.COLUMN_NAME_MDM_TITLE+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_ENTRY+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_ACT+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_ACT_TIGHTNESS_OF_VALVES+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_CMCSI+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_ENTRY+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_9_EQUIPMENT_SMAO_ENTRY+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_PPD_ENTRY+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_LIST_REGISTRATION_EQUIPMENT_PPD_ENTRY+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_ARM_PSP+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_CORRECTION_THERMOMETERS+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_HOURS_WORK_ENTRY+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_ACT_ENTER+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_ACT_OUTPUT+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_JOURNAL_EQUIPMENT_ENTRY+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_SCAN+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_MDM_NOTE+"='O' OR "+
                //verification of a measuring instrument (VMI) - Поверка (РМГ 29)
                //public static final String COLUMN_NAME_VMI_="vmi_"; //
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_CERTIFICATE+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_JOURNAL_EQUIPMENT_ENTRY+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_STAMP_INSTALL+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_ARM_PSP+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_ACT_SYBERVISOR_ADMIN+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_STAMP_JOURNAL_ENTRY+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_ACT_TIGHTNESS_OF_VALVES+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_LISTING_MF+"='O'OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_SCAN+"='O'OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_NOTE+"='O' OR "+
                //crash - авария, отказ, инциндент 18 пунктов @string/vMINote
                //public static final String COLUMN_NAME_CRASH_="crash_"; //
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_DISMANTLING+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_MOUNTING+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_CRASH_ENTRY+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_SMAO_ENTRY+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_9_EQUIPMENT_SMAO_ENTRY+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_ENTRY+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_SYBERVISOR_ADMIN+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ARM_PSP+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_CORRECTION_THERMOMETERS+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_LISTING_MF+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_HOURS_WORK_ENTRY+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_ACT_ENTER+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_ACT_OUTPUT+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_JOURNAL_EQUIPMENT_ENTRY+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_CRASH_SCAN+"='O' OR "+
                CheckListDBTableClass.COLUMN_NAME_CRASH_ANALYSIS+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_NOTE+"='O' OR "+
                //storage - хранение
                //public static final String COLUMN_NAME_STORAGE_="storage_"; //
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_ACT_ENTER+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_ACT_OUTPUT+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_JOURNAL_EQUIPMENT_ENTRY+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_SCAN+"='O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_NOTE+"='O' OR "+
                //repair - ремонт
                //public static final String COLUMN_NAME_REPAIR_="repair_"; //
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_ACT+" = 'O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_JOURNAL_EQUIPMENT_ENTRY+" = 'O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_JOURNAL_REPAIR_ENTRY+" = 'O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_SCAN+" = 'O' OR "+
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_NOTE+" = 'O' OR "+
                //note - Общее примечание
                DataBaseContract.CheckListDBTableClass.COLUMN_NAME_NOTE+"='O'";

        public static final String SELECT_OBJECT_WHERE_O = "SELECT DISTINCT "
                + CheckListDBTableClass.COLUMN_NAME_OBJECT
                + " FROM "
                + CheckListDBTableClass.TABLE_NAME
                + " WHERE "
                +WHERE_O;


    }
    //Блок 4 (конец): Создаем класс CheckListDBTable

    //Блок 5(н): Создаем класс Position для создания таблицы с позиционными номерами
    public static abstract class Location implements BaseColumns {
        public static final String TABLE_NAME = "location";
        //public static final String _ID = "id";//в приципе это лишнее но посмотрим, что получится,
        // это не обязательно, автоматически создается ID столбец с именем rowid
        public static final String COLUMN_NAME_OBJECT ="object";
        public static final String COLUMN_NAME_LOCATION ="location";

        //создаем запрос для создания таблицы оборудования
        public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ("+
                Location.COLUMN_NAME_OBJECT +TYPE_TEXT+COMMA_SEP +
                Location.COLUMN_NAME_LOCATION +TYPE_TEXT+" )";
        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + Location.TABLE_NAME;

    }
    //Блок 5(к): Создаем класс Position для создания таблицы с позиционными номерами

    //Блок 5(н): Создаем класс Position для создания таблицы с позиционными номерами
    public static abstract class Object implements BaseColumns {
        public static final String TABLE_NAME = "object";
        //public static final String _ID = "id";//в приципе это лишнее но посмотрим, что получится,
        // это не обязательно, автоматически создается ID столбец с именем rowid
        public static final String COLUMN_NAME_OBJECT ="object";

        //создаем запрос для создания таблицы оборудования
        public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ("+
                Object.COLUMN_NAME_OBJECT +TYPE_TEXT+" )";
        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + Object.TABLE_NAME;

    }
    //Блок 5(к): Создаем класс Position для создания таблицы с позиционными номерами
}
