package com.falquinho.alere.interpreter;

import android.util.Log;

import com.falquinho.alere.enums.TimeContext;
import com.falquinho.alere.model.Course;

import java.util.Calendar;
import java.util.Vector;

/**
 * Created by falquinho on 07/12/2016.
 */
public class TimeInterpreter
{
    public TimeContext getRelativeTimeContext(Course course)
    {
        Log.i("TimeContext","getRelativeTimeContext <START>");
        TimeContext c = TimeContext.notOnCoursePeriod;

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int min  = Calendar.getInstance().get(Calendar.MINUTE);

        Log.i("Calendar Hour:",String.valueOf(hour));
        Log.i("Calendar Mins:",String.valueOf(min));

        int canonical = hour*60 + min;

        if (canonical >= course.getStartTime() && canonical <= course.getStartTime()+(course.getDuration()/2))
        {
            Log.i("TimeInterpreter", "onCourseFirstHalf");
            c = TimeContext.onCourseFirstHalf;
        }
        else if (canonical > course.getStartTime()+(course.getDuration()/2) && canonical <= course.getStartTime()+course.getDuration())
        {
            Log.i("TimeInterpreter", "onCourseLastHalf");
            c = TimeContext.onCourseLastHalf;
        }
        else
            Log.i("TimeInterpreter", "notOnCoursePeriod");


        Log.i("TimeContext","getRelativeTimeContext <END>");
        return c;
    }
}
