package com.falquinho.alere.activities;

import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.falquinho.alere.R;
import com.falquinho.alere.controller.CoursesRepository;
import com.falquinho.alere.model.Course;
import com.falquinho.alere.model.Deadline;
import com.falquinho.alere.widgets.LocationWidget;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText et_name;
    NumberPicker start_hour, start_min, duration;
    LocationWidget loc_widget;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_done_add_course);
        fab.setOnClickListener(this);

        setTitle("Add a Course");

        loc_widget = LocationWidget.requestLocationWidget();

        duration = (NumberPicker)findViewById(R.id.numberPicker_duration);
        duration.setMinValue(Course.MIN_DURATION);
        duration.setMaxValue(Course.MAX_DURATION);

        start_hour = (NumberPicker)findViewById(R.id.numberPicker_start_hour);
        start_hour.setMaxValue(23);
        start_hour.setMinValue(0);

        start_min = (NumberPicker)findViewById(R.id.numberPicker_start_min);
        start_min.setMaxValue(59);
        start_min.setMinValue(0);

        et_name = (EditText)findViewById(R.id.editText_CourseName);
    }

    public void onClick(View v)
    {
        Log.i("AddCourseActivity","FAB clicked");

        boolean[] days = new boolean[7];
        days[0] = ((CheckBox)findViewById(R.id.checkBox_sun)).isChecked();
        days[1] = ((CheckBox)findViewById(R.id.checkBox_mon)).isChecked();
        days[2] = ((CheckBox)findViewById(R.id.checkBox_tue)).isChecked();
        days[3] = ((CheckBox)findViewById(R.id.checkBox_wed)).isChecked();
        days[4] = ((CheckBox)findViewById(R.id.checkBox_thu)).isChecked();
        days[5] = ((CheckBox)findViewById(R.id.checkBox_fri)).isChecked();
        days[6] = ((CheckBox)findViewById(R.id.checkBox_sat)).isChecked();

        CheckBox use_location = (CheckBox)findViewById(R.id.checkBox_use_location);

        int start    = start_hour.getValue()*60 + start_min.getValue();
        int dur      = duration.getValue();
        String name  = et_name.getText().toString();
        name         = name.trim();

        Location loc = null;
        if (use_location.isChecked())
        {
            if (loc_widget != null)
                loc = loc_widget.getLastLocation();
            else
                Log.i("AddCourseActivity","LocationWidget is null. Make sure you initialize it");
        }

        int result = CoursesRepository.addCourse(name, days, start, dur, loc);

        notifyResult(result);

        if (result == CoursesRepository.SUCCESS_ADD)
            finish();
    }

    private void notifyResult(int result)
    {
        if (result == CoursesRepository.ERROR_DAYS_ARRAY_BAD_SIZE)
        {
            Toast t = Toast.makeText(this, "Error: bad weekdays array lenght", Toast.LENGTH_LONG);
            t.show();
            return;
        }

        if (result == CoursesRepository.ERROR_DURATION_OUT_OF_BOUNDS)
        {
            Toast t = Toast.makeText(this, "Error: duration out of bounds", Toast.LENGTH_LONG);
            t.show();
            return;
        }

        if (result == CoursesRepository.ERROR_START_TIME_OUT_OF_BOUNDS)
        {
            Toast t = Toast.makeText(this, "Error: start time out of bounds", Toast.LENGTH_LONG);
            t.show();
            return;
        }

        if (result == CoursesRepository.ERROR_NAME_ALREADY_EXISTS)
        {
            Toast t = Toast.makeText(this, "There is already a Course with this name", Toast.LENGTH_LONG);
            t.show();
            return;
        }

        if (result == CoursesRepository.ERROR_NAME_EMPTY)
        {
            Toast t = Toast.makeText(this, "Name cant be empty!", Toast.LENGTH_LONG);
            t.show();
            return;
        }

        if (result == CoursesRepository.SUCCESS_ADD)
        {
            Toast t = Toast.makeText(this, "Course Added!", Toast.LENGTH_LONG);
            t.show();
            return;
        }
    }
}
