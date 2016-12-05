package com.falquinho.alere.model;

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
    public int daysDifference(Deadline d)
    {
        int year_diff = d.getYear() - year;
        int month_diff = d.getMonth() - month;
        int days_diff = d.getDay() - day;

        return (year_diff * 365 + month_diff * 60 + days_diff);
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
