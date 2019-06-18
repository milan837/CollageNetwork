package com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity.pojo.ParticipatedProjectResponse;

public class ParticipatedProjectListPresenter implements ParticipatedProjectListContract.Presenter {
    ParticipatedProjectListContract.Views views;
    Context context;

    ParticipatedProjectListRepository repository;

    public ParticipatedProjectListPresenter(ParticipatedProjectListContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new ParticipatedProjectListRepository(this,context);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(ParticipatedProjectResponse participatedProjectResponse) {
        views.displayResponseData(participatedProjectResponse);
    }
}
