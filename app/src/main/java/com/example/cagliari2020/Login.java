package com.example.cagliari2020;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class Login extends AppCompatActivity {

    /*URL url = new URL("http://localhost:3005/");//apro connessione
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();//prendo l'url
    BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));//leggo da quella connessione
    String data = "chiave1=valore1&chiave2=valore2\r\n";//corpo della richiesta
    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());//scrivo nella connessione*/

    private static final String TAG = "Login";

    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    public Login() throws IOException {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //prendo le informazioni inserite dall'utente
        mEmailView = (EditText) findViewById(R.id.attrEmail);
        mPasswordView = (EditText) findViewById(R.id.editText_password);

        Button mSignInButton = (Button) findViewById(R.id.titleLogin);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MakeNetworkRequestAsyncTask().execute();
            }
        });

        //gestisco la possibilità di registrazione
        TextView signInLink = (TextView) findViewById(R.id.titleRegistrati);
        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInAction();
            }
        });

        //gestisco il form del login
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

    }

    @Override
    public void onStart() {
        super.onStart();
        // mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        /*
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        */
    }

    //metodo per la registrazione
    private void signInAction() {
        Intent i = new Intent(Login.this, Registrazione.class);
        startActivityForResult(i,1);
    }


    //metodo per richiedere la password
    private void reAskPassword() {
        Intent i = new Intent(Login.this, PasswordDimenticata.class);
        startActivityForResult(i,0);
    }

    //controllo se la password è valida
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    //richiedo dei requisiti specifici per il funzionamento dell'applicazione
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    //gestisco il task del login
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mUsername;
        private final String mPassword;
        private Utente u = null;

        UserLoginTask(String username, String password) {
            mUsername = username;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            /*
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
            */
            //controllo se le credenziali inserite dall'utente siano presenti nella lista degli utenti
            UtentiFactory uf = UtentiFactory.getInstance();
            u = uf.check(mUsername,mPassword);
            if (u != null) {
                return true;
            }
            else
                Log.d(TAG,"Errore login: username="+mUsername+" password="+mPassword);
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            showProgress(false);

            if (success) {
                Intent i;

                SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("keyUser",u.getKey());
                editor.commit();
                //se l'utente si è autenticato allora può visualizzare le altre schermate
                if (u.getUtente()) {
                    i = new Intent(Login.this, UtenteActivity.class);
                }
                //MessaggiFactory.getInstance().startListener(); // carico i messaggi;

                i = new Intent(Login.this, UtenteActivity.class);

                startActivity(i);
                finish();
            } else {  //messaggio di errore se le credenziali non sono corrette
                mPasswordView.setError(getString(R.string.error_incorrect_credentials));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            showProgress(false);
        }
    }

    private class MakeNetworkRequestAsyncTask extends AsyncTask<Void, Void, Void> {
        // The system calls this to perform work in a worker thread and
        // delivers it the parameters given to AsyncTask.execute()
        protected Void doInBackground(Void[] params) {
            makeNetworkRequest();
            return null;
        }

    }

    private void makeNetworkRequest() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{ \"username\": \"" + email + "\" , \"password\":\""+ password +"\"}");
        Request request = new Request.Builder()
                .url("http://192.168.56.101:3005/authuser/signin")
                .post(body)
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtb2RlIjoibXMiLCJpc3MiOiJub3QgdXNlZCBmbyBtcyIsImVtYWlsIjoibm90IHVzZWQgZm8gbXMiLCJ0eXBlIjoiYXV0aG1zIiwiZW5hYmxlZCI6dHJ1ZSwiZXhwIjoxODI3NDgzMDc2MzQyfQ.kFnL1PDgIA8LEns_TLUzi9_JGu2Jm9zyMrjmdk7ppOw")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "ebf8e676-8c0a-09fb-1d0f-21647fffd810")
                .build();

        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(Login.this, Fotocamera.class);
        startActivityForResult(i,1);
    }
}
