package com.group.milan.collage.collagenetworking.ForumAnswerActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumAnswerActivity.pojo.ForumAnswerListResponse;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo.ForumQuestionListResponse;

public class ForumAnswerListPresenter implements ForumAnswerListContract.Presenter{
    ForumAnswerListContract.Views views;
    ForumAnswerListRepository repository;
    Context context;

    public ForumAnswerListPresenter(ForumAnswerListContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new ForumAnswerListRepository(this,context);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(ForumAnswerListResponse forumAnswerListResponse) {
        views.displayResponseData(forumAnswerListResponse);
    }

}
