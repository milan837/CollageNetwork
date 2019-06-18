package com.group.milan.collage.collagenetworking.SignUpActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.SignUpActivity.pojo.SignUpPojo;

public interface SignUpContract {
    public interface Views{
        void displayResponseData(SignUpPojo signUpPojo);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(SignUpPojo signUpPojo);
    }
}
