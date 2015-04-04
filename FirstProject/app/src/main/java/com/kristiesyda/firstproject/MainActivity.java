package com.kristiesyda.firstproject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;


public class MainActivity extends ActionBarActivity {
    private EditText et;
    private ListView lt;
    private String newWord;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> list = new ArrayList<>();
   // HashSet <String> words = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //edit text
        final EditText et = (EditText) findViewById(R.id.mainEdit);
        //listView
        final ListView lt = (ListView) findViewById(R.id.mainList);

        //adaptor
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lt.setAdapter(arrayAdapter);

        Button Btn = (Button) findViewById(R.id.mainButton);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // words from textField
                String words = et.getText().toString();
                newWord = et.getText().toString();

                //add the new word if not already there
                if(!list.contains(words)){
                    list.add(newWord);
                }

                arrayAdapter.notifyDataSetChanged();

            }
        });






    }

}
