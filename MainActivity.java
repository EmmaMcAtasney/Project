package com.passsafe;

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

    private static final String TAG = MainActivity.class.getName();

    private String mToBeSignedMessage;

    // Unique identifier of a key pair
    private static final String KEY_NAME = UUID.randomUUID().toString();


    private Context context;
    Button authenticateButton;
    private CancellationSignal cancellationSignal;
    private Executor executor;
    //private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    //private BiometricPrompt biometricPrompt;
    // private BiometricPrompt.PromptInfo promptInfo;
    //private Toolbar toolbar;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        //Button authenticateButton = findViewById(R.id.biometric_login);
        //  checkBiometricSupport();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
        executor = ContextCompat.getMainExecutor(this);



    }


   /* var km = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val biometricPrompt = BiometricPrompt.Builder(this)
                .setTitle(getString(R.string.screen_lock_title))
                .setDescription(getString(R.string.screen_lock_desc))
                .setDeviceCredentialAllowed(true)
                .build()

        val cancellationSignal = CancellationSignal()
        cancellationSignal.setOnCancelListener {
            println("@Biometric cancellationSignal.setOnCancelListener")
            //handle cancellation
        }


        val executors = mainExecutor
        val authCallBack = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                print("SecuritySetupActivity.onAuthenticationError ")
                println("@Biometric errorCode = [${errorCode}], errString = [${errString}]")
                //handle authentication error
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                print("SecuritySetupActivity.onAuthenticationSucceeded ")
                println("@Biometric result = [${result}]")

                //handle authentication success
            }
            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)
                print("SecuritySetupActivity.onAuthenticationHelp ")
                println("@Biometric helpCode = [${helpCode}], helpString = [${helpString}]")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                print("SecuritySetupActivity.onAuthenticationFailed ")

                //handle authentication failed
            }
        }



        biometricPrompt.authenticate(cancellationSignal, executors, authCallBack)


    } else {
        val i = km.createConfirmDeviceCredentialIntent(getString(R.string.screen_lock_title), getString(R.string.screen_lock_desc))
        startActivityForResult(i, 100)
    }
   /* public void fab(View view) {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_register) {
            if (canAuthenticateWithBiometrics()) {  // Check whether this device can authenticate with biometrics
                Log.i(TAG, "Try registration");
                // Generate keypair and init signature
                Signature signature;
                try {
                    KeyPair keyPair = generateKeyPair(KEY_NAME, true);
                    // Send public key part of key pair to the server, this public key will be used for authentication
                    mToBeSignedMessage = Base64.encodeToString(keyPair.getPublic().getEncoded(), Base64.URL_SAFE) +
                            ":" +
                            KEY_NAME +
                            ":" +
                            // Generated by the server to protect against replay attack
                            "12345";

                    signature = initSignature(KEY_NAME);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                // Create biometricPrompt
                showBiometricPrompt(signature);
            } else {
                // Cannot use biometric prompt
                Toast.makeText(this, "Cannot use biometric", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_authenticate) {
            if (canAuthenticateWithBiometrics()) {  // Check whether this device can authenticate with biometrics
                Log.i(TAG, "Try authentication");

                // Init signature
                Signature signature;
                try {
                    // Send key name and challenge to the server, this message will be verified with registered public key on the server
                    mToBeSignedMessage = KEY_NAME +
                            ":" +
                            // Generated by the server to protect against replay attack
                            "12345";
                    signature = initSignature(KEY_NAME);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                // Create biometricPrompt
                showBiometricPrompt(signature);
            } else {
                // Cannot use biometric prompt
                Toast.makeText(this, "Cannot use biometric", Toast.LENGTH_SHORT).show();
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showBiometricPrompt(Signature signature) {
        BiometricPrompt.AuthenticationCallback authenticationCallback = getAuthenticationCallback();
        BiometricPrompt mBiometricPrompt = new BiometricPrompt(this, getMainThreadExecutor(), authenticationCallback);

        // Set prompt info
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setDescription("Description")
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setNegativeButtonText("Cancel")
                .build();

        // Show biometric prompt
        if (signature != null) {
            Log.i(TAG, "Show biometric prompt");
            mBiometricPrompt.authenticate(promptInfo, new BiometricPrompt.CryptoObject(signature));
        }
    }

    private BiometricPrompt.AuthenticationCallback getAuthenticationCallback() {
        // Callback for biometric authentication result
        return new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                Log.i(TAG, "onAuthenticationSucceeded");
                super.onAuthenticationSucceeded(result);
                if (result.getCryptoObject() != null &&
                        result.getCryptoObject().getSignature() != null) {
                    try {
                        Signature signature = result.getCryptoObject().getSignature();
                        signature.update(mToBeSignedMessage.getBytes());
                        String signatureString = Base64.encodeToString(signature.sign(), Base64.URL_SAFE);
                        // Normally, ToBeSignedMessage and Signature are sent to the server and then verified
                        Log.i(TAG, "Message: " + mToBeSignedMessage);
                        Log.i(TAG, "Signature (Base64 EncodeD): " + signatureString);
                        Toast.makeText(getApplicationContext(), mToBeSignedMessage + ":" + signatureString, Toast.LENGTH_SHORT).show();
                    } catch (SignatureException e) {
                        throw new RuntimeException();
                    }
                } else {
                    // Error
                    Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        };
    }

    private KeyPair generateKeyPair(String keyName, boolean invalidatedByBiometricEnrollment) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_EC, "AndroidKeyStore");

        KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(keyName,
                KeyProperties.PURPOSE_SIGN)
                .setAlgorithmParameterSpec(new ECGenParameterSpec("secp256r1"))
                .setDigests(KeyProperties.DIGEST_SHA256,
                        KeyProperties.DIGEST_SHA384,
                        KeyProperties.DIGEST_SHA512)
                // Require the user to authenticate with a biometric to authorize every use of the key
                .setUserAuthenticationRequired(true);

        // Generated keys will be invalidated if the biometric templates are added more to user device
        if (Build.VERSION.SDK_INT >= 24) {
            builder.setInvalidatedByBiometricEnrollment(invalidatedByBiometricEnrollment);
        }

        keyPairGenerator.initialize(builder.build());

        return keyPairGenerator.generateKeyPair();
    }


    @Nullable
    private KeyPair getKeyPair(String keyName) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        if (keyStore.containsAlias(keyName)) {
            // Get public key
            PublicKey publicKey = keyStore.getCertificate(keyName).getPublicKey();
            // Get private key
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyName, null);
            // Return a key pair
            return new KeyPair(publicKey, privateKey);
        }
        return null;
    }

    @Nullable
    private Signature initSignature(String keyName) throws Exception {
        KeyPair keyPair = getKeyPair(keyName);

        if (keyPair != null) {
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(keyPair.getPrivate());
            return signature;
        }
        return null;
    }

    private Executor getMainThreadExecutor() {
        return new MainThreadExecutor();
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable r) {
            handler.post(r);
        }
    }

    /**
     * Indicate whether this device can authenticate the user with biometrics
     *
     * @return true if there are any available biometric sensors and biometrics are enrolled on the device, if not, return false
     */

    private SecretKey canAuthenticateWithBiometrics() throws InvalidAlgorithmParameterException, KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, NoSuchPaddingException {

        //Check whether the fingerprint can be used for authentication (Android M to P)
        //   if (Build.VERSION.SDK_INT < 29) {
        //   FingerprintManagerCompat fingerprintManagerCompat = FingerprintManagerCompat.from(this);
        //   return fingerprintManagerCompat.hasEnrolledFingerprints() && fingerprintManagerCompat.isHardwareDetected();
        //  } else {    // Check biometric manager (from Android Q)
        //   BiometricManager biometricManager = this.getSystemService(BiometricManager.class);
        //   if (biometricManager != null) {
        //       return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS;
        //   }
        //   return false;
        //  }

        // }
        // }

    /*  public void   override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)

            //The device does not have a biometric sensor.
            if (errorCode == BiometricPrompt.ERROR_HW_NOT_PRESENT){
                //Do something
            }
        }
*/

        //Executor executor = Executors.newSingleThreadExecutor();

        //FragmentActivity activity = this;
//
  /*  private void notifyUser(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG).show();
    }
/*

   /* private Boolean checkBiometricSupport() {

        KeyguardManager keyguardManager =
                (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        PackageManager packageManager = this.getPackageManager();

        if (!keyguardManager.isKeyguardSecure()) {
            notifyUser("Lock screen security not enabled in Settings");
            return false;
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.USE_BIOMETRIC) !=
                PackageManager.PERMISSION_GRANTED) {

            notifyUser("Fingerprint authentication permission not enabled");
            return false;
        }
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT))
        {
            return true;
        }

        return true;
    }

   /* private BiometricPrompt.AuthenticationCallback getAuthenticationCallback() {

        return new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              CharSequence errString) {
                notifyUser("Authentication error: " + errString);
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationHelp(int helpCode,
                                             CharSequence helpString) {
                super.onAuthenticationHelp(helpCode, helpString);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
            @Override
            public void onAuthenticationSucceeded(
                    BiometricPrompt.AuthenticationResult result) {
                notifyUser("Authentication Succeeded");
                super.onAuthenticationSucceeded(result);
            }
        };
    }

    private CancellationSignal getCancellationSignal() {

        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new
                                                       CancellationSignal.OnCancelListener() {
                                                           @Override
                                                           public void onCancel() {
                                                               notifyUser("Cancelled via signal");
                                                           }
                                                       });
        return cancellationSignal;
    }


    public void authenticateUser(View view) {
        BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("Biometric Demo")
                .setSubtitle("Authentication is required to continue")
                .setDescription("This app uses biometric authentication to protect your data.")
                .setNegativeButton("Cancel", this.getMainExecutor(),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                notifyUser("Authentication cancelled");
                            }
                        })
                .build();

        biometricPrompt.authenticate(getCancellationSignal(), getMainExecutor(),
                getAuthenticationCallback());
    }﻿

    */


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

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
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

        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        String masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);


        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "SC");
        ECGenParameterSpec spec = new ECGenParameterSpec("secp256k1");
        keyPairGenerator.initialize(spec, new SecureRandom());
        /*
         * Generate a new EC key pair entry in the Android Keystore by
         * using the KeyPairGenerator API. The private key can only be
         * used for signing or verification and only with SHA-256 or
         * SHA-512 as the message digest.
         */

     /*   public static void createKeys(Context context, String alias) throws NoSuchProviderException,
                NoSuchAlgorithmException, InvalidAlgorithmParameterException {
            if (!isSigningKey(alias)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    createKeysM(alias, false);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    createKeysJBMR2(context, alias);
                }
            }
        }

      */

        //KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        // keyStore.load(null);
        // KeyStore.Entry entry = keyStore.getEntry(alias, null);
        // PrivateKey privateKey = ((KeyStore.PrivateKeyEntry) entry).getPrivateKey();
        // PublicKey publicKey = keyStore.getCertificate(alias).getPublicKey();


      /*  public static boolean isSigningKey (String alias){
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_EC, "AndroidKeyStore");
            kpg.initialize(new KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_SIGN | KeyProperties.PURPOSE_VERIFY)
                    .setDigests(KeyProperties.DIGEST_SHA256,
                            KeyProperties.DIGEST_SHA512)
                    .build());

            KeyPair kp = kpg.generateKeyPair();
        }

*/




        public void generateSecretKey(){
            KeyGenerator keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyGenerator.init(keyGenParameterSpec);
            keyGenerator.generateKey();
        }
