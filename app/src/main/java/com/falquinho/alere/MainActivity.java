package com.falquinho.alere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.falquinho.alere.activities.AddCourseActivity;
import com.falquinho.alere.activities.AddTaskActivity;
import com.falquinho.alere.activities.CourseDetailsActivity;
import com.falquinho.alere.agregators.ContextAgregator;
import com.falquinho.alere.controller.CoursesRepository;
import com.falquinho.alere.enums.SystemContext;
import com.falquinho.alere.fragments.CoursesListFragment;
import com.falquinho.alere.fragments.TaskFragment;
import com.falquinho.alere.interpreter.LocationInterpreter;
import com.falquinho.alere.interpreter.TimeInterpreter;
import com.falquinho.alere.model.Course;
import com.falquinho.alere.widgets.LocationWidget;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    protected static SystemContext last_context;
    protected static Course last_related_course;

    protected ContextAgregator m_agregator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        LocationWidget loc_widget = LocationWidget.initializeLocationWidget(this);
        if (loc_widget == null)
            loc_widget = LocationWidget.requestLocationWidget();

        LocationInterpreter loc_inter = new LocationInterpreter(loc_widget);
        TimeInterpreter time_inter    = new TimeInterpreter();

        m_agregator = new ContextAgregator(loc_inter, time_inter, CoursesRepository.getAllCourses());

        setFragmentMyCourses();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        if (CoursesRepository.noOfCourses() == 0)
        {
            Intent i = new Intent(getApplicationContext(), AddCourseActivity.class);
            startActivity(i);
        }

        SystemContext curr_context   = m_agregator.agregateSystemContext();
        Course        related_course = m_agregator.getRelatedCourse();

        doContextualAction(curr_context, related_course);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        LocationWidget.requestLocationWidget().closeConnectionGoogleAPI();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        if (id == R.id.nav_menu_tasks)
        {
            setFragmentAllTasks();
        }
        else if (id == R.id.nav_menu_courses)
        {
            setFragmentMyCourses();
        }
        item.setChecked(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    protected void doContextualAction(SystemContext my_context, Course related_course)
    {
        if (my_context == last_context && related_course == last_related_course)
        {
            Log.i("MainActivity","doContextualAction: system context hasnt changed");
            return;
        }

        last_context = my_context;
        last_related_course = related_course;

        if (my_context == SystemContext.notPresent)
        {
            Log.i("MainActivity", "SYSTEM CONTEXT: NOT_PRESENT");
            setFragmentAllTasks();
        }
        else if (my_context == SystemContext.presentFirstHalf)
        {
            Log.i("MainActivity", "SYSTEM CONTEXT: PRESENT_FIRST_HALF");
            startCourseDetailsActivity(related_course);
        }
        else if (my_context == SystemContext.presentLastHalf)
        {
            Log.i("MainActivity", "SYSTEM CONTEXT: PRESENT_LAST_HALF");
            startAddTaskActivity(related_course);
        }
    }

    protected void setFragmentAllTasks()
    {
        setTitle("All Tasks");
        Fragment fragment = new TaskFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    protected void setFragmentMyCourses()
    {
        setTitle("My Courses");
        Fragment fragment = new CoursesListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    protected void startCourseDetailsActivity(Course relative_course)
    {
        if (relative_course == null)
        {
            Log.i("MainActivity","ERROR Startign CourseDetailActivity: relative_course is null");
            return;
        }

        Intent intent = new Intent(this, CourseDetailsActivity.class);
        intent.putExtra(AddTaskActivity.ASSOCIATED_COURSE_ID, relative_course.getName());

        startActivity(intent);
    }

    protected void startAddTaskActivity(Course relative_course)
    {
        Intent intent = new Intent(this, AddTaskActivity.class);
        intent.putExtra(AddTaskActivity.ASSOCIATED_COURSE_ID, relative_course.getName());

        startActivity(intent);
    }
}
