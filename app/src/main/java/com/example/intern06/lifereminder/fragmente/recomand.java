package com.example.intern06.lifereminder.fragmente;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.adaptori.adaptorrecomandare;
import com.example.intern06.lifereminder.obiecte.event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class recomand extends Fragment {

    List<event> recomandareList=new ArrayList<>();
    private ListView lista;
    private DatabaseReference ref;


    // newInstance constructor for creating fragment with arguments
    public static recomand newInstance() {
        recomand fragmentFirst = new recomand();
        return fragmentFirst;
    }




    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recomand, container, false);
        final adaptorrecomandare adaptorecomandare=new adaptorrecomandare(view.getContext(),R.layout.adaptorrecomand,recomandareList);
        lista=(ListView)view.findViewById(R.id.listarecomand);
        lista.setAdapter(adaptorecomandare);
        ref=FirebaseDatabase.getInstance().getReference().child("recomand");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                recomandareList.clear();
                for (DataSnapshot msgSnapshot: snapshot.getChildren()) {
                    event msg = msgSnapshot.getValue(event.class);

                    recomandareList.add(msg);

                }
                adaptorecomandare.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
            }
        });







        return view;
    }
}