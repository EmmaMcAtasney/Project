package com.passsafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import  android.hardware.biometrics.BiometricPrompt;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.biometrics.BiometricPrompt;


//this code was created by Emma mc Atasney B00710246
public class MainActivity extends AppCompatActivity {
    private  Context context;

    public MainActivity(Context context){
        this.context = context;
    }
    //this overides the onCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this will set the view for the layout of the main screen.
        setContentView(R.layout.activity_main);


    }


    public boolean isHardwareSupported(context  Context) {
        FingerprintManager fingerprintManager =  FingerprintManagerCompat.from(context);
        return fingerprintManager.isHardwareDetected();
    }

    boolean isFingerprintAvailable(context  Context) {
        FingerprintManager fingerprintManager = FingerprintManagerCompat.from(context);
        return fingerprintManager.hasEnrolledFingerprints();
    }

    boolean isSdkVersionSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    protected BiometricPrompt.PromptInfo buildBiometricPrompt() {
        return new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setSubtitle("Login into your account")
                .setDescription("Touch your finger on the finger print sensor to authorise your account.")
                .setNegativeButtonText("Cancel")
                .build();
    }



//this will is for the sign in button so when clicked it will go to the password generator screen.

    public void goToActivity2(View view) {
        Intent intent = new Intent(this, PasswordGenerator.class);
        startActivity(intent);
    }
}


