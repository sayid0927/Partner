package com.xyy.Gazella.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.partner.entity.Partner;
import com.xyy.Gazella.activity.SleepActivity;
import com.xyy.Gazella.utils.SomeUtills;
import com.ysp.hybridtwatch.R;
import com.ysp.newband.BaseFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/11.
 */

public class SleepDayFragment extends BaseFragment {
    @BindView(R.id.chart1)
    BarChart mChart;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.iv_left)
    LinearLayout ivLeft;
    @BindView(R.id.iv_right)
    LinearLayout ivRight;
    @BindView(R.id.ll_sleep_day)
    LinearLayout llSleepDay;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.ll_sleep_bata)
    LinearLayout llSleepBata;
    @BindView(R.id.tv_sleeptime)
    TextView tvSleeptime;
    @BindView(R.id.tv_lightsleepTime)
    TextView tvLightsleepTime;
    @BindView(R.id.tv_sleepingtime)
    TextView tvSleepingtime;
    @BindView(R.id.tv_awakeTime)
    TextView tvAwakeTime;
    @BindView(R.id.tv_awakeCount)
    TextView tvAwakeCount;
    private View view;
    private int widthChart = 0;
    private int heightChatr = 0;
    private ViewGroup.LayoutParams params;

    private String[] xValue ;
    private String[] XString = new String[24];
    private String[] yValue = new String[24];
    private String[] yesterdayValue = new String[12];
    private String[] todayValue = new String[12];
    ;
    private Time mCalendar;
    private int myear, month, day, previousDay;
    private StringBuffer sb = new StringBuffer();
    private List<Partner> todayPartners = new ArrayList<>();
    private List<Partner> yesterdayPartners = new ArrayList<>();
    private String strMonth, strDay;
    //    private int awakeTime, lightsleepTime, sleepingTime,sleepTime,awakeCount;
    private static String TAG = SleepDayFragment.class.getName();
    private String today,yeday,strday ;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_sleep_day, container, false);

        ButterKnife.bind(this, view);
        initTime();

        if (month < 10)
            strMonth = sb.append("0").append(String.valueOf(month)).toString();
        else
            strMonth = String.valueOf(month);
        sb.setLength(0);
        previousDay = day - 1;
        if (day < 10)
            strDay = sb.append("0").append(String.valueOf(day)).toString();
        else
            strDay = String.valueOf(day);
        sb.setLength(0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date date = null;
         strday = sb.append(String.valueOf(myear)).append(".").append(strMonth).append(".").append(strDay).toString();
        try {
            date = sdf.parse(strday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String yesterday = new SomeUtills().getAmountDate(date, 0, 0);
        initData(strday, yesterday);
        initChart();
        initView();
        tvDate.setText(yesterday);
        return view;
    }

    public void initData(String Today, String yesterday) {
        int sleepTime = 0;
        int lightsleepTime = 0;
        int sleepingTime = 0;
        int awakeTime = 0;
        int awakecount=0;
        boolean booleanAwake = true;
        xValue=new String[24];
        if (todayPartners != null || todayPartners.size() > 0) {
            todayPartners.clear();
            yesterdayPartners.clear();
        }
        todayPartners = SleepActivity.sleepActivity.mCommonUtils.queryByBuilder("sleep", Today);
        yesterdayPartners = SleepActivity.sleepActivity.mCommonUtils.queryByBuilder("sleep", yesterday);

        if (todayPartners.size() == 24 && yesterdayPartners.size() == 24 && !yesterday.equals(strday) ) {
            yesterdayPartners = yesterdayPartners.subList(12, 24);
            todayPartners = todayPartners.subList(0, 12);

            for (int i = 0; i < yesterdayPartners.size(); i++) {
                xValue[i] = yesterdayPartners.get(i).getSleep();
                XString[i] = yesterdayPartners.get(i).getTime();
            }
            for (int i = 0; i < todayPartners.size(); i++) {
                xValue[i + 12] = todayPartners.get(i).getSleep();
                XString[i + 12] = todayPartners.get(i).getTime();
            }
        }

              for (int i = 0; i < xValue.length; i++) {
                  String state = xValue[i];
                  if(state!=null) {
                      switch (state) {
                          case "0":
                              awakeTime += 1;
                              if (booleanAwake){
                                  awakecount++;
                                  booleanAwake=false;
                              }
                              break;
                          case "1":
                              sleepTime += 1;
                              lightsleepTime += 1;
                              booleanAwake=true;
                              break;
                          case "2":
                              sleepTime += 1;
                              sleepingTime += 1;
                              booleanAwake=true;
                              break;
                          case "3":
                              awakeTime += 1;
                              if (booleanAwake){
                                  awakecount++;
                                  booleanAwake=false;
                              }
                              break;
                      }
                  }
          }
        tvSleeptime.setText(String.valueOf(sleepTime));
        tvLightsleepTime.setText(String.valueOf(lightsleepTime));
        tvSleepingtime.setText(String.valueOf(sleepingTime));
        tvAwakeTime.setText(String.valueOf(awakeTime));
        tvAwakeCount.setText(String.valueOf(awakecount));
        updateUI(xValue);
    }


    private void initView() {
        params = mChart.getLayoutParams();
        ViewTreeObserver vto = mChart.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                heightChatr = mChart.getHeight();
                widthChart = mChart.getWidth();
                return true;
            }
        });
    }

    private void initChart() {
        mChart.setDescription("");
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);
        mChart.setBackground(getResources().getDrawable(R.drawable.page20_tubiao_bg));
        mChart.setBorderColor(Color.rgb(255, 255, 255));
        mChart.setDoubleTapToZoomEnabled(false);//双击缩放

        mChart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new SomeUtills().setCalendarViewGone(0);
                return false;
            }
        });

        XAxis xAxis = mChart.getXAxis();
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setTextColor(Color.rgb(255, 255, 255));
        xAxis.setAxisLineColor(Color.rgb(255, 255, 255));
        xAxis.setValueFormatter(new XAxisValue());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(10, false);
        xAxis.setDrawGridLines(false);

        mChart.getAxisLeft().setTextColor(Color.rgb(255, 255, 255));
        mChart.getAxisLeft().setAxisLineColor(Color.rgb(255, 255, 255));
        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisLeft().setEnabled(false);
        mChart.getAxisLeft().setAxisMinValue(5);
        //   mChart.getAxisLeft().setSpaceBottom(0);

        // setting data
        mChart.animateY(2500);   //动画
        mChart.getLegend().setEnabled(false);
        setChartData();
    }

    private void setChartData() {
        float Sober = 7;                 //清醒
        float LightSleep = 8;          //浅睡
        float DeepSleep = 8.8f;     //深睡
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        ArrayList<BarEntry> yVals2 = new ArrayList<>();
        ArrayList<BarEntry> yVals3 = new ArrayList<>();

            for (int i = 0; i < xValue.length; i++) {
                String state = xValue[i];
                if(state!=null) {
                    switch (state) {
                        case "3":
                            yVals1.add(new BarEntry(i, Sober));
                            break;
                        case "0":
                            yVals1.add(new BarEntry(i, Sober));
                            break;
                        case "1":
                            yVals2.add(new BarEntry(i, LightSleep));
                            break;
                        case "2":
                            yVals3.add(new BarEntry(i, DeepSleep));
                            break;
                    }
                }
        }
        BarDataSet set1;
        BarDataSet set2;
        BarDataSet set3;
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            set2 = (BarDataSet) mChart.getData().getDataSetByIndex(1);
            set2.setValues(yVals2);
            set3 = (BarDataSet) mChart.getData().getDataSetByIndex(2);
            set3.setValues(yVals3);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "");
            set2 = new BarDataSet(yVals2, "");
            set3 = new BarDataSet(yVals3, "");
            set1.setColor(Color.rgb(119, 211, 252));
