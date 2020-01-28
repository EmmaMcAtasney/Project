package com.passsafe;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditData extends AppCompatActivity {
    private static final String TAG = "EditData";
    private Button btnSave,btnDelete;
    private EditText editable_item;
    Databasehelper myDatabasehelper;
    private String selectedPassword;
    private int selectedID;
    TextView txtView;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdata_layout);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        txtView = (TextView) findViewById(R.id.txtView);
        myDatabasehelper = new Databasehelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedPassword = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        editable_item.setText(selectedPassword);


     btnSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String item = editable_item.getText().toString();
            if(!item.equals("")){
                myDatabasehelper.updateName(item,selectedID,selectedPassword);
            }else{
                toastMessage("You must enter a name");
            }
        }
    });

        btnDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            myDatabasehelper.deleteName(selectedID,selectedPassword);
            editable_item.setText("");
            toastMessage("removed from database");
        }
    });

}

    public void AddData(String newEntry) {
        boolean insertData = myDatabasehelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }
        /**
         * customizable toast
         * @param message
         */
        private void toastMessage(String message){
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        }

    }
