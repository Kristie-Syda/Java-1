package com.kristiesyda.connected_application;

import android.media.Image;

import org.json.JSONObject;

/**
 * Created by kristiesyda on 4/28/15.
 */
public class movie {


    public String mTitle;
    public String mPoster;
    public String mReleased;
    public Integer mVotes;


    public movie(String title, String poster, String released, Integer votes){

        mTitle = title;
        mPoster = poster;
        mReleased = released;
        mVotes = votes;
    }

    public movie (JSONObject movieData){
        try{
            mTitle = movieData.getString("title");
            mPoster = movieData.getString("poster_path");
            mReleased = movieData.getString("release_date");
            mVotes = movieData.getInt("vote_count");

        }catch(Exception e){
            System.out.println("Error trying to make movie object");
        }
    }
}
