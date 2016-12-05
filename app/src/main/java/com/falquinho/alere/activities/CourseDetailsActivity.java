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
    public final static String ASSOCIATED_COURSE_ID = "associated_course";

    TabLayout tab_layout;
    String course_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        course_id = getIntent().getStringExtra(CourseDetailsActivity.ASSOCIATED_COURSE_ID);

        getSupportActionBar().setTitle(course_id);

        tab_layout = (TabLayout)findViewById(R.id.tablayout);
        tab_layout.addOnTabSelectedListener(this);

        setupDefaultFragment();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {
        Log.i("CourseDetailActivity", "Tab Selected: "+tab.getText());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;

        if (tab_layout.getSelectedTabPosition() == 0)
        {
            fragment = new CourseDetailFragment();
        }
        else if (tab_layout.getSelectedTabPosition() == 1)
        {
            fragment = new TaskFragment();
        }

        fragment.setArguments(bundledCourseId());
        transaction.replace(R.id.course_details_fragment_container, fragment);

        transaction.commit();
    }

    private  void setupDefaultFragment()
    {
        Fragment default_fragment = new CourseDetailFragment();
        default_fragment.setArguments(bundledCourseId());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.course_details_fragment_container, default_fragment);
        transaction.commit();
    }

    private Bundle bundledCourseId()
    {
        Bundle data = new Bundle();
        data.putString(CourseDetailsActivity.ASSOCIATED_COURSE_ID, course_id);

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
