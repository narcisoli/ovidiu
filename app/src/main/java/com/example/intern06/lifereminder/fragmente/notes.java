package com.example.intern06.lifereminder.fragmente;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.intern06.lifereminder.adaptori.adaptornota;
import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.obiecte.note;
import com.example.intern06.lifereminder.sql.DatabaseHandler;
import com.github.clans.fab.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class notes extends Fragment {

    private static notes instance;
    DatabaseHandler db;
    private RelativeLayout button;
    public adaptornota adaptor;
    private GridView lista;
    public List<note> list = new ArrayList<>();
    private View view;
    // newInstance constructor for creating fragment with arguments
    public static notes newInstance() {
        if (instance == null)
            instance = new notes();
        return instance;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.noteslayout, container, false);
        button = (RelativeLayout) view.findViewById(R.id.addnote);
        db = new DatabaseHandler(view.getContext());
        lista = (GridView) view.findViewById(R.id.lista);
        list = db.getNotes();
        adaptor = new adaptornota(view.getContext(), R.layout.adaptornota, list);
        lista.setAdapter(adaptor);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(view.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(view.getContext());
                }
                builder.setTitle("Stergere note")
                        .setMessage("Esti sigur ca vrei sa stergi nota?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteContact(list.get(position));
                                list.remove(list.get(position));
                                adaptor.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
                return false;
            }
        });
    lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String text = list.get(position).getText();
            addnote aux=addnote.getInstace();
            aux.refresh(text);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containermare, aux).addToBackStack("").commit();

        }
    });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnote.getInstace().setdb(db);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containermare, addnote.getInstace()).addToBackStack("").commit();

            }
        });
        return view;
    }

    public void refresh(note s) {

        list.add(s);
        adaptor.notifyDataSetChanged();
    }


}