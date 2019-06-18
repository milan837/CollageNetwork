package com.group.milan.collage.collagenetworking.ForumPostQuestionActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumPostAnswerActivity.pojo.ForumPostAnswerResponse;

public class ForumPostQuestionPresenter implements ForumPostQuestionContract.Presenter {
    ForumPostQuestionContract.Views views;
    Context context;
    ForumPostQuestionRepository repository;

    public ForumPostQuestionPresenter(ForumPostQuestionContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new ForumPostQuestionRepository(this,context);
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
