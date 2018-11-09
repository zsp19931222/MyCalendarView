package com.zsp.mycalendarview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView chooseDate;

    private int[] cDate = CalendarUtil.getCurrentDate();

    private LinearLayout weekLin;
    private RelativeLayout weekRec1;
    private RelativeLayout weekRec2;
    private RelativeLayout weekRec3;
    private RelativeLayout weekRec4;
    private RelativeLayout weekRec5;
    private RelativeLayout weekRec6;
    private ImageView weekImage1;
    private ImageView weekImage2;
    private ImageView weekImage3;
    private ImageView weekImage4;
    private ImageView weekImage5;
    private ImageView weekImage6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView title = findViewById(R.id.title);
        weekRec1 = findViewById(R.id.week_rel1);
        weekRec2 = findViewById(R.id.week_rel2);
        weekRec3 = findViewById(R.id.week_rel3);
        weekRec4 = findViewById(R.id.week_rel4);
        weekRec5 = findViewById(R.id.week_rel5);
        weekRec6 = findViewById(R.id.week_rel6);
        weekImage1 = findViewById(R.id.week_image1);
        weekImage2 = findViewById(R.id.week_image2);
        weekImage3 = findViewById(R.id.week_image3);
        weekImage4 = findViewById(R.id.week_image4);
        weekImage5 = findViewById(R.id.week_image5);
        weekImage6 = findViewById(R.id.week_image6);
        //当前选中的日期
        chooseDate = findViewById(R.id.choose_date);

        calendarView = findViewById(R.id.calendar);
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
        chooseDate.setText("当前选中的日期：" + cDate[0] + "年" + cDate[1] + "月" + cDate[2] + "日");

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
                    chooseDate.setText("当前选中的日期：" + date.getSolar()[0] + "年" + date.getSolar()[1] + "月" + date.getSolar()[2] + "日");
                }
                Toast.makeText(MainActivity.this, "当前选中的日期：" + date.getSolar()[0] + "年" + date.getSolar()[1] + "月" + date.getSolar()[2] + "日", Toast.LENGTH_SHORT).show();
            }
        });
        setWeekLin(cDate[0],cDate[1]);
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
                            chooseDate.setText("当前选中的日期：" + year.getText() + "年" + month.getText() + "月" + day.getText() + "日");
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", null).show();
    }

    public void today(View view) {
        calendarView.today();
        chooseDate.setText("当前选中的日期：" + cDate[0] + "年" + cDate[1] + "月" + cDate[2] + "日");
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
        } else {
            weekRec6.setVisibility(View.VISIBLE);
        }
        if (year>=2018&&month==12){
            weekImage1.setBackgroundResource(R.mipmap.bg1);
            weekImage2.setBackgroundResource(R.mipmap.bg1);
            weekImage3.setBackgroundResource(R.mipmap.bg1);
            weekImage4.setBackgroundResource(R.mipmap.bg1);
            weekImage5.setBackgroundResource(R.mipmap.bg1);
            weekImage6.setBackgroundResource(R.mipmap.bg1);
        }else {
            weekImage1.setBackgroundResource(R.mipmap.bg);
            weekImage2.setBackgroundResource(R.mipmap.bg);
            weekImage3.setBackgroundResource(R.mipmap.bg);
            weekImage4.setBackgroundResource(R.mipmap.bg);
            weekImage5.setBackgroundResource(R.mipmap.bg);
            weekImage6.setBackgroundResource(R.mipmap.bg);
        }
    }
}