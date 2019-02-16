package com.example.cagliari2020;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cagliari2020.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

// la classe mi permette di inserire una nuova password  una volta che è stato verificato che l'email sia corretta
public class InserisciNuovaPassword extends AppCompatActivity{

    private static final String TAG = "InsertNewPassword";
    TextView mPassword;
    String email;
    private EditText mUsernameView;
    private EditText mPasswordView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recupero_password);

        String username = mUsernameView.getText().toString();
        String oldPassword = mPasswordView.getText().toString();
        String newPassword = mPassword.getText().toString();

        URL paginaURL = null;
        try {
            paginaURL = new URL("http://localhost:3020/action/setpassword" + username + oldPassword + newPassword);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection client = null;
        try {
            client = (HttpURLConnection) paginaURL.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String datiPost = null;
        try {
            datiPost = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        client.setDoOutput(true);

        OutputStreamWriter wr = null;
        try {
            wr = new OutputStreamWriter(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wr.write(datiPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wr.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.setChunkedStreamingMode(0);

        try {
            InputStream risposta = new BufferedInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                email= null;
            } else {
                email= extras.getString("email");
            }
        } else {
            email= (String) savedInstanceState.getSerializable("email");
        }

        mPassword = (TextView) findViewById(R.id.titleNuovaPassword);
        Button memorizza = (Button) findViewById(R.id.insert_new_password_button);
        memorizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memorizzaNewPassword();
            }
        });

    }
    //controllo che l'immissione della password rispetti il vincolo
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
    //memorizzo la nuova password
    private void memorizzaNewPassword() {
        String password = mPassword.getText().toString();

        mPassword.setError(null);
        Log.d(TAG,password);

        //controllo se sia valida o meno
        if (!isPasswordValid(password)) {
            mPassword.setError(getString(R.string.error_incorrect_password));
            mPassword.requestFocus();
        }
        else {  //se è valida avviene la modifica
            UtentiFactory.getInstance().changePassword(email,password);

            //messaggio che appare nella schermata che conferma all'utente che la password è stata cambiata con successo
            Toast.makeText(InserisciNuovaPassword.this,"Password cambiata con successo",Toast.LENGTH_LONG).show();

            Intent i = new Intent(InserisciNuovaPassword.this,Login.class);
            startActivity(i);
            finish();
        }

    }

}
