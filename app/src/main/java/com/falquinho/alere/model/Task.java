package com.falquinho.alere.model;

/**
 * Created by falquinho on 02/12/2016.
 */
public class Task
{
    String name;
    String owner;
    String description;
    Deadline deadline;

    public Task(String o, String n, String d, Deadline dl)
    {
        name        = n;
        owner       = o;
        description = d;
        deadline    = dl;
    }

    public String getName()
    {
        return name;
    }

    public String getOwner()
    {
        return owner;
    }

    public String getDescription()
    {
        return description;
    }

    public Deadline getDeadline()
    {
        return deadline;
    }
}
