package com.example.cagliari2020;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class RiproduzioneVideo extends Activity implements SurfaceHolder.Callback
{
    private MediaPlayer mediaPlayer;
    private SurfaceHolder holder;
    private SurfaceView surface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riproduzione_video);
        surface = (SurfaceView) findViewById(R.id.surfView);
        holder = surface.getHolder();
        holder.addCallback(this);
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mediaPlayer= MediaPlayer.create(this,R.raw.video);
        mediaPlayer.setDisplay(holder);
        mediaPlayer.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                    }
                }
        );
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }
}
