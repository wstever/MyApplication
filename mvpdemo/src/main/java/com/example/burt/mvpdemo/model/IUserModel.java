package com.example.burt.mvpdemo.model;


public interface IUserModel {
    public void login(String username, String password, OnLoginListener loginListener);
}
