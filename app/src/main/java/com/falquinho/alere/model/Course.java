package com.falquinho.alere.model;

import java.util.Vector;

/**
 * Created by falquinho on 02/12/2016.
 */
public class Course
{
    public static final int MIN_DURATION   = 1;
    public static final int MAX_DURATION   = 300;
    public static final int MIN_START_TIME = 0;
    public static final int MAN_START_TIME = 23*60 + 59;

    private String      name;
    private boolean[]   weekdays;
    private int         start_at;
    private int         duration;

    private Vector<Task> myTasks;

    public Course (String n, boolean[] days, int start, int dur)
    {
        name        = n;
        weekdays    = days;
        start_at    = start;
        duration    = dur;

        myTasks = new Vector<>();
    }

    public void addTask(Task t)
    {
        if (getTask(t.getName()) != null)
        {
            System.out.println("Course.addTask: ERROR: task with this name already exists");
            return;
        }

        myTasks.add(t);
    }

    public Task getTask(String name)
    {
        for (int i=0; i<myTasks.size(); ++i)
        {
            if (myTasks.elementAt(i).getName() == name)
                return myTasks.elementAt(i);
        }

        return null;
    }

    public Vector<Task> getMyTasks()
    {
        return myTasks;
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

    public boolean happensOnTime(int time)
    {
        if (time >= start_at && time <= start_at+duration)
            return true;

        return false;
    }
}
