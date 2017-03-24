package com.example.burt.mvpdemo.model;


import com.example.burt.mvpdemo.bean.User;

public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
