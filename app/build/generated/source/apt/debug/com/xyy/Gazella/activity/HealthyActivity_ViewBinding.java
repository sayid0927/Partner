// Generated code from Butter Knife. Do not modify!
package com.xyy.Gazella.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ysp.smartwatch.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HealthyActivity_ViewBinding<T extends HealthyActivity> implements Unbinder {
  protected T target;

  private View view2131624077;

  private View view2131624078;

  private View view2131624170;

  private View view2131624171;

  @UiThread
  public HealthyActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.step, "field 'step' and method 'onClick'");
    target.step = Utils.castView(view, R.id.step, "field 'step'", TextView.class);
    view2131624077 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.sleep, "field 'sleep' and method 'onClick'");
    target.sleep = Utils.castView(view, R.id.sleep, "field 'sleep'", TextView.class);
    view2131624078 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnExit, "field 'btnExit' and method 'onClick'");
    target.btnExit = Utils.castView(view, R.id.btnExit, "field 'btnExit'", LinearLayout.class);
    view2131624170 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnOpt, "field 'btnOpt' and method 'onClick'");
    target.btnOpt = Utils.castView(view, R.id.btnOpt, "field 'btnOpt'", Button.class);
    view2131624171 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.TVTitle = Utils.findRequiredViewAsType(source, R.id.TVTitle, "field 'TVTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.step = null;
    target.sleep = null;
    target.btnExit = null;
    target.btnOpt = null;
    target.TVTitle = null;

    view2131624077.setOnClickListener(null);
    view2131624077 = null;
    view2131624078.setOnClickListener(null);
    view2131624078 = null;
    view2131624170.setOnClickListener(null);
    view2131624170 = null;
    view2131624171.setOnClickListener(null);
    view2131624171 = null;

    this.target = null;
  }
}