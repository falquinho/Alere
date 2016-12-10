package com.falquinho.alere.model;

import android.os.SystemClock;

import java.util.Calendar;

/**
 * Created by falquinho on 02/12/2016.
 */
public class Deadline
{
    int year;
    int month;
    int day;

    public Deadline(int y, int m, int d)
    {
        year = y;
        month = m;
        day = d;
    }

    // errado, mas deve servir para proposito de teste
    public int daysLeft()
    {
        int curr_year = Calendar.getInstance().get(Calendar.YEAR);
        int curr_mont = Calendar.getInstance().get(Calendar.MONTH);
        int curr_day  = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        return ((year-curr_year)*365)+((month-curr_mont)*30)+(day-curr_day);
    }

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public int getYear()
    {
        return year;
    }
}
