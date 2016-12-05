package com.falquinho.alere.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.falquinho.alere.R;
import com.falquinho.alere.activities.CourseDetailsActivity;
import com.falquinho.alere.controller.CoursesRepository;
import com.falquinho.alere.model.Course;


public class CourseDetailFragment extends Fragment
{
    Course course = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        String course_id = getArguments().getString(CourseDetailsActivity.ASSOCIATED_COURSE_ID);
        course = CoursesRepository.getCourse(course_id);

        return inflater.inflate(R.layout.fragment_course_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle saved_instance)
    {
        super.onActivityCreated(saved_instance);

        if (course != null)
        {
            setupViewWeekRepetition();
            setupViewStartTime();
            setupViewDuration();
        }
    }

    private void setupViewWeekRepetition()
    {
        CheckBox cb;

        cb = (CheckBox) getActivity().findViewById(R.id.checkBox_course_detail_sun);
        cb.setChecked(course.happensOnDay(0));
        cb.setClickable(false);

        cb = (CheckBox) getActivity().findViewById(R.id.checkBox_course_detail_mon);
        cb.setChecked(course.happensOnDay(1));
        cb.setClickable(false);

        cb = (CheckBox) getActivity().findViewById(R.id.checkBox_course_detail_tue);
        cb.setChecked(course.happensOnDay(2));
        cb.setClickable(false);

        cb = (CheckBox) getActivity().findViewById(R.id.checkBox_course_detail_wed);
        cb.setChecked(course.happensOnDay(3));
        cb.setClickable(false);

        cb = (CheckBox) getActivity().findViewById(R.id.checkBox_course_detail_thu);
        cb.setChecked(course.happensOnDay(4));
        cb.setClickable(false);

        cb = (CheckBox) getActivity().findViewById(R.id.checkBox_course_detail_fri);
        cb.setChecked(course.happensOnDay(5));
        cb.setClickable(false);

        cb = (CheckBox) getActivity().findViewById(R.id.checkBox_course_detail_sat);
        cb.setChecked(course.happensOnDay(6));
        cb.setClickable(false);
    }

    private void setupViewStartTime()
    {
        int start = course.getStartTime();

        TextView tv;
        tv = (TextView)getActivity().findViewById(R.id.textView_course_detail_start);
        tv.setText(String.valueOf(start/60).concat(":").concat(String.valueOf(start%60)));
    }

    private void setupViewDuration()
    {
        TextView tv;
        tv = (TextView)getActivity().findViewById(R.id.textView_course_detail_duration);
        tv.setText(String.valueOf(course.getDuration()));
    }
}
