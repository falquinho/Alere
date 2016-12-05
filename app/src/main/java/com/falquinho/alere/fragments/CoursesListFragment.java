package com.falquinho.alere.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.falquinho.alere.R;
import com.falquinho.alere.activities.AddCourseActivity;

/**
 * Created by falquinho on 24/11/2016.
 */
public class CoursesListFragment extends ListFragment implements View.OnClickListener
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        return inflater.inflate(R.layout.course_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance)
    {
        super.onActivityCreated(savedInstance);

        FloatingActionButton fab;
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_course);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Log.i("TaskFragment","Add Task FAB Clicked");

        Intent intent = new Intent(getActivity(), AddCourseActivity.class);
        startActivity(intent);
    }
}
