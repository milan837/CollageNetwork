package com.group.milan.collage.collagenetworking.UpdateDetailsActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.UpdateDetailsActivity.pojo.UpdateDetailsPojo;

public class UpdateDetailsPresenter implements UpdateDetailsContract.Presenter{
    UpdateDetailsContract.Views views;
    UpdateDetailsRepository repository;
    Context context;

    public UpdateDetailsPresenter(UpdateDetailsContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new UpdateDetailsRepository(this,context);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(UpdateDetailsPojo updateDetailsPojo) {
        views.displayResponseData(updateDetailsPojo);
    }
}
