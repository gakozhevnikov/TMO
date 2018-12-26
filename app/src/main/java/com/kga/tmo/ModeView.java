package com.kga.tmo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by home on 31.05.2018.
 */
/**View для режимов*/
public class ModeView extends TableRow {
    //Блок 1(н):Объявление переменных
    /**Для фильтрации Log при отладки */private static final String tagLog = "MyLog";

    Context context;
    /**LinearLayout*/
    LinearLayout linearLayout;
    /**linLayoutParamMatchParent*/
    LinearLayout.LayoutParams linLayoutParamMatchParent;
    /**linLayoutParamWrapContent*/
    LinearLayout.LayoutParams linLayoutParamWrapContent;
    /**linLayoutParamMPWC*/
    LinearLayout.LayoutParams linLayoutParamMPWC;
    /***/
    TableRow.LayoutParams tableRowParam0WCF07;//(ширина, высота)
    /***/
    TableRow.LayoutParams tableRowParam0WCF03;//(ширина, высота)
    /***/
    TableRow.LayoutParams tableRowParam0WCF08;//(ширина, высота)
    /***/
    TableRow.LayoutParams tableRowParam0WCF02;//(ширина, высота)
    /***/
    TableRow.LayoutParams tableRowParam0WCF06;//(ширина, высота)
    /***/
    TableRow.LayoutParams tableRowParam0WCF04;//(ширина, высота)
    /***/
    TableRow.LayoutParams tableRowParam0WCF05;//(ширина, высота)
    /***/
    TableRow tableRow;
    /***/
    TableRow tableRowView;
    /***/
    TableRow tableRowMode;
    /***/
    TextView textViewMode;
    /***/
    TextView textViewView;
    /**Спинер с выбором вида*/
    TMOSpinner spinnerView;
    /**Спинер с выбором режимов*/
    TMOSpinner spinnerMode;
    /**Объzвлен константой для доступа к списку в ListCursorDB*/
    public static final List<String> LIST_SPINNER_MODE = Arrays.asList("Обычный","О журнала ТО","O","V","+","-","X");//через Arrays.asList добавляется массив. У данного способа есть недостаток. Если вы определили массив таким образом, то уже не можете вставлять или удалять другой элемент (методы add() и delete()), хотя при этом можете изменять существующий элемент.
    /**Объzвлен константой для доступа к списку в ListCursorDB*/
    public static final List<String> LIST_SPINNER_VIEW = Arrays.asList("Обычный","Таблица", "Внеплановое");//через Arrays.asList добавляется массив. У данного способа есть недостаток. Если вы определили массив таким образом, то уже не можете вставлять или удалять другой элемент (методы add() и delete()), хотя при этом можете изменять существующий элемент.
    /***/
    ArrayAdapter<String> adapterSpinnerMode;

    /***/
    TMOSpinner modeTMOSpiner;

    //Блок 1(к):Объявление переменных

    //Блок 2(н):Конструктор первый
    /**Конструктор*/
    public ModeView(Context context) {
        //Log.d(tagLog, "ModeView.ModeView,Блок 2,1:");
        super(context);
        linLayoutParamMatchParent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);//(ширина(width), высота(height))
        linLayoutParamWrapContent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linLayoutParamMPWC = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tableRowParam0WCF07 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,0.7f);//(ширина, высота)
        tableRowParam0WCF03 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,0.3f);//(ширина, высота)
        tableRowParam0WCF08 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,0.85f);//(ширина, высота)
        tableRowParam0WCF02 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,0.15f);//(ширина, высота)
        tableRowParam0WCF06 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,0.6f);//(ширина, высота)
        tableRowParam0WCF04 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,0.4f);//(ширина, высота)
        tableRowParam0WCF05 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,0.5f);//(ширина, высота)
        tableRow= new TableRow(context);
        tableRowView = new TableRow(context);
        tableRowMode = new TableRow(context);
        tableRow.setGravity(Gravity.CENTER);
        linearLayout = new LinearLayout(context);
        textViewView= new TextView(context);
        textViewMode = new TextView(context);
        spinnerView= new TMOSpinner(context);
        spinnerMode  = new TMOSpinner(context);
        modeTMOSpiner = new TMOSpinner(context);
        this.context=context;
    }
    //Блок 2(к):Конструктор первый
    /**Конструктор*/
    public ModeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    TableRow getModeView(){
        //Log.d(tagLog, "ModeView.getModeView,1:"+spinnerMode);
        textViewView.setText(context.getString(R.string.mode_view_text_view_view));
        textViewMode.setText(context.getString(R.string.mode_view_text_view_mode));
        //spinnerMode.setSelection(0,false);
        /*adapterSpinnerMode= new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,listSpinnerMode);
        adapterSpinnerMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMode.setAdapter(adapterSpinnerMode);*/
        spinnerView.getTMOSpinner(LIST_SPINNER_VIEW);
        spinnerMode.getTMOSpinner(LIST_SPINNER_MODE);
        //Log.d(tagLog, "ModeView.getModeView,2:"+spinnerMode);
        tableRowView.addView(textViewView,tableRowParam0WCF02);
        tableRowView.addView(spinnerView,tableRowParam0WCF08);
        tableRowMode.addView(textViewMode,tableRowParam0WCF03);
        tableRowMode.addView(spinnerMode,tableRowParam0WCF07);
        tableRow.addView(tableRowView,tableRowParam0WCF05);
        tableRow.addView(tableRowMode,tableRowParam0WCF05);
        //Log.d(tagLog, "ModeView.getModeView,3:");
        return tableRow;

    }

}
