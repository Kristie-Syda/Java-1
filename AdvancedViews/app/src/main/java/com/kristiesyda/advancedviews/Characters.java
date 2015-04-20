package com.kristiesyda.advancedviews;

import android.media.Image;

/**
 * Created by kristie Syda on 4/19/15.
 */
public class Characters {

    //Data points
    public String name;
    public String godName;
    public String godDesc;
    public String power;
    public int pic;

    public Characters(String nameLabel, String god, String desc, String powerText, int picture){
        name = nameLabel;
        godName = god;
        godDesc = desc;
        power = powerText;
        pic = picture;
    }

}
