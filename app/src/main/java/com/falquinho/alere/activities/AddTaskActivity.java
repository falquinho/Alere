package com.falquinho.alere.activities;

import android.app.Activity;
import android.os.Bundle;

import com.falquinho.alere.R;

/**
 * Created by falquinho on 24/11/2016.
 */
public class AddTaskActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.add_task_layout);

        setTitle("Add New Task");
    }
}
