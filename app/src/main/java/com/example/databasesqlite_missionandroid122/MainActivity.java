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

        //this wont work because this Id was deleted later
        Contact james = new Contact();
        james.setName("james");
        james.setContactNumber("635471080");

        Contact mathew = new Contact();
        mathew.setName("mathew");
        mathew.setContactNumber("5361774152");

        //once added then comment out
//        databaseHandler.addContact(james);
//        databaseHandler.addContact(mathew);

        //to get single contact
/*        Contact singleContact = databaseHandler.getContact(1);
        Log.d("SingleContact", "onCreate: "+singleContact.getName());
        //update a contact,doing update to 1
        singleContact.setName("newJames");
        singleContact.setContactNumber("0096541326");
        //updating
        int updateContact = databaseHandler.updateContact(singleContact);
        Log.d("Update", "onCreate: "+updateContact);
*/

        //delete a contact
        //once row 1 is deleted, make sure to comment out singleContact, and comment out deleteContact()
//        databaseHandler.deleteContact(singleContact);


            List<Contact> contactList = databaseHandler.getAllContact();
            for (Contact contact : contactList){
                Log.d("AllContact", "onCreate: "+contact.getId()+","+contact.getName()+", "+contact.getContactNumber());
            }


    }
}
