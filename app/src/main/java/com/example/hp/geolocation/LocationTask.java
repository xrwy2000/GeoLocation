package com.example.hp.geolocation;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by HP on 2017/1/20.
 */

public class LocationTask extends AsyncTask<String, Void, String> {


    public LocationTask(){
    }
    @Override
    protected String doInBackground(String... params) {
        String jsonString = "http://api.map.baidu.com/geocoder/v2/?ak=pPGNKs75nVZPloDFuppTLFO3WXebPgXg&callback=renderReverse"+params[0]+"&output=json&pois=0";
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(jsonString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("MainActivity",sb.toString());
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            String[] a1 = s.split("\\(");
            String[] a2 =a1[1].split("\\)");
            JSONObject root = new JSONObject(a2[0]);
            JSONObject child = root.getJSONObject("result");
            String result = child.getString("formatted_address");
            Log.e("MainActivity",result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.onPostExecute(s);
    }
}
