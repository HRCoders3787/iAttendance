package com.example.iattendance.Faculty_Attendance_Screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class StudListViewPagerAdapter extends FragmentPagerAdapter {

    public StudListViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PresentStud();
        } else {
            return new AbsentStud();
        }
    }

    @Override
    public int getCount() {
        return 2;       //No. of tabs
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Present";
        }
        else{
            return "Absent";
        }
    }
}
