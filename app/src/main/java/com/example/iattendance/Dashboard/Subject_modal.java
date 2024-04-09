package com.example.iattendance.Dashboard;

public class Subject_modal {
    String subj_name, prof_name, abbr, subj_first_Letter, total_cnt, present_cnt, attendance_percent;

    public Subject_modal(String subj_name, String prof_name, String abbr, String subj_first_Letter, String total_cnt, String present_cnt, String attendance_percent) {
        this.subj_name = subj_name;
        this.prof_name = prof_name;
        this.abbr = abbr;
        this.subj_first_Letter = subj_first_Letter;
        this.total_cnt = total_cnt;
        this.present_cnt = present_cnt;
        this.attendance_percent = attendance_percent;
    }
}