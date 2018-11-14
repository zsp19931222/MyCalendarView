package com.zsp.mycalendarview;

/**
 * Created by Administrator on 2018/11/14 0014.
 */

public class CalendarBean {
    private String name;
    private String time;
    private String explain;
    private int state;

    public CalendarBean(String name, String time, String explain, int state) {
        this.name = name;
        this.time = time;
        this.explain = explain;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
