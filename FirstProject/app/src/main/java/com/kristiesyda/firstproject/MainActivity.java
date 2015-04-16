//Kristie syda
package com.kristiesyda.firstproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private EditText et;
    private ListView lt;
    private TextView entNum;
    private TextView avgLen;
    private double average;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> list = new ArrayList<>();
    private int number;


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

        //set initial number
        number = 0;
        entNum.setText("Number of Entries: " + number);

        //set initial average
        average = 0;
        avgLen.setText("Average length: " + average);

        //adaptor
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lt.setAdapter(arrayAdapter);

        //button
        Button Btn = (Button) findViewById(R.id.mainButton);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // words from textField
                String newWord = et.getText().toString();

                //no blank entries in list
                if (newWord.isEmpty()== false)
                {
                    //add the new word if not already there
                    if (!list.contains(newWord)) {
                        list.add(newWord);

                        //toast
                        //Toast
                        Context context = getApplicationContext();
                        CharSequence text = newWord + " was added.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    arrayAdapter.notifyDataSetChanged();

                    //clears field when button is clicked
                    et.setText("");

                    //get number of entries
                    number = list.size();
                    entNum.setText("Number of Entries: " + number);

                    //calls custom method
                    avgLength();
                    avgLen.setText("Average length: " + average);
                }
                else
                {
                    AlertDialog.Builder emptyAlert = new AlertDialog.Builder(MainActivity.this);
                    emptyAlert.setTitle("Alert");
                    emptyAlert.setMessage("Please add a word");
                    emptyAlert.setPositiveButton("Okay",null);
                    emptyAlert.show();
                }
            }
        });

        //Alert box
        lt.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get word on list
                final String index = (String) lt.getItemAtPosition(position);

                //Alert builder
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Alert");
                alert.setMessage("You selected " + index);
                alert.setPositiveButton("Okay",null);

                //removing words from list
                alert.setNegativeButton("Remove",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(index);
                        arrayAdapter.notifyDataSetChanged();

                        //get number of entries
                        number = list.size();
                        entNum.setText("Number of Entries: " + number);

                        //calls custom method
                        avgLength();

                        //check to make sure average is not NaN
                        if(Double.isNaN(average)){
                            avgLen.setText("Average length: 0.0");
                        }
                        else{
                            avgLen.setText("Average length: " + average);
                        }

                       //Toast
                        Context context = getApplicationContext();
                        CharSequence text = index + " was removed.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
                alert.show();
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
    }
}
