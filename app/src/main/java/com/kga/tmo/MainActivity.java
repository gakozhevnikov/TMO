package com.kga.tmo;
/*Библиотеку POI берём с https://poi.apache.org/. После в AndroidStudio копируем в папку Libs, затем нажимаем праввой кнопкой на libs или POI и внизу в меню будет Add Library
* */
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;

//import android.app.FragmentManager;

//Блок 0(н):
//***/
//Блок 0(к):

//Log.d(tagLog, "Класс.метод(конструктор),Блок X,иное"); Формат Лога

public class MainActivity extends AppCompatActivity implements TMOFragment.OnFragmentInteractionListener{
    //Блок 1(н): Объявление переменных

    Setting setting = new Setting();
    TMOFragment tmoFragment = new TMOFragment();
    private static final String tagLog = "MyLog";
    //Log.d(tagLog, "onCreateOptionsMenu");
    //Блок 1.1(н): Переменные для меню (идентификаторы)
    private static final int MENU_CONTROL = Menu.FIRST;
    private static final int MENU_SETTING = Menu.FIRST+1;
    private static final int MENU_DATABASE = Menu.FIRST+2;
    //Блок 1.1(к):Переменные для меню (идентификаторы)
    //создаем новый Helper для базы данных, объявляем переменную класса DBHelper
    //DBHelper mainDBHelper;


    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

    TMOPermissions tmoPermissions = new TMOPermissions(this, this);

    TMOFile tmoFile = new TMOFile();

    File directoryRoot = Environment.getRootDirectory();
    File directoryData = Environment.getDataDirectory();
    File directoryDCD = Environment.getDownloadCacheDirectory();
    File directoryEsD = Environment.getExternalStorageDirectory();//onCreatedirectoryRoot-/system, directoryData-/data, directoryDCD-/cache, directoryEsD-/storage/emulated/0, directoryESPD-/storage/emulated/0/Documents
    File directoryESPD = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
    //File directoryEss = Environment.getExternalStorageState();
    //File directorySS = Environment.getExternalStorageState();
    //Блок 1(к): Объявление переменных

    //Блок 2(н): Метод onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Log.d(tagLog, "onCreate");
        //try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //создаем его экземпляр, наподобии фанандройд урок 34 7:13, почему не знаю
            //mainDBHelper= new DBHelper(this);//удалить  20.07.2018
            tmoPermissions.tmoRequestPermissions();
        //Log.d(tagLog, "MainActivity.onCreate.1:");
            if (tmoPermissions.hasPermissions()){
               //Log.d(tagLog, "MainActivity.onCreate.2:");
                if (!tmoFile.createDirectoryTMO()){//Доработать чтобы директория создавалаясь без перезапуска программы, к примеру сразу после нахатия кнопки разрешить
                    Toast.makeText(this, R.string.error_creat_directory, Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this, R.string.msg_permissions, Toast.LENGTH_LONG).show();
                Toast.makeText(this, R.string.app_restart_then_user_give_permissions, Toast.LENGTH_LONG).show();
            }
            if (!tmoFile.isExistFileGraph()){
                Toast.makeText(this, R.string.toast_file_graph, Toast.LENGTH_LONG).show();
            }
        /*} catch (Exception e) {
            Log.d(tagLog, "MainActivity.onCreate:"+e);
        }*/


    }
    //Блок 2(к): Метод onCreate


    //Блок 3(н): Создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        /*
        - идентификатор группы - позволяет связывать пункт меню с группой других пунктов этого меню
        - идентификатор пункта для обработчика события выбора пункта меню
        - порядок расположения пункта в меню - позволяет определять позицию в меню. По умолчанию (Menu.NONE или 0) пункты идут в том порядке, как задано в коде
        - заголовок - текст, который выводится в пункте меню. Можно использовать строковый ресурс
        */
        if (tmoFile.isExistFileSetting()){//Если файла нет в устройстве то кнопка не будет добавлена.
            menu.add(Menu.NONE,MENU_CONTROL, Menu.NONE, R.string.menu_control);
        }

        menu.add(Menu.NONE,MENU_SETTING, Menu.NONE, R.string.menu_setting);
        if (tmoFile.isExistFileGraph()&tmoFile.isExistFileSetting()){//Если файла нет в устройстве или не создан файл с настройками то кнопка не будет добавлена.
        menu.add(Menu.NONE,MENU_DATABASE, Menu.NONE, R.string.menu_database);
        }
        return super.onCreateOptionsMenu(menu);
    }
    //Блок 3(к): Создание меню

    //Блок 4(н):Обработка нажатия пунктов меню
    /**onOptionsItemSelected
     * @param item*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            //Блок 4.1(н):Обработка нажатия пунктов меню:Выбор кнопки контроль
            case MENU_CONTROL:
                Log.d(tagLog, "Menu control selected");

                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//FragmentTransaction обявлять надо каждый раз т.к. после commit эта FragmentTransaction закрывается
                fragmentTransaction.replace(R.id.linear_layout_main_activity, tmoFragment);//примененил replace для того чтобы не писать код для определения где какой фрагмент.
                fragmentTransaction.commit();
                Log.d(tagLog, "Menu control selected 2");
                return true;
            //Блок 4.1(к):Обработка нажатия пунктов меню:Выбор кнопки контроль

            //Блок 4.2(н):Обработка нажатия пунктов меню:Выбор кнопки настройка
            case MENU_SETTING:

               android.support.v4.app.FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();//FragmentTransaction обявлять надо каждый раз т.к. после commit эта FragmentTransaction закрывается
                fragmentTransaction1.replace(R.id.linear_layout_main_activity, setting);
                fragmentTransaction1.commit();
                Toast.makeText(this, R.string.app_restart_after_setting, Toast.LENGTH_LONG).show();//т.к. файл с настройка изначально отсутствует то после запуска фрагмента надо сделать так чтобы в меню кнопка контроль появилась а это можно сделать перезапуском программы
                return true;
            //Блок 4.2(к):Обработка нажатия пунктов меню:Выбор кнопки настройка

            //Блок 4.3(н):Обработка нажатия пунктов меню:Выбор кнопки контроль
            case MENU_DATABASE:
                Log.d(tagLog, "Menu control selected,Блок 4.3,1:");
                GraphToDataBase graphToDataBase = new GraphToDataBase(this);
                if (graphToDataBase.MainMethod()) {
                    Toast.makeText(this, R.string.toast_main_activity_menu_update_ok, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, R.string.toast_main_activity_menu_update_fault, Toast.LENGTH_LONG).show();
                }

                Log.d(tagLog, "Menu control selected,Блок 4.3,2:"+tmoFile.isExistFileSetting());
                return true;
            //Блок 4.3(к):Обработка нажатия пунктов меню:Выбор кнопки контроль
            default:
                return super.onOptionsItemSelected(item);//Если пункт меню не обрабатывается, следует вызвать реализацию суперкласса onOptionsItemSelected() (реализация по умолчанию возвращает значение false).
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    //Блок 4(к):Обработка нажатия пунктов меню


}
