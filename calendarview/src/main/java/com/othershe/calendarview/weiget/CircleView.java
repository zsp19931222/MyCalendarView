package com.othershe.calendarview.weiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/11/14 0014.
 */

public class CircleView extends View {
    private Paint circlePaint;
    private int interval = 9;
    private int radius = 10;
    private boolean show;
    private int insideColor;
    private int outsideColor;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }


    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }


    public int getInsideColor() {
        return insideColor;
    }

    public void setInsideColor(int insideColor) {
        this.insideColor = insideColor;
    }

    public int getOutsideColor() {
        return outsideColor;
    }

    public void setOutsideColor(int outsideColor) {
        this.outsideColor = outsideColor;
    }


    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        circlePaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        circlePaint.setColor(getInsideColor());
        canvas.drawCircle(getRadius(), getRadius() + getInterval(), getRadius(), circlePaint);
        if (isShow()) {
            circlePaint.setColor(Color.WHITE);
            canvas.drawCircle(getRadius() + getRadius() / 2, getRadius() + getInterval(), getRadius()+2, circlePaint);
            circlePaint.setColor(getOutsideColor());
            canvas.drawCircle(getRadius() + getRadius() / 3 * 2, getRadius() + getInterval(), getRadius(), circlePaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isShow()) {
            setMeasuredDimension(getRadius() * 2 + getRadius() / 3 * 2, getRadius() * 2 + getInterval());
        } else {
            setMeasuredDimension(getRadius() * 2, getRadius() * 2 + getInterval());

        }
    }
}
