package com.group.milan.collage.collagenetworking.PostProjectActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumPostAnswerActivity.pojo.ForumPostAnswerResponse;
import com.group.milan.collage.collagenetworking.PostProjectActivity.pojo.PostProjectResponse;

public interface PostProjectContract {
    public interface Views{
        void displayResponseData(PostProjectResponse postProjectResponse);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(PostProjectResponse postProjectResponse);
    }
}
