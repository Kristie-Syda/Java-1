package com.kristiesyda.connected_application;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    String searchText;
    EditText text;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.mainText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CustomHelper.isConnected(MainActivity.this)){
                    searchText = text.getText().toString();
                    try{
                        String baseUrl = "https://api.themoviedb.org/3/search/movie?api_key=62dbead3af716cd1edf3092f3be3bf5e&query=";
                        String queryUrl = baseUrl + searchText;
                        URL url = new URL(queryUrl);
                        System.out.println("working url = " + url);
                        new getData().execute(url);

                    } catch (Exception e){
                         System.out.println("catch url");
                    }

                } else {

                    System.out.println("No network connection");
                }

            }
        });

    }


    public class getData extends AsyncTask<URL,Integer,JSONObject> {

        ProgressDialog mDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mDialog.setMessage("Loading...");
            mDialog.setIcon(R.mipmap.ic_launcher);
            mDialog.setTitle("Network Check");
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.show();
        }

        @Override
        protected JSONObject doInBackground(URL... urls) {

            try{
                String baseUrl = "https://api.themoviedb.org/3/search/movie?api_key=62dbead3af716cd1edf3092f3be3bf5e&query=";
                String queryUrl = baseUrl + searchText;
                URL url = new URL(queryUrl);
                System.out.println("working url = " + url);
                new getData().execute(url);

            } catch (Exception e){
                System.out.println("catch url");
            }

            String jsonString = "";
            for (URL url : urls){
                try {

                    URLConnection con = url.openConnection();
                    jsonString = IOUtils.toString(con.getInputStream());
                    break;
                } catch (Exception e) {
                    System.out.println("no url connection");
                }
            }

            System.out.println("Received data =" + jsonString);

            JSONObject apiData;

            try{
                apiData = new JSONObject(jsonString);

            } catch(Exception e){
                System.out.println("No Data");
                apiData = null;
            }

            return apiData;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);



            mDialog.cancel();
        }
    }


}
