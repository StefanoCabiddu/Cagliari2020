package com.example.cagliari2020;

import android.util.Log;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.MissingResourceException;

//classe che gestisce la comunicazione tra schermata login e database

public class UtentiFactory {
    private static UtentiFactory ul;
    private ArrayList ulist = new ArrayList();
    private Utente current;
    private static String TAG = "UtentiFactory";
    private Object DataSnapshot;
    private String s;
//    private UserLoginListener listener;

    public static UtentiFactory getInstance() {
        if (ul == null) {
            ul = new UtentiFactory();
        }
        return ul;
    }
    //controllo se l'utente che si sta loggando esiste
    public Utente check(String mEmail, String mPassword) {
        Utente u = findUserByEmail(mEmail);
        if (u != null) {
            Log.d(TAG,"trovato utente: "+u.getEmail() + "," + u.getPassword());
            if (u.getPassword().equals(mPassword)) {
                setCurrent(u);
                return u;
            }
        }
        else
            Log.d(TAG,"Non trovato utente: "+mEmail + "," + mPassword);
        return null;
    }
    //utente trovato
    public void setCurrent(Utente u) {
        current = u;
//        if (listener != null)
//            listener.onUserLogin(u);
    }
    public Utente getCurrent() {
        return current;
    }
    //qua aggiungo alla lista l'utente che si è registrato per la prima volta
    public void add(Utente u) {
        String key = ul.push().getKey();
        u.setKey(key);
        ul.child(key).setValue(String.valueOf(u));
    }

    private Attr child(String key) {
        return null;
    }

    private MissingResourceException push() {
        return null;
    }
    //controllo che lo username e l'email esistano
    public boolean usernameExist(String username) {
        return (findUserByEmail(username) != null);
    }

    public boolean emailExist(String email) {
        return (findUserByEmail(email) != null);
    }
    //do la possibilità in caso di password dimenticata di poterla modificare
    public void changePassword(String email, String password) {
        Utente u = findUserByEmail(email);
        if (u != null) {
            u.setPassword(password);
            //ul.child(u.getKey()).updatechildren(u.toMap());
        }Chat
                ChatPending;
        ListaMsgAdapter
                Login;
        MessaggiFactory
                MyAppCompatActivity;
        ServizioNotifiche
                PasswordDimenticata;
        InserisciNuovaPassword
                Registrazione;
        /*RifiutoIngombrante
                Utente;*/
        UtenteActivity
                UtentiFactory;
    }
    //ricerco l'utente in base alla sua userkey
    public Utente findUserByKey(String key) {
        Iterator i = ulist.iterator();

        while (i.hasNext()) {
            Utente u = (Utente) i.next();
            if (u.getKey().equals(key)) {
                return u;
            }
        }
        return null;
    }
    //ricerco l'utente in base alla sua email
    public Utente findUserByEmail(String email) {
        Iterator i = ulist.iterator();

        while (i.hasNext()) {
            Utente u = (Utente) i.next();
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

}