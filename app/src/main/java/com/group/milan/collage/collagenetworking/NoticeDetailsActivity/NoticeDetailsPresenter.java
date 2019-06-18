package com.group.milan.collage.collagenetworking.NoticeDetailsActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;
import com.group.milan.collage.collagenetworking.NoticeDetailsActivity.pojo.NoticeDetailsResponse;

public class NoticeDetailsPresenter implements NoticeDetailsContract.Presenter{
    NoticeDetailsContract.Views views;
    NoticeDetailsRepository repository;
    Context context;

    public NoticeDetailsPresenter(NoticeDetailsContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new NoticeDetailsRepository(this,context);
    }

    @Override
    public void sendDataToApi(String noticeId) {
        repository.hitApi(noticeId);
    }

    @Override
    public void getDataFromApi(NoticeDetailsResponse noticeDetailsResponse) {
        views.displayResponseData(noticeDetailsResponse);
    }
}
