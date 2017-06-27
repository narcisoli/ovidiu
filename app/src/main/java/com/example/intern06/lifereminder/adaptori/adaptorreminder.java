package com.example.intern06.lifereminder.adaptori;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.obiecte.event;
import com.example.intern06.lifereminder.obiecte.note;
import com.example.intern06.lifereminder.obiecte.reminder;

import java.util.List;

public class adaptorreminder extends ArrayAdapter<reminder> implements Filterable {



    private int layoutResource;
    private reminder ev;
    private View view;


    public adaptorreminder(Context context, int layoutResource, List<reminder> pizzalist) {
        super(context, layoutResource, pizzalist);
        this.layoutResource = layoutResource;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);

        }
        RelativeLayout relativeLayout=(RelativeLayout)view.findViewById(R.id.relfundal);
        TextView textnume=(TextView)view.findViewById(R.id.reminderdata);
        TextView descrioere=(TextView)view.findViewById(R.id.remindertext);
        ImageView menu1=(ImageView)view.findViewById(R.id.more);


        ev=getItem(position);

        if (ev != null) {
            textnume.setText(ev.getData());
            descrioere.setText(ev.getText());
            relativeLayout.setBackgroundColor(ev.getCuloarefundal());
            Log.i("Culori", ev.getCuloarefundal()+ev.getCuloaretext()+ev.getItalic()+ev.getBold()+"");


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
        }

        return view;
    }



}

