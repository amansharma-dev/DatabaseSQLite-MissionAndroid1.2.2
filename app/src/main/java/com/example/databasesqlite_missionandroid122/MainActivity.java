package com.example.databasesqlite_missionandroid122;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.databasesqlite_missionandroid122.Data.DatabaseHandler;
import com.example.databasesqlite_missionandroid122.Model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);

        Contact james = new Contact();
        james.setName("james");
        james.setContactNumber("635471080");

        Contact mathew = new Contact();
        mathew.setName("mathew");
        mathew.setContactNumber("5361774152");
        
//        databaseHandler.addContact(james);
//        databaseHandler.addContact(mathew);

            List<Contact> contactList = databaseHandler.getAllContact();
            for (Contact contact : contactList){
                Log.d("MainActivity", "onCreate: "+contact.getId()+","+contact.getName()+", "+contact.getContactNumber());
            }


    }
}
