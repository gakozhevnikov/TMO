package com.kga.tmo;

/**
 * Created by ASUS on 05.03.2018.
 */

//Блок 0(н): объявление переменных

//Блок 0(к): объявление переменных

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//import android.app.Fragment;

/**Класс для настройки обработки файла с графиком технического обслуживания*/

public class Setting extends Fragment{
    //Блок 1(н): объявление переменных

    /**Для фильтрации Log при отладки */                                    private static final String tagLog = "MyLog";
    /**Количество одноименного, однотипного оборудования в графике*/        int countTitleEquipment;
    /**Количество видов технического обслуживания*/                         int countTypesTO;
    /**Начальная строка с которой начинается чтение Excel файла*/           int startRow;
    /**Конечная строка на которой заканчивается чтение Excel файла*/        int endRow;
    /**Номер столбца в котором перечисляется наименование оборудования*/    int columnTitle;
    /**Номер столбца в котором перечисляется индентификационное обозначение.
     * Параметр по которому можно отличить этот экземпляр оборудования от другого,
     * серийный, заводской и т.д.*/                     int columnSerial;
    /**Номер столбца соответствующий месяцу Январь*/    int columnJanuary;
    /**Номер столбца соответствующий месяцу Февраль*/   int columnFebruary;
    /**Номер столбца соответствующий месяцу Март*/      int columnMarch;
    /**Номер столбца соответствующий месяцу Апрель*/    int columnApril;
    /**Номер столбца соответствующий месяцу Май*/       int columnMay;
    /**Номер столбца соответствующий месяцу июнь*/      int columnJune;
    /**Номер столбца соответствующий месяцу июль*/      int columnJuly;
    /**Номер столбца соответствующий месяцу Август*/    int columnAugust;
    /**Номер столбца соответствующий месяцу Сентябрь*/  int columnSeptember;
    /**Номер столбца соответствующий месяцу Октябрь*/   int columnOctober;
    /**Номер столбца соответствующий месяцу Ноябрь*/    int columnNovember;
    /**Номер столбца соответствующий месяцу Декабрь*/   int columnDecember; //18
    /**Переменная типа File которая хранит путь к файлам*/
    File directory = Environment.getExternalStoragePublicDirectory(Environment.getExternalStorageState());
    /**Лайоут-контейнер в котором будут создаваться элементы управления*/
    FrameLayout frameLayoutSetting;
    /**Контейнер с вертикальной прокруткой. В этот контейнер помещается TableLayout
     * @see Setting#tableLayoutSetting*/
    ScrollView scrollViewSetting;
    /**Просто View*/
    View settingView;
    /**Просто Context применяемый с методом getActivity*/
    Context contextSetting;
    /**Табличный лайаут для фрагмента с настройками в котором будут две колонки: первая колонка с
     * TextView с текстом для чего данная настройка, вторая колонка c NumberPicker, в котором будут
     * отображаться и меняться значения настроек*/
    TableLayout tableLayoutSetting;
    /**Параметры размеров для TextView для нормального отображения в TableRow, три параметра:<br/>
     * int width (ширина) - устанавливается равным нулю (0);<br/>
     * int height (высота) - устанавливается WRAP_CONTENT;<br/>
     * float initWeight (вес) - для TextView  установлен 0.7f. Этот атрибут присваивает значение «важности» view с точки зрения того, сколько места оно должно занимать на экране. Большее значение веса позволяет ему расширяться, заполняя все пространство в родительском view. Дочерние view могут устанавливать значение веса, занимая место во view group пропорционально их весу. */
    TableRow.LayoutParams tableRowParam0WCF07;
    /**Параметры размеров для NumberPicker для нормального отображения в TableRow, три параметра:<br/>
     * int width (ширина) - устанавливается равным нулю (0);<br/>
     * int height (высота) - устанавливается WRAP_CONTENT;<br/>
     * float initWeight (вес) - для TextView  установлен 0.3f. Этот атрибут присваивает значение «важности» view с точки зрения того, сколько места оно должно занимать на экране. Большее значение веса позволяет ему расширяться, заполняя все пространство в родительском view. Дочерние view могут устанавливать значение веса, занимая место во view group пропорционально их весу. */
    TableRow.LayoutParams tableRowParam0WCF03;

