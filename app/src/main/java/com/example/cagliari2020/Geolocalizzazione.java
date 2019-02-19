package com.example.cagliari2020;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Geolocalizzazione extends Activity {

    private String providerId = LocationManager.GPS_PROVIDER;
    private Geocoder geo = null;
    private LocationManager locationManager = null;
    private static final int MIN_DIST = 20;
    private static final int MIN_PERIOD = 30000;

    private LocationListener locationListener = new LocationListener()
    {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
        }
        @Override
        public void onProviderEnabled(String provider)
        {
            // attivo GPS su dispositivo
            updateText(R.id.enabled, "TRUE");
        }
        @Override
        public void onProviderDisabled(String provider)
        {
            // disattivo GPS su dispositivo
            updateText(R.id.enabled, "FALSE");
        }
        @Override
        public void onLocationChanged(Location location)
        {
            updateGUI(location);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocalizzazione);
    }

    @Override
    protected void onResume() {
        super.onResume();
        geo = new Geocoder(this, Locale.getDefault());
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null)
            updateGUI(location);
        if (locationManager != null && locationManager.isProviderEnabled(providerId))
            updateText(R.id.enabled, "TRUE");
        else
            updateText(R.id.enabled, "FALSE");
        locationManager.requestLocationUpdates(providerId, MIN_PERIOD, MIN_DIST, locationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (locationManager != null && locationManager.isProviderEnabled(providerId))
            locationManager.removeUpdates(locationListener);
    }

    private void updateGUI(Location location)
    {
        Date timestamp = new Date(location.getTime());
        updateText(R.id.timestamp, timestamp.toString());
        double latitude = location.getLatitude();
        updateText(R.id.latitude, String.valueOf(latitude));
        double longitude = location.getLongitude();
        updateText(R.id.longitude, String.valueOf(longitude));
        new AddressSolver().execute(location);
    }
    private void updateText(int id, String text)
    {
        TextView textView = (TextView) findViewById(id);
        textView.setText(text);
    }

    public LocationListener getLocationListener() {
        return locationListener;
    }

    public void setLocationListener(LocationListener locationListener) {
        this.locationListener = locationListener;
    }

    private class AddressSolver extends AsyncTask<Location, Void, String>
    {
        @Override
        protected String doInBackground(Location... params)
        {
            Location pos=params[0];
            double latitude = pos.getLatitude();
            double longitude = pos.getLongitude();
            List<Address> addresses = null;
            try
            {
                addresses = geo.getFromLocation(latitude, longitude, 1);
            }
            catch (IOException e)
            {
            }
            if (addresses!=null)
            {
                if (addresses.isEmpty())
                {
                    return null;
                }
                else {
                    if (addresses.size() > 0)
                    {
                        StringBuffer address=new StringBuffer();
                        Address tmp=addresses.get(0);
                        for (int y=0;y<tmp.getMaxAddressLineIndex();y++)
                            address.append(tmp.getAddressLine(y)+"\n");
                        return address.toString();
                    }
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result)
        {
            if (result!=null)
                updateText(R.id.where, result);
            else
                updateText(R.id.where, "N.A.");
        }
    }

}
