package com.example.intern06.lifereminder.fragmente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.adaptori.adaptorreminder;
import com.example.intern06.lifereminder.obiecte.reminder;

import java.util.List;

/**
 * Created by intern06 on 29.05.2017.
 */

public class adaptorsugestii extends ArrayAdapter<String> {


    private int layoutResource;
    private String ev;


    public adaptorsugestii(Context context, int layoutResource, List<String> list) {
        super(context, layoutResource, list);
        this.layoutResource = layoutResource;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);

        }
        TextView textnume=(TextView)view.findViewById(R.id.textsugestie);
        ImageView image=(ImageView)view.findViewById(R.id.imaginesugestie) ;



        ev=getItem(position);

        if (ev != null) {
            textnume.setText(ev);
            switch (ev){
               case "Cumpara ":image.setImageDrawable(view.getResources().getDrawable(R.drawable.transaction));break;
                case "Suna ":image.setImageDrawable(view.getResources().getDrawable(R.drawable.telephone));break;
                case "E-mail ":image.setImageDrawable(view.getResources().getDrawable(R.drawable.email));break;
                case "Curata ":image.setImageDrawable(view.getResources().getDrawable(R.drawable.bathtub));break;
                case "Verifica ":image.setImageDrawable(view.getResources().getDrawable(R.drawable.checked));break;
                case "Invata ":image.setImageDrawable(view.getResources().getDrawable(R.drawable.student));break;
                case "Trimite ":image.setImageDrawable(view.getResources().getDrawable(R.drawable.send));break;
                case "Plateste ":image.setImageDrawable(view.getResources().getDrawable(R.drawable.cash));break;
                case "Termina ":image.setImageDrawable(view.getResources().getDrawable(R.drawable.flag));break;
            }
        }

        return view;
    }



}