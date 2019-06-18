package com.group.milan.collage.collagenetworking.SignUpActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.SignUpActivity.pojo.SignUpPojo;

public class SignUpPresenter implements SignUpContract.Presenter {
    SignUpContract.Views views;
    Context context;
    SignUpRepository repository;

    public SignUpPresenter(SignUpContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        this.repository = new SignUpRepository(this,context);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(SignUpPojo signUpPojo) {
        views.displayResponseData(signUpPojo);
    }
}
