package com.example.intern06.lifereminder.adaptori;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.obiecte.event;
import com.example.intern06.lifereminder.obiecte.note;
import com.example.intern06.lifereminder.obiecte.reminder;

import java.util.List;

public class adaptorreminder extends ArrayAdapter<reminder> implements Filterable {



    private int layoutResource;
    private reminder ev;


    public adaptorreminder(Context context, int layoutResource, List<reminder> pizzalist) {
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
        RelativeLayout relativeLayout=(RelativeLayout)view.findViewById(R.id.relfundal);
        TextView textnume=(TextView)view.findViewById(R.id.reminderdata);
        TextView descrioere=(TextView)view.findViewById(R.id.remindertext);



        ev=getItem(position);

        if (ev != null) {
            textnume.setText(ev.getData());
            descrioere.setText(ev.getText());
            relativeLayout.setBackgroundColor(ev.getCuloarefundal());
            Log.i("Culori", ev.getCuloarefundal()+ev.getCuloaretext()+ev.getItalic()+ev.getBold()+"");
        }

        return view;
    }



}

