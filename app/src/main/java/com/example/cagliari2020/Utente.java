package com.example.cagliari2020;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.cagliari2020.R;

/**
 * Created by Stefano95 on 01/09/2017.
 */
//classe con tutte le informazioni riguardanti l'utente. Contiene i setter e i getter
public class Utente extends AppCompatActivity {

    private String key;
    private String email;
    private String password;
    private Boolean utente;

    public Utente()
    {
        key = "";
        email = "";
        password = "";
        utente = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getUtente() {
        return utente;
    }

    public void setUtente(Boolean utente) {
        this.utente = utente;
    }

}
