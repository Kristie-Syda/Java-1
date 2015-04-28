package com.kristiesyda.connected_application;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.io.IOUtils;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting our connectivity manager.
        ConnectivityManager mgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        // Getting our active network information.
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();
        //checks connection.
        if(netInfo != null) {
            CharSequence text;
            if (netInfo.isConnected()) {
                // We have a valid data connection
                text = "Connected";
            }
            else {
                text = "No Connection";
            }
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        // The URL string that points to our web resource.
        //String urlString = "http://www.example.com/some_api";
        // Creating the URL object that points to our web resource.
        URL url = null;
        try {
            url = new URL("https://api.themoviedb.org/3/movie/550?api_key=62dbead3af716cd1edf3092f3be3bf5e");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Setting connection properties.
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000); // 10 seconds
            connection.setReadTimeout(10000); // 10 seconds
            // Refreshing the connection.
            connection.connect();
            // Optionally check the status code. Status 200 means everything went OK.
            int statusCode = connection.getResponseCode();
            // Getting the InputStream with the data from our resource.
            InputStream stream = connection.getInputStream();
            // Reading data from the InputStream using the Apache library.
            String resourceData = IOUtils.toString(stream);
            // Cleaning up our connection resources.
            stream.close();
            connection.disconnect();
            // The resourceData string should now have our data.

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





    }


//    public class MyTask extends AsyncTask<String,String,String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            return null;
//        }
//    }

}
