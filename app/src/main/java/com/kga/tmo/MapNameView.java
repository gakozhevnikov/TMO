package com.kga.tmo;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by home on 09.06.2018.
 */


public class MapNameView extends HashMap<String, String> {

    private static final String tagLog = "MyLog";
    MapNameView(Context context){
        //Log.d(tagLog, "MapNameView.MapNameView.1:"+context);
        //Mt
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_LETTER, context.getString(R.string.mtLetter));
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_PLANNED_DATE_OF_WORK,context.getString(R.string.mtPlannedDateOfWork));
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY,context.getString(R.string.mtJournalMtEntry));
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_EQUIPMENT_ENTRY,context.getString(R.string.mtJournalEquipmentEntry));
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_ACT,context.getString(R.string.mtAct));
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_ACT_TIGHTNESS_OF_VALVES,context.getString(R.string.mtActTightnessOfValves));
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_STAMP_INSTALL,context.getString(R.string.mtStampInstall));
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_STAMP_JOURNAL_ENTRY,context.getString(R.string.mtStampJournalEntry));
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN,context.getString(R.string.mtJournalMtEntrySybervisorAdmin));
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_ACT_SYBERVISOR_ADMIN,context.getString(R.string.mtActSybervisorAdmin));
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_SCAN,context.getString(R.string.mtScan));
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_NOTE,context.getString(R.string.mtNote));
        //MDM mdm - монтаж демонтаж 17 пунктов
        //public static final String COLUMN_NAME_MDM_="mdm_"; //
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_TITLE,context.getString(R.string.mDMTitle)); //наименование операции монтаж или демонтаж
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_ENTRY ,context.getString(R.string.mDMJournalEntry)); //journal_entry-запись в журнале
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_ACT,context.getString(R.string.mDMAct)); //Акт
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_ACT_TIGHTNESS_OF_VALVES,context.getString(R.string.mDMActTightnessOfValves));//tightness of valves - акт герметичности запорной арматуры
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_CMCSI,context.getString(R.string.mDM_CMCSI)); //control of metrological characteristics-контроль метрологических характеристик СИ
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_ENTRY,context.getString(R.string.mDMJournalEquipmentEntry)); //journal_equipment-формуляр, _entry запись в формуляре
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_9_EQUIPMENT_SMAO_ENTRY,context.getString(R.string.mDMJournal9EquipmentSMAO_Entry)); //journal_equipment-формуляр, _entry запись в формуляре, system for measuring amount of oil smao система измерения количества нефти, раздел 9
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_PPD_ENTRY,context.getString(R.string.mDMJournalEquipmentPPD_Entry)); //journal_equipment-формуляр, _entry запись в формуляре, pipe pusher device, труба толкатель устройство трубопоршневая установка ТПУ
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_LIST_REGISTRATION_EQUIPMENT_PPD_ENTRY,context.getString(R.string.mDMListRegistrationJournalEquipmentPPD_Entry)); //journal_equipment-формуляр, _entry запись в формуляре, pipe pusher device, труба толкатель устройство трубопоршневая установка ТПУ
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_ARM_PSP,context.getString(R.string.mDM_ARM_PSP)); //арм псп
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_CORRECTION_THERMOMETERS,context.getString(R.string.mDMCorrectionThermometers)); //correction (РМГ29) thermometers - поправки термометров
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_EQUIPMENT_HOURS_WORK_ENTRY,context.getString(R.string.mDMJournalEquipmentHoursWorkEntry)); //the number of hours of work - отметка в формуляре оборудования о количестве часов работы
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_ACT_ENTER,context.getString(R.string.mDMStorageActEnter)); //Акт ввода на хранение при демонтаже
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_ACT_OUTPUT,context.getString(R.string.mDMStorageActOutput)); //Акт вывода из хранения при монтаже
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_STORAGE_JOURNAL_EQUIPMENT_ENTRY,context.getString(R.string.mDMStorageJournalEquipmentEntry)); //Запись в формуляре о хранении
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_SCAN,context.getString(R.string.mDMScan)); //Запись в формуляре о хранении
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_NOTE,context.getString(R.string.mDMNote)); //Примечание

        //verification of a measuring instrument (VMI) - Поверка (РМГ 29) 11 пунктов
        //public static final String COLUMN_NAME_VMI_="vmi_"; //
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_CERTIFICATE,context.getString(R.string.vMICertificate)); //проверка данных свидетельства о поверке
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_JOURNAL_EQUIPMENT_ENTRY,context.getString(R.string.vMIJournalEquipmentEntry)); // отметка о поверке в формуляре
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_STAMP_INSTALL,context.getString(R.string.vMIStampInstall)); //установка пломбы
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_ARM_PSP,context.getString(R.string.vMI_ARM_PSP)); //введение данных о поверке в АРМ ПСП
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN,context.getString(R.string.vMIJournalMtEntrySybervisorAdmin)); //Запись о входе на 3(администратор) уровень в Sybervisore
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_ACT_SYBERVISOR_ADMIN,context.getString(R.string.vMIActSybervisorAdmin)); //Акт или запись в акте о входе на 3(администратор) уровень в Sybervisore
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_STAMP_JOURNAL_ENTRY,context.getString(R.string.vMIStampJournalEntry));// Оттиск клейма или пломбы на листе образцов в журнале снятия и установки пломб
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_ACT_TIGHTNESS_OF_VALVES,context.getString(R.string.vMIActTightnessOfValves)); //Акт герметичности запорной арматуры при поверки ТПР
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_LISTING_MF,context.getString(R.string.vMIListingMf));//Распечатка МФ ТПР после ввода коэффициентов
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_SCAN,context.getString(R.string.vMIScan));//Распечатка МФ ТПР после ввода коэффициентов
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_NOTE,context.getString(R.string.vMINote)); //Примечание

        //crash - авария, отказ, инциндент 20 пунктов @string/vMINote
        //public static final String COLUMN_NAME_CRASH_="crash_"; //
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT,context.getString(R.string.crashAct)); //Акт о сбое
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_DISMANTLING,context.getString(R.string.crashActDismantling)); //Акт о снятии
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_MOUNTING,context.getString(R.string.crashActMounting)); //Акт о установке
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY,context.getString(R.string.crashJournalMtEntry)); //Запись в журнале тех.обслуживания об аварии, сбое, отказа, инциндента
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_CRASH_ENTRY,context.getString(R.string.crashJournalCrashEntry)); //Запись в журнале отказов СИ СИКН об аварии, сбое, отказа, инциндента
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_SMAO_ENTRY,context.getString(R.string.crashJournalEquipmentSmaoEntry)); //Запись об аварии в формуляре СИКН В РАЗДЕЛЕ УЧЕТА
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_9_EQUIPMENT_SMAO_ENTRY,context.getString(R.string.crashJournal9EquipmentSmaoEntry)); //Запись об аварии в формуляре СИКН при замене
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_ENTRY,context.getString(R.string.crashJournalEquipmentEntry)); //Запись в формуляре оборудования
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN,context.getString(R.string.crashJournalMtEntrySybervisorAdmin)); //Запись о входе на 3(администратор) уровень в Sybervisore
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ACT_SYBERVISOR_ADMIN,context.getString(R.string.crashActSybervisorAdmin)); //Акт или запись в акте о входе на 3(администратор) уровень в Sybervisore
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ARM_PSP,context.getString(R.string.crashArmPsp)); //Замена оборудования в АРМ ПСП
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_CORRECTION_THERMOMETERS,context.getString(R.string.crashCorrectionThermometers)); //correction (РМГ29) thermometers - поправки термометров
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_LISTING_MF,context.getString(R.string.crashListingMf));//Распечатка МФ
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_EQUIPMENT_HOURS_WORK_ENTRY,context.getString(R.string.crashJournalEquipmentHoursWorkEntry)); // отметка в формуляре оборудования о количестве часов работы
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_ACT_ENTER,context.getString(R.string.crashStorageActEnter)); //Акт ввода на хранение
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_ACT_OUTPUT,context.getString(R.string.crashStorageActOutput)); //Акт вывода из хранения
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_STORAGE_JOURNAL_EQUIPMENT_ENTRY,context.getString(R.string.crashStorageJournalEquipmentEntry)); //Запись в формуляре о хранении
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_SCAN,context.getString(R.string.crashScan)); //Запись в формуляре о хранении
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_ANALYSIS,context.getString(R.string.crashAnalysis)); //Запись в формуляре о хранении
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_NOTE,context.getString(R.string.crashNote)); //Примечание

        //storage - хранение
        //public static final String COLUMN_NAME_STORAGE_="storage_"; //
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_ACT_ENTER,context.getString(R.string.storageActEnter)); //Акт ввода на хранение
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_ACT_OUTPUT,context.getString(R.string.storageActOutput)); //Акт вывода из хранения
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_JOURNAL_EQUIPMENT_ENTRY,context.getString(R.string.storageJournalEquipmentEntry)); //Запись в формуляре о хранении
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_SCAN,context.getString(R.string.storageScan)); //Запись в формуляре о хранении
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_NOTE,context.getString(R.string.storageNote)); //Примечание

        //repair - ремонт
        //public static final String COLUMN_NAME_REPAIR_="repair_"; //
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_ACT,context.getString(R.string.repairAct)); //Акт о ремонте
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_JOURNAL_EQUIPMENT_ENTRY,context.getString(R.string.repairJournalEquipmentEntry)); //Запись о ремонте в формуляре
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_JOURNAL_REPAIR_ENTRY,context.getString(R.string.repairJournalRepairEntry)); //Запись в журнале ремонтов оборудования
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_SCAN,context.getString(R.string.repairScan)); //Запись в журнале ремонтов оборудования
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_NOTE,context.getString(R.string.repairNote)); //

        //note - Общее примечание
        put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_NOTE,context.getString(R.string.note)); //Общее примечание


        //put(DataBaseContract.CheckListDBTableClass.,);


    }

    @Override
    public String put(String key, String value) {
        return super.put(key, value);
    }

    @Override
    public String get(Object key) {
        return super.get(key);
    }
}
