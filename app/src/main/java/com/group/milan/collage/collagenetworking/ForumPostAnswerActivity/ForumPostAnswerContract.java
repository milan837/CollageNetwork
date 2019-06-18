package com.group.milan.collage.collagenetworking.ForumPostAnswerActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumPostAnswerActivity.pojo.ForumPostAnswerResponse;

public interface ForumPostAnswerContract {
    public interface Views{
        void displayResponseData(ForumPostAnswerResponse forumPostAnswerResponse);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(ForumPostAnswerResponse forumPostAnswerResponse);
    }
}
