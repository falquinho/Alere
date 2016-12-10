package com.falquinho.alere.interfaces;

import com.falquinho.alere.enums.LocationContext;
import com.falquinho.alere.model.Course;

/**
 * Created by falquinho on 08/12/2016.
 */
public interface LocationInterpreterListener
{
    public void updateLocationContext(LocationContext context, Course relative_course);
}
