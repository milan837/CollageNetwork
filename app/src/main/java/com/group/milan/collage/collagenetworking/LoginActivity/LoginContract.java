package com.group.milan.collage.collagenetworking.LoginActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.LoginActivity.pojo.LoginResponse;

public interface LoginContract {

    public interface Views{
        void displayResponseData(LoginResponse loginResponse);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(LoginResponse loginResponse);
    }
}
