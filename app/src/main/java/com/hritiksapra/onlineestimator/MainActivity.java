package com.hritiksapra.onlineestimator;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GPSTracker gpsTracker = new GPSTracker(this);
        TextView text=(TextView) findViewById(R.id.textView);
        if (gpsTracker.getIsGPSTrackingEnabled()) {
            String stringLatitude = String.valueOf(gpsTracker.latitude);
            double lat1 = Double.parseDouble(stringLatitude);
            String stringLongitude = String.valueOf(gpsTracker.longitude);
            double long1 = Double.parseDouble(stringLongitude);
            EditText et=(EditText) findViewById(R.id.editText);
            double lat2=latitude(et.getText().toString());
            double long2=longititude(et.getText().toString());
            double dist=distance(lat1,long1, lat2, long2);
            dist=dist/1000;
            double cost;
            if(dist<=2)
            {
                cost= dist*12.5;
            }
            else
            {
                cost= 25+ (dist-2)*8;
            }
            text.setText("The cost approx is Rs"+cost);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        else{
            Toast.makeText(MainActivity.this, "Nah", Toast.LENGTH_SHORT).show();
        }
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    public double latitude(String address) {
        double lat=0.0;
        if (address != null && !address.isEmpty()) {
            try {
                Geocoder geoCoder= new Geocoder(this);
                List<Address> addressList = geoCoder.getFromLocationName(address, 1);
                if (addressList != null && addressList.size() > 0) {
                     lat = addressList.get(0).getLatitude();

                }
            } catch (Exception e) {
                e.printStackTrace();
            } // end catch
        } // end if
        return lat;
    }
    public double longititude(String address) {
        double longs=0.0;
        if (address != null && !address.isEmpty()) {
            try {
                Geocoder geoCoder= new Geocoder(this);
                List<Address> addressList = geoCoder.getFromLocationName(address, 1);
                if (addressList != null && addressList.size() > 0) {
                    longs = addressList.get(0).getLongitude();

                }
            } catch (Exception e) {
                e.printStackTrace();
            } // end catch
        } // end if
        return longs;
    }
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    public void click(View view)
    {
        GPSTracker gpsTracker = new GPSTracker(this);
        TextView text=(TextView) findViewById(R.id.textView);
        if (gpsTracker.getIsGPSTrackingEnabled()) {
            String stringLatitude = String.valueOf(gpsTracker.latitude);
            double lat1 = Double.parseDouble(stringLatitude);
            String stringLongitude = String.valueOf(gpsTracker.longitude);
            double long1 = Double.parseDouble(stringLongitude);
            EditText et=(EditText) findViewById(R.id.editText);
            double lat2=latitude(et.getText().toString());
            double long2=longititude(et.getText().toString());
            double dist=distance(lat1,long1, lat2, long2);
            double cost;
            if(dist<=2)
            {
                cost= dist*12.5;
            }
            else
            {
                cost= 25+ (dist-2)*8;
            }
            text.setText("The cost approx is Rs"+cost);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        else{
            Toast.makeText(MainActivity.this, "Nah", Toast.LENGTH_SHORT).show();
        }
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
}

