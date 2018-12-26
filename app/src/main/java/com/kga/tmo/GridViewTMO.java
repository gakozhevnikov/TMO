package com.kga.tmo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by home on 11.06.2018.
 */
/***/
public class GridViewTMO extends GridView {
    private static final String tagLog = "MyLog";
    /***/
    Context context;
    /***/
    String columnDB;
    /***/
    List<String> listRowid;
    /***/
    ListCursorDB listCursorDB;
    /***/
    String [] checkSymbolMDM = {"O","Монтаж","Демонтаж"};
    /***/
    String [] checkSymbol = {"O","V", "+", "-", "X"};
    /***/
    int positionBefor = -1;
    /***/
    int positionBeforItem = -1;

    AdapterView.OnItemClickListener OnItemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d(tagLog, "GridViewTMO.onItemClick.1:");
            View BackSelectedItem = null;
            try {
                positionBefor = -1;
                view.setBackgroundColor(Color.RED);
                BackSelectedItem = getChildAt(positionBeforItem);//Разобраться как это работает.
                positionBefor=positionBeforItem;
                positionBeforItem=position;
                if (positionBefor==positionBeforItem & positionBefor==position){
                    positionBefor = -1;
                }
                if (positionBefor != -1) {
                    //Log.d(tagLog, "GridViewTMO.onItemClick.2:"+positionBefor);
                    BackSelectedItem.setSelected(false);
                    BackSelectedItem.setBackgroundColor(Color.WHITE);
                    positionBefor = -1;
                    //Log.d(tagLog, "GridViewTMO.onItemClick.3:"+positionBefor);
                }
                if (columnDB.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_TITLE)) {
                    //Log.d(tagLog, "GridViewTMO.onItemClick.4:"+columnDB+": "+DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_TITLE);
                    listCursorDB.insertContentCellDB(listRowid,columnDB,checkSymbolMDM[position]);
                } else {listCursorDB.insertContentCellDB(listRowid,columnDB,checkSymbol[position]);}
            } catch (Exception e) {
                Log.d(tagLog, "GridViewTMO.onItemClick.1:"+e);
                Toast.makeText(context, context.getString(R.string.toast_grindview_click)+": GridViewTMO.onItemClick.1:", Toast.LENGTH_LONG).show();
            }

        }
    };

    public GridViewTMO(Context context,String columnDB, List<String> listRowId) {
        super(context);
        this.context=context;
        Log.d(tagLog, "GridViewTMO.GridViewTMO.1:");
        String[] checkSymbol;
        listCursorDB = new ListCursorDB(context);
        this.columnDB =columnDB;
        this.listRowid = listRowId;
        if (columnDB.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_TITLE)) {
            checkSymbol = checkSymbolMDM;
        } else {
            checkSymbol =this.checkSymbol;}
        AdapterGridViewTMOFragment adapterGridViewTMO = new AdapterGridViewTMOFragment(context,
                android.R.layout.simple_list_item_1, checkSymbol,listCursorDB.getContent(listRowid,
                columnDB) );
        setAdapter(adapterGridViewTMO);
        setNumColumns(checkSymbol.length);
        setOnItemClickListener(OnItemClickListener);

    }

    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        super.setOnItemClickListener(listener);
    }
}
