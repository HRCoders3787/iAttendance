// Generated by view binder compiler. Do not edit!
package com.example.iattendance.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.iattendance.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class StudentSignupPg3Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton backBtn;

  @NonNull
  public final LinearLayout linearLayout4;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final ScrollView scrollView2;

  @NonNull
  public final MaterialButton signUpBtn;

  @NonNull
  public final TextInputEditText studCourse;

  @NonNull
  public final TextInputEditText studDivision;

  @NonNull
  public final TextInputEditText studName;

  @NonNull
  public final TextInputEditText studPassword;

  @NonNull
  public final TextInputEditText studRollCall;

  private StudentSignupPg3Binding(@NonNull ConstraintLayout rootView, @NonNull ImageButton backBtn,
      @NonNull LinearLayout linearLayout4, @NonNull ProgressBar progressBar,
      @NonNull ScrollView scrollView2, @NonNull MaterialButton signUpBtn,
      @NonNull TextInputEditText studCourse, @NonNull TextInputEditText studDivision,
      @NonNull TextInputEditText studName, @NonNull TextInputEditText studPassword,
      @NonNull TextInputEditText studRollCall) {
    this.rootView = rootView;
    this.backBtn = backBtn;
    this.linearLayout4 = linearLayout4;
    this.progressBar = progressBar;
    this.scrollView2 = scrollView2;
    this.signUpBtn = signUpBtn;
    this.studCourse = studCourse;
    this.studDivision = studDivision;
    this.studName = studName;
    this.studPassword = studPassword;
    this.studRollCall = studRollCall;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static StudentSignupPg3Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static StudentSignupPg3Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.student_signup_pg3, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static StudentSignupPg3Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backBtn;
      ImageButton backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.linearLayout4;
      LinearLayout linearLayout4 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout4 == null) {
        break missingId;
      }

      id = R.id.progress_bar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.scrollView2;
      ScrollView scrollView2 = ViewBindings.findChildViewById(rootView, id);
      if (scrollView2 == null) {
        break missingId;
      }

      id = R.id.signUpBtn;
      MaterialButton signUpBtn = ViewBindings.findChildViewById(rootView, id);
      if (signUpBtn == null) {
        break missingId;
      }

      id = R.id.studCourse;
      TextInputEditText studCourse = ViewBindings.findChildViewById(rootView, id);
      if (studCourse == null) {
        break missingId;
      }

      id = R.id.studDivision;
      TextInputEditText studDivision = ViewBindings.findChildViewById(rootView, id);
      if (studDivision == null) {
        break missingId;
      }

      id = R.id.studName;
      TextInputEditText studName = ViewBindings.findChildViewById(rootView, id);
      if (studName == null) {
        break missingId;
      }

      id = R.id.studPassword;
      TextInputEditText studPassword = ViewBindings.findChildViewById(rootView, id);
      if (studPassword == null) {
        break missingId;
      }

      id = R.id.studRollCall;
      TextInputEditText studRollCall = ViewBindings.findChildViewById(rootView, id);
      if (studRollCall == null) {
        break missingId;
      }

      return new StudentSignupPg3Binding((ConstraintLayout) rootView, backBtn, linearLayout4,
          progressBar, scrollView2, signUpBtn, studCourse, studDivision, studName, studPassword,
          studRollCall);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
