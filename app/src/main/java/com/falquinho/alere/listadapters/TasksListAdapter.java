package com.falquinho.alere.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.falquinho.alere.R;
import com.falquinho.alere.model.Task;

import java.util.List;

/**
 * Created by falquinho on 09/12/2016.
 */
public class TasksListAdapter extends ArrayAdapter<Task>
{
    public TasksListAdapter(Context context, List<Task> tasks_list)
    {
        super(context, 0, tasks_list);
    }

    @Override
    public View getView(int pos, View convert, ViewGroup parent)
    {
        if (convert == null)
        {
            convert = LayoutInflater.from(getContext()).inflate(R.layout.list_item_course, parent, false);
        }

        Task task = getItem(pos);

        ((TextView)convert.findViewById(R.id.listitem_course_name)).setText(task.getName());

        String s = new String("On Course ");
        s = s.concat(task.getOwner()).concat(". ");
        s = s.concat(String.valueOf(task.getDeadline().daysLeft()));
        s= s.concat(" days left");

        ((TextView)convert.findViewById(R.id.listitem_no_tasks)).setText(s);

        return convert;
    }
}
