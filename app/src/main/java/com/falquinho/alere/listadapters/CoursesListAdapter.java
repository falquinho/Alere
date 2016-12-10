package com.falquinho.alere.listadapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.falquinho.alere.R;
import com.falquinho.alere.controller.CoursesRepository;
import com.falquinho.alere.controller.TaskRepository;
import com.falquinho.alere.model.Course;

import java.util.List;

/**
 * Created by falquinho on 04/12/2016.
 */
public class CoursesListAdapter extends ArrayAdapter<Course>
{
    public CoursesListAdapter(Context context, List<Course> data)
    {
        super(context, 0, data);
    }

    @Override
    public View getView(int pos, View convert, ViewGroup parent)
    {
        if (convert == null)
        {
            convert = LayoutInflater.from(getContext()).inflate(R.layout.list_item_course, parent, false);
        }

        Course c = getItem(pos);

        ((TextView)convert.findViewById(R.id.listitem_course_name)).setText(c.getName());

        String s = new String("");
        s = s.concat(String.valueOf(TaskRepository.getTasksOwnedBy(c.getName()).size()));
        s = s.concat(" Tasks Registered");

        ((TextView)convert.findViewById(R.id.listitem_no_tasks)).setText(s);

        return convert;
    }
}
