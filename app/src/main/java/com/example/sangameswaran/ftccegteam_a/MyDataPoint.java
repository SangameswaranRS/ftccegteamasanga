package com.example.sangameswaran.ftccegteam_a;

/**
 * Created by Sangameswaran on 25-05-2017.
 */

public class MyDataPoint {
    String current_date,date_progress;
    public MyDataPoint(){}

    public MyDataPoint(String current_date, String date_progress) {
        this.current_date = current_date;
        this.date_progress = date_progress;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public String getDate_progress() {
        return date_progress;
    }

    public void setDate_progress(String date_progress) {
        this.date_progress = date_progress;
    }
}
