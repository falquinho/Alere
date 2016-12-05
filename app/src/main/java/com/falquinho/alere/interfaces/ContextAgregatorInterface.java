package com.falquinho.alere.interfaces;

import com.falquinho.alere.enums.ContextLocationEnum;
import com.falquinho.alere.enums.ContextTimeEnum;
import com.falquinho.alere.model.Course;

/**
 * Created by falquinho on 03/12/2016.
 */
public interface ContextAgregatorInterface
{
    public ContextLocationEnum getLocationContext();
    public ContextTimeEnum getTimeContext();
    public Course getCourseContext();
}
