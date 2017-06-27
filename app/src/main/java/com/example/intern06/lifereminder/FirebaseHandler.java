package com.example.intern06.lifereminder;

import com.example.intern06.lifereminder.obiecte.reminder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inter99 on 26.05.2017.
 */

public class FirebaseHandler {
    List<reminder> reminderList;
    DatabaseReference databaseReference;
    private static FirebaseHandler instance;
    private FirebaseHandler(){
        this.reminderList=new ArrayList<>();
        this.databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    public List<reminder> getReminder(){

        databaseReference.child("reminder").child(FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reminderList.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    reminderList.add(dataSnapshot1.getValue(reminder.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return reminderList;

    }

    public static FirebaseHandler getInstance(){
        if (instance==null)
            instance=new FirebaseHandler();
        return instance;

    }
}
