package com.falquinho.alere.model;

import android.location.Location;


/**
 * Created by falquinho on 02/12/2016.
 */
public class Course
{
    public static final int MIN_DURATION   = 1;
    public static final int MAX_DURATION   = 300;
    public static final int MIN_START_TIME = 0;
    public static final int MAX_START_TIME = 23*60 + 59;

    private String      name;
    private boolean[]   weekdays;
    private int         start_at;
    private int         duration;
    private Location    location;

    public Course (String n, boolean[] days, int start, int dur,Location loc)
    {
        name        = n;
        weekdays    = days;
        start_at    = start;
        duration    = dur;
        location    = loc;
    }

    public String getName()
    {
        return name;
    }

    public boolean happensOnDay(int day)
    {
        return weekdays[day];
    }

    public int getStartTime()
    {
        return start_at;
    }

    public int getDuration()
    {
        return duration;
    }

    public Location getLocation(){ return location; }

}
