package com.example.cagliari2020;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Animazione extends Activity
{
    private Animation anim=null;
    private TextView txt=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animazione);
        txt=(TextView) findViewById(R.id.txt);
        anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animazione);
    }
    public void avvia(View v)
    {
        txt.startAnimation(anim);
    }
}
