package com.kristiesyda.connected_application;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    URL url = new URL("");
                }
            }
        });

//        // Getting our connectivity manager.
//        ConnectivityManager mgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        // Getting our active network information.
//        NetworkInfo netInfo = mgr.getActiveNetworkInfo();
//        //checks connection.
//        if(netInfo != null) {
//            CharSequence text;
//            if (netInfo.isConnected()) {
//                // We have a valid data connection
//                text = "Connected";
//            }
//            else {
//                text = "No Connection";
//            }
//            Context context = getApplicationContext();
//            int duration = Toast.LENGTH_SHORT;
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//        }

        // The URL string that points to our web resource.
        String urlString = "https://api.themoviedb.org/3/movie/550?api_key=62dbead3af716cd1edf3092f3be3bf5e";
        URL url = null;
        try {
            url = new URL(urlString);
            // Establish a connection to the resource at the URL.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Setting connection properties.
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000); // 10 seconds
            connection.setReadTimeout(10000); // 10 seconds
            // Refreshing the connection.
            connection.connect();
            // Optionally check the status code. Status 200 means everything went OK.
            int statusCode = connection.getResponseCode();
            System.out.println(statusCode);
            // Getting the InputStream with the data from our resource.

            // Reading data from the InputStream using the Apache library.
            String resourceData = IOUtils.toString(connection.getInputStream());
            System.out.println(resourceData);

            connection.disconnect();
            // The resourceData string should now have our data.
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
