package com.example.burt.android_sample_study.mvp_demo.model;


public interface IUserModel {
    public void login(String username, String password, OnLoginListener loginListener);
}