    /**Экземпляр класса для работы с файлом настройки.*/TMOFile tmoFile = new TMOFile();
    /**Карта - набор пар объектов ключ-значение:
     * имя тега в файле setting.xml, значение тега*/ Map<String, Integer> readHashMapSetting;
     /**LinkedListSettingXml в который будет
      * передоваться LinkedListSettingXml из
      * MainActivity (ПОДУМАТЬ ПРО ДОСТУП иЗ
      * Setting.java КАК К ГЛОБАЛЬНОЙ ПЕРЕМЕННОЙ)*/  LinkedListSettingXml linkedListSettingXml;


    //Блок 1(к): объявление переменных


    //Блок 2(н): метод onCreate
    /**Переопределенный метод onCreate от Fragment
     * @param savedInstanceState savedInstanceState*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //**Блок 2(к): метод onCreate

    //**Блок 3(н): метод onCreateView*/
    /**Переопределенный метод onCreateView от фрагмента
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle*/

    /**onCreateView
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState  savedInstanceState*/
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //Блок 3.1(н): объявление переменных и настройка параметров
        contextSetting = getActivity();//Этот и другие 2 параметра для работы в SQL инициализируются здесь т.к. в других местах уменя не получалось
        settingView = inflater.inflate(R.layout.fragment_setting, container,false);
        //Log.d(tagLog, "onCreateView:context"+contextSetting);
        //Эти параметры пока временные, если по окончании проекта они будут не востребованы, то удалить, кроме tableRowParam0WCF07, tableRowParam0WCF03
        LinearLayout.LayoutParams linLayoutParamMatchParent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);//(ширина(width), высота(height))
        LinearLayout.LayoutParams linLayoutParamWrapContent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams linLayoutParamMPWC = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TableRow.LayoutParams tableRowParamMatchParent = new TableRow.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);//(ширина, высота)
        TableRow.LayoutParams tableRowParamWrapContent = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams tableRowParamMPWC = new TableRow.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams tableRowParamWCMP = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams tableRowParam0WC = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams tableRowParamMatchParentF = new TableRow.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,0.5f);//(ширина, высота)
        TableRow.LayoutParams tableRowParamWrapContentF = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.5f);//(ширина, высота)
        TableRow.LayoutParams tableRowParamMPWCF = new TableRow.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.5f);//(ширина, высота)
        tableRowParam0WCF07 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,0.8f);//(ширина, высота)
        tableRowParam0WCF03 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,0.2f);//(ширина, высота)
        TableRow.LayoutParams tableRowParamWC0F = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 0,0.5f);//(ширина, высота)

        android.app.Activity activityCompatTMOSetting;

        frameLayoutSetting = (FrameLayout) settingView.findViewById(R.id.fragment_setting);
        scrollViewSetting = new ScrollView(contextSetting);
        tableLayoutSetting = new TableLayout(contextSetting);
        tableLayoutSetting.setStretchAllColumns(true);//для растягивания всех столбцов
        tableLayoutSetting.setShrinkAllColumns(true);//для сокращения всех столбцов
        readHashMapSetting = new HashMap<String, Integer>();

        //tmoFile.readSetting();

        //Блок 3.1(к): объявление переменныхи настройка параметров


        //Блок 3.2(н): Создание объекта LinkedListSettingXml для дальнейшего построения View
        //Эти действия проводяться здесь т.к. в этом методе передаётся Context который необходим для обращения к строковым ресурсам с текстом названия настроек для TextView
        //Log.d(tagLog, "Setting.onCreate:Блок 3.3(н):"+contextSetting);
        linkedListSettingXml = new LinkedListSettingXml(contextSetting);
        linkedListSettingXml.createLinkedListSettingXml();
        //Создаем файл с настройками если его нет на основе созданного LinkedListSettingXml
        if(!tmoFile.isExistFileSetting()){
            //Log.d(tagLog, "Setting.onCreate:Блок 3.3(н):greatFileSetting");
            boolean resultGreatFileSetting = tmoFile.createFileSetting(linkedListSettingXml);
            //Log.d(tagLog, "Setting.onCreate:greatFileSetting"+resultGreatFileSetting);
        }

        //Блок 3.2(к): Создание объекта LinkedListSettingXml для дальнейшего построения View

        //Блок 3.3(н): Заполнение фрагмента элементами через цикл
        //Log.d(tagLog, "onCreateView");
        Iterator<SettingXml> iterator = linkedListSettingXml.iterator();
        //Log.d(tagLog, "onCreateViewSet Iterator");
        while (iterator.hasNext()){
            SettingXml settingXml=iterator.next();
            TableRow tableRowSetting= new TableRow(contextSetting);
            tableRowSetting.setGravity(Gravity.CENTER);
            TextView textViewSetting = new TextView(contextSetting);//TextView в котором будут отображаться наименование настройки
            textViewSetting.setText(settingXml.getTextViewResourse());
            tableRowSetting.addView(textViewSetting,tableRowParam0WCF07);
            EditText EditTextSettingValue = new EditText(contextSetting);//EditText в котором буду отображаться и вводиться настройки
            EditTextSettingValue.setInputType(InputType.TYPE_CLASS_NUMBER);//Устанавливаем тип ввода: цифры
            EditTextSettingValue.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);//Устанвливаем выравнивание по горизонтали и вертикали
            EditTextSettingValue.setText(settingXml.getValue());
            EditTextSettingValue.setTag(settingXml);//помещаем или передаем ссылку в tag settingXml соответствующий данному EditText
            EditTextSettingValue.setOnKeyListener(OnKeyListenerEditTextSettingValue);
            //Log.d(tagLog, "Setting:onCreateViewSet EditTextSettingValue.setTag:"+EditTextSettingValue.getTag());
            tableRowSetting.addView(EditTextSettingValue,tableRowParam0WCF03);

            tableLayoutSetting.addView(tableRowSetting);
            //Log.d(tagLog, "onCreateViewSet Iterator2:"+settingXml.getKey()+"-"+settingXml.getTextViewResourse()+"-"+settingXml.getValue());
        }
        scrollViewSetting.addView(tableLayoutSetting);
        frameLayoutSetting.addView(scrollViewSetting);
        //Блок 3.3(к): Заполнение фрагмента элементами через цикл

        //Log.d(tagLog, "onCreateView after readHashMapSetting: " +tmoFile.readHashMapSetting.toString());

        //return super.onCreateView(inflater, container, savedInstanceState); этот код автоматический и его надо заменить на return settingView;
        return settingView;
    }
    //**Блок 3(к): метод onCreateView*/


    //Блок 4(н): Интерфейс события для EditTextSettingValue
    /**Обработчик события для EditTextSettingValue*/
    View.OnKeyListener OnKeyListenerEditTextSettingValue =new View.OnKeyListener(){

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if (event.getAction()==KeyEvent.ACTION_DOWN &event.getKeyCode()==KeyEvent.KEYCODE_ENTER){
                Log.d(tagLog, "Setting.OnKeyListenerEditTextSettingValue,Блок 4,1:");
                EditText editTextOnKeyListener = (EditText)v;//Приводим View v к типу EditText
                Log.d(tagLog, "Setting.OnKeyListenerEditTextSettingValue, Блок 4,2:"+editTextOnKeyListener.getText().toString());
                SettingXml settingXmlOnKeyListernerEditText =  (SettingXml) v.getTag();//передача объекта или ссылки на объект из тэга EditText
                Iterator<SettingXml> iterator = linkedListSettingXml.iterator();
                while (iterator.hasNext()){
                    SettingXml settingXmlOnKeyListernerLL=iterator.next();
                    if (settingXmlOnKeyListernerLL.getKey()==settingXmlOnKeyListernerEditText.getKey()){//Сравниваем значания ключей в переданном теге EditText с ключем в SettingXml в linkedListSettingXml

                        settingXmlOnKeyListernerLL.setValue(editTextOnKeyListener.getText().toString());//Меняем значение в SettingXml в linkedListSettingXml
                    }
                }
                Log.d(tagLog, "Setting.OnKeyListenerEditTextSettingValue,Блок 4,3:"+linkedListSettingXml);
                /*
                Iterator<SettingXml> iterator1 = linkedListSettingXml.iterator();
                while (iterator1.hasNext()){
                    SettingXml settingXmlOnKeyListernerLL1=iterator1.next();
                    Log.d(tagLog, "Setting.OnKeyListenerEditTextSettingValue,Блок 4,4:"+settingXmlOnKeyListernerLL1.getValue());
                }*/
                tmoFile.deleteFileSetting();//Удаляем файл с настройками
                tmoFile.createFileSetting(linkedListSettingXml);//Создаем файл с настройками с новыми значениями в linkedListSettingXml
                return true;
            }
            return false;
        }
    };
    //Блок 4(к): Интерфейс события для EditTextSettingValue

}
