package com.falquinho.alere.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.falquinho.alere.R;

/**
 * Created by falquinho on 24/11/2016.
 */

public class TaskFragment extends ListFragment implements View.OnClickListener
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        return inflater.inflate(R.layout.task_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance)
    {
        super.onActivityCreated(savedInstance);

        FloatingActionButton fab;
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_task);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Log.i("TaskFragment","Add Task FAB Clicked");
    }
}
