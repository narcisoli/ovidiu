package com.example.intern06.lifereminder.fragmente;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.example.intern06.lifereminder.FirebaseHandler;
import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.adaptori.adaptorreminder;
import com.example.intern06.lifereminder.obiecte.reminder;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class reminderfragment extends Fragment {


    public static final String TAG = reminderfragment.class.getSimpleName();
    com.github.clans.fab.FloatingActionButton floatingActionButton;
    StaggeredGridView listView;
    private adaptorreminder adaptor;
    private List<reminder> reminderList = new ArrayList<>();
    private List<reminder> aux=new ArrayList<>();
    private SearchView searchview;
    private TextView numetext;


    public static reminderfragment newInstance() {
        reminderfragment fragmentFirst = new reminderfragment();
        return fragmentFirst;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.reminderfragment, container, false);
        FirebaseDatabase.getInstance().getReference().child("Reminder").child(FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reminderList.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    reminder aux=ds.getValue(reminder.class);
                    reminderList.add(aux);

                }


                Collections.reverse(reminderList);
               aux=reminderList;
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        floatingActionButton = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.fabreminder);
        adaptor = new adaptorreminder(view.getContext(), R.layout.adaptorreminder, aux);
        listView = (StaggeredGridView) view.findViewById(R.id.listareminder);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containermare, addreminder.getInstace(), TAG).addToBackStack("").commit();
            }
        });


        listView.setAdapter(adaptor);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(view.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(view.getContext());
                }
                builder.setTitle("Stergere reminder")
                        .setMessage("Esti sigur ca vrei sa stergi reminder?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                reminderList.remove(reminderList.get(position));
                                adaptor.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
                return false;
            }
        });

        searchview=(SearchView)view.findViewById(R.id.searchview);
        numetext=(TextView)view.findViewById(R.id.numetext);
        searchview.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    numetext.setVisibility(View.GONE);
                else
                    numetext.setVisibility(View.VISIBLE);
                    aux=reminderList;
                    adaptor.notifyDataSetChanged();
            }
        });
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adaptor.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return view;
    }

    private void updatelist(String query) {
        aux.clear();
        for(int i=0;i<reminderList.size();i++)
            if(reminderList.get(i).getText().contains(query))
                aux.add(reminderList.get(i));
        adaptor.notifyDataSetChanged();
    }


}