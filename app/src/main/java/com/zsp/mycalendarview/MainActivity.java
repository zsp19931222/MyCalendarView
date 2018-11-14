package com.zsp.mycalendarview;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.othershe.calendarview.bean.DateBean;
import com.othershe.calendarview.listener.OnPagerChangeListener;
import com.othershe.calendarview.listener.OnSingleChooseListener;
import com.othershe.calendarview.utils.CalendarUtil;
import com.othershe.calendarview.weiget.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private CalendarView calendarView;

    private int[] cDate = CalendarUtil.getCurrentDate();

    private LinearLayout weekLin;
    private RelativeLayout weekRec1;
    private RelativeLayout weekRec2;
    private RelativeLayout weekRec3;
    private RelativeLayout weekRec4;
    private RelativeLayout weekRec5;
    private RelativeLayout weekRec6;
    private TextView weekImage1;
    private TextView weekImage2;
    private TextView weekImage3;
    private TextView weekImage4;
    private TextView weekImage5;
    private TextView weekImage6;
    private RelativeLayout cut_line_rel_5;
    private RelativeLayout cut_line_rel_6;
    private TextView title;

    private CalendarAdapter adapter;
    private List<CalendarBean> calendarBeans=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        recyclerView = findViewById(R.id.rec);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        findId(view);
        for (int i = 0; i <15 ; i++) {
            calendarBeans.add(new CalendarBean("事件","时间","说明",i));
        }

        adapter=new CalendarAdapter(R.layout.item_calendar,calendarBeans);
        adapter.addHeaderView(view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HashMap<String, String> map = new HashMap<>();
        map.put("2017.10.30", "qaz");
        map.put("2017.10.1", "wsx");
        map.put("2017.11.12", "yhn");
        map.put("2017.9.15", "edc");
        map.put("2017.11.6", "rfv");
        map.put("2017.11.11", "tgb");
        calendarView
//                .setSpecifyMap(map)
                .setStartEndDate("2016.1", "2028.12")
                .setDisableStartEndDate("2016.10.10", "2028.10.10")
                .setInitDate(cDate[0] + "." + cDate[1])
                .setSingleDate(cDate[0] + "." + cDate[1] + "." + cDate[2])
                .init();

//       .setOnCalendarViewAdapter(R.layout.item_layout, new CalendarViewAdapter() {
//            @Override
//            public TextView[] convertView(View view, DateBean date) {
//                TextView solarDay = (TextView) view.findViewById(R.id.solar_day);
//                TextView lunarDay = (TextView) view.findViewById(R.id.lunar_day);
//                return new TextView[]{solarDay, lunarDay};
//            }
//        }).init();

        title.setText(cDate[0] + "年" + cDate[1] + "月");

        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                title.setText(date[0] + "年" + date[1] + "月");
                Log.d("zsp", "onCreate: " + CalendarUtil.getMonthRows(date[0], date[1]));
                setWeekLin(date[0], date[1]);
            }
        });

        calendarView.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean date) {
                title.setText(date.getSolar()[0] + "年" + date.getSolar()[1] + "月");
                if (date.getType() == 1) {
                }
                calendarBeans.clear();
                if (date.getSolar()[0]==2018&&date.getSolar()[1]==11&&date.getSolar()[2]==15){
                    for (int i = 0; i <3 ; i++) {
                        calendarBeans.add(new CalendarBean("事件","时间","说明",i));
                    }
                }else if (date.getSolar()[0]==2018&&date.getSolar()[1]==11&&date.getSolar()[2]==16){
                    for (int i = 0; i <16 ; i++) {
                        calendarBeans.add(new CalendarBean("事件","时间","说明",i));
                    }
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "当前选中的日期：" + date.getSolar()[0] + "年" + date.getSolar()[1] + "月" + date.getSolar()[2] + "日", Toast.LENGTH_SHORT).show();
            }
        });
        setWeekLin(cDate[0], cDate[1]);
    }

    private void findId(View view) {
        title = view.findViewById(R.id.title);
        weekRec1 = view.findViewById(R.id.week_rel1);
        weekRec2 = view.findViewById(R.id.week_rel2);
        weekRec3 = view.findViewById(R.id.week_rel3);
        weekRec4 = view.findViewById(R.id.week_rel4);
        weekRec5 = view.findViewById(R.id.week_rel5);
        weekRec6 = view.findViewById(R.id.week_rel6);
        weekImage1 = view.findViewById(R.id.week_image1);
        weekImage2 = view.findViewById(R.id.week_image2);
        weekImage3 = view.findViewById(R.id.week_image3);
        weekImage4 = view.findViewById(R.id.week_image4);
        weekImage5 = view.findViewById(R.id.week_image5);
        weekImage6 = view.findViewById(R.id.week_image6);
        cut_line_rel_5 = view.findViewById(R.id.cut_line_rel_5);
        cut_line_rel_6 = view.findViewById(R.id.cut_line_rel_6);

        calendarView = view.findViewById(R.id.calendar);
    }

    public void someday(View v) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.input_layout, null);
        final EditText year = view.findViewById(R.id.year);
        final EditText month = view.findViewById(R.id.month);
        final EditText day = view.findViewById(R.id.day);

        new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(year.getText())
                                || TextUtils.isEmpty(month.getText())
                                || TextUtils.isEmpty(day.getText())) {
                            Toast.makeText(MainActivity.this, "请完善日期！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        boolean result = calendarView.toSpecifyDate(Integer.valueOf(year.getText().toString()),
                                Integer.valueOf(month.getText().toString()),
                                Integer.valueOf(day.getText().toString()));
                        if (!result) {
                            Toast.makeText(MainActivity.this, "日期越界！", Toast.LENGTH_SHORT).show();
                        } else {
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", null).show();
    }

    public void today(View view) {
        calendarView.today();
    }

    public void lastMonth(View view) {
        calendarView.lastMonth();

    }

    public void nextMonth(View view) {
        calendarView.nextMonth();

    }

    public void start(View view) {
        calendarView.toStart();
    }

    public void end(View view) {
        calendarView.toEnd();
    }

    public void lastYear(View view) {
        calendarView.lastYear();
    }

    public void nextYear(View view) {
        calendarView.nextYear();
    }

    public void multiChoose(View view) {
//        startActivity(new Intent(MainActivity.this, MultiChooseActivity.class));
    }

    private void setWeekLin(int year, int month) {
        int num = CalendarUtil.getMonthRows(year, month);
        if (num == 5) {
            weekRec6.setVisibility(View.GONE);
            cut_line_rel_6.setVisibility(View.GONE);
        } else {
            weekRec6.setVisibility(View.VISIBLE);
            cut_line_rel_6.setVisibility(View.VISIBLE);
        }
        if (year >= 2018 && month == 12) {
            setText(weekImage1, true);
            setText(weekImage2, true);
            setText(weekImage3, true);
            setText(weekImage4, true);
            setText(weekImage5, true);
            setText(weekImage6, true);
        } else {
            setText(weekImage1, false);
            setText(weekImage2, false);
            setText(weekImage3, false);
            setText(weekImage4, false);
            setText(weekImage5, false);
            setText(weekImage6, false);
        }
    }

    private void setText(TextView textView, boolean isHoliday) {
        if (isHoliday) {
            textView.setText("暑假");
            textView.setTextColor(Color.parseColor("#FF1F1F"));
            textView.setBackgroundResource(R.mipmap.holiday_bg);
        } else {
            textView.setText("1");
            textView.setTextColor(Color.parseColor("#55B2F5"));
            textView.setBackgroundResource(R.mipmap.week_bg);
        }
    }
}
