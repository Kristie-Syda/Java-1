package com.kristiesyda.connected_application;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by kristiesyda on 4/28/15.
 */
public class CustomAdapter extends ArrayAdapter<Object> {

    private Context context;
    private ArrayList<Object> movieObjects;

    public CustomAdapter (Context context, int resource, ArrayList<Object> movieObjects){

        super(context, resource, movieObjects);
        this.context = context;
        this.movieObjects = movieObjects;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return convertView;
    }
}
