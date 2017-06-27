package com.example.intern06.lifereminder.adaptori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.obiecte.event;

import java.util.List;

public class adaptorrecomandare extends ArrayAdapter<event>  {


    private int layoutResource;
    private event ev;


    public adaptorrecomandare(Context context, int layoutResource, List<event> pizzalist) {
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
        TextView textnume=(TextView)view.findViewById(R.id.numerecomandare);
        TextView descrioere=(TextView)view.findViewById(R.id.descriererecomandare);


        ev=getItem(position);

        if (ev != null) {
            textnume.setText(ev.getNume());
            descrioere.setText(ev.getDescriere());
        }

        return view;
    }



}