package com.group.milan.collage.collagenetworking.ForumAnswerActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumAnswerActivity.pojo.ForumAnswerListResponse;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo.ForumQuestionListResponse;

public interface ForumAnswerListContract {
    public interface Views{
        void displayResponseData(ForumAnswerListResponse forumAnswerListResponse);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(ForumAnswerListResponse forumAnswerListResponse);
    }
}
