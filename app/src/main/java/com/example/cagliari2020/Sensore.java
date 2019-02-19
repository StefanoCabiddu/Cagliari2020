package com.example.cagliari2020;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Sensore extends Activity implements SensorEventListener
{
    private SensorManager mSensorManager;
    private Sensor sensor;
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
       // sensor = mSensorManager.getDefaultSensor(
                /*
                 * Costante relativa al sensore da monitorare
                 * */
       // );
    }
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        /*
         * Codice di gestione dei nuovi eventi del sensore
         * */
    }
    @Override
    public void onAccuracyChanged(Sensor s, int i)
    {
    }
}
