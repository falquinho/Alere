package com.falquinho.alere.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import com.falquinho.alere.R;

/**
 * Created by falquinho on 24/11/2016.
 */
public class AddTaskActivity extends Activity implements View.OnClickListener
{
    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.add_task_layout);

        setTitle("Add New Task");

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_done_add_task);
        fab.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Log.i("AddTaskActivity","FAB Clicked");
    }
}
