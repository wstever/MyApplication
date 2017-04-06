package com.example.burt.lab.project_report;

class ProjectReportBean {
    private int status;


    private String project;
    private String content;
    private boolean isSolved;

    public ProjectReportBean(int status, String project, String content, boolean isSolved) {
        this.status = status;
        this.project = project;
        this.content = content;
        this.isSolved = isSolved;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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
