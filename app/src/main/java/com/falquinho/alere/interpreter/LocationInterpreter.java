package com.falquinho.alere.interpreter;

import android.content.Context;
import android.location.Location;

import com.falquinho.alere.controller.CoursesRepository;
import com.falquinho.alere.enums.LocationContext;
import com.falquinho.alere.interfaces.LocationInterpreterListener;
import com.falquinho.alere.interfaces.LocationWidgetListener;
import com.falquinho.alere.model.Course;
import com.falquinho.alere.widgets.LocationWidget;
import com.google.android.gms.location.LocationListener;

import java.util.Vector;

/**
 * Created by falquinho on 07/12/2016.
 */
public class LocationInterpreter implements LocationWidgetListener
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
            return LocationContext.onCourseLocation;
        else
            return LocationContext.notOnCourseLocation;
    }

    @Override
    public void locationUpdate(Location new_location)
    {
        //
    }

}
