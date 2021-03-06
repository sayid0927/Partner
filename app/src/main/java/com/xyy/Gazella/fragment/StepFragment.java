package com.xyy.Gazella.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.polidea.rxandroidble.RxBleConnection;
import com.xyy.Gazella.activity.HealthyActivity;
import com.xyy.Gazella.activity.StepActivity;
import com.xyy.Gazella.utils.BleUtils;
import com.xyy.Gazella.view.NumberProgressBar;
import com.xyy.Gazella.view.RiseNumberTextView;
import com.ysp.hybridtwatch.R;
import com.ysp.newband.BaseFragment;
import com.ysp.newband.PreferenceData;

import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/11.
 */

public class StepFragment extends BaseFragment {
    @BindView(R.id.circle)
    ImageView circle;
    @BindView(R.id.step_num)
    public RiseNumberTextView stepNum;
    @BindView(R.id.step_target)
    TextView stepTarget;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.cal)
    TextView cal;
    @BindView(R.id.details)
    TextView details;
    @BindView(R.id.numberbar)
    NumberProgressBar numberbar;
    @BindView(R.id.ll_NumberProgressBar)
    LinearLayout llNumberProgressBar;
    @BindView(R.id.ll_quality)
    LinearLayout llQuality;
    @BindView(R.id.iv_tip)
    ImageView ivTip;
    @BindView(R.id.tv_day_1)
    TextView tvDay1;
    @BindView(R.id.tv_day_2)
    TextView tvDay2;
    @BindView(R.id.tv_day_3)
    TextView tvDay3;
    @BindView(R.id.tv_day_4)
    TextView tvDay4;
    @BindView(R.id.tv_day_5)
    TextView tvDay5;
    @BindView(R.id.tv_day_6)
    TextView tvDay6;
    @BindView(R.id.tv_day_7)
    TextView tvDay7;
    @BindView(R.id.tv_synchronizationtime)
    TextView tvSynchronizationtime;
    private View view;
    private BleUtils bleUtils;
    private Observable<RxBleConnection> connectionObservable;
    private setStepNumTextOnEndListener setStepNumTextOnEndListener;
    private  int barNum;
    private  static  String TAG= StepFragment.class.getName();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1001:
                    break;
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_step, container, false);
        ButterKnife.bind(this, view);
        initView();
        connectionObservable = HealthyActivity.install.connectionObservable;
        bleUtils = new BleUtils();
        if (connectionObservable != null)
            getTodayStepPost();
        return view;
    }

    public void getTodayStepPost() {
        if (connectionObservable != null)
            mHandler.post(getTodayStep);
    }

    public void removeTodayStepPost() {
        if (getTodayStep != null)
            mHandler.removeCallbacks(getTodayStep);
    }

    private void initView() {
        String Synchronizationtime = PreferenceData.getSynchronizationTime(getActivity());
        if (Synchronizationtime != null && !Synchronizationtime.equals("")) {
            tvSynchronizationtime.setText("最后同步时间  "+Synchronizationtime);
        }
        final ViewGroup.LayoutParams params = circle.getLayoutParams();
        ViewTreeObserver vto = circle.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                params.width = circle.getHeight();
                circle.setLayoutParams(params);
                return true;
            }
        });
        stepTarget.setText(String.valueOf(PreferenceData.getTargetRunValue(getActivity())));
        stepNum.setOnEndListener(new RiseNumberTextView.EndListener() {
            @Override
            public void onEndFinish() {
                if (setStepNumTextOnEndListener != null) {
                    setStepNumTextOnEndListener.setStepNumTextOnEndListener();
                }
            }
        });
    }

    @OnClick(R.id.circle)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circle:
                Intent intent = new Intent(getActivity(), StepActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_righttoleft);
                break;
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            barNum++;
            mHandler.sendEmptyMessage(1001);
            mHandler.postDelayed(this, 100);
        }
    };

    Runnable getTodayStep = new Runnable() {
        @Override
        public void run() {
            if (connectionObservable != null && HealthyActivity.install.isNotify)
                Write(bleUtils.getTodayStep(), connectionObservable);
            mHandler.sendEmptyMessage(1002);
            mHandler.postDelayed(this, 1000);
        }
    };

    public void setSynchronizationData() {
        llNumberProgressBar.setVisibility(View.VISIBLE);
        llQuality.setVisibility(View.GONE);
        numberbar.setProgress(0);
        tvDay1.setText("...");
        tvDay2.setText("...");
        tvDay3.setText("...");
        tvDay4.setText("...");
        tvDay5.setText("...");
        tvDay6.setText("...");
        tvDay7.setText("...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Write(bleUtils.getStepData(6), connectionObservable);
            }
        }, 1000);
    }

    public void setBerbarNum(int type, int day) {
        switch (type) {
            case 1:
                tvDay1.setText(String.valueOf(day) + "号" + "更新完成");
                numberbar.setProgress(10);
                break;

            case 2:
                tvDay2.setText(String.valueOf(day) + "号" + "更新完成");
                numberbar.setProgress(30);
                break;

            case 3:
                tvDay3.setText(String.valueOf(day) + "号" + "更新完成");
                numberbar.setProgress(40);
                break;

            case 4:
                tvDay4.setText(String.valueOf(day) + "号" + "更新完成");
                numberbar.setProgress(60);
                break;

            case 5:
                tvDay5.setText(String.valueOf(day) + "号" + "更新完成");
                numberbar.setProgress(80);
                break;

            case 6:
                tvDay6.setText(String.valueOf(day) + "号" + "更新完成");
                numberbar.setProgress(90);
                break;

            case 7:
                tvDay7.setText(String.valueOf(day) + "号" + "更新完成");
                numberbar.setProgress(100);
                llNumberProgressBar.setVisibility(View.GONE);
                llQuality.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setStepNum(int num, RiseNumberTextView stepNum) {
        // 设置数据
        stepNum.withNumber(num);
        // 设置动画播放时间
        stepNum.setDuration(1000);
        // 开始播放动画
        stepNum.start();
    }

    public void setStepNumFalse(int num) {
        stepNum.setText(String.valueOf(num));
    }

    public void setCalcalNum(String num) {
        cal.setText(num);
    }

    public void setTime(String t) {
        time.setText(t);
    }

    public void setDistanceNum(String num) {
        distance.setText(num);
    }

    public void setIvTip(Drawable drawable, String Str) {
        ivTip.setBackground(drawable);
        details.setText(Str);
    }

   public void  setTvSynchronizationtime(){
       Time mCalendar;
       StringBuffer sb= new StringBuffer();
       TimeZone tz = TimeZone.getTimeZone(PreferenceData.getTimeZonesState(getActivity()));
       mCalendar = new Time(tz.getID());
       mCalendar.setToNow();
       String time=sb.append(String.valueOf(mCalendar.year)).append(".").append(String.valueOf(mCalendar.month+1)).append(".")
               .append(String.valueOf(mCalendar.monthDay)).append("  ").append(String.valueOf(mCalendar.hour)).append(":")
               .append(String.valueOf(mCalendar.minute)).append(":").append(String.valueOf(mCalendar.second)).toString();
       tvSynchronizationtime.setText("最后同步时间  "+time);
       PreferenceData.setSynchronizationTime(getActivity(),time);
   }


    public interface setStepNumTextOnEndListener {
        void setStepNumTextOnEndListener();
    }

    public void setStepNumTextOnEndListener(setStepNumTextOnEndListener setStepNumTextOnEndListener) {
        this.setStepNumTextOnEndListener = setStepNumTextOnEndListener;
    }
}
