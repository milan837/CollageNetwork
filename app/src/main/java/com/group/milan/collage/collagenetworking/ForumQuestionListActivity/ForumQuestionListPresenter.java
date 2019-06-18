package com.group.milan.collage.collagenetworking.ForumQuestionListActivity;

import android.content.Context;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo.ForumQuestionListResponse;

public class ForumQuestionListPresenter implements ForumQuestionListContract.Presenter{
    ForumQuestionListContract.Views views;
    ForumQuestionListRepository repository;
    Context context;

    public ForumQuestionListPresenter(ForumQuestionListContract.Views views, Context context) {
        this.views = views;
        this.context = context;
        repository=new ForumQuestionListRepository(this,context);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(ForumQuestionListResponse forumQuestionListResponse) {
        views.displayResponseData(forumQuestionListResponse);
    }
}
