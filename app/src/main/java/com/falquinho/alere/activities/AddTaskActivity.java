package com.falquinho.alere.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.falquinho.alere.R;
import com.falquinho.alere.controller.CoursesRepository;
import com.falquinho.alere.controller.TaskRepository;
import com.falquinho.alere.model.Deadline;

/**
 * Created by falquinho on 24/11/2016.
 */
public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener
{
    public static final String ASSOCIATED_COURSE_ID = "assosciated_course_id";
    public static final String SHOW_ALL_COURSES = "all_courses";

    EditText   edit_text_name;
    EditText   edit_text_description;
    Spinner    spinner;
    DatePicker date_picker;
    String     spinner_selection;

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.add_task_layout);

        setTitle("Add New Task");

        String bundled_data = getIntent().getStringExtra(AddTaskActivity.ASSOCIATED_COURSE_ID);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_done_add_task);
        fab.setOnClickListener(this);

        spinner = (Spinner)findViewById(R.id.spinner_available_courses);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CoursesRepository.getCoursesNames());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        if (bundled_data != AddTaskActivity.SHOW_ALL_COURSES)
        {
            spinner_selection = bundled_data;
            spinner.setSelection(adapter.getPosition(bundled_data));
        }

        edit_text_name = (EditText)findViewById(R.id.edittext_task_name);
        edit_text_description = (EditText)findViewById(R.id.editText_add_task_description);

        date_picker = (DatePicker)findViewById(R.id.datepicker_deadline);
        date_picker.setMinDate(System.currentTimeMillis());
    }

    public void onClick(View v)
    {
        Log.i("AddTaskActivity","Finish Add Task FAB Clicked");
        Deadline dl = new Deadline(date_picker.getYear(), date_picker.getMonth(), date_picker.getDayOfMonth());
        String name = edit_text_name.getText().toString();
        String description = edit_text_description.getText().toString();

        int result = TaskRepository.addTask(spinner_selection, name, description, dl);

        if (result == TaskRepository.ERROR_EMPTY_NAME)
            showToastMessage("Name cant be empty");

        if (result == TaskRepository.ERROR_INVALID_OWNER)
            showToastMessage("Invalid Owner Course");

        if (result == TaskRepository.SUCCESS)
            finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        spinner_selection = (String)adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //
    }

    protected void showToastMessage(String message)
    {
        Toast t = Toast.makeText(this, message, Toast.LENGTH_LONG);
        t.show();
    }
}
