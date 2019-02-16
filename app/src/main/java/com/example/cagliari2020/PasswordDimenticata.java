package com.example.cagliari2020;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.cagliari2020.R;

import java.io.IOException;

//classe che getisce il caso di password dimenticata
public class PasswordDimenticata extends AppCompatActivity{

    private EditText mEmailView;

    public PasswordDimenticata() throws IOException {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_password_dimenticata);

        //partiamo dal link di password dimenticata presente nella schermata del login
        Button mLostPasswordButton = (Button) findViewById(R.id.password_lost_button);
        mLostPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*
            Intent i = new Intent(Intent.ACTION_SEND);

            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{mEmailView.getText().toString()});
            i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
            i.putExtra(Intent.EXTRA_TEXT   , "body of email");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            }
            catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(LostPassword.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
            */
                //controlliamo se l'email inserita dall'utente è presente della lista degli utenti
                mEmailView.setError(null);

                boolean cancel = false;
                View focusView = null;

                String email = mEmailView.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    //avvisiamo l'utente che il form è da compilare
                    mEmailView.setError("Questo campo è obbligatorio");
                    if (!cancel) focusView = mEmailView;
                    cancel = true;

                } /*else if (!isEmailValid(email)) {
                    //se la mail inserita non esiste mandiamo un messaggio d'errore
                    mEmailView.setError(getString(R.string.error_invalid_email));
                    mEmailView.requestFocus();
                } else {
                    //se invece esiste mandiamo l'utente alla schermata dove inserendo un codice che arriva per posta elettronica
                    //potrà cambiare password
                    if (UtentiFactory.getInstance().emailExist(email)) {
                        Intent i = new Intent(PasswordDimenticata.this, InserisciNuovaPassword.class);
                        i.putExtra("email", email);
                        startActivity(i);
                        finish();
                    } else {
                        mEmailView.setError(getString(R.string.error_email_not_exist));
                        mEmailView.requestFocus();
                    }
                }*/
            }
        });
    }
    //piccolo controllo relativo alla validazione delle email
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

}