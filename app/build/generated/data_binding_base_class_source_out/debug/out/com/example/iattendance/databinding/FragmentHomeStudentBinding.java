// Generated by view binder compiler. Do not edit!
package com.example.iattendance.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.iattendance.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeStudentBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final TextView activeText;

  @NonNull
  public final RelativeLayout animatedComponent;

  @NonNull
  public final RecyclerView categoryRecView;

  @NonNull
  public final ImageView emptyIcon;

  @NonNull
  public final TextView firstLetter;

  @NonNull
  public final TextView id;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final Spinner spinnerSemesters;

  @NonNull
  public final TextView studentName;

  @NonNull
  public final ImageView tickIcon;

  private FragmentHomeStudentBinding(@NonNull FrameLayout rootView, @NonNull TextView activeText,
      @NonNull RelativeLayout animatedComponent, @NonNull RecyclerView categoryRecView,
      @NonNull ImageView emptyIcon, @NonNull TextView firstLetter, @NonNull TextView id,
      @NonNull LinearLayout linearLayout, @NonNull Spinner spinnerSemesters,
      @NonNull TextView studentName, @NonNull ImageView tickIcon) {
    this.rootView = rootView;
    this.activeText = activeText;
    this.animatedComponent = animatedComponent;
    this.categoryRecView = categoryRecView;
    this.emptyIcon = emptyIcon;
    this.firstLetter = firstLetter;
    this.id = id;
    this.linearLayout = linearLayout;
    this.spinnerSemesters = spinnerSemesters;
    this.studentName = studentName;
    this.tickIcon = tickIcon;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeStudentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeStudentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home_student, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeStudentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.activeText;
      TextView activeText = ViewBindings.findChildViewById(rootView, id);
      if (activeText == null) {
        break missingId;
      }

      id = R.id.animatedComponent;
      RelativeLayout animatedComponent = ViewBindings.findChildViewById(rootView, id);
      if (animatedComponent == null) {
        break missingId;
      }

      id = R.id.category_recView;
      RecyclerView categoryRecView = ViewBindings.findChildViewById(rootView, id);
      if (categoryRecView == null) {
        break missingId;
      }

      id = R.id.empty_icon;
      ImageView emptyIcon = ViewBindings.findChildViewById(rootView, id);
      if (emptyIcon == null) {
        break missingId;
      }

      id = R.id.first_letter;
      TextView firstLetter = ViewBindings.findChildViewById(rootView, id);
      if (firstLetter == null) {
        break missingId;
      }

      id = R.id.id;
      TextView id_ = ViewBindings.findChildViewById(rootView, id);
      if (id_ == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.spinner_semesters;
      Spinner spinnerSemesters = ViewBindings.findChildViewById(rootView, id);
      if (spinnerSemesters == null) {
        break missingId;
      }

      id = R.id.studentName;
      TextView studentName = ViewBindings.findChildViewById(rootView, id);
      if (studentName == null) {
        break missingId;
      }

      id = R.id.tickIcon;
      ImageView tickIcon = ViewBindings.findChildViewById(rootView, id);
      if (tickIcon == null) {
        break missingId;
      }

      return new FragmentHomeStudentBinding((FrameLayout) rootView, activeText, animatedComponent,
          categoryRecView, emptyIcon, firstLetter, id_, linearLayout, spinnerSemesters, studentName,
          tickIcon);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
