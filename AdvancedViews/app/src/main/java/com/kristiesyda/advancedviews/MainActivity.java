package com.kristiesyda.advancedviews;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    // HashMap
    public HashMap<Integer, Characters> charInfo = new HashMap<Integer, Characters>();

    //variables
    Spinner spinner;

    //custom adapter to feed spinner
    public class CustomAdapter extends BaseAdapter {

        public CustomAdapter(HashMap<Integer, Characters> charInfo) {

        }

        @Override
        public int getCount() {
            return charInfo.size();
        }

        @Override
        public Object getItem(int _position) {

            return charInfo.get(_position);
        }

        @Override
        public long getItemId(int _position) {
            return _position;
        }

        @Override  //Where all the magic happens /////****
        public View getView(int _position, View _convertView, ViewGroup _parent) {
            Characters charInfo = (Characters) getItem(_position);

            final View results;
            // If we don't have a recycled view, create a new view.
            if (_convertView == null) {
                // Creating the new view.
                results = LayoutInflater.from(_parent.getContext()).inflate(R.layout.spinner, _parent, false);
            } else {
                results = _convertView;
            }
            TextView names = (TextView) results.findViewById(R.id.spinName);
            names.setText(charInfo.name);
            return results;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Custom Object
        Characters axl = new Characters("Axl Johnson", "Odin", "King of the Norse gods \"The All Father\"", "Wisdom, Enhanced Reflexes, Increased Stamina to toxins and drugs, Tranformations and Visions.", R.drawable.axl);
        Characters mike = new Characters("Mike Johnson", "Ullr", "God of the Hunt and of Games", "Enhanced Tacking and Tychokinesis (ability to manipulate the laws of probability to win any game).", R.drawable.mike);
        Characters anders = new Characters("Anders Johnson", "Bragi", "God of Poetry", "Manipulation by words", R.drawable.anders);
        Characters ty = new Characters("Tyrone Johnson", "Hod", "God of all things Cold and Dark", "Cryokinesis (ability to freeze things and lower the temperature around him)", R.drawable.ty);
        Characters olaf = new Characters("Olaf Johnson", "Baldr", "God of rebirth, light and beauty", "Oracle, Longevity/Rebirth, Enhanced Charisma, Enhanced Intelligence, Light Manipulation and Supernatural Beauty", R.drawable.olaf);

        //add to Hashmap
        charInfo.put(0, axl);
        charInfo.put(1, mike);
        charInfo.put(2, anders);
        charInfo.put(3, ty);
        charInfo.put(4, olaf);

        //Attach Adapter to spinner
        spinner = (Spinner) findViewById(R.id.mainSpin);
        CustomAdapter adapt = new CustomAdapter(charInfo);
        spinner.setAdapter(adapt);

        //Add spinner event listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //grabs actual object that was selected
                Characters current = (Characters) ((Spinner) findViewById(R.id.mainSpin)).getSelectedItem();

                //initialize detailViews
                TextView detailName = (TextView) findViewById(R.id.detailName);
                TextView detailGodName = (TextView) findViewById(R.id.detailGodName);
                TextView detailGodDesc = (TextView) findViewById(R.id.detailGodDesc);
                TextView detailPower = (TextView) findViewById(R.id.powerText);
                ImageView image = (ImageView) findViewById(R.id.imageView);

                //Set Text
                detailName.setText(current.name);
                detailGodName.setText("God: " + current.godName);
                detailGodDesc.setText("Info: " + current.godDesc);
                detailPower.setText("Powers:  " + current.power);
                image.setImageResource(current.pic);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
