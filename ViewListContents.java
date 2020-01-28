package com.passsafe;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.database.Cursor;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

//this code was created by Emma mc Atasney
public class ViewListContents extends PasswordGenerator {
    //declared variables
    Databasehelper myDB;
    ListView listView;
    private static final String TAG = "ViewListContents";

    //override the onCreate method
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//this sets the views for the layout, listView and declares the variable for the database.
        setContentView(R.layout.viewdata_layout);

        listView = (ListView) findViewById(R.id.listView);
        myDB = new Databasehelper(this);

        populatelistView();


    }
    private void populatelistView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = myDB.getListContents();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = myDB.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ViewListContents.this, EditData.class);
                    editScreenIntent.putExtra("id", itemID);
                    editScreenIntent.putExtra("name", name);
                    startActivity(editScreenIntent);
                } else {
                    toastMessage("No ID associated with that name");
                }
            }
        });

        /**
         * customizable toast
         * @param message
         */
    }

        private void toastMessage (String message){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }
        //this method will populate the list with the passwords.
   /* public void populateList() {
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();//error
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }

        }
    }/*

    /**
     * customizable toast
     * @param message
     */
   // private void toastMessage(String message){
       // Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    //}









