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
    private TextView entNum;
    private TextView avgLen;
    private double average;
    private String newWord;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //edit text
        final EditText et = (EditText) findViewById(R.id.mainEdit);
        //listView
        final ListView lt = (ListView) findViewById(R.id.mainList);
        //Number of Entries
        final TextView entNum = (TextView) findViewById(R.id.entriesNum);
        //average length of entries
        final TextView avgLen = (TextView) findViewById(R.id.avgLen);

        //adaptor
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lt.setAdapter(arrayAdapter);

        //button
        Button Btn = (Button) findViewById(R.id.mainButton);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // words from textField
                String words = et.getText().toString();
                newWord = et.getText().toString();

                //no blank entries in list
                if (words.isEmpty()== false)
                {
                    //add the new word if not already there
                    if (!list.contains(words)) {
                        list.add(newWord);
                    }
                }
                else {
                    //do nothing .. maybe add alert
                }

                arrayAdapter.notifyDataSetChanged();

                //clears field when button is clicked
                et.setText("");

                //get number of entries
                int number = list.size();
                entNum.setText("Number of Entries: " + number);

                //calls custom method
                avgLength();
                avgLen.setText("Average length: " + average);
            }
        });
    }

    //Custom method
    public void avgLength()
    {
        int words = 0;

        //get length of each word in list
        for (String word : list)
        {
            words += word.length();
        }

        //double makes int float
        average = (double) words/list.size();
       // System.out.println("This method has been called " + average);
    }

}
