package com.falquinho.alere.controller;

import android.util.Log;

import com.falquinho.alere.model.Deadline;
import com.falquinho.alere.model.Task;

import java.util.Vector;

/**
 * Created by falquinho on 09/12/2016.
 */
public class TaskRepository
{
    public static final int SUCCESS = 0;
    public static final int ERROR_INVALID_OWNER = 1;
    public static final int ERROR_EMPTY_NAME = 2;

    private static Vector<Task> all_tasks = new Vector<>();

    public static int addTask(String owner_id, String name, String description, Deadline dl)
    {
        if (CoursesRepository.getCourse(owner_id) == null)
            return TaskRepository.ERROR_INVALID_OWNER;

        if (name == null || name.length() == 0)
            return TaskRepository.ERROR_EMPTY_NAME;

        Task t = new Task(owner_id, name, description, dl);

        all_tasks.add(t);

        return TaskRepository.SUCCESS;
    }

    public static Vector<Task> getAllTasks()
    {
        Log.i("TasksRepository","getAllTasks: no of tasks: ".concat(String.valueOf(all_tasks.size())));
        return all_tasks;
    }

    public static Vector<Task> getTasksOwnedBy(String course_id)
    {
        Log.i("TaskRepository", "getTasksOwnedBy: ".concat(course_id));
        Vector<Task> filtered_tasks = new Vector<>();

        for (int i = 0; i < all_tasks.size(); i++)
        {
            if (course_id.equals(all_tasks.elementAt(i).getOwner()))
                filtered_tasks.add(all_tasks.elementAt(i));
        }

        Log.i("TasksRepository","getTasksOwnedBy: no of tasks: ".concat(String.valueOf(filtered_tasks.size())));
        return filtered_tasks;
    }
}
