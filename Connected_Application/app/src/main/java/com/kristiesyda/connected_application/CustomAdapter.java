package com.kristiesyda.connected_application;

/**
 * Created by Kristie Syda on 4/28/15.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.InputStream;
import java.util.HashMap;


public class CustomAdapter extends BaseAdapter{

    private HashMap<Integer,movie> movieInfo = MainActivity.MovieInfo;

    public CustomAdapter(HashMap<Integer, movie> movieInfo) {
        this.movieInfo = movieInfo;
    }

    @Override
    public int getCount() {
        return movieInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return movieInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        movie movieInfo = (movie) getItem(position);
        String url;
        final View results;

        if(convertView == null){
            results = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent,false);
        } else {
            results = convertView;
        }

        //Set textViews and labels
        TextView title = (TextView) results.findViewById(R.id.listTitle);
        TextView vote = (TextView) results.findViewById(R.id.listVote);
        TextView release = (TextView) results.findViewById(R.id.listRelease);

        title.setText(movieInfo.mTitle);
        vote.setText("Vote Count: "+ movieInfo.mVotes.toString());

        if(movieInfo.mReleased.length() == 0) {
            release.setText("Released: N/A");
        }else {
            release.setText("Released: " + movieInfo.mReleased);
        }

        if(movieInfo.mPoster == "null"){
            url = ("http://ozarktech.com/wp-content/uploads/2014/05/image-not-available-grid.jpg");
        } else {
            url = ("http://image.tmdb.org/t/p/w500"+ movieInfo.mPoster);
        }

            try{
                new DownloadImageTask((ImageView) results.findViewById(R.id.listImage))
                        .execute(url);
            } catch (Exception e){

            }

        return results;
    }

    //Image Async -- http://stackoverflow.com/questions/5776851/load-image-from-url --
    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

