package com.example.burt.mvpdemo.presenter;

import android.os.Handler;

import com.example.burt.mvpdemo.bean.User;
import com.example.burt.mvpdemo.model.IUserModel;
import com.example.burt.mvpdemo.model.OnLoginListener;
import com.example.burt.mvpdemo.model.UserModel;
import com.example.burt.mvpdemo.view.IUserLoginView;


public class UserLoginPresenter {
    private IUserModel userModel;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userModel = new UserModel();
    }

    public void login() {
        userLoginView.showLoading();
        userModel.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }


}