//https://developer.android.com/reference/java/security/KeyStore.SecretKeyEntry
        private SecretKey getSecretKey(){
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");

            // Before the keystore can be accessed, it must be loaded.
            keyStore.load(null);
            return ((SecretKey) keyStore.getKey(KEY_NAME, null));

        }

//https://gist.github.com/JosiasSena/3bf4ca59777f7dedcaf41a495d96d984
//https://developer.android.com/reference/android/hardware/biometrics/BiometricPrompt.CryptoObject#getCipher()
        private Cipher getCipher () {
            return (SecretKey) Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        }

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

        biometricLoginButton.setOnClickListener(view -> {
            // Exceptions are unhandled within this snippet.
            Cipher cipher = getCipher();
            SecretKey secretKey = getSecretKey();
            try {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
            biometricPrompt.authenticate(promptInfo,
                    new BiometricPrompt.CryptoObject(cipher));
        });



     /*   private void generateSecretKey(KeyGenParameterSpec keyGenParameterSpec) {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyGenerator.init(keyGenParameterSpec);
            keyGenerator.generateKey();
        }

    /*    private SecretKey getSecretKey() {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");

            // Before the keystore can be accessed, it must be loaded.
            keyStore.load(null);
            return ((SecretKey)keyStore.getKey(KEY_NAME, null));
        }

        private Cipher getCipher() {
            return Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        }


//https://developer.android.com/training/sign-in/biometric-auth

        generateSecretKey(new Builder(
                KEY_NAME,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .setUserAuthenticationRequired(true)
                .setUserAuthenticationValidityDurationSeconds(VALIDITY_DURATION)
                .setInvalidatedByBiometricEnrollment(true)
                .build());





       final  BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    // user clicked negative button
                    Toast.makeText(getApplicationContext(),
                            "Authentication error: " + errString, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    //TODO: Called when an unrecoverable error has been encountered and the operation is complete.
                }
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //TODO: Called when a biometric is recognized.
                // NullPointerException is unhandled; use Objects.requireNonNull().
                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                byte[] encryptedInfo = result.getCryptoObject().getCipher().doFinal(
                        plaintext-string.getBytes(Charset.defaultCharset()));
                Log.d("MY_APP_TAG", "Encrypted information: " +
                        Arrays.toString(encryptedInfo));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                //TODO: Called when a biometric is valid but not recognized.
                Toast.makeText(getApplicationContext(), "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login or my app.")
                .setSubtitle("Log in using your biometric credential.")
                .setDescription("log into your app with the fingerprint scanner")
                .setNegativeButtonText("Use account password")
                .build();


       // findViewById(R.id.authenticateButton).setOnClickListener(new View.OnClickListener() {
        //  @Override
        // public void onClick (View v){
       //  biometricPrompt.authenticate(promptInfo);
       // }
       // });
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








  /*  public boolean isHardwareSupported(context  Context) {
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
*/

//this will is for the sign in button so when clicked it will go to the password generator screen.

        public void goToActivity2 (View view){
            Intent intent = new Intent(this, PasswordGenerator.class);
            startActivity(intent);
        }
    }


}




