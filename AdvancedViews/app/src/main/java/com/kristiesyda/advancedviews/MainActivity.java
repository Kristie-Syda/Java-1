package com.kristiesyda.advancedviews;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends ActionBarActivity {

    Spinner spinner;
    ArrayAdapter arrAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attach Adapter to spinner
        spinner = (Spinner) findViewById(R.id.mainSpin);

    }

}
