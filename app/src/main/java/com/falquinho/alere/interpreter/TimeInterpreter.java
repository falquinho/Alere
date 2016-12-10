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
        int hour = Calendar.getInstance().get(Calendar.HOUR);
        int min  = Calendar.getInstance().get(Calendar.MINUTE);

        int canonical = hour*60 + min;

        if (canonical >= course.getStartTime() && canonical <= course.getStartTime()+(course.getDuration()/2))
        {
            Log.i("TimeInterpreter", "Relative Context is: onCourseFirstHalf");
            return TimeContext.onCourseFirstHalf;
        }

        if (canonical > course.getStartTime()+(course.getDuration()/2) && canonical <= course.getStartTime()+course.getDuration())
        {
            Log.i("TimeInterpreter", "Relative Context is: onCourseLastHalf");
            return TimeContext.onCourseLastHalf;
        }

        return TimeContext.notOnCoursePeriod;
    }
}
