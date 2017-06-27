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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.obiecte.note;
import com.example.intern06.lifereminder.sql.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by intern06 on 18.05.2017.
 */

public class addnote extends Fragment {
    public String textt = "";
    private View view;
    private static addnote instace;
    private EditText edittext;
    private DatabaseHandler db;
    private ImageView buttonadd;
    private ImageView buttonback;
    private TextView data;
    private TextView titlunota;

    @Override
    public void onStart() {
        super.onStart();
        edittext.setText(textt);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.addnote, container, false);
        edittext = (EditText) view.findViewById(R.id.edittextnota);
        edittext.setText(textt);
        buttonadd = (ImageView) view.findViewById(R.id.buttonadd);
        buttonback = (ImageView) view.findViewById(R.id.butback);
        titlunota = (TextView) view.findViewById(R.id.titlunota);
        data = (TextView) view.findViewById(R.id.data);
        edittext.setText(textt);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy  HH:mm ss");
        String currentDateandTime = sdf.format(new Date());
        data.setText(currentDateandTime);
        edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    titlunota.setText("Editare nota");
                    buttonadd.setVisibility(View.VISIBLE);
                } else {
                    titlunota.setText("Nota");
                    buttonadd.setVisibility(View.GONE);
                }
            }
        });
        buttonadd.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM ");
                String currentDateandTime = sdf.format(new Date());
                db.addContact(new note(edittext.getText().toString().trim(), currentDateandTime));
                getActivity().getSupportFragmentManager().beginTransaction().remove(getActivity().getSupportFragmentManager().findFragmentById(R.id.containermare)).commit();
                notes.newInstance().refresh(new note(edittext.getText().toString(), currentDateandTime));
            }
        });
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext.setText("");
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    public static addnote getInstace() {
        if (instace == null)
            instace = new addnote();
        return instace;
    }

    public void setdb(DatabaseHandler db) {
        this.db = db;
    }

    public void refresh(String text) {
        textt = text;
    }
}
