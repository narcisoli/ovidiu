package com.example.intern06.lifereminder.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.example.intern06.lifereminder.obiecte.note;
import com.example.intern06.lifereminder.obiecte.reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderDatabase extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "reminder";

    // Contacts table name
    private static final String TABLE_CONTACTS = "remindertable";

    // Contacts Table Columns names
    private static final String DATA = "data";
    private static final String TEXT = "text";
    private static final String STATUS = "status";
    private static final String CULOARETEXT = "culoaretext";
    private static final String CULOAREFUNDAL = "culoarefundal";
    private static final String ITALIC = "italic";
    private static final String SIZE="size";
    private static final String BOLD = "bold";



    public ReminderDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + DATA + " TEXT PRIMARY KEY," + TEXT + " TEXT,"
                + STATUS + " INTEGER,"+ CULOARETEXT + " INTEGER," + CULOAREFUNDAL + " INTEGER,"+ ITALIC + " INTEGER,"+ SIZE + " INTEGER,"+ BOLD + " INTEGER"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);


        onCreate(db);
    }
    public void addReminder(reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATA, reminder.getData());
        values.put(TEXT, reminder.getText());
        values.put(STATUS, reminder.getText());
        values.put(CULOARETEXT, reminder.getText());
        values.put(CULOAREFUNDAL, reminder.getText());
        values.put(ITALIC, reminder.getText());
        values.put(SIZE, reminder.getText());
        values.put(BOLD, reminder.getText());


        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }
    public List<reminder> getReminders() {
        List<reminder> contactList = new ArrayList<reminder>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                reminder rem=new reminder();
                rem.setData(cursor.getString(0));
                rem.setText(cursor.getString(1));
                rem.setStatus(cursor.getInt(2));
                rem.setCuloaretext(cursor.getInt(3));
                rem.setCuloarefundal(cursor.getInt(4));
                rem.setItalic(cursor.getInt(5));
                rem.setTextsize(cursor.getInt(6));
                rem.setBold(cursor.getInt(7));

                contactList.add(rem);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public void deleteContact(reminder note){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, DATA + " = ?",
                new String[]{note.getData()});


    }
}