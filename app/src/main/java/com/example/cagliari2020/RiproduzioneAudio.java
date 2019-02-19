package com.example.cagliari2020;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

public class RiproduzioneAudio extends Activity
{
    private MediaPlayer mp=null;
    private Handler handler = new Handler();
    private double startTime = 0;
    private SeekBar sk=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riproduzione_audio);
        sk=(SeekBar) findViewById(R.id.bar);
        mp=MediaPlayer.create(this, R.raw.canzone );
    }
    private Runnable updateBar = new Runnable() {
        public void run()
        {
            startTime = mp.getCurrentPosition();
            sk.setProgress((int)startTime);
            handler.postDelayed(this, 100);
        }
    };
    public void play(View v)
    {
        mp.start();
        sk.setMax((int) mp.getDuration());
        handler.postDelayed(updateBar,100);
    }
    public void pause(View v)
    {
        mp.pause();
    }
    public void stop(View v)
    {
        mp.stop();
    }
}
