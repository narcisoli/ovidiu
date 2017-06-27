package com.example.intern06.lifereminder.obiecte;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intern02 on 19.05.2017.
 */

public class reminder {
    int status;
    String text;
    String data;
    List<memeto> memetoList;


    public reminder(){}
    public reminder(int status, String text, String data) {
        this.status = status;
        this.text = text;
        this.data = data;
        memetoList=new ArrayList<>();
    }

    public int getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public String getData() {
        return data;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setData(String data) {
        this.data = data;
    }
    public void adaugamemeto(memeto m){
        memetoList.add(m);
    }


}
