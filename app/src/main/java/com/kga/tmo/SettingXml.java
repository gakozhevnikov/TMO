package com.kga.tmo;

import android.content.res.Resources;
import android.view.View;

/**
 * Created by home on 23.03.2018.
 */
/**Класс в котором будут содержаться параметры настройки.*/
public class SettingXml {
    /**Строковая переменная в которой содержиться ключ
     * или идентификационное обозначение настройки*/
    String key;
    /**Строковая переменная в которой содержиться текст
     * для элемента отображения TextView из ресурса
     * R.string.*/
    String textViewResourse;
    /**Строковая переменная в которой содержиться
     * значение настройки*/
    String value;

    /**Конструктор на вход которому передаются параметры из методов greatLinkedListSettingXmlDefault,
     *  greatLinkedListSettingXmlFromFile класса LinkedListSettingXml.
     * @param key Строковая переменная в которой содержиться ключ
     * или идинтификационное обозначение настройки.
     * @param textViewResourse Строковая переменная в которой содержиться текст
     * для элемента отображения TextView из ресурса
     * R.string.
     * @param value Строковая переменная в которой содержиться
     * значение настройки.
     * @see LinkedListSettingXml#createLinkedListSettingXmlDefault()
     * @see LinkedListSettingXml#createLinkedListSettingXmlFromFile() */
    public SettingXml(String key, String textViewResourse, String value) {
        this.key = key;
        this.textViewResourse = textViewResourse;
        this.value = value;
    }
    /**Метод возвращающий ключ настройки
     * @see SettingXml#key*/
    public String getKey() {
        return key;
    }
    /**Метод возвращающий текст для TextView
     * @see SettingXml#textViewResourse*/
    public String getTextViewResourse() {
        return textViewResourse;
    }
    /**Метод возвращающий значение настройки
     * @see SettingXml#value*/
    public String getValue() {
        return value;
    }
    /**Метод присваивания переменной key класса SettingXml ключа
     * @see SettingXml#key*/
    public void setKey(String key) {
        this.key = key;
    }
    /**Метод присваивания переменной textViewResourse класса SettingXml текста из ресурса R.string
     * @see SettingXml#textViewResourse*/
    public void setTextViewResourse(String textViewResourse) {
        this.textViewResourse = textViewResourse;
    }
    /**Метод присваивания переменной value класса SettingXml значения
     * @see SettingXml#value*/
    public void setValue(String value) {
        this.value = value;
    }


}
