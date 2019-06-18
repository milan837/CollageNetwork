package com.group.milan.collage.collagenetworking.UpdateDetailsActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.UpdateDetailsActivity.pojo.UpdateDetailsPojo;

public interface UpdateDetailsContract {
    public interface Views{
        void displayResponseData(UpdateDetailsPojo updateDetailsPojo);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(UpdateDetailsPojo updateDetailsPojo);
    }
}
