package com.example.cagliari2020;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class ModificaAudio extends Activity
{
    private MediaRecorder registratore = null;
    private MediaPlayer riproduttore = null;
    private static String filename = null;
    private boolean ascoltando=false;
    private boolean registrando=false;

    @Override
    public void onCreate(Bundle args)
    {
        super.onCreate(args);
        setContentView(R.layout.activity_riproduzione_audio);
        filename = Environment.getExternalStorageDirectory().getAbsolutePath();
        filename += "/registrazione.3gp";
    }
    @Override
    public void onPause() {
        super.onPause();
        if (registratore != null) {
            registratore.release();
            registratore = null;
        }
        if (riproduttore != null) {
            riproduttore.release();
            riproduttore = null;
        }
    }
    public void registra(View v)
    {
        Button btn=(Button) v;
        if (registrando)
        {
            // serve ad interrompere
            fermaRegistrazione();
            btn.setText("Registra");
        }
        else
        {
            // serve ad iniziare la registrazione
            registra();
            btn.setText("Ferma registrazione");
        }
        registrando=!registrando;
    }
    public void ascolta(View v)
    {
        Button btn=(Button) v;
        if (ascoltando)
        {
            // serve ad interrompere
            fermaRiproduzione();
            btn.setText("Ascolta");
        }
        else
        {
            // serve ad iniziare la riproduzione dell'audio
            riproduci();
            btn.setText("Ferma");
        }
        ascoltando=!ascoltando;
    }

    private void riproduci() {
        riproduttore = new MediaPlayer();
        try
        {
            riproduttore.setDataSource(filename);
            riproduttore.prepare();
            riproduttore.start();
        }
        catch (IOException e)
        {
            // gestisci eccezione
        }
    }
    private void fermaRiproduzione() {
        riproduttore.release();
        riproduttore = null;
    }
    private void registra() {
        registratore = new MediaRecorder();
        registratore.setAudioSource(MediaRecorder.AudioSource.MIC);
        registratore.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        registratore.setOutputFile(filename);
        registratore.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try
        {
            registratore.prepare();
        }
        catch (IOException e)
        {
            // gestisci eccezione
        }
        registratore.start();
    }
    private void fermaRegistrazione() {
        registratore.stop();
        registratore.release();
        registratore = null;
    }

}
