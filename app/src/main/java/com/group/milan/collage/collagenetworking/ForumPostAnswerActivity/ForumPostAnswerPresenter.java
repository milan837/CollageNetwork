package com.group.milan.collage.collagenetworking.ForumPostAnswerActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumPostAnswerActivity.pojo.ForumPostAnswerResponse;

public class ForumPostAnswerPresenter implements ForumPostAnswerContract.Presenter {
    ForumPostAnswerContract.Views views;
    Context context;
    ForumPostAnswerRepository repository;

    public ForumPostAnswerPresenter(ForumPostAnswerContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new ForumPostAnswerRepository(this,context);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(ForumPostAnswerResponse forumPostAnswerResponse) {
        views.displayResponseData(forumPostAnswerResponse);
    }
}
