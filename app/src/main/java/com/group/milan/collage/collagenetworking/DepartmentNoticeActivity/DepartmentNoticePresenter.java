package com.group.milan.collage.collagenetworking.DepartmentNoticeActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;

public class DepartmentNoticePresenter implements DepartmentNoticeContract.Presenter{
    DepartmentNoticeContract.Views views;
    DepartmentNoticeRepository repository;
    Context context;

    public DepartmentNoticePresenter(DepartmentNoticeContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new DepartmentNoticeRepository(this,context);
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
