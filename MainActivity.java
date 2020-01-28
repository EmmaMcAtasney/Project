package com.passsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//this code was created by Emma mc Atasney B00710246
public class MainActivity extends AppCompatActivity {
    //this overides the onCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this will set the view for the layout of the main screen.
        setContentView(R.layout.activity_main);
    }


//this will is for the sign in button so when clicked it will go to the password generator screen.

    public void goToActivity2(View view) {
        Intent intent = new Intent(this, PasswordGenerator.class);
        startActivity(intent);
    }
}


