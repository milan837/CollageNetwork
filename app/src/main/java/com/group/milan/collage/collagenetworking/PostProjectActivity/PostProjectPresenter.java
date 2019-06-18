package com.group.milan.collage.collagenetworking.PostProjectActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumPostAnswerActivity.pojo.ForumPostAnswerResponse;
import com.group.milan.collage.collagenetworking.PostProjectActivity.pojo.PostProjectResponse;

public class PostProjectPresenter implements PostProjectContract.Presenter {
    PostProjectContract.Views views;
    Context context;
    PostProjectRepository repository;

    public PostProjectPresenter(PostProjectContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new PostProjectRepository(this,context);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(PostProjectResponse postProjectResponse) {
        views.displayResponseData(postProjectResponse);
    }
}
