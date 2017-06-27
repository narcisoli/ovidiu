package com.example.intern06.lifereminder.fragmente;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.example.intern06.lifereminder.FirebaseHandler;
import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.adaptori.adaptorreminder;
import com.example.intern06.lifereminder.obiecte.reminder;

import com.example.intern06.lifereminder.sql.ReminderDatabase;
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
    private List<reminder> aux = new ArrayList<>();
    private SearchView searchview;
    private TextView numetext;
    private ReminderDatabase db;
    private View view;
    private ImageView menu;
    boolean a=true;
    private ImageView menu1;

    public static reminderfragment newInstance() {
        reminderfragment fragmentFirst = new reminderfragment();
        return fragmentFirst;
    }


    @Override
    public void onResume() {
        super.onResume();
        db = new ReminderDatabase(view.getContext());
        reminderList = db.getReminders();
        aux = reminderList;
        adaptor.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.reminderfragment, container, false);
        menu = (ImageView) view.findViewById(R.id.menu);
        menu1 = (ImageView) view.findViewById(R.id.more);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a){
                listView.setColumnCount(1);
                a=false;
                adaptor.notifyDataSetChanged();}
                else
                {
                    a=true;
                    listView.setColumnCount(2);
                    adaptor.notifyDataSetChanged();
                }
            }
        });

        db = new ReminderDatabase(view.getContext());
        reminderList = db.getReminders();
        aux = reminderList;
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




        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu (view.getContext(), view);
                menu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener ()
                {
                    @Override
                    public boolean onMenuItemClick (MenuItem item)
                    {
                        int id = item.getItemId();
                        switch (id)
                        {
                            case R.id.item_delete:
                                Toast.makeText(view.getContext(), "delete", Toast.LENGTH_SHORT).show(); break;
                            case R.id.item_share: Toast.makeText(view.getContext(), "share", Toast.LENGTH_SHORT).show(); break;
                            case R.id.item_edit: Toast.makeText(view.getContext(), "edit", Toast.LENGTH_SHORT).show(); break;
                        }
                        return true;
                    }
                });
                menu.inflate (R.menu.menu);
                menu.show();
            }
        });



        searchview = (SearchView) view.findViewById(R.id.searchview);
        numetext = (TextView) view.findViewById(R.id.numetext);
        searchview.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    numetext.setVisibility(View.GONE);
                else
                    numetext.setVisibility(View.VISIBLE);
                aux = reminderList;
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
        for (int i = 0; i < reminderList.size(); i++)
            if (reminderList.get(i).getText().contains(query))
                aux.add(reminderList.get(i));
        adaptor.notifyDataSetChanged();
    }

}