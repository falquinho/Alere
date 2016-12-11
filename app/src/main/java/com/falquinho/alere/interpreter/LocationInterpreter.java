package com.falquinho.alere.interpreter;

import android.location.Location;
import android.util.Log;

import com.falquinho.alere.enums.LocationContext;
import com.falquinho.alere.model.Course;
import com.falquinho.alere.widgets.LocationWidget;

/**
 * Created by falquinho on 07/12/2016.
 */
public class LocationInterpreter
{
    public static final float COURSE_AREA_RADIUS = 20f;

    protected LocationWidget location_widget;

    public LocationInterpreter(LocationWidget widget)
    {
        location_widget = widget;
    }

    public LocationContext contextRelativeTo(Course course)
    {
        // calculate distance to course location
        if (course.getLocation() == null)
            return LocationContext.notOnCourseLocation;

        Location last_known = location_widget.getLastLocation();

        float distance = last_known.distanceTo(course.getLocation());

        if (distance <= LocationInterpreter.COURSE_AREA_RADIUS)
        {
            Log.i("LocationInterpreter","contextRelativeTo(): onCourseLocation");
            return LocationContext.onCourseLocation;
        }
        else
        {
            Log.i("LocationInterpreter","contextRelativeTo(): notOnCourseLocation");
            return LocationContext.notOnCourseLocation;
        }
    }

}
