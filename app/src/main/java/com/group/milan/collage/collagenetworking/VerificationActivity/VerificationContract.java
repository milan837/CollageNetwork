package com.group.milan.collage.collagenetworking.VerificationActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.VerificationActivity.pojo.VerificationResponse;

public interface VerificationContract {

    public interface Views{
        void displayResponseData(VerificationResponse verificationResponse);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(VerificationResponse verificationResponse);
    }
}
