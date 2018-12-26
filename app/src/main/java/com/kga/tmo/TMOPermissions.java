package com.kga.tmo;

//Блок 0(н): объявление переменных

//Блок 0(к): объявление переменных

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by ASUS on 07.03.2018.
 */

/**Класс для настройки обработки файла с графиком технического обслуживания*/

public class TMOPermissions {
    //Блок 1(н): Объявление переменных
    /**Массив с перечнем разрешений в манифесте */
    private String [] permissions =new String [] {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
    /**Хранит произвольное значение, по которому в дальнейшем можно определить, на какой запрос разрешения вам пришел ответ*/
    private static final int PERMISSION_REQUEST_CODE = 1755;
    /**Контекст класса TMOPermissions*/
    private Context contextTmoPermissions;
    /**Активити приложения для проверки разрешений приложения*/
    private android.app.Activity activityCompatTMOPermissions;
    /**Переменная для отладки, фильтрация по имени переменной при отладке.*/
    private static final String tagLog = "MyLog";
    ////Log.d(tagLog, "onCreateOptionsMenu");
    //Блок 1(к): Объявление переменных

    //Блок 2(н): Конструктор с передачей классу ContextActivity
    /**Конструктор с передачей классу Context и Activity
     *@param context Контекст передаваемый классу
     * @param activityCompat Активити передаваемое классу
     */
    public TMOPermissions(Context context, android.app.Activity activityCompat){
        activityCompatTMOPermissions =activityCompat;
        contextTmoPermissions = context;
    }
    //Блок 2(к): Конструктор с передачей классу Context и Activity

    //Блок 3(н): Проверка разрешений
     /**Метод проверяющий есть ли разрешения. В методе циклом перебераются разрешения в массиве из
      * манифеста и сравниваются с разрешениями которые предоставил пользователь. Если разрешения есть возвращается true, если нет false
     * */
    boolean hasPermissions (){
        //Log.d(tagLog, "hasPermissions");
        int res =0;//Для сравнения значений разрешений с PERMISSION_GRANTED
        for (String perms : permissions){
            res = (ContextCompat.checkSelfPermission(contextTmoPermissions, perms));
            //Log.d(tagLog, "hasPermissions2" + perms+ " *** "+Integer.toString(res)+" PG "+PackageManager.PERMISSION_GRANTED);
            if (!(res == PackageManager.PERMISSION_GRANTED)){
                return false;
            }
        }
        return true;

    }
    //Блок 3(к): Проверка разрешений

    //Блок 4(н): Запрос разрешений
    /**Метод запроса разрешений
     *@see TMOPermissions#hasPermissions()*/

    public void tmoRequestPermissions (){
        if (!hasPermissions()){
            for (String perms : permissions){
                //Log.d(tagLog, "tmoRequestPermissions" + perms.toString()+ " *** ");
                if (ActivityCompat.shouldShowRequestPermissionRationale(activityCompatTMOPermissions,perms)){
                    switch (perms) {
                        case android.Manifest.permission.READ_EXTERNAL_STORAGE:
                            //Log.d(tagLog, "tmoRequestPermissions READ_EXTERNAL_STORAGE" + perms+ " *** ");
                            Toast.makeText(contextTmoPermissions, R.string.read_external_storage, Toast.LENGTH_SHORT).show();
                            break;
                        case android.Manifest.permission.WRITE_EXTERNAL_STORAGE:
                            //Log.d(tagLog, "tmoRequestPermissions WRITE_EXTERNAL_STORAGE" + perms + " *** ");
                            Toast.makeText(contextTmoPermissions, R.string.write_external_storage, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }else {
                    ActivityCompat.requestPermissions(activityCompatTMOPermissions, permissions, PERMISSION_REQUEST_CODE);
                }
            }
        }else {
            //переписать ЧТО ЗА БАиДА ЧТО ЗДЕСЬ ПиСАТЬ иЛи УБРАТЬ
        }

    //Блок 4(к): Запрос разрешений
    }
}
