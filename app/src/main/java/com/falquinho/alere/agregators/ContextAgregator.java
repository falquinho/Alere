package com.falquinho.alere.agregators;

import com.falquinho.alere.enums.LocationContext;
import com.falquinho.alere.enums.SystemContext;
import com.falquinho.alere.enums.TimeContext;
import com.falquinho.alere.interpreter.LocationInterpreter;
import com.falquinho.alere.interpreter.TimeInterpreter;
import com.falquinho.alere.model.Course;

import java.util.Vector;

/**
 * Created by falquinho on 08/12/2016.
 */
public class ContextAgregator
{
    protected LocationInterpreter loca_interpreter;
    protected TimeInterpreter     time_interpreter;
    protected Course              related_course;
    protected Vector<Course>      user_courses;

    public ContextAgregator(LocationInterpreter loc, TimeInterpreter time, Vector<Course> courses)
    {
        loca_interpreter = loc;
        time_interpreter = time;
        user_courses     = courses;
        related_course   = null;
    }

    public SystemContext agregateSystemContext()
    {
        SystemContext context = SystemContext.notPresent;
        related_course = null;

        // do stuff
        for (int i = 0; i < user_courses.size(); i++)
        {
            Course c = user_courses.elementAt(i);

            TimeContext t_context = time_interpreter.getRelativeTimeContext(c);
            LocationContext l_context = loca_interpreter.contextRelativeTo(c);

            if (l_context == LocationContext.onCourseLocation && t_context == TimeContext.onCourseFirstHalf)
            {
                related_course = c;
                context = SystemContext.presentFirstHalf;
            }
            if (l_context == LocationContext.onCourseLocation && t_context == TimeContext.onCourseLastHalf)
            {
                related_course = c;
                context = SystemContext.presentLastHalf;
            }
        }

        return context;
    }

    public Course getRelatedCourse()
    {
        return related_course;
    }
}
