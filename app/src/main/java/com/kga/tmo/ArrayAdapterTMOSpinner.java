package com.kga.tmo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by home on 12.06.2018.
 */

public class ArrayAdapterTMOSpinner extends ArrayAdapter {
    /***/
    int selectedItem = -1;
    Context context;
    public ArrayAdapterTMOSpinner(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource,  objects);
        this.context=context;
    }
    void setSelectedItemTMOSpiner (int selectedItem){
        this.selectedItem=selectedItem;
    }
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

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
