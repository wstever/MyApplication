package com.example.burt.android_sample_study.mvp_demo.model;


import com.example.burt.android_sample_study.mvp_demo.bean.User;

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}
