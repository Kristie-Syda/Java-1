package com.kristiesyda.advancedviews;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;


import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    //Intialize HashMap
    public HashMap<Integer, Characters> charInfo = new HashMap<Integer, Characters>();

    Spinner spinner;

    //custom adapter to feed spinner
    public class CustomAdapter extends BaseAdapter {
        private Integer[] index;
        public CustomAdapter(HashMap<Integer, Characters> charInfo) {
            index = charInfo.keySet().toArray(new Integer[charInfo.size()]);
        }

        @Override
        public int getCount() {
            return charInfo.size();
        }

        @Override
        public Object getItem(int _position) {

            return charInfo.get(index[_position]);
        }

        @Override
        public long getItemId(int _position) {
            return _position;
        }

        @Override  //Where all the magic happens /////****
        public View getView(int _position, View _convertView, ViewGroup _parent) {

            // If we don't have a recycled view, create a new view.
            if(_convertView == null) {
                // Creating the new view.
                _convertView = LayoutInflater.from(_parent.getContext()).inflate(R.layout.support_simple_spinner_dropdown_item, _parent, false);
            }

            return _convertView;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Custom Object
        Characters axl = new Characters();
        axl.name = "Axl Johnson";
        axl.godName = "Odin";
        axl.godDesc = "King of the Norse gods \"The All Father\"";
        axl.power = "Wisdom, Enhanced Reflexes, Increased Stamina to toxins and drugs, Tranformations and Visions.";

        Characters mike = new Characters();
        mike.name = "Mike Johnson";
        mike.godName = "Ullr";
        mike.godDesc = "God of the Hunt and of Games";
        mike.power = "Enhanced Tacking and Tychokinesis (ability to manipulate the laws of probability to win any game).";

        Characters anders = new Characters();
        anders.name = "Anders Johnson";
        anders.godName = "Bragi";
        anders.godDesc = "God of Poetry";
        anders.power = "Manipulation by words";

        Characters ty = new Characters();
        ty.name = "Tyrone Johnson";
        ty.godName = "Hod";
        ty.godDesc = "God of all things Cold and Dark";
        ty.power = "Cryokinesis (ability to freeze things and lower the temperature around him)";

        Characters olaf = new Characters();
        olaf.name = "Olaf Johnson";
        olaf.godName = "Baldr";
        olaf.godDesc = "God of rebirth, light and beauty";
        olaf.power = "Oracle, Longevity/Rebirth, Enhanced Charisma, Enhanced Intelligence, Light Manipulation and Supernatural Beauty";

        //add to hashmap
        charInfo.put(0, axl);
        charInfo.put(1, mike);
        charInfo.put(2, anders);
        charInfo.put(3, ty);
        charInfo.put(4, olaf);

        //Attach Adapter to spinner
        spinner = (Spinner) findViewById(R.id.mainSpin);
        CustomAdapter adapt = new CustomAdapter(charInfo);
        spinner.setAdapter(adapt);

    }

}
