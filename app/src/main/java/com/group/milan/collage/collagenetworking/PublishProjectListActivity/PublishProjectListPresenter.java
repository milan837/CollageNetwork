package com.group.milan.collage.collagenetworking.PublishProjectListActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.ParticipateButtonResponse;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.PublishProjectResponse;

public class PublishProjectListPresenter implements PublishProjectListContract.Presenter {
    PublishProjectListContract.Views views;
    Context context;

    PublishProjectListRepository repository;

    public PublishProjectListPresenter(PublishProjectListContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new PublishProjectListRepository(this,context);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }


    @Override
    public void getDataFromApi(PublishProjectResponse publishProjectResponse) {
        views.displayResponseData(publishProjectResponse);
    }

}
