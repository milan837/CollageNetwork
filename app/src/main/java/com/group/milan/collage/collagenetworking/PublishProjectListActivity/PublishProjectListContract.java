package com.group.milan.collage.collagenetworking.PublishProjectListActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity.pojo.ParticipatedProjectResponse;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.ParticipateButtonResponse;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.PublishProjectResponse;

public interface PublishProjectListContract {
    public interface Views{
        void displayResponseData(PublishProjectResponse publishProjectResponse);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);

        void getDataFromApi(PublishProjectResponse publishProjectResponse);
    }
}
