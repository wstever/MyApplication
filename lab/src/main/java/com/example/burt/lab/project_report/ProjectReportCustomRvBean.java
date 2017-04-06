package com.example.burt.lab.project_report;


public class ProjectReportCustomRvBean {
    private String project_report_time;
    private String project_report_finish;
    private String project_report_plan;
    private String project_report_progress;


    public ProjectReportCustomRvBean(String project_report_time, String project_report_finish, String project_report_plan, String project_report_progress) {
        this.project_report_time = project_report_time;
        this.project_report_finish = project_report_finish;
        this.project_report_plan = project_report_plan;
        this.project_report_progress = project_report_progress;
    }

    public String getProject_report_time() {
        return project_report_time;
    }

    public void setProject_report_time(String project_report_time) {
        this.project_report_time = project_report_time;
    }

    public String getProject_report_finish() {
        return project_report_finish;
    }

    public void setProject_report_finish(String project_report_finish) {
        this.project_report_finish = project_report_finish;
    }

    public String getProject_report_plan() {
        return project_report_plan;
    }

    public void setProject_report_plan(String project_report_plan) {
        this.project_report_plan = project_report_plan;
    }

    public String getProject_report_progress() {
        return project_report_progress;
    }

    public void setProject_report_progress(String project_report_progress) {
        this.project_report_progress = project_report_progress;
    }


}
