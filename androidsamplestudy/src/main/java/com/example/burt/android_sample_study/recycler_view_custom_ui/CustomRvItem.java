package com.example.burt.android_sample_study.recycler_view_custom_ui;


public class CustomRvItem {
    private String maintitle;
    private String subtitle;
    private boolean active;

    public CustomRvItem(String maintitle, String subtitle, boolean active) {
        this.maintitle = maintitle;
        this.subtitle = subtitle;
        this.active = active;
    }

    public CustomRvItem(String maintitle, String subtitle) {
        this.maintitle = maintitle;
        this.subtitle = subtitle;

    }

    public String getTitle() {
        return maintitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public boolean isActive() {
        return active;
    }
}
