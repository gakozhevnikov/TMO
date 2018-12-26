package com.kga.tmo;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by home on 23.03.2017.
 */

public class AdapterGridViewTMOFragment extends ArrayAdapter<String> {
    private static final String tagLog = "MyLog";
    private final String[] mContacts;
    private final String stringContentCellDB;
    Context mContext;

    //Конструктор
    public AdapterGridViewTMOFragment(Context context, int textViewResourceId, String[] mContacts, String stringContentCellDB) {
        super(context, textViewResourceId, mContacts);
        //Log.d(tagLog, "0 :" + textViewResourceId);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        this.mContacts = mContacts;
        this.stringContentCellDB = stringContentCellDB;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        //Log.d(tagLog, "AdapterGridViewTMOFragment 1 :"+convertView);
        TextView label = (TextView) convertView;


        if (convertView == null) {
            //Log.d(tagLog, "AdapterGridViewTMOFragment 2 :"+convertView);
            convertView = new TextView(mContext);
            ((TextView) convertView).setTextColor(Color.BLACK);
            label = (TextView) convertView;
        }
        //Log.d(tagLog, "AdapterGridViewTMOFragment 3 :"+position);
        label.setText(mContacts[position]);
        label.setTag(stringContentCellDB);
        if (mContacts[position].equals(stringContentCellDB)){
            //Log.d(tagLog, "AdapterGridViewTMOFragment 4 :"+position);
            convertView.setBackgroundColor(Color.YELLOW);
        }

        return (convertView);
    }

    // возвращает содержимое выделенного элемента списка
    public String getItem(int position) {
        return mContacts[position];
    }
}

