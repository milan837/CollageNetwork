package com.group.milan.collage.collagenetworking.LoginActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.LoginActivity.pojo.LoginResponse;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.Views views;
    LoginRepository repository;
    Context context;

    public LoginPresenter(LoginContract.Views views, Context context) {
        this.views=views;
        this.repository=new LoginRepository(this,context);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(LoginResponse loginResponse) {
        views.displayResponseData(loginResponse);
    }
}
