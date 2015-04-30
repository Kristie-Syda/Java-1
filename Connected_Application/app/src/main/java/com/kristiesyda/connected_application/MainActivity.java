package com.kristiesyda.connected_application;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    //variables
    EditText text;
    URL searchUrl;
    ListView list;
    ArrayList<HashMap<Integer, Object>> movieObjects;
    HashMap<Integer, Object> MovieInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieInfo = new HashMap<Integer, Object>();
        movieObjects = new ArrayList<HashMap<Integer, Object>>();
        text = (EditText) findViewById(R.id.mainText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks connection
                if(CustomHelper.isConnected(MainActivity.this)){
                    //grabs user input & makes the right url
                    searchUrl = getURL(text.getText().toString());
                    //Runs Async
                    new getData().execute(searchUrl);
                    //Add toast
                } else {
                    //Add toast
                    System.out.println("No network connection");
                }
            }
        });
    }

    //Makes url based on user input
    public URL getURL(String search) {
        URL results;
        try{
            String baseUrl = "https://api.themoviedb.org/3/search/movie?api_key=62dbead3af716cd1edf3092f3be3bf5e&query=";
            String queryUrl = baseUrl + search;
            URL url = new URL(queryUrl);
            System.out.println("working url = " + url);
            results = url;

        } catch (Exception e){
            results = null;
            System.out.println("catch url");
        }
        return results;
    }

    //Async
    public class getData extends AsyncTask<URL,Integer,JSONArray> {

        ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
        JSONObject apiData;
        JSONObject single;
        JSONArray apiArray;

        @Override //Starts progress dialog
        protected void onPreExecute() {
            super.onPreExecute();

            mDialog.setMessage("Loading...");
            mDialog.setIcon(R.mipmap.ic_launcher);
            mDialog.setTitle("Network Check");
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.show();
        }

        @Override //Pulls info from api & creates api data object
        protected JSONArray doInBackground(URL... urls) {

            String jsonString = "";
            for (URL url : urls) {
                try {

                    URLConnection con = url.openConnection();
                    jsonString = IOUtils.toString(con.getInputStream());
                    break;
                } catch (Exception e) {
                    System.out.println("no url connection");
                }
            }

            try {
                apiData = new JSONObject(jsonString);
            } catch (Exception e) {
                System.out.println("No Data");
                apiData = null;
            }

            try {
                //pulls Array from the Json object
                apiArray = (apiData != null) ? apiData.getJSONArray("results") : null;

                for (int i = 0; i < apiArray.length(); i++) {

                    //grab every object out of Array
                    single = apiArray.getJSONObject(i);

                    //run every object out through custom object
                    movie singleMovie = new movie(single);

                    //add custom object to HashMap for ListView
                    MovieInfo.put(i,singleMovie);

                    //add HashMapObjects to list
                    movieObjects.add(MovieInfo);
                }
                System.out.println("parse data == " + MovieInfo);
            } catch (Exception e) {
                System.out.println("couldn't parse data");
            }
            return null;
        }

        @Override //Passes api data object & creates movie object
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);



            System.out.println("End");
            //Cancels Progress dialog
            mDialog.cancel();

        }

    }
}
