package com.example.iattendance.Utils.Subjects.db;

public class SubjectModal {

    String batches, collegeCode, course, division, facultyName;
    String subSemester, subject, subjectCode, subjectType;

    public SubjectModal() {
    }

    public SubjectModal(String batches, String collegeCode, String course, String division, String facultyName, String subSemester, String subject, String subjectCode, String subjectType) {
        this.batches = batches;
        this.collegeCode = collegeCode;
        this.course = course;
        this.division = division;
        this.facultyName = facultyName;
        this.subSemester = subSemester;
        this.subject = subject;
        this.subjectCode = subjectCode;
        this.subjectType = subjectType;
    }

    public String getBatches() {
        return batches;
    }

    public void setBatches(String batches) {
        this.batches = batches;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getSubSemester() {
        return subSemester;
    }

    public void setSubSemester(String subSemester) {
        this.subSemester = subSemester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }
}
