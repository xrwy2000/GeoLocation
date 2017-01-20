package com.example.hp.geolocation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Provider;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    TextView mTextView;

    private double latitude;//纬度
    private double longitude;//经度


    public LocationClient mLocationClient = null;
    public BDLocationListener myListener;
    public BDNotifyListener notify;
    public Vibrator mVibrator;


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.tv);
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        initLocation();

        mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getLocationByBDLBS();
                }
            });
        }
    private void initLocation(){
        mLocationClient = new LocationClient(getApplicationContext());
        myListener = new MyLocationListener(mTextView);
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        int span = 3000;
        option.setScanSpan(span);
        option.setIsNeedAddress(false);
        mLocationClient.setLocOption(option);
        notify = new NotifyListener(mVibrator);
        notify.SetNotifyLocation(29.63799,111.751078,3000,"gps");
        mLocationClient.registerNotify(notify);
    }
    private void getLocationByBDLBS(){
        mLocationClient.start();
        mVibrator.vibrate(1000);
    }


}


