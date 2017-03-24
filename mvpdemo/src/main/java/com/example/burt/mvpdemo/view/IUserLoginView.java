package com.example.burt.mvpdemo.view;


import com.example.burt.mvpdemo.bean.User;

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
