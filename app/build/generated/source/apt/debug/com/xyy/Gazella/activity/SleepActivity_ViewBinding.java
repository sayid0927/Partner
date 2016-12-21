// Generated code from Butter Knife. Do not modify!
package com.xyy.Gazella.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.ysp.smartwatch.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SleepActivity_ViewBinding<T extends SleepActivity> implements Unbinder {
  protected T target;

  private View view2131689751;

  private View view2131689752;

  private View view2131689753;

  private View view2131689629;

  private View view2131689630;

  private View view2131689631;

  @UiThread
  public SleepActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.widget = Utils.findRequiredViewAsType(source, R.id.calendarView, "field 'widget'", MaterialCalendarView.class);
    view = Utils.findRequiredView(source, R.id.btnExit, "field 'btnExit' and method 'onClick'");
    target.btnExit = Utils.castView(view, R.id.btnExit, "field 'btnExit'", LinearLayout.class);
    view2131689751 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnOpt, "field 'btnOpt' and method 'onClick'");
    target.btnOpt = Utils.castView(view, R.id.btnOpt, "field 'btnOpt'", Button.class);
    view2131689752 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnDate, "field 'btnDate' and method 'onClick'");
    target.btnDate = Utils.castView(view, R.id.btnDate, "field 'btnDate'", Button.class);
    view2131689753 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.TVTitle = Utils.findRequiredViewAsType(source, R.id.TVTitle, "field 'TVTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.button_day, "field 'butDay' and method 'onClick'");
    target.butDay = Utils.castView(view, R.id.button_day, "field 'butDay'", Button.class);
    view2131689629 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.button_week, "field 'butWeek' and method 'onClick'");
    target.butWeek = Utils.castView(view, R.id.button_week, "field 'butWeek'", Button.class);
    view2131689630 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.button_month, "field 'butMonth' and method 'onClick'");
    target.butMonth = Utils.castView(view, R.id.button_month, "field 'butMonth'", Button.class);
    view2131689631 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewpager'", ViewPager.class);
    target.llCheckDate = Utils.findRequiredViewAsType(source, R.id.ll_check_date, "field 'llCheckDate'", LinearLayout.class);
    target.SlsleepActivity = Utils.findRequiredViewAsType(source, R.id.sleepActivity, "field 'SlsleepActivity'", ScrollView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.widget = null;
    target.btnExit = null;
    target.btnOpt = null;
    target.btnDate = null;
    target.TVTitle = null;
    target.butDay = null;
    target.butWeek = null;
    target.butMonth = null;
    target.viewpager = null;
    target.llCheckDate = null;
    target.SlsleepActivity = null;

    view2131689751.setOnClickListener(null);
    view2131689751 = null;
    view2131689752.setOnClickListener(null);
    view2131689752 = null;
    view2131689753.setOnClickListener(null);
    view2131689753 = null;
    view2131689629.setOnClickListener(null);
    view2131689629 = null;
    view2131689630.setOnClickListener(null);
    view2131689630 = null;
    view2131689631.setOnClickListener(null);
    view2131689631 = null;

    this.target = null;
  }
}
