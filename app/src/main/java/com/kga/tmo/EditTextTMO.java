package com.kga.tmo;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.List;

/**
 * Created by home on 11.06.2018.
 */

/**Класс для создания EditText с необходиммми параметрами и методами для работы в приложении */
public class EditTextTMO extends android.support.v7.widget.AppCompatEditText {
    private static final String tagLog = "MyLog";

    String columnDB;
    List<String> listRowid;
    ListCursorDB listCursorDB;

    View.OnKeyListener onKeyListenerEditText = new View.OnKeyListener (){//обязательно инициализация должна быть доя конструктора в котором происходит присваивание методу
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {//ДОБАВИТЬ условие типа Alt+Enter для вставки нового абзаца
            if (keyEvent.getAction()==KeyEvent.ACTION_DOWN &keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER){
                Log.d(tagLog, "EditTextTMO.EditTextTMO.3:");
                listCursorDB.insertContentCellDB(listRowid,columnDB,getText().toString());
                return true;
            }else {return false;}

        }
    };



    public EditTextTMO(Context context, String columnDB, List<String> listRowId, String text) {
        //Log.d(tagLog, "EditTextTMO.EditTextTMO.1:");
        super(context);
        listCursorDB = new ListCursorDB(context);
        this.listRowid=listRowId;
        this.columnDB=columnDB;
        setBackgroundColor(Color.GRAY);
        setText(text);
        setOnKeyListener(onKeyListenerEditText);
    }

    @Override
    public void setOnKeyListener(OnKeyListener l) {
        Log.d(tagLog, "EditTextTMO.EditTextTMO.2:");
        super.setOnKeyListener(l);

    }




}
