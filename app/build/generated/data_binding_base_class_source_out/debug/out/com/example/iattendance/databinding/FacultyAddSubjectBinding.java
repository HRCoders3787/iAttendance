// Generated by view binder compiler. Do not edit!
package com.example.iattendance.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.iattendance.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FacultyAddSubjectBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton addSubjectBtn;

  @NonNull
  public final AppBarLayout appBarLayout2;

  @NonNull
  public final TextInputLayout batch;

  @NonNull
  public final TextInputEditText batchCount;

  @NonNull
  public final TextInputEditText batchRange;

  @NonNull
  public final TextInputEditText courseName;

  @NonNull
  public final TextInputLayout div;

  @NonNull
  public final TextInputEditText division;

  @NonNull
  public final TextInputLayout sem;

  @NonNull
  public final TextInputLayout subCodeTb;

  @NonNull
  public final TextInputEditText subSemester;

  @NonNull
  public final TextInputLayout subTb;

  @NonNull
  public final TextInputLayout subType;

  @NonNull
  public final TextInputEditText subjectCode;

  @NonNull
  public final TextInputEditText subjectName;

  @NonNull
  public final TextInputEditText subjectType;

  @NonNull
  public final MaterialToolbar toolbar;

  private FacultyAddSubjectBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton addSubjectBtn, @NonNull AppBarLayout appBarLayout2,
      @NonNull TextInputLayout batch, @NonNull TextInputEditText batchCount,
      @NonNull TextInputEditText batchRange, @NonNull TextInputEditText courseName,
      @NonNull TextInputLayout div, @NonNull TextInputEditText division,
      @NonNull TextInputLayout sem, @NonNull TextInputLayout subCodeTb,
      @NonNull TextInputEditText subSemester, @NonNull TextInputLayout subTb,
      @NonNull TextInputLayout subType, @NonNull TextInputEditText subjectCode,
      @NonNull TextInputEditText subjectName, @NonNull TextInputEditText subjectType,
      @NonNull MaterialToolbar toolbar) {
    this.rootView = rootView;
    this.addSubjectBtn = addSubjectBtn;
    this.appBarLayout2 = appBarLayout2;
    this.batch = batch;
    this.batchCount = batchCount;
    this.batchRange = batchRange;
    this.courseName = courseName;
    this.div = div;
    this.division = division;
    this.sem = sem;
    this.subCodeTb = subCodeTb;
    this.subSemester = subSemester;
    this.subTb = subTb;
    this.subType = subType;
    this.subjectCode = subjectCode;
    this.subjectName = subjectName;
    this.subjectType = subjectType;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FacultyAddSubjectBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FacultyAddSubjectBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.faculty_add_subject, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FacultyAddSubjectBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addSubjectBtn;
      MaterialButton addSubjectBtn = ViewBindings.findChildViewById(rootView, id);
      if (addSubjectBtn == null) {
        break missingId;
      }

      id = R.id.appBarLayout2;
      AppBarLayout appBarLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (appBarLayout2 == null) {
        break missingId;
      }

      id = R.id.batch;
      TextInputLayout batch = ViewBindings.findChildViewById(rootView, id);
      if (batch == null) {
        break missingId;
      }

      id = R.id.batchCount;
      TextInputEditText batchCount = ViewBindings.findChildViewById(rootView, id);
      if (batchCount == null) {
        break missingId;
      }

      id = R.id.batchRange;
      TextInputEditText batchRange = ViewBindings.findChildViewById(rootView, id);
      if (batchRange == null) {
        break missingId;
      }

      id = R.id.courseName;
      TextInputEditText courseName = ViewBindings.findChildViewById(rootView, id);
      if (courseName == null) {
        break missingId;
      }

      id = R.id.div;
      TextInputLayout div = ViewBindings.findChildViewById(rootView, id);
      if (div == null) {
        break missingId;
      }

      id = R.id.division;
      TextInputEditText division = ViewBindings.findChildViewById(rootView, id);
      if (division == null) {
        break missingId;
      }

      id = R.id.sem;
      TextInputLayout sem = ViewBindings.findChildViewById(rootView, id);
      if (sem == null) {
        break missingId;
      }

      id = R.id.sub_code_tb;
      TextInputLayout subCodeTb = ViewBindings.findChildViewById(rootView, id);
      if (subCodeTb == null) {
        break missingId;
      }

      id = R.id.subSemester;
      TextInputEditText subSemester = ViewBindings.findChildViewById(rootView, id);
      if (subSemester == null) {
        break missingId;
      }

      id = R.id.sub_tb;
      TextInputLayout subTb = ViewBindings.findChildViewById(rootView, id);
      if (subTb == null) {
        break missingId;
      }

      id = R.id.sub_type;
      TextInputLayout subType = ViewBindings.findChildViewById(rootView, id);
      if (subType == null) {
        break missingId;
      }

      id = R.id.subjectCode;
      TextInputEditText subjectCode = ViewBindings.findChildViewById(rootView, id);
      if (subjectCode == null) {
        break missingId;
      }

      id = R.id.subjectName;
      TextInputEditText subjectName = ViewBindings.findChildViewById(rootView, id);
      if (subjectName == null) {
        break missingId;
      }

      id = R.id.subjectType;
      TextInputEditText subjectType = ViewBindings.findChildViewById(rootView, id);
      if (subjectType == null) {
        break missingId;
      }

      id = R.id.toolbar;
      MaterialToolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new FacultyAddSubjectBinding((ConstraintLayout) rootView, addSubjectBtn, appBarLayout2,
          batch, batchCount, batchRange, courseName, div, division, sem, subCodeTb, subSemester,
          subTb, subType, subjectCode, subjectName, subjectType, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
