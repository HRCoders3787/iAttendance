// Generated by view binder compiler. Do not edit!
package com.example.iattendance.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.iattendance.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FacultySignupPg2Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextInputLayout adminNameTb;

  @NonNull
  public final TextInputLayout adminPassTb;

  @NonNull
  public final ImageButton backBtn;

  @NonNull
  public final LinearLayout linearLayout4;

  @NonNull
  public final ScrollView scrollView2;

  @NonNull
  public final MaterialButton signupBtn;

  private FacultySignupPg2Binding(@NonNull ConstraintLayout rootView,
      @NonNull TextInputLayout adminNameTb, @NonNull TextInputLayout adminPassTb,
      @NonNull ImageButton backBtn, @NonNull LinearLayout linearLayout4,
      @NonNull ScrollView scrollView2, @NonNull MaterialButton signupBtn) {
    this.rootView = rootView;
    this.adminNameTb = adminNameTb;
    this.adminPassTb = adminPassTb;
    this.backBtn = backBtn;
    this.linearLayout4 = linearLayout4;
    this.scrollView2 = scrollView2;
    this.signupBtn = signupBtn;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FacultySignupPg2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FacultySignupPg2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.faculty_signup_pg2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FacultySignupPg2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.admin_name_tb;
      TextInputLayout adminNameTb = ViewBindings.findChildViewById(rootView, id);
      if (adminNameTb == null) {
        break missingId;
      }

      id = R.id.admin_pass_tb;
      TextInputLayout adminPassTb = ViewBindings.findChildViewById(rootView, id);
      if (adminPassTb == null) {
        break missingId;
      }

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

      id = R.id.scrollView2;
      ScrollView scrollView2 = ViewBindings.findChildViewById(rootView, id);
      if (scrollView2 == null) {
        break missingId;
      }

      id = R.id.signup_btn;
      MaterialButton signupBtn = ViewBindings.findChildViewById(rootView, id);
      if (signupBtn == null) {
        break missingId;
      }

      return new FacultySignupPg2Binding((ConstraintLayout) rootView, adminNameTb, adminPassTb,
          backBtn, linearLayout4, scrollView2, signupBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
