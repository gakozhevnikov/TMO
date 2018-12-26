package com.kga.tmo;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link TMOFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TMOFragment extends Fragment {
    //Блок 1(н):Обьявление переменный
    private static final String tagLog = "MyLog";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /**Автоматически сформированные */private static final String ARG_PARAM1 = "param1";
    /**Автоматически сформированные */private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    /**Автоматически сформированные */private String mParam1;
    /**Автоматически сформированные */private String mParam2;

    /**Автоматически сформированные*/
    private OnFragmentInteractionListener mListener;
    Context context;
    LayoutInflater myInflater;
    ViewGroup myContainer;
    Bundle mySavedInstanceState;


    /**Основной LinearLayout на весь фрагмент*/
    LinearLayout mainLinearLayoutTMOFragment;
    /**Основной LinearLayout на спинеры*/
    LinearLayout linearLayoutSpinnerTMOFragment;
    /**Основной LinearLayout на весь фрагмент*/
    LinearLayout linearLayoutTMOFragmentCheckBoxTab;
    /**linLayoutParamMatchParent*/
    LinearLayout.LayoutParams linLayoutParamMatchParent;
    /**linLayoutParamWrapContent*/
    LinearLayout.LayoutParams linLayoutParamWrapContent;
    /**linLayoutParamMPWC*/
    LinearLayout.LayoutParams linLayoutParamMPWC;
    /**ModeView*/
    ModeView modeView;
    /***/
    AdapterView.OnItemSelectedListener setOnItemSelectedListenerSpinnerView;
    /**LinearLayout для режима*/
    TableRow tableRowModeTMOFragment;
    /**LinearLayout тест*/
    LinearLayout linearLayoutTest;
    /***/
    TMOSpinner TMOSpinner;
    /***/
    TMOSpinner spinnerObject;
    /***/
    List<String> listObject = new ArrayList<String>();
    /***/
    ArrayAdapterTMOSpinner arrayAdapterTMOSpinnerObject;
    /***/
    AdapterView.OnItemSelectedListener setOnItemSelectedListenerSpinnerObject;


    /**Для предовращения срабатывания setOnItemSelectedListenerSpinnerView вызываемое автоматически при формировании Spinner*/
    boolean booleanFRSpinnerView = true;
    /***/
    boolean booleanFRSpinnerObject = true;
    /***/
    boolean booleanFRSpinnerMonth = true;
    /***/
    boolean booleanFRSpinnerPeriod = true;
    /***/
    boolean booleanFRSpinnerCategory = true;
    /***/
    boolean booleanFRSpinnerTitle = true;
    /***/
    boolean booleanFRSpinnerSerial = true;

    /***/
    TMOSpinner spinnerMonth;
    /***/
    AdapterView.OnItemSelectedListener setOnItemSelectedListenerSpinnerMonth;
    /***/
    TMOSpinner spinnerPeriod;
    /***/
    AdapterView.OnItemSelectedListener setOnItemSelectedListenerSpinnerPeriod;
    /***/
    TMOSpinner spinnerCategory;
    /***/
    AdapterView.OnItemSelectedListener setOnItemSelectedListenerSpinnerCategory;
    /***/
    TMOSpinner spinnerTitle;
    /***/
    AdapterView.OnItemSelectedListener setOnItemSelectedListenerSpinnerTitle;
    /***/
    android.support.v7.widget.AppCompatSpinner spinnerSerial;
    /***/
    ArrayList<StateVO> listVOs = new ArrayList<>();
    /***/
    AdapterSpinnerCheckBox adapterSpinnerSerial ;
    /***/
    AdapterView.OnItemSelectedListener setOnItemSelectedListenerSpinnerSerial;
    /***/
    AdapterView.OnTouchListener setOnTouchListenerSpinnerSerial;
    /***/
    View.OnFocusChangeListener setOnFocusChangeListenerSpinnerSerial;
    /***/
    List<String> listSerial = new ArrayList<>();
    /**В этот лист загружаются только колонки для чек-листа*/
    List<String> listRowsColumnsCheck = new ArrayList<>();
    /**В этот лист загружаются только колонки для чек-листа*/
    List<String> listRowsColumnsRowID = new ArrayList<>();
    //String stringsRowIds;

    ListCursorDB listCursorDB;

    /**Horizontal CheckBox*/
    HorizontalScrollView HSVCheckBoxTMO;
    /**LinearLayout Horizontal CheckBox*/
    LinearLayout LLHSVCheckBoxTMO;
    /***/
    CheckBox checkBoxMt;
    /***/
    CheckBox checkBoxMDM;
    /***/
    CheckBox checkBoxVMI;
    /***/
    CheckBox checkBoxCrash;
    /***/
    CheckBox checkBoxStorage;
    /***/
    CheckBox checkBoxRepair;
    /***/
    CheckBox checkBoxNote;
    /***/
    boolean booleanCheckBox = false;
    /***/
    boolean booleanCheckBoxMt = false;
    /***/
    boolean booleanCheckBoxMDM = false;
    /***/
    boolean booleanCheckBoxVMI = false;
    /***/
    boolean booleanCheckBoxCrash = false;
    /***/
    boolean booleanCheckBoxStorage = false;
    /***/
    boolean booleanCheckBoxRepair = false;
    /***/
    boolean booleanCheckBoxNote = false;
    /***/
    CompoundButton.OnCheckedChangeListener OnCheckedChangeListenerCheckBoxTMO;
    /***/
    CompoundButton.OnCheckedChangeListener OnCheckedChangeListenerCheckBoxTMOMt;
    /***/
    CompoundButton.OnCheckedChangeListener OnCheckedChangeListenerCheckBoxTMOMDM;
    /***/
    CompoundButton.OnCheckedChangeListener OnCheckedChangeListenerCheckBoxTMOVMI;
    /***/
    CompoundButton.OnCheckedChangeListener OnCheckedChangeListenerCheckBoxTMOCrash;
    /***/
    CompoundButton.OnCheckedChangeListener OnCheckedChangeListenerCheckBoxTMOStorage;
    /***/
    CompoundButton.OnCheckedChangeListener OnCheckedChangeListenerCheckBoxTMORepair;
    /***/
    CompoundButton.OnCheckedChangeListener OnCheckedChangeListenerCheckBoxTMONote;


    //TabHost

    public TabHost tabHost;
    TabHost.TabSpec tabSpecTMO;
    boolean booleanGreatTabHost = false;
    //LinearLayout linearLayoutTab;
    LinearLayout linearLayoutMt;
    LinearLayout linearLayoutMt1;
    LinearLayout linearLayoutMDM;
    LinearLayout linearLayoutVMI;
    LinearLayout linearLayoutCrash;
    LinearLayout linearLayoutStorage;
    LinearLayout linearLayoutRepair;
    LinearLayout linearLayoutNote;

    boolean booleanTabMT=false;
    boolean booleanTabMDM=false;
    boolean booleanTabVMI=false;
    boolean booleanTabCrash=false;
    boolean booleanTabStorage=false;
    boolean booleanTabRepair=false;
    boolean booleanTabNote=false;

    /***/
    MapNameView mapNameView;

    /**Необходима для определения позиции в GridView и ВСТАВКИ значения в базу*/
    int positionBefor = -1;
    /***/
    //Map<String,String> mapNameView = new HashMap<String, String>();

    //Блок 1(к):Обьявление переменный



    public TMOFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TMOFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TMOFragment newInstance(String param1, String param2) {
        TMOFragment fragment = new TMOFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context= getActivity();



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



        setOnItemSelectedListenerSpinnerView = new AdapterView.OnItemSelectedListener(){//setOnItemSelectedListenerSpinnerView надо инициализировать до назначения
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (booleanFRSpinnerView) {
                    booleanFRSpinnerView= false;
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerView,1:"+position);
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    modeView.modeTMOSpiner.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                } else if (modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))) {
                    booleanFRSpinnerObject = true; booleanFRSpinnerMonth = true;booleanFRSpinnerPeriod=true;booleanFRSpinnerCategory=true;booleanFRSpinnerTitle=true;booleanFRSpinnerSerial= true;//для предовращения создания других спинеров при создании спинера объект, т.к. при первом создании объекта спинер автоматически создается следующий.
                    linearLayoutSpinnerTMOFragment.removeAllViews();
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerView,2:"+position);
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    modeView.modeTMOSpiner.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                    modeView.spinnerMode.setSelection(0);//меняем выделенный элемент в spinnerMode на 0 чтобы в дальнейшем в методе listCursorDB.getListObject режим был обычный т.к. для нвепланового нужно чтобы режим был только обычный.
                    spinnerObject.getTMOSpinner(listCursorDB.getListObject(modeView.spinnerMode.getSelectedItem().toString()));//альтернатива двум верхним строчкам. ранее без переопределенного метода в TMOSpinner.getTMOSpinner.notifyDataSetChanged не работал
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerView,3:");
                    linearLayoutSpinnerTMOFragment.addView(spinnerObject);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        };

        setOnItemSelectedListenerSpinnerObject = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (booleanFRSpinnerObject) {
                    booleanFRSpinnerObject= false;
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerObject,1:"+position);
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerObject.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                } else if (modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))) {
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerObject,2:"+position);
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerObject.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                    if (spinnerMonth.isShown()) {
                        booleanFRSpinnerMonth = true;booleanFRSpinnerTitle=true;booleanFRSpinnerSerial= true;
                        linearLayoutSpinnerTMOFragment.removeView(spinnerMonth); linearLayoutSpinnerTMOFragment.removeView(spinnerTitle);
                        linearLayoutSpinnerTMOFragment.removeView(spinnerSerial);
                        spinnerMonth.getTMOSpinner(listCursorDB.getListMonth(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString()));
                        spinnerMonth.setSelection(0);//меняем выделенный элемент в spinnerMode на 0 чтобы в дальнейшем в методе listCursorDB.getListObject режим был обычный т.к. для внепланового нужно чтобы режим был только обычный.
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerObject,21:"+ spinnerObject);
                        linearLayoutSpinnerTMOFragment.addView(spinnerMonth,linLayoutParamMPWC);
                    }else{
                        spinnerMonth.getTMOSpinner(listCursorDB.getListMonth(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString()));
                        spinnerMonth.setSelection(0);//меняем выделенный элемент в spinnerMode на 0 чтобы в дальнейшем в методе listCursorDB.getListObject режим был обычный т.к. для внепланового нужно чтобы режим был только обычный.
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerObject,3:"+ spinnerObject);
                        linearLayoutSpinnerTMOFragment.addView(spinnerMonth,linLayoutParamMPWC);
                    }
                }else {
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerObject.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerObject,4,spinnerMonth.isInLayout():"+ spinnerMonth.isShown());
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerObject,41,spinnerMonth.isInLayout():"+spinnerMonth.is);
                    if (spinnerMonth.isShown()) {
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerObject,5:");
                        booleanFRSpinnerMonth = true;booleanFRSpinnerPeriod=true;booleanFRSpinnerCategory=true;booleanFRSpinnerTitle=true;booleanFRSpinnerSerial= true;
                        linearLayoutSpinnerTMOFragment.removeView(spinnerMonth); linearLayoutSpinnerTMOFragment.removeView(spinnerPeriod);linearLayoutSpinnerTMOFragment.removeView(spinnerCategory);
                        linearLayoutSpinnerTMOFragment.removeView(spinnerTitle);linearLayoutSpinnerTMOFragment.removeView(spinnerSerial);
                        spinnerMonth.getTMOSpinner(listCursorDB.getListMonth(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString()));//две одинаковые строки оптимизировать здесь и далее
                         //spinnerMonth.setSelection(0);
                        linearLayoutSpinnerTMOFragment.addView(spinnerMonth,linLayoutParamMPWC);
                    } else {
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerObject,6:"+ spinnerObject);
                        spinnerMonth.getTMOSpinner(listCursorDB.getListMonth(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString()));//две одинаковые строки оптимизировать здесь и далее
                        linearLayoutSpinnerTMOFragment.addView(spinnerMonth,linLayoutParamMPWC);
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerObject,61:"+ spinnerMonth.isShown());

                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        setOnItemSelectedListenerSpinnerMonth = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (booleanFRSpinnerMonth) {
                    booleanFRSpinnerMonth= false;
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,1:"+position);
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerMonth.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                } else if (modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))) {
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,2:"+position);
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerMonth.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбранperiodSpinner.getTMOSpinner(listCursorDB.getListPeriod(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString(),spinnerMonth .getSelectedItem().toString()));
                    if (spinnerTitle.isShown()){
                        booleanFRSpinnerTitle=true;booleanFRSpinnerSerial= true;
                        linearLayoutSpinnerTMOFragment.removeView(spinnerTitle); linearLayoutSpinnerTMOFragment.removeView(spinnerSerial);
                        spinnerTitle.getTMOSpinner(listCursorDB.getListTitle(modeView.spinnerView.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString()));
                        spinnerTitle.setSelection(0);
                        linearLayoutSpinnerTMOFragment.addView(spinnerTitle,linLayoutParamMPWC);
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,3:"+ spinnerPeriod);
                    }else{
                        spinnerTitle.getTMOSpinner(listCursorDB.getListTitle(modeView.spinnerView.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString()));
                        spinnerTitle.setSelection(0);
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,31:"+ spinnerPeriod);
                        linearLayoutSpinnerTMOFragment.addView(spinnerTitle,linLayoutParamMPWC);
                    }
                }else {
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerMonth.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,4,spinnerMonth.isInLayout():");
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerObject,41,spinnerMonth.isInLayout():"+spinnerMonth.is);
                    if (spinnerPeriod.getTMOSpinner(listCursorDB.getListPeriod(modeView.spinnerMode.getSelectedItem().toString(),
                            spinnerObject.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString()))
                            .getSelectedItem().toString().equals(context.getString(R.string.data_base_no_information))&!spinnerCategory.isShown()){//& modeView.spinnerMode.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_MODE.get(0))){
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,5:");
                        spinnerCategory.getTMOSpinner(listCursorDB.getListCategory(modeView.spinnerMode.getSelectedItem().toString(),
                                spinnerObject.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString()));
                        linearLayoutSpinnerTMOFragment.addView(spinnerCategory,linLayoutParamMPWC);
                    }
                    else {
                        if (spinnerCategory.isShown()&!spinnerPeriod.isShown()){
                            //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,6:");
                            booleanFRSpinnerCategory=true;booleanFRSpinnerTitle=true;booleanFRSpinnerSerial= true;
                            linearLayoutSpinnerTMOFragment.removeView(spinnerCategory);
                            linearLayoutSpinnerTMOFragment.removeView(spinnerTitle);linearLayoutSpinnerTMOFragment.removeView(spinnerSerial);
                        }
                        else if (spinnerPeriod.isShown()) {
                            //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,7:");
                            //listCursorDB.getListMonth(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString());
                            //spinnerMonth.adapterSpinner.notifyDataSetChanged();
                            booleanFRSpinnerPeriod=true;booleanFRSpinnerCategory=true;booleanFRSpinnerTitle=true;booleanFRSpinnerSerial= true;
                            linearLayoutSpinnerTMOFragment.removeView(spinnerPeriod);linearLayoutSpinnerTMOFragment.removeView(spinnerCategory);
                            linearLayoutSpinnerTMOFragment.removeView(spinnerTitle);linearLayoutSpinnerTMOFragment.removeView(spinnerSerial);
                            spinnerPeriod.getTMOSpinner(listCursorDB.getListPeriod(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString(),
                                spinnerMonth.getSelectedItem().toString()));
                            spinnerPeriod.setSelection(0);
                            linearLayoutSpinnerTMOFragment.addView(spinnerPeriod,linLayoutParamMPWC);
                    } else {
                            //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,8:");
                        spinnerPeriod.getTMOSpinner(listCursorDB.getListPeriod(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString(),
                                spinnerMonth.getSelectedItem().toString()));
                            linearLayoutSpinnerTMOFragment.addView(spinnerPeriod,linLayoutParamMPWC);
                            //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,9:");
                    }}


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        setOnItemSelectedListenerSpinnerPeriod = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (booleanFRSpinnerPeriod) {
                        booleanFRSpinnerPeriod= false;
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerPeriod,1:"+position);
                        ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                        spinnerPeriod.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                    } else if (modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))) {
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerPeriod,2:"+position);
                        ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                        spinnerPeriod.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                        //spinnerTitle.getTMOSpinner(listCursorDB.getListTitle(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString()));
                        //spinnerTitle.setSelection(0);
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,3:"+ spinnerPeriod);
                        //mainLinearLayoutTMOFragment.addView(spinnerTitle,linLayoutParamMPWC);
                        //добавить обновление
                    }else {
                        ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                        spinnerPeriod.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerPeriod,4,spinnerMonth.isInLayout():");
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerObject,41,spinnerMonth.isInLayout():"+spinnerMonth.is);
                        if (spinnerCategory.isShown()) {
                            //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerPeriod,7:");
                            booleanFRSpinnerCategory=true;booleanFRSpinnerTitle=true;booleanFRSpinnerSerial= true;
                            linearLayoutSpinnerTMOFragment.removeView(spinnerCategory); linearLayoutSpinnerTMOFragment.removeView(spinnerTitle);linearLayoutSpinnerTMOFragment.removeView(spinnerSerial);
                            spinnerCategory.getTMOSpinner(listCursorDB.getListCategory(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString(),
                                    spinnerMonth.getSelectedItem().toString(), spinnerPeriod.getSelectedItem().toString()));
                            //spinnerCategory.setSelection(0);
                            linearLayoutSpinnerTMOFragment.addView(spinnerCategory,linLayoutParamMPWC);
                        } else {//Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerPeriod,8:");
                            spinnerCategory.getTMOSpinner(listCursorDB.getListCategory(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString(),
                                    spinnerMonth.getSelectedItem().toString(), spinnerPeriod.getSelectedItem().toString()));
                            linearLayoutSpinnerTMOFragment.addView(spinnerCategory,linLayoutParamMPWC);
                            //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerPeriod,9:");
                        }

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
        };

        setOnItemSelectedListenerSpinnerCategory = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerCategory,1:"+position);
                //((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                //spinnerCategory.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                if (booleanFRSpinnerCategory) {
                    booleanFRSpinnerCategory= false;
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerCategory,1:"+position);
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerCategory.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                } else if (modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))) {//Внеплановое
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerCategory,2:"+position);
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerCategory.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                    //spinnerTitle.getTMOSpinner(listCursorDB.getListTitle(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString()));
                    //spinnerTitle.setSelection(0);
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerCategory,3:"+ spinnerPeriod);
                    //mainLinearLayoutTMOFragment.addView(spinnerTitle,linLayoutParamMPWC);
                    //добавить обновление
                }else {
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerCategory.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerCategory,4,spinnerMonth.isInLayout():");
                    if (spinnerTitle.isShown()) {
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerCategory,7:");
                        booleanFRSpinnerTitle=true;booleanFRSpinnerSerial= true;
                        linearLayoutSpinnerTMOFragment.removeView(spinnerTitle);linearLayoutSpinnerTMOFragment.removeView(spinnerSerial);
                        spinnerTitle.getTMOSpinner(listCursorDB.getListTitle(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString(),
                                spinnerMonth.getSelectedItem().toString(), spinnerPeriod.getSelectedItem().toString(),spinnerCategory.getSelectedItem().toString()));
                        //spinnerTitle.setSelection(0);
                        linearLayoutSpinnerTMOFragment.addView(spinnerTitle,linLayoutParamMPWC);
                    } else {//Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerCategory,8:");
                        spinnerTitle.getTMOSpinner(listCursorDB.getListTitle(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString(),
                                spinnerMonth.getSelectedItem().toString(), spinnerPeriod.getSelectedItem().toString(),spinnerCategory.getSelectedItem().toString()));
                        linearLayoutSpinnerTMOFragment.addView(spinnerTitle,linLayoutParamMPWC);
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerCategory,9:");
                    }

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        setOnItemSelectedListenerSpinnerTitle = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerTitle,1:"+position);
                listVOs.clear();
                //((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                //spinnerCategory.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                if (booleanFRSpinnerTitle) {
                    booleanFRSpinnerTitle= false;
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerTitle,1:"+position);
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerTitle.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                } else if (modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))) {//Внеплановое
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerTitle,2:"+position);
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerTitle.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                    listVOs= listCursorDB.getListSerial(modeView.spinnerView.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString(),
                            spinnerMonth.getSelectedItem().toString(),spinnerTitle.getSelectedItem().toString());
                    adapterSpinnerSerial = new AdapterSpinnerCheckBox(context,android.R.layout.simple_spinner_item,listVOs);
                    spinnerSerial.setAdapter(adapterSpinnerSerial);
                    spinnerSerial.setSelection(0);
                    if (spinnerSerial.isShown()){
                        booleanFRSpinnerSerial= true;
                        linearLayoutSpinnerTMOFragment.removeView(spinnerSerial);
                        linearLayoutSpinnerTMOFragment.addView(spinnerSerial,linLayoutParamMPWC);
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,3:"+ spinnerPeriod);
                    }else{
                        linearLayoutSpinnerTMOFragment.addView(spinnerSerial,linLayoutParamMPWC);
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerMonth,31:"+ spinnerPeriod);
                    }
                }else {
                    ((TextView) parent.getChildAt(0)).setBackgroundColor(ContextCompat.getColor(context,R.color.spinner_selected_item));//выделяем выбранный элемент
                    spinnerTitle.setSelectedItemTMOSpiner(position);//передаем номер выбранного элемента, чтобы было видно какой элемент выбран
                    listVOs= listCursorDB.getListSerial(modeView.spinnerMode.getSelectedItem().toString(),
                            spinnerObject.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString(), spinnerPeriod.getSelectedItem().toString(),
                            spinnerCategory.getSelectedItem().toString(),spinnerTitle.getSelectedItem().toString());
                    adapterSpinnerSerial = new AdapterSpinnerCheckBox(context,android.R.layout.simple_spinner_item,listVOs);
                    spinnerSerial.setAdapter(adapterSpinnerSerial);
                    spinnerSerial.setSelection(0);
                    //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerTitle,4,spinnerMonth.isInLayout():");
                    if (spinnerSerial.isShown()) {
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerTitle,7:");
                        booleanFRSpinnerSerial= true;
                        linearLayoutSpinnerTMOFragment.removeView(spinnerSerial);
                        linearLayoutSpinnerTMOFragment.addView(spinnerSerial,linLayoutParamMPWC);
                    } else {//Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerTitle,8:");
                        linearLayoutSpinnerTMOFragment.addView(spinnerSerial,linLayoutParamMPWC);
                        //Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerTitle,9:");
                    }

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        /*setOnItemSelectedListenerSpinnerSerial = new AdapterView.OnItemSelectedListener() {//не работает

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.d(tagLog, "TMOFragment.onCreateView.setOnItemSelectedListenerSpinnerTitle,1:");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        };*/
        setOnTouchListenerSpinnerSerial = new AdapterView.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Log.d(tagLog, "TMOFragment.onCreateView.setOnTouchListenerSpinnerSerial,1:"+spinnerSerial.hasFocus()+"+"+spinnerSerial.hasFocusable()+ listSerial.isEmpty());
                if (listSerial.isEmpty()){}else {
                    listSerial.clear();}
                return false;
            }
        };
        setOnFocusChangeListenerSpinnerSerial = new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                //Log.d(tagLog, "TMOFragment.onCreateView.setOnFocusChangeListenerSpinnerSerial,1:"+b);
            }
        };
    }
    //Блок 2(н):Метод onCreateView
    /**Метод onCreateView*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Log.d(tagLog, "TMOFragment.onCreateView,Блок 2,1:"+context);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_tmo, container, false);//автоматическая вставка в код
        myInflater = inflater;
        myContainer=container;
        mySavedInstanceState = savedInstanceState;
        mapNameView = new MapNameView(context);
        /*mapNameView.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_LETTER,context.getString(R.string.mtLetter));
        mapNameView.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_PLANNED_DATE_OF_WORK,context.getString(R.string.mtPlannedDateOfWork));
        mapNameView.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY,context.getString(R.string.mtJournalMtEntry));
        mapNameView.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_EQUIPMENT_ENTRY,context.getString(R.string.mtJournalEquipmentEntry));
        mapNameView.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_ACT,context.getString(R.string.mtAct));
        mapNameView.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_ACT_TIGHTNESS_OF_VALVES,context.getString(R.string.mtActTightnessOfValves));
        mapNameView.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_STAMP_INSTALL,context.getString(R.string.mtStampInstall));
        mapNameView.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_STAMP_JOURNAL_ENTRY,context.getString(R.string.mtStampJournalEntry));
        mapNameView.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY_SYBERVISOR_ADMIN,context.getString(R.string.mtJournalMtEntrySybervisorAdmin));
        mapNameView.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_ACT_SYBERVISOR_ADMIN,context.getString(R.string.mtActSybervisorAdmin));
        mapNameView.put(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_NOTE,context.getString(R.string.mtNote));*/


        View tmoViewFragment =inflater.inflate(R.layout.fragment_tmo, container, false);
        listCursorDB = new ListCursorDB(context);//инициализировать надо здесь
        linLayoutParamMatchParent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);//(ширина(width), высота(height))
        linLayoutParamWrapContent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linLayoutParamMPWC = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLinearLayoutTMOFragment = (LinearLayout) tmoViewFragment.findViewById(R.id.mainLinearLayoutTMOFragment);
        linearLayoutSpinnerTMOFragment = new LinearLayout(context);
        linearLayoutSpinnerTMOFragment.setOrientation(LinearLayout.VERTICAL);
        linearLayoutSpinnerTMOFragment.removeAllViews();
        linearLayoutTMOFragmentCheckBoxTab=new LinearLayout(context);
        linearLayoutTMOFragmentCheckBoxTab.setOrientation(LinearLayout.VERTICAL);
        linearLayoutTMOFragmentCheckBoxTab.removeAllViews();
        modeView = new ModeView(context);
        modeView.spinnerView.setOnItemSelectedListener(setOnItemSelectedListenerSpinnerView);
        tableRowModeTMOFragment = modeView.getModeView();
        //TMOSpinner = new TMOSpinner(context);
        spinnerObject = new TMOSpinner(context);
        spinnerMonth = new TMOSpinner (context);
        spinnerPeriod = new TMOSpinner(context);
        spinnerCategory = new TMOSpinner(context);
        spinnerTitle = new TMOSpinner(context);
        spinnerSerial = new android.support.v7.widget.AppCompatSpinner(context);




        //modeView.spinnerMode.setSelection(2); //удалить тест
        //Log.d(tagLog, "TMOFragment.onCreateView,Блок 2,2:"+modeView.spinnerMode.getSelectedItem());//ВАЖНО ТАК выбирается текст в Spinner
        //в зависимости от режима выбирается
        //modeView.spinnerMode.setSelection(7);
        spinnerObject.getTMOSpinner(listCursorDB.getListObject(modeView.spinnerMode.getSelectedItem().toString()));

        spinnerObject.setOnItemSelectedListener(setOnItemSelectedListenerSpinnerObject);
        spinnerMonth.setOnItemSelectedListener(setOnItemSelectedListenerSpinnerMonth);
        spinnerPeriod.setOnItemSelectedListener(setOnItemSelectedListenerSpinnerPeriod);
        spinnerCategory.setOnItemSelectedListener(setOnItemSelectedListenerSpinnerCategory);
        spinnerTitle.setOnItemSelectedListener(setOnItemSelectedListenerSpinnerTitle);
        //spinnerSerial.setOnItemSelectedListener(setOnItemSelectedListenerSpinnerSerial);почемуто не работает
        spinnerSerial.setOnTouchListener(setOnTouchListenerSpinnerSerial);
        //spinnerSerial.setFocusableInTouchMode(true);//если установить этот параметр то смена фокуса будет работать
        spinnerSerial.setOnFocusChangeListener(setOnFocusChangeListenerSpinnerSerial); //не работает
        //adapterSpinnerSerial.
        //spinnerSerial.getSelectedItem();

        //spinnerMonth.getTMOSpinner(listCursorDB.getListMonth(modeView.spinnerMode.getSelectedItem().toString(), spinnerObject.getSelectedItem().toString()));
        //Log.d(tagLog, "TMOFragment.onCreateView,Блок 2,22:"+ spinnerObject);
        //linearLayoutSpinnerTMOFragment.addView(spinnerObject,linLayoutParamMPWC);
        mainLinearLayoutTMOFragment.addView(tableRowModeTMOFragment,linLayoutParamMPWC);
        mainLinearLayoutTMOFragment.addView(linearLayoutSpinnerTMOFragment,linLayoutParamMPWC);
        linearLayoutSpinnerTMOFragment.addView(spinnerObject,linLayoutParamMPWC);
        mainLinearLayoutTMOFragment.addView(linearLayoutTMOFragmentCheckBoxTab,linLayoutParamMPWC);

        //Log.d(tagLog, "TMOFragment.onCreateView,Блок 2,3:");



        return tmoViewFragment;//пишется в конце метода
    }
    //Блок 2(к):Метод onCreateView


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    /***/
    public class AdapterSpinnerCheckBox extends ArrayAdapter<StateVO> {
        private static final String tagLog = "MyLog";
        private Context mContext;
        private ArrayList<StateVO> listState;
        private AdapterSpinnerCheckBox myAdapter;
        private boolean isFromView = false;

        public AdapterSpinnerCheckBox(Context context, int resource, List<StateVO> objects) {
            super(context, resource, objects);
            this.mContext = context;
            this.listState = (ArrayList<StateVO>) objects;
            this.myAdapter = this;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(final int position, View convertView,ViewGroup parent) {

            final ViewHolder holder;
            if (convertView == null) {
                LayoutInflater layoutInflator = LayoutInflater.from(mContext);
                convertView = layoutInflator.inflate(R.layout.spinner_item, null);
                holder = new ViewHolder();
                holder.mTextView = (TextView) convertView
                        .findViewById(R.id.text);
                holder.mCheckBox = (CheckBox) convertView
                        .findViewById(R.id.checkbox);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.mTextView.setText(listState.get(position).getTitle());

            // To check weather checked event fire from getview() or user input
            isFromView = true;
            holder.mCheckBox.setChecked(listState.get(position).isSelected());
            isFromView = false;

            if (position == 0 & !modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))) {
                holder.mCheckBox.setVisibility(View.INVISIBLE);
            } else if (position == 0 & modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))){
                holder.mCheckBox.setVisibility(View.VISIBLE);
            } else {
                holder.mCheckBox.setVisibility(View.VISIBLE);
            }
            holder.mCheckBox.setTag(position);
            holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();
                    Log.d(tagLog, "AdapterSpinnerCheckBox.setOnCheckedChangeListener.onCheckedChanged,1:"+getPosition+": "+isChecked+": "+holder.mTextView.getText());
                    if (isChecked & !modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))){
                        listSerial.add(holder.mTextView.getText().toString()) ;
                    } else if (isChecked & !holder.mTextView.getText().toString().equals(context.getString(R.string.spinner_serial_first)) & modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))){
                        listSerial.add(holder.mTextView.getText().toString()) ;
                    }
                    else {
                        listSerial.remove(holder.mTextView.getText().toString());
                    }
                    Log.d(tagLog, "AdapterSpinnerCheckBox.setOnCheckedChangeListener.onCheckedChanged,2:"+ listSerial);
                    if(listSerial.size()>0){
                        if (holder.mTextView.getText().toString().equals(context.getString(R.string.spinner_serial_first)) & modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))){
                            Log.d(tagLog, "AdapterSpinnerCheckBox.setOnCheckedChangeListener.onCheckedChanged,21:");
                            listCursorDB.getNewRow_s(spinnerObject.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString(),
                                    spinnerTitle.getSelectedItem().toString(),listSerial);
                            listRowsColumnsCheck = listCursorDB.getRow_sColumnsCheck();
                            listRowsColumnsRowID = listCursorDB.getRow_sRowID();
                        }
                        else if (!modeView.spinnerView.getSelectedItem().toString().equals(ModeView.LIST_SPINNER_VIEW.get(2))){
                            Log.d(tagLog, "AdapterSpinnerCheckBox.setOnCheckedChangeListener.onCheckedChanged,22:");
                        listRowsColumnsCheck =listCursorDB.getRow_s(modeView.spinnerMode.getSelectedItem().toString(),
                                spinnerObject.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString(), spinnerPeriod.getSelectedItem().toString(),
                                spinnerCategory.getSelectedItem().toString(),spinnerTitle.getSelectedItem().toString(),listSerial,ListCursorDB.COLUMNS_CHECK);
                        listRowsColumnsRowID = listCursorDB.getRow_s(modeView.spinnerMode.getSelectedItem().toString(),
                                spinnerObject.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString(), spinnerPeriod.getSelectedItem().toString(),
                                spinnerCategory.getSelectedItem().toString(),spinnerTitle.getSelectedItem().toString(),listSerial,ListCursorDB.COLUMNS_ROWID);
                        }
                    }
                    Log.d(tagLog, "AdapterSpinnerCheckBox.setOnCheckedChangeListener.onCheckedChanged,3:"+ listRowsColumnsCheck);

                    if (!spinnerSerial.getSelectedItem().toString().equals(context.getString(R.string.spinner_serial_first))& !booleanCheckBox) {
                        Log.d(tagLog, "AdapterSpinnerCheckBox.setOnCheckedChangeListener.onCheckedChanged,4:");
                        linearLayoutTMOFragmentCheckBoxTab.addView(onCreateViewCheckBoxTMO(myInflater, myContainer, mySavedInstanceState));
                        booleanCheckBox=true;
                    } else if (!spinnerSerial.getSelectedItem().toString().equals(context.getString(R.string.spinner_serial_first))& booleanCheckBox) {//если меняется серийный номер то таб и чек сбрасываются чтобы потом обновить
                        Log.d(tagLog, "AdapterSpinnerCheckBox.setOnCheckedChangeListener.onCheckedChanged,5:");
                        //linearLayoutTMOFragmentCheckBoxTab.removeAllViews();
                        //linearLayoutTMOFragmentCheckBoxTab.addView(onCreateViewCheckBoxTMO(myInflater, myContainer, mySavedInstanceState));
                        booleanGreatTabHost = false;
                        booleanTabMT=false;
                        booleanTabMDM=false;
                        booleanTabVMI=false;
                        booleanTabCrash=false;
                        booleanTabStorage=false;
                        booleanTabRepair=false;
                        booleanTabNote=false;
                        tabHost.clearAllTabs();
                        HSVCheckBoxTMO.removeAllViews();
                        linearLayoutTMOFragmentCheckBoxTab.removeAllViews();
                        linearLayoutTMOFragmentCheckBoxTab.addView(onCreateViewCheckBoxTMO(myInflater, myContainer, mySavedInstanceState));
                        booleanCheckBox=true;
                    }

                }
            });

            return convertView;
        }
        private class ViewHolder {
            private TextView mTextView;
            private CheckBox mCheckBox;
        }


    }
    /***/
    void getLinearLayoutToTab (String stringMtView,LinearLayout linearLayoutTab ){
        //linearLayoutTab = new LinearLayout(context);
        linearLayoutTab.setOrientation(TableLayout.VERTICAL);
        linearLayoutTab.setLayoutParams(linLayoutParamMatchParent);
        linearLayoutTab.setGravity(Gravity.CENTER_HORIZONTAL);
        if (stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_PLANNED_DATE_OF_WORK)|stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY)
                |stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_ENTRY)|stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY)
                |stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_NOTE)|stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_NOTE)
                |stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_VMI_NOTE) |stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_NOTE)
                |stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_STORAGE_NOTE) |stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_REPAIR_NOTE)
                |stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_NOTE)) {
            //Log.d(tagLog, "TMOFragment.getLinearLayoutToTab.1:");
            if (listRowsColumnsRowID.size()>1){
                //Log.d(tagLog, "TMOFragment.getLinearLayoutToTab.2:");
                String stringSerials="";
                Iterator iterator1=listSerial.iterator();
                while (iterator1.hasNext()){//получаем строку с серийными номерами для вставки в запросы
                    if (stringSerials.equals("")){
                        stringSerials="'"+iterator1.next().toString()+"'";
                    }else{
                        stringSerials=stringSerials+", '"+iterator1.next().toString()+"'";
                    }
                }
                //Log.d(tagLog, "TMOFragment.getLinearLayoutToTab.3:"+stringSerials);
                TextView textView = new TextView(context);
                textView.setText(mapNameView.get(stringMtView)+": "+stringSerials);
                EditTextTMO editTextTMO = new EditTextTMO(context, stringMtView, listRowsColumnsRowID, listCursorDB.getContent(listRowsColumnsRowID,stringMtView));
                if(stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_PLANNED_DATE_OF_WORK)|stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY)//Для полей в которые вносяться дата, устанавливаем тип Number чтобы была клавиатура с цифрами
                        |stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_ENTRY)|stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY)){
                    //Log.d(tagLog, "TMOFragment.getLinearLayoutToTab.3.1:");
                    editTextTMO.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                linearLayoutTab.addView(textView);
                linearLayoutTab.addView(editTextTMO);
                //Log.d(tagLog, "TMOFragment.getLinearLayoutToTab.4:");
            }else if(listRowsColumnsRowID.size()==1){
                //Log.d(tagLog, "TMOFragment.getLinearLayoutToTab.5:");
                TextView textView = new TextView(context);
                textView.setText(mapNameView.get(stringMtView));
                EditTextTMO editTextTMO = new EditTextTMO(context, stringMtView,listRowsColumnsRowID, listCursorDB.getContent(listRowsColumnsRowID,stringMtView));
                if(stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_PLANNED_DATE_OF_WORK)|stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MT_JOURNAL_MT_ENTRY)//Для полей в которые вносяться дата, устанавливаем тип Number чтобы была клавиатура с цифрами
                        |stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_MDM_JOURNAL_ENTRY)|stringMtView.equals(DataBaseContract.CheckListDBTableClass.COLUMN_NAME_CRASH_JOURNAL_MT_ENTRY)){
                    //Log.d(tagLog, "TMOFragment.getLinearLayoutToTab.5.1:");
                    editTextTMO.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                linearLayoutTab.addView(textView);
                linearLayoutTab.addView(editTextTMO);
                //Log.d(tagLog, "TMOFragment.getLinearLayoutToTab.5:");
            }
        }else {
            //Log.d(tagLog, "TMOFragment.getLinearLayoutToTab.7:");
            TextView textView = new TextView(context);
            textView.setText(mapNameView.get(stringMtView));
            GridViewTMO gridViewTMO = new GridViewTMO(context, stringMtView,listRowsColumnsRowID);
            linearLayoutTab.addView(textView);
            linearLayoutTab.addView(gridViewTMO);
            //Log.d(tagLog, "TMOFragment.getLinearLayoutToTab.8:");
        }
        //return linearLayoutTab;
    }
    /***/
    public HorizontalScrollView onCreateViewCheckBoxTMO (final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        final View viewTabHostControl = inflater.inflate(R.layout.tab_host, container, false);
        final Context context = getActivity();


        //LinearLayout Horizontal
        LLHSVCheckBoxTMO = new LinearLayout (context);
        LLHSVCheckBoxTMO.setOrientation(LinearLayout.HORIZONTAL);
        HSVCheckBoxTMO = new HorizontalScrollView(context);
        booleanCheckBoxMt = false;booleanCheckBoxMDM = false;booleanCheckBoxVMI = false;booleanCheckBoxCrash = false;
        booleanCheckBoxStorage = false;booleanCheckBoxRepair = false;booleanCheckBoxNote = false;


        //
        OnCheckedChangeListenerCheckBoxTMOMt = new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!booleanTabMT&isChecked){
                    linearLayoutMt = new LinearLayout(context);
                    booleanGreatTabHost = true;
                    booleanTabMT = true;
                    tabSpecTMO = tabHost.newTabSpec("mt");//Создаем вкладку MT и указываем тег
                    tabSpecTMO.setIndicator(getResources().getText(R.string.mt));// наименование берем из ресурса
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMt.Mt.1:");
                    tabSpecTMO.setContent(new TabHost.TabContentFactory() {
                        @Override
                        public View createTabContent(String tag) {
                            //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMt.Mt.2:"+listRowsColumnsCheck);
                            Iterator<String> iterator =listRowsColumnsCheck.iterator();
                            while(iterator.hasNext()){
                                String stringMtView = iterator.next();
                                boolean pattern = Pattern.matches("^mt_\\w*", stringMtView);
                                //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMt.Mt.21:");
                                if (pattern) {
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMt.Mt.3:");
                                    getLinearLayoutToTab(stringMtView, linearLayoutMt);
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMt.Mt.31:");
                                }
                            }
                            return (linearLayoutMt);
                        }
                    });
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMt.Mt.4:");
                    tabHost.addTab(tabSpecTMO);
                    tabHost.setCurrentTabByTag("mt");
                    //Log.d(tagLog, "onItemSelected 7812:");
                    mainLinearLayoutTMOFragment.removeView(viewTabHostControl);//При повторном добавлении элемента в linearLayoutSVLControl обязательно нужно его удалить т.к. возникает ошибка,
                    mainLinearLayoutTMOFragment.addView(viewTabHostControl);
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMt.Mt.5:");
                }

            }
        };
        //MDM
        OnCheckedChangeListenerCheckBoxTMOMDM = new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!booleanTabMDM&isChecked){
                    linearLayoutMDM= new LinearLayout(context);
                    booleanGreatTabHost = true;
                    booleanTabMDM = true;
                    tabSpecTMO = tabHost.newTabSpec("mDM");//Создаем вкладку MT и указываем тег
                    tabSpecTMO.setIndicator(getResources().getText(R.string.mDM));// наименование берем из ресурса
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMDM1:");
                    tabSpecTMO.setContent(new TabHost.TabContentFactory() {
                        @Override
                        public View createTabContent(String tag) {
                            //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMDM2:"+listRowsColumnsCheck);
                            Iterator<String> iterator =listRowsColumnsCheck.iterator();
                            while(iterator.hasNext()){
                                String stringMtView = iterator.next();
                                boolean pattern = Pattern.matches("^mdm_\\w*", stringMtView);
                                //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMDM.21:");
                                if (pattern) {
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMDM.3:");
                                    getLinearLayoutToTab(stringMtView,linearLayoutMDM);
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMDM.31:");
                                }
                            }
                            return (linearLayoutMDM);
                        }
                    });
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMDM.4:");
                    tabHost.addTab(tabSpecTMO);
                    tabHost.setCurrentTabByTag("mDM");
                    //Log.d(tagLog, "onItemSelected 7812:");
                    mainLinearLayoutTMOFragment.removeView(viewTabHostControl);//При повторном добавлении элемента в linearLayoutSVLControl обязательно нужно его удалить т.к. возникает ошибка,
                    mainLinearLayoutTMOFragment.addView(viewTabHostControl);
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOMDM.5:");
                }

            }
        };
        //VMI
        OnCheckedChangeListenerCheckBoxTMOVMI = new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!booleanTabVMI&isChecked){
                    linearLayoutVMI= new LinearLayout(context);
                    booleanGreatTabHost = true;
                    booleanTabVMI = true;
                    tabSpecTMO = tabHost.newTabSpec("vMI");//Создаем вкладку MT и указываем тег
                    tabSpecTMO.setIndicator(getResources().getText(R.string.vMI));// наименование берем из ресурса
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOVMI.1:");
                    tabSpecTMO.setContent(new TabHost.TabContentFactory() {
                        @Override
                        public View createTabContent(String tag) {
                            //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOVMI.2:"+listRowsColumnsCheck);
                            Iterator<String> iterator =listRowsColumnsCheck.iterator();
                            while(iterator.hasNext()){
                                String stringMtView = iterator.next();
                                boolean pattern = Pattern.matches("^vmi_\\w*", stringMtView);
                                //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOVMI.21:");
                                if (pattern) {
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOVMI.3:");
                                    getLinearLayoutToTab(stringMtView,linearLayoutVMI);
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOVMI.31:");
                                }
                            }
                            return (linearLayoutVMI);
                        }
                    });
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOVMI.4:");
                    tabHost.addTab(tabSpecTMO);
                    tabHost.setCurrentTabByTag("vMI");
                    //Log.d(tagLog, "onItemSelected 7812:");
                    mainLinearLayoutTMOFragment.removeView(viewTabHostControl);//При повторном добавлении элемента в linearLayoutSVLControl обязательно нужно его удалить т.к. возникает ошибка,
                    mainLinearLayoutTMOFragment.addView(viewTabHostControl);
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOVMI.5:");
                }

            }
        };
        //Crash
        OnCheckedChangeListenerCheckBoxTMOCrash = new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!booleanTabCrash&isChecked){
                    linearLayoutCrash= new LinearLayout(context);
                    booleanGreatTabHost = true;
                    booleanTabCrash = true;
                    tabSpecTMO = tabHost.newTabSpec("crash");//Создаем вкладку MT и указываем тег
                    tabSpecTMO.setIndicator(getResources().getText(R.string.crash));// наименование берем из ресурса
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOCrash.1:");
                    tabSpecTMO.setContent(new TabHost.TabContentFactory() {
                        @Override
                        public View createTabContent(String tag) {
                            //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOCrash.2:"+listRowsColumnsCheck);
                            Iterator<String> iterator =listRowsColumnsCheck.iterator();
                            while(iterator.hasNext()){
                                String stringMtView = iterator.next();
                                boolean pattern = Pattern.matches("^crash_\\w*", stringMtView);
                                //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOCrash.21:");
                                if (pattern) {
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOCrash.3:");
                                    getLinearLayoutToTab(stringMtView,linearLayoutCrash);
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOCrash.31:");
                                }
                            }
                            return (linearLayoutCrash);
                        }
                    });
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOCrash.4:");
                    tabHost.addTab(tabSpecTMO);
                    tabHost.setCurrentTabByTag("crash");
                    //Log.d(tagLog, "onItemSelected 7812:");
                    mainLinearLayoutTMOFragment.removeView(viewTabHostControl);//При повторном добавлении элемента в linearLayoutSVLControl обязательно нужно его удалить т.к. возникает ошибка,
                    mainLinearLayoutTMOFragment.addView(viewTabHostControl);
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOCrash.5:");
                }

            }
        };
        //Storage
        OnCheckedChangeListenerCheckBoxTMOStorage = new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!booleanTabStorage&isChecked){
                    linearLayoutStorage= new LinearLayout(context);
                    booleanGreatTabHost = true;
                    booleanTabStorage = true;
                    tabSpecTMO = tabHost.newTabSpec("storage");//Создаем вкладку MT и указываем тег
                    tabSpecTMO.setIndicator(getResources().getText(R.string.storage));// наименование берем из ресурса
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOStorage.1:");
                    tabSpecTMO.setContent(new TabHost.TabContentFactory() {
                        @Override
                        public View createTabContent(String tag) {
                            //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOStorage.2:"+listRowsColumnsCheck);
                            Iterator<String> iterator =listRowsColumnsCheck.iterator();
                            while(iterator.hasNext()){
                                String stringMtView = iterator.next();
                                boolean pattern = Pattern.matches("^storage_\\w*", stringMtView);
                                //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOStorage.21:");
                                if (pattern) {
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOStorage.3:");
                                    getLinearLayoutToTab(stringMtView,linearLayoutStorage);
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOStorage.31:");
                                }
                            }
                            return (linearLayoutStorage);
                        }
                    });
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOStorage.4:");
                    tabHost.addTab(tabSpecTMO);
                    tabHost.setCurrentTabByTag("storage");
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOStorage.5:");
                    mainLinearLayoutTMOFragment.removeView(viewTabHostControl);//При повторном добавлении элемента в linearLayoutSVLControl обязательно нужно его удалить т.к. возникает ошибка,
                    mainLinearLayoutTMOFragment.addView(viewTabHostControl);
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOStorage.6:");
                }

            }
        };
        //Repair
        OnCheckedChangeListenerCheckBoxTMORepair = new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!booleanTabRepair&isChecked){
                    linearLayoutRepair= new LinearLayout(context);
                    booleanGreatTabHost = true;
                    booleanTabRepair = true;
                    tabSpecTMO = tabHost.newTabSpec("repair");//Создаем вкладку MT и указываем тег
                    tabSpecTMO.setIndicator(getResources().getText(R.string.repair));// наименование берем из ресурса
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMORepair.1:");
                    tabSpecTMO.setContent(new TabHost.TabContentFactory() {
                        @Override
                        public View createTabContent(String tag) {
                            //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMORepair.2:"+listRowsColumnsCheck);
                            Iterator<String> iterator =listRowsColumnsCheck.iterator();
                            while(iterator.hasNext()){
                                String stringMtView = iterator.next();
                                boolean pattern = Pattern.matches("^repair_\\w*", stringMtView);
                                //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMORepair.21:");
                                if (pattern) {
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMORepair.3:");
                                    getLinearLayoutToTab(stringMtView,linearLayoutRepair);
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMORepair.31:");
                                }
                            }
                            return (linearLayoutRepair);
                        }
                    });
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMORepair.4:");
                    tabHost.addTab(tabSpecTMO);
                    tabHost.setCurrentTabByTag("repair");
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMOStorage.5:");
                    mainLinearLayoutTMOFragment.removeView(viewTabHostControl);//При повторном добавлении элемента в linearLayoutSVLControl обязательно нужно его удалить т.к. возникает ошибка,
                    mainLinearLayoutTMOFragment.addView(viewTabHostControl);
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMORepair.6:");
                }

            }
        };
        //Note
        OnCheckedChangeListenerCheckBoxTMONote = new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!booleanTabNote&isChecked){
                    linearLayoutNote= new LinearLayout(context);
                    booleanGreatTabHost = true;
                    booleanTabNote = true;
                    tabSpecTMO = tabHost.newTabSpec("note");//Создаем вкладку MT и указываем тег
                    tabSpecTMO.setIndicator(getResources().getText(R.string.note));// наименование берем из ресурса
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMONote.1:");
                    tabSpecTMO.setContent(new TabHost.TabContentFactory() {
                        @Override
                        public View createTabContent(String tag) {
                            //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMONote.2:"+listRowsColumnsCheck);
                            Iterator<String> iterator =listRowsColumnsCheck.iterator();
                            while(iterator.hasNext()){
                                String stringMtView = iterator.next();
                                boolean pattern = Pattern.matches("^note\\w*", stringMtView);
                                //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMONote.21:");
                                if (pattern) {
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMONote.3:");
                                    getLinearLayoutToTab(stringMtView,linearLayoutNote);
                                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMONote.31:");
                                }
                            }
                            return (linearLayoutNote);
                        }
                    });
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMONote.4:");
                    tabHost.addTab(tabSpecTMO);
                    tabHost.setCurrentTabByTag("note");
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMONote.5:");
                    mainLinearLayoutTMOFragment.removeView(viewTabHostControl);//При повторном добавлении элемента в linearLayoutSVLControl обязательно нужно его удалить т.к. возникает ошибка,
                    mainLinearLayoutTMOFragment.addView(viewTabHostControl);
                    //Log.d(tagLog, "TMOFragment.OnCheckedChangeListenerCheckBoxTMONote.6:");
                }

            }
        };
        //
        try {

            //Log.d(tagLog, "TMOFragment.onCreateViewCheckBoxTMO,1:");
            for (int a = 0; a< listRowsColumnsCheck.size(); a++) {//ПОДУМАТЬ КАК МОЖНО ЭТО РЕАЛИЗОВАТЬ ЧЕРЕЗ SWITCH CASE
                if(listRowsColumnsCheck.get(a).equals(context.getString(R.string.data_base_no_information))){
                    Toast.makeText(context, context.getString(R.string.data_base_no_information), Toast.LENGTH_LONG).show();
                }
                else{
                    boolean patternMT = Pattern.matches("^mt_\\w*", listRowsColumnsCheck.get(a));
                    //Log.d(tagLog, "TMOFragment.onCreateViewCheckBoxTMO,2:");

                    if (patternMT&!booleanCheckBoxMt){
                        checkBoxMt = new CheckBox (context);
                        checkBoxMt.setTag("checkBoxMt");
                        checkBoxMt.setText(R.string.mt);
                        checkBoxMt.setOnCheckedChangeListener(OnCheckedChangeListenerCheckBoxTMOMt);
                        LLHSVCheckBoxTMO.addView(checkBoxMt);
                        booleanCheckBoxMt=true;
                        //Log.d(tagLog, "TMOFragment.onCreateViewCheckBoxTMO,2:");
                    }
                    boolean patternMDM = Pattern.matches("^mdm_\\w*", listRowsColumnsCheck.get(a));
                    if (patternMDM&!booleanCheckBoxMDM){
                        checkBoxMDM= new CheckBox (context);
                        checkBoxMDM.setTag("checkBoxMDM");
                        checkBoxMDM.setText(R.string.mDM);
                        checkBoxMDM.setOnCheckedChangeListener(OnCheckedChangeListenerCheckBoxTMOMDM);
                        LLHSVCheckBoxTMO.addView(checkBoxMDM);
                        booleanCheckBoxMDM=true;
                    }
                    boolean patternVMI = Pattern.matches("^vmi_\\w*", listRowsColumnsCheck.get(a));
                    if (patternVMI&!booleanCheckBoxVMI){
                        checkBoxVMI= new CheckBox (context);
                        checkBoxVMI.setTag("checkBoxVMI");
                        checkBoxVMI.setText(R.string.vMI);
                        checkBoxVMI.setOnCheckedChangeListener(OnCheckedChangeListenerCheckBoxTMOVMI);
                        LLHSVCheckBoxTMO.addView(checkBoxVMI);
                        booleanCheckBoxVMI=true;
                    }
                    boolean patternCrash = Pattern.matches("^crash_\\w*", listRowsColumnsCheck.get(a));
                    if (patternCrash&!booleanCheckBoxCrash){
                        checkBoxCrash= new CheckBox (context);
                        checkBoxCrash.setTag("checkBoxCrash");
                        checkBoxCrash.setText(R.string.crash);
                        checkBoxCrash.setOnCheckedChangeListener(OnCheckedChangeListenerCheckBoxTMOCrash);
                        LLHSVCheckBoxTMO.addView(checkBoxCrash);
                        booleanCheckBoxCrash=true;
                    }
                    boolean patternStorage = Pattern.matches("^storage_\\w*", listRowsColumnsCheck.get(a));
                    if (patternStorage&!booleanCheckBoxStorage){
                        checkBoxStorage= new CheckBox (context);
                        checkBoxStorage.setTag("checkBoxStorage");
                        checkBoxStorage.setText(R.string.storage);
                        checkBoxStorage.setOnCheckedChangeListener(OnCheckedChangeListenerCheckBoxTMOStorage);
                        LLHSVCheckBoxTMO.addView(checkBoxStorage);
                        booleanCheckBoxStorage= true;
                    }
                    boolean patternRepair = Pattern.matches("^repair_\\w*", listRowsColumnsCheck.get(a));
                    if (patternRepair&!booleanCheckBoxRepair){
                        checkBoxRepair= new CheckBox (context);
                        checkBoxRepair.setTag("checkBoxRepair");
                        checkBoxRepair.setText(R.string.repair);
                        checkBoxRepair.setOnCheckedChangeListener(OnCheckedChangeListenerCheckBoxTMORepair);
                        LLHSVCheckBoxTMO.addView(checkBoxRepair);
                        booleanCheckBoxRepair=true;
                    }
                    boolean patternNote = Pattern.matches("^note\\w*", listRowsColumnsCheck.get(a));
                    if (patternNote&!booleanCheckBoxNote){
                        checkBoxNote= new CheckBox (context);
                        checkBoxNote.setTag("checkBoxNote");
                        checkBoxNote.setText(R.string.note);
                        checkBoxNote.setOnCheckedChangeListener(OnCheckedChangeListenerCheckBoxTMONote);
                        LLHSVCheckBoxTMO.addView(checkBoxNote);
                        booleanCheckBoxNote=true;
                    }
                }

            }
            HSVCheckBoxTMO.removeView(LLHSVCheckBoxTMO);
            HSVCheckBoxTMO.addView(LLHSVCheckBoxTMO);
        } catch (Exception e) {
            Log.d(tagLog, "TMOFragment.onCreateViewCheckBoxTMO: "+e);
            Toast.makeText(context, context.getString(R.string.toast_checkbox)+": TMOFragment.onCreateViewCheckBoxTMO", Toast.LENGTH_LONG).show();

        }






        linearLayoutMDM= new LinearLayout(context);
        linearLayoutMDM.setOrientation(LinearLayout.VERTICAL);
        linearLayoutMDM.setLayoutParams(linLayoutParamMatchParent);
        linearLayoutVMI= new LinearLayout(context);
        linearLayoutVMI.setOrientation(LinearLayout.VERTICAL);
        linearLayoutVMI.setLayoutParams(linLayoutParamMatchParent);
        linearLayoutCrash= new LinearLayout(context);
        linearLayoutCrash.setOrientation(LinearLayout.VERTICAL);
        linearLayoutCrash.setLayoutParams(linLayoutParamMatchParent);
        linearLayoutStorage= new LinearLayout(context);
        linearLayoutStorage.setOrientation(LinearLayout.VERTICAL);
        linearLayoutStorage.setLayoutParams(linLayoutParamMatchParent);
        linearLayoutRepair= new LinearLayout(context);
        linearLayoutRepair.setOrientation(LinearLayout.VERTICAL);
        linearLayoutRepair.setLayoutParams(linLayoutParamMatchParent);
        linearLayoutNote= new LinearLayout(context);
        linearLayoutNote.setOrientation(LinearLayout.VERTICAL);
        linearLayoutNote.setLayoutParams(linLayoutParamMatchParent);
        tabHost =(TabHost) viewTabHostControl.findViewById(R.id.tabhostcontrol);
        tabHost.setup();





        OnCheckedChangeListenerCheckBoxTMO = new CompoundButton.OnCheckedChangeListener(){//пустой удалить

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        };


        return HSVCheckBoxTMO;
    }


}
