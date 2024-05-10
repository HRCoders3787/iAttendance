// Generated by view binder compiler. Do not edit!
package com.example.iattendance.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.iattendance.R;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ScanWifiScreenBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView categoryTxt;

  @NonNull
  public final ListView listView;

  @NonNull
  public final MaterialButton scanBtn;

  @NonNull
  public final TextView textView;

  private ScanWifiScreenBinding(@NonNull ConstraintLayout rootView, @NonNull TextView categoryTxt,
      @NonNull ListView listView, @NonNull MaterialButton scanBtn, @NonNull TextView textView) {
    this.rootView = rootView;
    this.categoryTxt = categoryTxt;
    this.listView = listView;
    this.scanBtn = scanBtn;
    this.textView = textView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ScanWifiScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ScanWifiScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.scan_wifi_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ScanWifiScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.category_txt;
      TextView categoryTxt = ViewBindings.findChildViewById(rootView, id);
      if (categoryTxt == null) {
        break missingId;
      }

      id = R.id.listView;
      ListView listView = ViewBindings.findChildViewById(rootView, id);
      if (listView == null) {
        break missingId;
      }

      id = R.id.scan_btn;
      MaterialButton scanBtn = ViewBindings.findChildViewById(rootView, id);
      if (scanBtn == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new ScanWifiScreenBinding((ConstraintLayout) rootView, categoryTxt, listView, scanBtn,
          textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
