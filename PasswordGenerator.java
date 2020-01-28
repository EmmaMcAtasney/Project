package com.passsafe;
//this code has been written by Emma mc Atasney B00710246
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import android.database.sqlite.SQLiteOpenHelper;

public class PasswordGenerator extends AppCompatActivity {
   //declaring the variables for this class
    Button btnGenerate;
    TextView txtView;
    Databasehelper myDB;
    Button btnAdd, btnViewData;

    //overrides the create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this sets the view Id for the buttons, textview and layout of the class
        setContentView(R.layout.activity_password_generator);
        btnGenerate = (Button) findViewById(R.id.btnGenerate);
        txtView = (TextView) findViewById(R.id.txtView);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnViewData);
        myDB = new Databasehelper(this);
       // String text = txtView.getText().toString();



    }
//this method creates the password
    public void makePassword()
    {

        txtView.setText(generateString(12));
        String text = txtView.getText().toString();

    }
    //adds the data into the database
     public void addData(){
         Toast.makeText(PasswordGenerator.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
     }


//this adds the onClick listener onto the add data button so that when click it will add the password into the database.
    public void addDataOnClick(View v){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = txtView.getText().toString();
                if(txtView.length()!= 0){
                    AddData(newEntry);
                    txtView.setText("");
                }else{
                    Toast.makeText(PasswordGenerator.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //this adds an OnClick listener to the view Data button so that when clicked it will open the list View where the user can see the passwords.

    public void viewDataOnClick(View v){
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordGenerator.this, ViewListContents.class);
                startActivity(intent);
            }
        });
    }

    public void  passwordOnClick(View view) {
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                makePassword();
            }
        });
    }



//this generates an array that will contains characters that will be used to create the passwords
    private String generateString(int length){
        char[] chars = "0123456789qwertyuiopasdfghjklzxcvbnmASDFGGH$%&@".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i< length;i++){
            char ch = chars[random.nextInt(chars.length)];
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
//this will check to see if the data has been added into the database.
    public void AddData(String newEntry) {

        boolean insertData = myDB.addData(newEntry);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }


}
