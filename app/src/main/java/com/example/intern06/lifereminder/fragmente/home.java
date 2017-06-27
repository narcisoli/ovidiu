package com.example.intern06.lifereminder.fragmente;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.intern06.lifereminder.R;

public class home extends Fragment {




    // newInstance constructor for creating fragment with arguments
    public static home newInstance() {
        home fragmentFirst = new home();
        return fragmentFirst;
    }


    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homelayout, container, false);
        return view;
    }
}