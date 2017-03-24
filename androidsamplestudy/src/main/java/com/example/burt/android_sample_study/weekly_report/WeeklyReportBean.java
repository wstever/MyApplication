package com.example.burt.android_sample_study.weekly_report;

class WeeklyReportBean {
    private int status;
    private String content;
    private boolean isSolved;

    public WeeklyReportBean(int status, String content, boolean isSolved) {
        this.status = status;
        this.content = content;
        this.isSolved = isSolved;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }
}
