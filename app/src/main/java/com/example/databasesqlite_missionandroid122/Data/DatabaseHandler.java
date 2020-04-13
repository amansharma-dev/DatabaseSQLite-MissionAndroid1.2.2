package com.example.databasesqlite_missionandroid122.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.databasesqlite_missionandroid122.Model.Contact;
import com.example.databasesqlite_missionandroid122.R;
import com.example.databasesqlite_missionandroid122.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    //Create Table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //SQL - structured query language

        /*
        create table _name(id, name, contactNum)
        */

        String CREATE_CONTACT_TABLE = "CREATE TABLE "+ Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT,"
                + Util.KEY_CONTACT_NUMBER + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE); //creating our table
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_TABLE = String.valueOf(R.string.drop_table);
        sqLiteDatabase.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        //create table again
        onCreate(sqLiteDatabase);

    }

    /*
    CRUD- create , read, update, delete
     */

    //add contact
    public void addContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, contact.getName());
        contentValues.put(Util.KEY_CONTACT_NUMBER, contact.getContactNumber());

        //insert to row
        sqLiteDatabase.insert(Util.TABLE_NAME,null,contentValues);
        //log to check if data is saved
        Log.d("DbH", "addContact: item added");
        // close db connection
        sqLiteDatabase.close();
    }

    //get contact
    public Contact getContact(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        //add cursor to iterate
        Cursor cursor = sqLiteDatabase.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_CONTACT_NUMBER},
                Util.KEY_ID +"=?",new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

            Contact contact = new Contact();
            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setContactNumber(cursor.getString(2));
            return contact;
    }

    //get all contact
    public List<Contact> getAllContact(){

        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        //Select Contact
        String selectAllContact = "SELECT * FROM " +Util.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(selectAllContact,null);
        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setContactNumber(cursor.getString(2));
                //add contact objects to list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;
    }

    //update contact

    public int updateContact(Contact contact){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME,contact.getName());
        contentValues.put(Util.KEY_CONTACT_NUMBER,contact.getContactNumber());

        //update row
        return sqLiteDatabase.update(Util.TABLE_NAME,contentValues,
                Util.KEY_ID+"=?",
                new String[]{String.valueOf(contact.getId())});
    }

    //delete single contact
    public void deleteContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Util.TABLE_NAME,Util.KEY_ID+"=?",new String[]{String.valueOf(contact.getId())});

        sqLiteDatabase.close();
    }


}
