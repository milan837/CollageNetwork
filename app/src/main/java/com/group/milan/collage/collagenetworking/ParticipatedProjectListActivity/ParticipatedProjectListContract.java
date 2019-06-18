package com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity.pojo.ParticipatedProjectResponse;

public interface ParticipatedProjectListContract {
    public interface Views{
        void displayResponseData(ParticipatedProjectResponse participatedProjectResponse);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(ParticipatedProjectResponse participatedProjectResponse);
    }
}
