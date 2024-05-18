// Generated by view binder compiler. Do not edit!
package com.example.iattendance.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.iattendance.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeFacultyBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final FloatingActionButton addSubjectFab;

  @NonNull
  public final TextView facultyCollCode;

  @NonNull
  public final TextView facultyName;

  @NonNull
  public final TextView firstLetter;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final RecyclerView rvParent;

  @NonNull
  public final AutoCompleteTextView selectDivision;

  @NonNull
  public final AutoCompleteTextView selectSubType;

  private FragmentHomeFacultyBinding(@NonNull FrameLayout rootView,
      @NonNull FloatingActionButton addSubjectFab, @NonNull TextView facultyCollCode,
      @NonNull TextView facultyName, @NonNull TextView firstLetter,
      @NonNull LinearLayout linearLayout, @NonNull RecyclerView rvParent,
      @NonNull AutoCompleteTextView selectDivision, @NonNull AutoCompleteTextView selectSubType) {
    this.rootView = rootView;
    this.addSubjectFab = addSubjectFab;
    this.facultyCollCode = facultyCollCode;
    this.facultyName = facultyName;
    this.firstLetter = firstLetter;
    this.linearLayout = linearLayout;
    this.rvParent = rvParent;
    this.selectDivision = selectDivision;
    this.selectSubType = selectSubType;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeFacultyBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeFacultyBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home_faculty, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeFacultyBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_subject_fab;
      FloatingActionButton addSubjectFab = ViewBindings.findChildViewById(rootView, id);
      if (addSubjectFab == null) {
        break missingId;
      }

      id = R.id.faculty_coll_code;
      TextView facultyCollCode = ViewBindings.findChildViewById(rootView, id);
      if (facultyCollCode == null) {
        break missingId;
      }

      id = R.id.faculty_name;
      TextView facultyName = ViewBindings.findChildViewById(rootView, id);
      if (facultyName == null) {
        break missingId;
      }

      id = R.id.first_letter;
      TextView firstLetter = ViewBindings.findChildViewById(rootView, id);
      if (firstLetter == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.rv_parent;
      RecyclerView rvParent = ViewBindings.findChildViewById(rootView, id);
      if (rvParent == null) {
        break missingId;
      }

      id = R.id.selectDivision;
      AutoCompleteTextView selectDivision = ViewBindings.findChildViewById(rootView, id);
      if (selectDivision == null) {
        break missingId;
      }

      id = R.id.selectSubType;
      AutoCompleteTextView selectSubType = ViewBindings.findChildViewById(rootView, id);
      if (selectSubType == null) {
        break missingId;
      }

      return new FragmentHomeFacultyBinding((FrameLayout) rootView, addSubjectFab, facultyCollCode,
          facultyName, firstLetter, linearLayout, rvParent, selectDivision, selectSubType);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
