package com.example.intern06.lifereminder.obiecte;

/**
 * Created by intern06 on 15.05.2017.
 */

public class event {
    int id;
    String nume;

    public event() {
        this.id=0;
        this.nume="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    String descriere;

    public event(int id, String nume, String descriere) {
        this.id = id;
        this.nume = nume;
        this.descriere = descriere;
    }
}
