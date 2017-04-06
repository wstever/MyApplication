package com.example.burt.lab.weekly_report;


public class WeeklyReportCustomRvBean {
    private String weekly_report_time;
    private String weekly_report_finish;
    private String weekly_report_plan;
    private String weekly_report_progress;

    public String getWeekly_report_time() {
        return weekly_report_time;
    }

    public void setWeekly_report_time(String weekly_report_time) {
        this.weekly_report_time = weekly_report_time;
    }

    public String getWeekly_report_finish() {
        return weekly_report_finish;
    }

    public void setWeekly_report_finish(String weekly_report_finish) {
        this.weekly_report_finish = weekly_report_finish;
    }

    public String getWeekly_report_plan() {
        return weekly_report_plan;
    }

    public void setWeekly_report_plan(String weekly_report_plan) {
        this.weekly_report_plan = weekly_report_plan;
    }

    public String getWeekly_report_progress() {
        return weekly_report_progress;
    }

    public void setWeekly_report_progress(String weekly_report_progress) {
        this.weekly_report_progress = weekly_report_progress;
    }


    public WeeklyReportCustomRvBean(String weekly_report_time, String weekly_report_finish, String weekly_report_plan, String weekly_report_progress) {
        this.weekly_report_time = weekly_report_time;
        this.weekly_report_finish = weekly_report_finish;
        this.weekly_report_plan = weekly_report_plan;
        this.weekly_report_progress = weekly_report_progress;
    }

}
