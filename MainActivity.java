package com.passsafe;
//import statements for the app
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v4.app.ActivityCompat;
import android.os.CancellationSignal;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;
//import  android.hardware.biometrics.BiometricPrompt;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
//import android.hardware.fingerprint.FingerprintManager.CryptoObject;
import androidx.biometric.BiometricPrompt;
import android.hardware.biometrics.BiometricManager;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import android.hardware.biometrics.BiometricPrompt.CryptoObject;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.Executor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import java.lang.NullPointerException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;
import 	java.security.KeyStoreException;
	import android.os.Build.VERSION_CODES;
import java.security.SignatureException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.Mac;
import javax.security.auth.Destroyable;
import java.security.Key;
import javax.crypto.interfaces.PBEKey;
import 	java.security.KeyStore.SecretKeyEntry;
import androidx.core.content.ContextCompat;




//this code was created by Emma mc Atasney B00710246
public class MainActivity extends AppCompatActivity {
    //implements NavigationView.OnNavigationItemSelectedListener {
//creating a string TAG
    private static final String TAG = MainActivity.class.getName();

    private String mToBeSignedMessage;

    // Unique identifier of a key pair
    private static final String KEY_NAME = UUID.randomUUID().toString();

// initalising objects
    private Context context;
    Button authenticateButton;
    private CancellationSignal cancellationSignal;
    private Executor executor;
    
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    Button btnSignin;

    //public MainActivity(Context context) {
    // this.context = context;
    //}

    public MainActivity() {

    }

    //this overides the onCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this will set the view for the layout of the main screen.
        setContentView(R.layout.activity_main);
        authenticateButton = (Button) findViewById(R.id.authenticateButton);
        btnSignin = (Button) findViewById(R.id.btnSignin);

        executor = ContextCompat.getMainExecutor(this);


    }




//this method takes the user to the password generator screen
    public void goToActivity2(View view) {
        Intent intent = new Intent(this, PasswordGenerator.class);
        startActivity(intent);
    }



/*
    private SecretKey canAuthenticateWithBiometrics() throws InvalidAlgorithmParameterException, KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, NoSuchPaddingException {












//https://developer.android.com/training/sign-in/biometric-auth this website was used for the following code bellow
//checks to see if authentication is available
        BiometricManager biometricManager = BiometricManager.from(this);
        if (Build.VERSION.SDK_INT >= VERSION_CODES.Q) {
            switch (biometricManager.canAuthenticate()) {
                case BiometricManager.BIOMETRIC_SUCCESS:
                    Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    Log.e("MY_APP_TAG", "No biometric features available on this device.");
                    break;
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    Log.e("MY_APP_TAG", "The user hasn't associated " +
                            "any biometric credentials with their account.");
                    break;
            }
        }
//this is used to display the fingerprint prompt on the app
        biometricPrompt = new BiometricPrompt(MainActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }
// uses the secret key to encrypt the passwords stored inside the app.
            @Override
            public void onAuthenticationSucceeded(
                       result: BiometricPrompt.AuthenticationResult) {
                  val encryptedInfo: ByteArray = result.cryptoObject.cipher?.doFinal(
                 plaintext-string.toByteArray(Charset.defaultCharset())
                  )
               Log.d("MY_APP_TAG", "Encrypted information: " +
                  Arrays.toString(encryptedInfo))
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        Button biometricLoginButton = findViewById(R.id.authenticateButton);
        biometricLoginButton.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });

        override fun onAuthenticationSucceeded(
        result: BiometricPrompt.AuthenticationResult) {
    val encryptedInfo: ByteArray = result.cryptoObject.cipher?.doFinal(
            plaintext-string.toByteArray(Charset.defaultCharset())
    )
    Log.d("MY_APP_TAG", "Encrypted information: " +
            Arrays.toString(encryptedInfo))
}
 */

/*

//https://developer.android.com/training/sign-in/biometric-auth  this website is wre i got the following code bellow
// this generates a secret key
        public void generateSecretKey(){
            KeyGenerator keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyGenerator.init(keyGenParameterSpec);
            keyGenerator.generateKey();
        }

 //this gets the secret key and stores it in the android key store.
        private SecretKey getSecretKey(){
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");

            // Before the keystore can be accessed, it must be loaded.
            keyStore.load(null);
            return ((SecretKey) keyStore.getKey(KEY_NAME, null));

        }



    //this creates the cipher for the app
        private Cipher getCipher () {
            return (SecretKey) Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        }

 // creates a key using KeyGenParameterSpec configuration
//setBlockModes, ensures that only the block modes specified can be used to both encrypt and decrypt the data
//the padding is set to PKCS7 to ensure that all data will be encrypted in block cipher encryption
        generateSecretKey(new KeyGenParameterSpec.Builder(
                KEY_NAME,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .setUserAuthenticationRequired(true)
                // Invalidate the keys if the user has registered a new biometric
                // credential, such as a new fingerprint. Can call this method only
                // on Android 7.0 (API level 24) or higher. The variable
                // "invalidatedByBiometricEnrollment" is true by default.
                .setInvalidatedByBiometricEnrollment(true)
                .build());





//https://developer.android.com/training/sign-in/biometric-auth

// this is a biometric authentication workflow that includes  a cipher

       public void gotoAuthicateButton (View v){

        authenticateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // biometricPrompt.authenticate(promptInfo);
                Cipher cipher = getCipher();
                SecretKey secretKey = getSecretKey();
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                biometricPrompt.authenticate(promptInfo,
                        new CryptoObject(cipher));
            }
        });
    }

*/

//this will is for the sign in button so when clicked it will go to the password generator screen.

    /*
        }
    }
}

//}

*/

}
