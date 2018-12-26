package com.kga.tmo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by home on 06.10.2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    DataBaseContract dataBaseContract = new DataBaseContract();
    //передаем из класса-контракта в DBHelper имя базы и версию
    public DBHelper(Context context) {

        super(context, DataBaseContract.DATABASE_NAME, null, DataBaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseContract.EquipmentClass.CREATE_TABLE);
        db.execSQL(DataBaseContract.GraphDBTableClass.CREATE_TABLE);
        db.execSQL(DataBaseContract.TypesTOClass.CREATE_TABLE);
        db.execSQL(DataBaseContract.CheckListDBTableClass.CREATE_TABLE);
        db.execSQL(DataBaseContract.Location.CREATE_TABLE);
        db.execSQL(DataBaseContract.Object.CREATE_TABLE);
    }
    //В методе onUpgrade сначала удаляем таблицу, затем снова создаем таблицу.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataBaseContract.EquipmentClass.DELETE_TABLE);
        db.execSQL(DataBaseContract.GraphDBTableClass.DELETE_TABLE);
        db.execSQL(DataBaseContract.TypesTOClass.DELETE_TABLE);
        db.execSQL(DataBaseContract.CheckListDBTableClass.DELETE_TABLE);
        db.execSQL(DataBaseContract.Location.DELETE_TABLE);
        onCreate(db);
    }
    public void onUpdate(SQLiteDatabase db) {
        db.execSQL(DataBaseContract.EquipmentClass.DELETE_TABLE);
        db.execSQL(DataBaseContract.GraphDBTableClass.DELETE_TABLE);
        db.execSQL(DataBaseContract.TypesTOClass.DELETE_TABLE);
        db.execSQL(DataBaseContract.CheckListDBTableClass.DELETE_TABLE);
        db.execSQL(DataBaseContract.Location.DELETE_TABLE);
        db.execSQL(DataBaseContract.Object.DELETE_TABLE);
        onCreate(db);
    }

}