//            set1.setColor(Color.rgb(255, 255, 255));
            set2.setColor(Color.rgb(3, 137, 198));
            set3.setColor(Color.rgb(0, 61, 89));
            // set1.setColors(CreateColor.MATERIAL_COLORS);
            set1.setDrawValues(false);
            set2.setDrawValues(false);
            set3.setDrawValues(false);
            set1.setHighlightEnabled(false);
            set2.setHighlightEnabled(false);
            set3.setHighlightEnabled(false);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            dataSets.add(set2);
            dataSets.add(set3);

            BarData data = new BarData(dataSets);
            mChart.setData(data);
            mChart.setFitBars(true);
        }
        mChart.invalidate();
    }

    class XAxisValue implements AxisValueFormatter {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            //Log.e(Tag, " " + value);
            return XString[(int) value % XString.length];
        }

        @Override
        public int getDecimalDigits() {
            return 0;
        }
    }


    /***
     * 设置日期栏是否显示
     *
     * @param visible
     */
    public void setLlDateVisible(int visible) {
        if (visible == View.VISIBLE) {
            scrollView.setFillViewport(true);
        } else {
            scrollView.setFillViewport(false);
            params.height = heightChatr;
            params.width = widthChart;
            mChart.setLayoutParams(params);
        }
        llDate.setVisibility(visible);
        llSleepBata.setVisibility(visible);
    }

    public boolean getLlDateVisible() {
        if (llDate.getVisibility() == View.VISIBLE && llDate != null)
            return true;
        else
            return false;
    }


    public void setTvDateValue(String date) {
        tvDate.setText(date);
    }

    @OnClick({R.id.iv_left, R.id.iv_right, R.id.ll_sleep_day})
    public void onClick(View view) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date date = null;

        try {
            date = sdf.parse(tvDate.getText().toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (view.getId()) {
            case R.id.iv_left:

                 today = tvDate.getText().toString();
                 yeday = new SomeUtills().getAmountDate(date, 0, 0);
                tvDate.setText(yeday);
                initData(today, yeday);

                break;
            case R.id.iv_right:
                yeday = new SomeUtills().getAmountDate(date, 0, 1);
                try {
                    date = sdf.parse(yeday);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                today = new SomeUtills().getAmountDate(date, 0, 1);
                tvDate.setText(yeday);
                initData(today,yeday);

                break;
            case R.id.ll_sleep_day:
                new SomeUtills().setCalendarViewGone(0);
                break;
        }
    }

    public void updateUI(String[] xValue) {
        this.xValue = xValue;
        setChartData();
    }

    private void initTime() {
        mCalendar = new Time();
        mCalendar.setToNow();
        myear = mCalendar.year;
        month = mCalendar.month + 1;
        day = mCalendar.monthDay;
    }
}
