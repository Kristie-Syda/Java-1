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
import org.json.JSONObject;


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
                    URL url = new URL("http://api.themoviedb.org/3/discover/movie?api_key=62dbead3af716cd1edf3092f3be3bf5e");
                    System.out.println("working url");
                    new getData().execute(url);

                } catch (Exception e){
                    System.out.println("catch url");
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

    }


    public class getData extends AsyncTask<URL,Integer,JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... urls) {

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

            System.out.println("Received date =" + jsonString);

            JSONObject apiData;

            try{
                apiData = new JSONObject(jsonString);

            } catch(Exception e){
                System.out.println("No Data");
                apiData = null;
            }

            try{

                apiData = (apiData != null)? apiData.getJSONObject("page").getJSONObject("results") : null;
                System.out.println("Data = " + apiData.toString());
            } catch(Exception e){
                System.out.println("Could not parse data");

            }
            return apiData;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
        }
    }


}
