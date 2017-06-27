package com.example.intern06.lifereminder.obiecte;

/**
 * Created by intern06 on 18.05.2017.
 */

public class note {
    String text;
    String data;

    public note(String text, String data) {
        this.text = text;
        this.data = data;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
