package com.example.intern06.lifereminder.fragmente.FragmenteProfil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.intern06.lifereminder.R;


public class Despre extends Fragment {

    private View view;


    public static Despre newInstance() {
        Despre fragment = new Despre();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_despre, container, false);
        return view;
    }


}
