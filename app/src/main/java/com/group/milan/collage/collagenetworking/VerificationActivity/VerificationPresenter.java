package com.group.milan.collage.collagenetworking.VerificationActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.VerificationActivity.pojo.VerificationResponse;

public class VerificationPresenter implements VerificationContract.Presenter {
    VerificationContract.Views views;
    VerificationRepository repository;
    Context context;

    public VerificationPresenter(VerificationContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new VerificationRepository(this,context);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(VerificationResponse verificationResponse) {
        views.displayResponseData(verificationResponse);
    }
}
