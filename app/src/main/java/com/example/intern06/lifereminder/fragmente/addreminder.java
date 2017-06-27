package com.example.intern06.lifereminder.fragmente;


import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.adaptori.adaptorreminder;
import com.example.intern06.lifereminder.obiecte.reminder;
import com.example.intern06.lifereminder.sql.ReminderDatabase;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.turkialkhateeb.materialcolorpicker.ColorChooserDialog;
import com.turkialkhateeb.materialcolorpicker.ColorListener;

import java.text.SimpleDateFormat;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by intern06 on 18.05.2017.
 */

public class addreminder extends Fragment {



    private View view;
    private static addreminder instace;
    private adaptorsugestii adaptor;
;
    private FloatingActionButton fab;
    private ImageView culoare;
    private EditText editText;
    private List<String> sugesiiList=new ArrayList<>();
    private StaggeredGridView gridView;
    private ImageView verif;
    private ReminderDatabase db;
    int culoarefundal=-1;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.addreminder,container,false);
        verif=(ImageView)view.findViewById(R.id.buttonverif);
        verif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH mm ss");
                String currentDateandTime = sdf.format(new Date());
                db=new ReminderDatabase(view.getContext());
                reminder rem=new reminder(0,editText.getText().toString(),currentDateandTime,0,5454,0,0,0);
                db.addReminder(rem);
               getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        editText=(EditText)view.findViewById(R.id.edittextreminder);
        editText.requestFocus();
        InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        imgr.showSoftInput(editText, 0);
        gridview();
        culoare=(ImageView)view.findViewById(R.id.culoare);
        culoare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChooserDialog dialog = new ColorChooserDialog(view.getContext());
                dialog.setTitle("Alege o culoare");
                dialog.setColorListener(new ColorListener() {
                    @Override
                    public void OnColorClick(View v, int color) {
                        editText.setBackgroundColor(color);
                        culoarefundal=color;
                    }
                });
                //customize the dialog however you want
                dialog.show();
            }
        });


        return view;
    }

    private void gridview() {
        gridView=(StaggeredGridView)view.findViewById(R.id.gridsugestii);

        sugesiiList.clear();
        sugesiiList.add("Cumpara ");
        sugesiiList.add("Suna ");
        sugesiiList.add("E-mail ");
        sugesiiList.add("Curata ");
        sugesiiList.add("Verifica ");
        sugesiiList.add("Invata ");
        sugesiiList.add("Trimite ");
        sugesiiList.add("Plateste ");
        sugesiiList.add("Termina ");
        adaptor=new adaptorsugestii(view.getContext(),R.layout.adaptorsugestii,sugesiiList);
        gridView.setAdapter(adaptor);

    }

    public static addreminder getInstace(){
        if(instace==null)
            instace=new addreminder();
        return instace;
    }}

