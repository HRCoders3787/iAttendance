// Generated by view binder compiler. Do not edit!
package com.example.iattendance.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.iattendance.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSettingsAdminBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final MaterialButton aboutBtn;

  @NonNull
  public final AppBarLayout appBarLayout;

  @NonNull
  public final MaterialButton collCodeBtn;

  @NonNull
  public final MaterialButton deleteBtn;

  @NonNull
  public final MaterialButton editProfBtn;

  @NonNull
  public final MaterialButton helpBtn;

  @NonNull
  public final MaterialButton logOutBtn;

  @NonNull
  public final ScrollView scrollView3;

  @NonNull
  public final MaterialToolbar toolbar;

  @NonNull
  public final MaterialButton workingBtn;

  private FragmentSettingsAdminBinding(@NonNull FrameLayout rootView,
      @NonNull MaterialButton aboutBtn, @NonNull AppBarLayout appBarLayout,
      @NonNull MaterialButton collCodeBtn, @NonNull MaterialButton deleteBtn,
      @NonNull MaterialButton editProfBtn, @NonNull MaterialButton helpBtn,
      @NonNull MaterialButton logOutBtn, @NonNull ScrollView scrollView3,
      @NonNull MaterialToolbar toolbar, @NonNull MaterialButton workingBtn) {
    this.rootView = rootView;
    this.aboutBtn = aboutBtn;
    this.appBarLayout = appBarLayout;
    this.collCodeBtn = collCodeBtn;
    this.deleteBtn = deleteBtn;
    this.editProfBtn = editProfBtn;
    this.helpBtn = helpBtn;
    this.logOutBtn = logOutBtn;
    this.scrollView3 = scrollView3;
    this.toolbar = toolbar;
    this.workingBtn = workingBtn;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSettingsAdminBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSettingsAdminBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_settings_admin, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSettingsAdminBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.about_btn;
      MaterialButton aboutBtn = ViewBindings.findChildViewById(rootView, id);
      if (aboutBtn == null) {
        break missingId;
      }

      id = R.id.appBarLayout;
      AppBarLayout appBarLayout = ViewBindings.findChildViewById(rootView, id);
      if (appBarLayout == null) {
        break missingId;
      }

      id = R.id.coll_code_btn;
      MaterialButton collCodeBtn = ViewBindings.findChildViewById(rootView, id);
      if (collCodeBtn == null) {
        break missingId;
      }

      id = R.id.delete_btn;
      MaterialButton deleteBtn = ViewBindings.findChildViewById(rootView, id);
      if (deleteBtn == null) {
        break missingId;
      }

      id = R.id.edit_prof_btn;
      MaterialButton editProfBtn = ViewBindings.findChildViewById(rootView, id);
      if (editProfBtn == null) {
        break missingId;
      }

      id = R.id.help_btn;
      MaterialButton helpBtn = ViewBindings.findChildViewById(rootView, id);
      if (helpBtn == null) {
        break missingId;
      }

      id = R.id.log_out_btn;
      MaterialButton logOutBtn = ViewBindings.findChildViewById(rootView, id);
      if (logOutBtn == null) {
        break missingId;
      }

      id = R.id.scrollView3;
      ScrollView scrollView3 = ViewBindings.findChildViewById(rootView, id);
      if (scrollView3 == null) {
        break missingId;
      }

      id = R.id.toolbar;
      MaterialToolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.working_btn;
      MaterialButton workingBtn = ViewBindings.findChildViewById(rootView, id);
      if (workingBtn == null) {
        break missingId;
      }

      return new FragmentSettingsAdminBinding((FrameLayout) rootView, aboutBtn, appBarLayout,
          collCodeBtn, deleteBtn, editProfBtn, helpBtn, logOutBtn, scrollView3, toolbar,
          workingBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
