package com.kga.tmo;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by home on 31.05.2018.
 */

public class TMOSpinner extends android.support.v7.widget.AppCompatSpinner {
    /**Для фильтрации Log при отладки */private static final String tagLog = "MyLog";
    /***/
    ArrayAdapter<String> adapterSpinner;
    Context context;
    /***/
    List<String> list;
    /***/
    int selectedItem = -1;
    /**Конструктор*/
    public TMOSpinner(Context context) {
        super(context);
        this.context=context;
    }
    /**Методом передаётся индекс выбранного элемента при изменении для выделения выбранного элемента*/
    void setSelectedItemTMOSpiner (int selectedItem){
        this.selectedItem=selectedItem;
    }

    Spinner getTMOSpinner (List<String> list){
        this.list=list;
        Log.d(tagLog, "TMOSpinner.getTMOSpinner,1:"+this.list);
        setSelection(0,false);//элемент, который мы хотим выделить

        adapterSpinner= new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,this.list){

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {//для выделения выбранного элемента
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                if (position == selectedItem) {
                    v.setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));
                }
                else {
                    // for other views
                    v.setBackgroundColor(Color.WHITE);
                }
                return v;
            }
        };
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setAdapter(adapterSpinner);
        //Log.d(tagLog, "TMOSpinner.getTMOSpinner,3:"+spinner);
        return this;
    }

}
