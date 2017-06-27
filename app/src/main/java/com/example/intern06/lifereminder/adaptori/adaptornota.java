package com.example.intern06.lifereminder.adaptori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.obiecte.event;
import com.example.intern06.lifereminder.obiecte.note;

import java.util.List;

public class adaptornota extends ArrayAdapter<note> {





    private int layoutResource;
    private note ev;


    public adaptornota(Context context, int layoutResource, List<note> pizzalist) {
        super(context, layoutResource, pizzalist);

        this.layoutResource = layoutResource;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);

        }
        TextView textnume=(TextView)view.findViewById(R.id.notadata);
        TextView descrioere=(TextView)view.findViewById(R.id.notatext);


        ev=getItem(position);

        if (ev != null) {
            String a=ev.getData();
            a= a.substring(0,a.length()-2);
            textnume.setText(a);
            descrioere.setText(ev.getText());
        }

        return view;
    }



}

