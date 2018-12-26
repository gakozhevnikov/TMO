package com.kga.tmo;

import android.os.Environment;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//Блок 0(н):

//Блок 0(к):
/**
 * Created by ASUS on 06.03.2018.
 */
/**Класс для работы (создания, чтения и записи) со вспомогательными файлами (настройка и т.д.)*/
public class TMOFile {
    //Блок 1(н): Объявление переменных
    /**Для фильтрации Log при отладки */    private static final String tagLog = "MyLog";
    /**Слэш для разделения текста пути*/    private static String SLASH_SEP="/";
    /**имя папки */                         private static String DIRECTORY_TMO="TMO";
    /**имя файла с настройками*/            public static String FILE_NAME_SETTING="setting.xml";
    /**имя файла в формате Excel
     * (по умолчанию graph.xls) с графиком
     * технического обслуживания*/          public static String FILE_NAME_GRAPH="graph.xls";
    /**Папка где будут храниться файли*/private File directory = new File (Environment.getExternalStorageDirectory(),DIRECTORY_TMO);
    /**Файл с настройками */ private File fileSetting = new File (Environment.getExternalStorageDirectory()+SLASH_SEP+DIRECTORY_TMO,FILE_NAME_SETTING);
    /**Файл graph.xls в папке TMO*/ private File fileGraph = new File (Environment.getExternalStorageDirectory()+SLASH_SEP+DIRECTORY_TMO,FILE_NAME_GRAPH);
    /**Файл graph.xls в папке Download*/ private File fileGraphDownload = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),FILE_NAME_GRAPH);

    /**DocumentBuilderFactory*/ DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    /**DocumentBuilder*/ DocumentBuilder documentBuilder;
    /**Element*/ Element element;
    /**Document*/ Document doc;
    /**TransformerFactory*/ TransformerFactory transformerFactory;
    /**Transformer*/ Transformer transformer;
    /**DOMSource*/ DOMSource domSource;
    /**StreamResult*/ StreamResult sr;
    /**Возвращаемое значение в результате выполнения метода greatFileSetting
     * @see TMOFile#createFileSetting(LinkedListSettingXml) ()
     */boolean resultGreatFileSetting;
    /**Карта - набор пар объектов ключ-значение:
     * имя тега в файле setting.xml, значение тега*/ Map <String, String> readHashMapSetting;
    /**Промежуточная переменная для сохранения
     * имени тега (настройки) из файла Setting.xml*/private String xpParserXmlSettingName;




    //Блок 1(к):Объявление переменных

    //Блок 2(н): Метод для создания папки
    /**Метод для создания папки.
     * Методом directory.mkdir() создаётся папка.
     * @return  Значение true возращается если папка уже создана или её удалось создать.
     * Значение false возращается если по указанному пути нет папки или поэтому пути это не папка,
     * а также если папку не удалось создать.*/
    boolean createDirectoryTMO() {
        boolean result;
        //Log.d(tagLog, directory.toString());
        if (!directory.exists()&&!directory.isDirectory()) {
            //Log.d(tagLog, "!directory.exists() true");
            result=false;
            if(directory.mkdir()){
                //Log.d(tagLog, "directory.mkdir() true");
                result=true;
            }
            else{
                //Log.d(tagLog, "directory.mkdir() false");
                result=false;
            }
        }else {
            //Log.d(tagLog, "directory.exists() false");
            result = true;
        }
    return result;
    }
    //Блок 2(к): Метод для создания папки

    //Блок 2(н): Метод возвращает путь к файлам
    /**Метод возвращает путь к файлам
     * @return Возвращается строковая переменная с именем directoryString. Если папка существует то возращается путь, иначе возвращается null */
    String getDirectoryTmo(){
        String directoryString;
        if (directory.exists()&&directory.isDirectory()) {
            //Log.d(tagLog, "getDirectoryTmo true");
            directoryString=directory.toString();
        }else {
            //Log.d(tagLog, "getDirectoryTmo false");
            directoryString = null;
        }
        return directoryString;
    }
    //Блок 2(к): Метод возвращает путь к файлам


    //Блок 3(н): Метод создающий файл setting.xml
    /**Метод создания файла setting.xml. На основе LinkedList
     * Дописать*/
    public boolean createFileSetting (LinkedListSettingXml linkedListSettingXml){
        boolean resultCreateFileSetting= false;
        if (!fileSetting.exists()){
            //Log.d(tagLog, "greatFileSettingLL !setting.exists()"+fileSetting);
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
                doc = documentBuilder.newDocument();

                element = doc.createElement("Setting");
                doc.appendChild(element);

                Iterator<SettingXml> iterator = linkedListSettingXml.iterator();
                //Log.d(tagLog, "greatFileSettingLL ");
                while (iterator.hasNext()){
                    SettingXml settingXml=iterator.next();
                    element = doc.getDocumentElement();
                    Element childelement = doc.createElement(settingXml.getKey());
                    element.appendChild(childelement);//Добавьте корневой элемент в объект Document с помощью метода appendChild()
                    childelement.appendChild(doc.createTextNode(settingXml.getValue()));
                    //Log.d(tagLog, "greatFileSettingLL2: "+settingXml.getKey()+"-"+settingXml.getTextViewResourse()+"-"+settingXml.getValue());
                }
                //Для установки аттрибутов в тегах
                /*Attr attr = doc.createAttribute("Title");
                attr.setValue(title);
                childelement.setAttributeNode(attr);//установите атрибуты publisher и journal корневого элемента с помощью метода setAttribute;*/

                transformerFactory = TransformerFactory.newInstance();
                transformer = transformerFactory.newTransformer();
                Properties outFormat = new Properties();//Задаем выходные свойства
                outFormat.setProperty(OutputKeys.INDENT, "yes");//отступ Indent
                outFormat.setProperty(OutputKeys.METHOD, "xml");//формат вывода (METHOD);
                outFormat.setProperty(OutputKeys.OMIT_XML_DECLARATION, "no");//XML-декларацию (OMIT_XML_DECLARATION);
                outFormat.setProperty(OutputKeys.VERSION, "1.0");//версию XML (VERSION);
                outFormat.setProperty(OutputKeys.ENCODING, "UTF-8");//кодировку XML-документа (ENCODING).
                transformer.setOutputProperties(outFormat);
                domSource = new DOMSource(doc);
                sr = new StreamResult(fileSetting);
                transformer.transform(domSource, sr);
                resultCreateFileSetting = true;
                //Log.d(tagLog, "Сохранено.");
            } catch (TransformerConfigurationException tce){
                Log.d(tagLog, "greatFileSetting Ошибка: ", tce);
                return resultCreateFileSetting= false;
            } catch (TransformerException te){
                Log.d("greatFileSetting Error", String.valueOf(te));
                return resultCreateFileSetting= false;
            } catch (ParserConfigurationException pce) {
                Log.d(tagLog, "greatFileSetting Ошибка: ", pce);
                return resultCreateFileSetting= false;
            } catch (Exception e){
                Log.d(tagLog, "greatFileSetting Ошибка: ", e);
                return resultCreateFileSetting= false;
            }
        }

        return resultCreateFileSetting;
    }
    //Блок 3(к): Метод создающий файл setting.xml

    //Блок 4(н): Метод_проверки_наличия_файла_с_настройками
    /**Метод проверки существования файла setting.xml
     * @return При наличии файла setting.xml возвращается true, при его отсутствии false*/
    boolean isExistFileSetting(){
        if (fileSetting.exists())
            return fileSetting.exists();
        else
            return fileSetting.exists();
    }
    //Блок 4(к): Метод_проверки_наличия_файла_с_настройками


    //Блок 5(н): Метод_чтения_файла_setting.xml
    Map <String, String> readSetting (){
        readHashMapSetting = new HashMap<String, String>();
        //Log.d(tagLog, "Блок 5: readSetting)");

        try {
            //Log.d(tagLog, "После  try ");
            //Блок 5.1 (н): Объявляем переменные,объекты, настраиваем фабрику парсера и передаем файл для обработки
            //Можно XmlPullParser parser = getResources().getXml(R.xml.contacts); но попробовал как рекомендуют в deve//android и xmlparser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();// Create an object of XmlPullParserFactory(получаем фабрику)
            factory.setNamespaceAware(true);//включаем поддержку namespace (по умолчанию выключена)
            XmlPullParser xpParserXmlSetting = factory.newPullParser();
            FileInputStream fisXmlSetting = new FileInputStream(fileSetting);
            xpParserXmlSetting.setInput(new InputStreamReader(fisXmlSetting));
            int eventType;
            //Блок 5.1 (к): Объявляем переменные,объекты, настраиваем фабрику парсера и передаем файл для обработки

            //Блок 5.2 (н): Перебираем файл setting.xml через do while
            do {
                switch (xpParserXmlSetting.getEventType()) {
                    //начало документа
                    case XmlPullParser.START_DOCUMENT:
                        //Log.d(tagLog, "START_DOCUMENT");
                        break;
                    // начало тэга
                    case XmlPullParser.START_TAG:
                        xpParserXmlSettingName = xpParserXmlSetting.getName();
                        //Log.d(tagLog, "START_TAG: " + xpParserXmlSettingName);
                        break;
                        // конец тэга
                    case XmlPullParser.END_TAG:
                        //Log.d(tagLog, "END_TAG: " + xpParserXmlSetting.getName());
                        break;
                    // содержимое тэга
                    case XmlPullParser.TEXT:
                        //Log.d(tagLog, "перед if " );
                        if (xpParserXmlSetting.getText().equals("\n")){//также читает и перенос строки, поэтому если встречается текст переноса строки то его пропускаем.
                            //Log.d(tagLog, "text =  null");
                            break;
                        }
                        //Log.d(tagLog, "text =/" + xpParserXmlSetting.getText()+"/");
                        readHashMapSetting.put(xpParserXmlSettingName, xpParserXmlSetting.getText());
                        break;

                    default:
                        break;
                }
                eventType = xpParserXmlSetting.next();
            } while (eventType != xpParserXmlSetting.END_DOCUMENT);
            fisXmlSetting.close();
            //Блок 5.2 (конец):Перебираем файл setting.xml через do while

        }
        catch (XmlPullParserException e) {
            //Log.d(tagLog, "XmlPullParserException e", e);
        } catch (IOException e) {
            //Log.d(tagLog, "IOException e", e);
        }
        //Log.d(tagLog, "Блок 5: readSetting2)");
        return readHashMapSetting;
    }

    //Блок 5(к): Метод_чтения_файла_setting.xml

    //Блок 6(н):Удаление файла с настройками
    /**Удаление файла с настройками.*/
    boolean deleteFileSetting (){
        boolean resultDeleteFileSetting;
        resultDeleteFileSetting=fileSetting.delete();

        return resultDeleteFileSetting;
    }

    //Блок 6(к):Удаление файла с настройками

    //Блок 7(н):Проверка наличия файла graph.xls
    /**Проверка наличия файла graph.xls*/
    boolean isExistFileGraph (){
        if (fileGraph.exists())
            return fileGraph.exists();
        else if (fileGraphDownload.exists()&!fileGraph.exists()){
            return fileGraphDownload.exists();
        }
         else{
            return fileGraphDownload.exists();
        }
    }
    //Блок 7(к):Проверка наличия файла graph.xls

    //Блок 8(н):Метод в зависимости от расположения файла возвращает существующий File
    /**Метод в зависимости от расположения файла возвращает существующий File*/
    File getFileGraph (){
        if (fileGraph.exists())
            return fileGraph;
        else if (fileGraphDownload.exists()&!fileGraph.exists()){
            return fileGraphDownload;
        }
        else{
            return null;
        }
    }
    //Блок 8(к):Метод в зависимости от расположения файла возвращает существующий File

}
