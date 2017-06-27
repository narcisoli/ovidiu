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
    int culoaretext;
    int culoarefundal;
    int bold;
    int italic;
    int textsize;

    public int getBold() {
        return bold;
    }

    public void setBold(int bold) {
        this.bold = bold;
    }

    public int getItalic() {
        return italic;
    }

    public void setItalic(int italic) {
        this.italic = italic;
    }

    public int getCuloaretext() {
        return culoaretext;
    }

    public void setCuloaretext(int culoaretext) {
        this.culoaretext = culoaretext;
    }

    public int getCuloarefundal() {
        return culoarefundal;
    }

    public void setCuloarefundal(int culoarefundal) {
        this.culoarefundal = culoarefundal;
    }


    public int getTextsize() {
        return textsize;
    }

    public void setTextsize(int textsize) {
        this.textsize = textsize;
    }

    public reminder(){}

    public reminder(int status, String text, String data, int culoaretext, int culoarefundal, int bold, int italic, int textsize) {
        this.status = status;
        this.text = text;
        this.data = data;
        this.culoaretext = culoaretext;
        this.culoarefundal = culoarefundal;
        this.bold = bold;
        this.italic = italic;
        this.textsize = textsize;
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



}
