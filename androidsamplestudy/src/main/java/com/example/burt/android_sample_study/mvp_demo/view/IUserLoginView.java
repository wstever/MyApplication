package com.example.burt.android_sample_study.mvp_demo.view;


import com.example.burt.android_sample_study.mvp_demo.bean.User;

public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

}
