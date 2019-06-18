package com.group.milan.collage.collagenetworking.ForumQuestionListActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo.ForumQuestionListResponse;

public interface ForumQuestionListContract {
    public interface Views{
        void displayResponseData(ForumQuestionListResponse forumQuestionListResponse);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(ForumQuestionListResponse forumQuestionListResponse);
    }
}
