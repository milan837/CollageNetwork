package com.group.milan.collage.collagenetworking.AdminNoticeActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;

public class AdminNoticePresenter implements AdminNoticeContract.Presenter{
    AdminNoticeContract.Views views;
    AdminNoticeRepository repository;
    Context context;

    public AdminNoticePresenter(AdminNoticeContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new AdminNoticeRepository(this,context);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(AdminNoticeResponse adminNoticeResponse) {
        views.displayResponseData(adminNoticeResponse);
    }
}
