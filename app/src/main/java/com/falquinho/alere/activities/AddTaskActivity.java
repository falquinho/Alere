package com.falquinho.alere.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.falquinho.alere.R;

/**
 * Created by falquinho on 24/11/2016.
 */
public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.add_task_layout);

        //setSupportActionBar((Toolbar) findViewById(R.id.add_task_toolbar));
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Add New Task");

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_done_add_task);
        fab.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Log.i("AddTaskActivity","Finish Add Task FAB Clicked");
    }
}
