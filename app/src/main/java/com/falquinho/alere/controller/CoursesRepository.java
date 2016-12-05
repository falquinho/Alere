package com.falquinho.alere.controller;

import android.os.Debug;
import android.util.Log;

import com.falquinho.alere.model.Course;

import java.util.Vector;

/**
 * Created by falquinho on 02/12/2016.
 */
public class CoursesRepository
{
    public static final int ERROR_DAYS_ARRAY_BAD_SIZE       = 1;
    public static final int ERROR_START_TIME_OUT_OF_BOUNDS  = 2;
    public static final int ERROR_DURATION_OUT_OF_BOUNDS    = 3;
    public static final int ERROR_NAME_ALREADY_EXISTS       = 4;
    public static final int ERROR_NAME_EMPTY                = 5;
    public static final int SUCCESS_ADD                     = 6;

    static Vector<Course> courses = new Vector<Course>();

    public static int addCourse(String n, boolean[] days, int start, int duration)
    {
        if (days.length != 7)
        {
            Log.i("CourseRepository","addCourse: days array of wrong size");
            return ERROR_DAYS_ARRAY_BAD_SIZE;
        }

        if (start > Course.MAX_DURATION || start < Course.MIN_START_TIME)
        {
            Log.i("CourseRepository","addCourse: start out of bounds");
            return ERROR_START_TIME_OUT_OF_BOUNDS;
        }

        if (duration > Course.MAX_DURATION || duration < Course.MIN_DURATION)
        {
            Log.i("CourseRepository","addCourse: duration out of limits");
            return ERROR_DURATION_OUT_OF_BOUNDS;
        }

        if (getCourse(n) != null)
        {
            Log.i("CourseRepository","addCourse: duration out of limits");
            return ERROR_NAME_ALREADY_EXISTS;
        }

        if (n.length() == 0)
        {
            Log.i("CourseRepository","addCourse: name is empty");
            return ERROR_NAME_EMPTY;
        }

        courses.add(new Course(n, days, start, duration));

        return SUCCESS_ADD;
    }

    public static Course getCourse(String name)
    {
        for (int i=0;i<courses.size();++i)
        {
            if (courses.elementAt(i).getName() == name)
                return courses.elementAt(i);
        }

        return null;
    }

    public static Course getCourse(int index)
    {
        return courses.elementAt(index);
    }

    public static Vector<String> getCoursesNames()
    {
        Vector<String> names = new Vector<String>();

        for (int i=0; i<courses.size(); ++i)
        {
            names.add(courses.elementAt(i).getName());
        }

        return names;
    }

    public static Vector<Course> getAllCourses()
    {
        return courses;
    }

    public static int noOfCourses()
    {
        return courses.size();
    }
}
