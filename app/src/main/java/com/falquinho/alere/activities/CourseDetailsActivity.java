package com.falquinho.alere.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.falquinho.alere.R;
import com.falquinho.alere.fragments.CourseDetailFragment;
import com.falquinho.alere.fragments.TaskFragment;

public class CourseDetailsActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener
{
    TabLayout tab_layout;
    String course_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        course_id = getIntent().getStringExtra(AddTaskActivity.ASSOCIATED_COURSE_ID);

        getSupportActionBar().setTitle(course_id);

        tab_layout = (TabLayout)findViewById(R.id.tablayout);
        tab_layout.addOnTabSelectedListener(this);

        setDetailsFragment();

        Log.i("CourseDetailActivity","Related Course ID: ".concat(course_id));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {
        Log.i("CourseDetailActivity", "Tab Selected: "+tab.getText());

        if (tab_layout.getSelectedTabPosition() == 0)
        {
            setDetailsFragment();
        }
        else if (tab_layout.getSelectedTabPosition() == 1)
        {
            setTasksFragment();
        }
    }

    private void setDetailsFragment()
    {
        Fragment default_fragment = new CourseDetailFragment();
        default_fragment.setArguments(bundledCourseId());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.course_details_fragment_container, default_fragment);
        transaction.commit();
    }

    private void setTasksFragment()
    {
        Fragment default_fragment = new TaskFragment();
        default_fragment.setArguments(bundledCourseId());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.course_details_fragment_container, default_fragment);
        transaction.commit();
    }

    private Bundle bundledCourseId()
    {
        Bundle data = new Bundle();
        data.putString(AddTaskActivity.ASSOCIATED_COURSE_ID, course_id);

        return data;
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab)
    {
        //
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab)
    {
        //
    }
}
