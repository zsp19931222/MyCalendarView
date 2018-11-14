package com.zsp.mycalendarview;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.othershe.calendarview.weiget.CircleView;

import java.util.List;

/**
 * Created by Administrator on 2018/11/14 0014.
 */

public class CalendarAdapter extends BaseQuickAdapter<CalendarBean, BaseViewHolder> {
    private CircleView circleView;
    private CircleView circleView1;
    private List<CalendarBean> data;

    public CalendarAdapter(int layoutResId, @Nullable List<CalendarBean> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, CalendarBean item) {
        helper.setText(R.id.item_calendar_name_text, item.getName());
        helper.setText(R.id.item_calendar_time_text, item.getTime());
        helper.setText(R.id.item_calendar_explain_text, item.getExplain());
        helper.getView(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(helper.getAdapterPosition()-1);
                notifyDataSetChanged();
            }
        });

        circleView = helper.getView(R.id.item_calendar_circle);
        circleView.setInterval(0);
        circleView.setRadius(15);
        circleView.setShow(false);

        circleView1 = helper.getView(R.id.item_calendar_circle1);
        circleView1.setInterval(0);
        circleView1.setRadius(15);
        circleView1.setShow(true);

        if (item.getState() == 0) {
            circleView.setInsideColor(Color.BLUE);
            circleView.setOutsideColor(Color.RED);
            circleView1.setInsideColor(Color.BLUE);
            circleView1.setOutsideColor(Color.RED);
        } else {
            circleView.setInsideColor(Color.RED);
            circleView1.setInsideColor(Color.RED);
        }
        if (helper.getAdapterPosition() == 1) {
            circleView1.setVisibility(View.VISIBLE);
            circleView.setVisibility(View.GONE);
            helper.getView(R.id.item_calendar_left_image1).setVisibility(View.INVISIBLE);
            helper.getView(R.id.item_calendar_left_image2).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_calendar_cut_line_image).setVisibility(View.VISIBLE);
        } else if (helper.getAdapterPosition() == getItemCount() - 1) {
            circleView.setVisibility(View.VISIBLE);
            circleView1.setVisibility(View.GONE);
            helper.getView(R.id.item_calendar_left_image1).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_calendar_left_image2).setVisibility(View.INVISIBLE);
            helper.getView(R.id.item_calendar_cut_line_image).setVisibility(View.INVISIBLE);
        } else {
            circleView.setVisibility(View.VISIBLE);
            circleView1.setVisibility(View.GONE);
            helper.getView(R.id.item_calendar_left_image1).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_calendar_left_image2).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_calendar_cut_line_image).setVisibility(View.VISIBLE);
        }
    }
}
