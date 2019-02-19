package com.example.cagliari2020;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Shake extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float lastAcc = 0.0f;
    private float acceleration = 0.0f;
    private float totAcc = 0.0f;
    private boolean onEvent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lastAcc=SensorManager.GRAVITY_EARTH;
        acceleration=SensorManager.GRAVITY_EARTH;
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1)
    { 	}
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (!onEvent)
        {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            lastAcc = acceleration;
            acceleration = x*x+y*y+z*z;
            float diff = acceleration - lastAcc;
            totAcc = diff*acceleration;
            if (totAcc>15000)
            {
                onEvent=true;
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("Pulire il form?");
                builder.setPositiveButton("Sì", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        clean();
                        onEvent = false;
                    }
                });
                builder.setNegativeButton("No",  new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        onEvent=false;
                    }
                });
                builder.show();
            }
        }
    }
    private void clean()
    {
        TextView txt1=(TextView) findViewById(R.id.text1);
        TextView txt2=(TextView) findViewById(R.id.text2);
        txt1.setText("");
        txt2.setText("");
    }

}
