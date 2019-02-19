package com.example.cagliari2020;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.crypto.Cipher;

public class ImprontaDigitale extends AppCompatActivity{

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private FingerprintManager.CryptoObject cryptoObject;
    private CancellationSignal cancellationSignal;
    private AlertDialog dialog;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        keyguardManager =
                (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager =
                    (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (!keyguardManager.isKeyguardSecure()) {

                Toast.makeText(this, "Il dispositivo non dispone di alcun blocco",Toast.LENGTH_LONG).show();
                return;
            }
        }

        Cipher cipher=CipherGenerator.getCipher();
        if (cipher!=null)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cryptoObject = new FingerprintManager.CryptoObject(cipher);
            }
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        String msg = null;
        String title = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT)
                    == PackageManager.PERMISSION_GRANTED && fingerprintManager.hasEnrolledFingerprints() && cryptoObject!=null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    cancellationSignal = new CancellationSignal();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, callback, null);
                }
                title="Autenticazione richiesta";
                msg="Quest'app è soggetta a controllo mediante impronta digitale";
            }
            else {
                title="Errore";
                msg = "Impossibile procedere con autenticazione via impronta digitale";
            }
        }

        dialog=new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_fp_40px)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(msg)
                .create();
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cancellationSignal != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                cancellationSignal.cancel();
            }
            cancellationSignal= null;
        }
    }

    public void cliccato(View v)
    {
        Toast.makeText(ImprontaDigitale.this, "App funziona", Toast.LENGTH_LONG).show();
    }

    private FingerprintManager.AuthenticationCallback callback= new FingerprintManager.AuthenticationCallback() {


        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            // errore non rimediabile, autenticazione terminata
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId,
                                         CharSequence helpString) {
            // errore rimediabile, l'autenticazione procede
        }

        @Override
        public void onAuthenticationFailed() {
            // impronta valida ma non riconosciuta
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            // autenticazione riuscita, sblocchiamo app
            dialog.dismiss();
        }
    };

}
