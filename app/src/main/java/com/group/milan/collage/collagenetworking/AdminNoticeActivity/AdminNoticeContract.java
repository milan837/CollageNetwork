package com.group.milan.collage.collagenetworking.AdminNoticeActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;

public interface AdminNoticeContract {
    public interface Views{
        void displayResponseData(AdminNoticeResponse adminNoticeResponse);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(AdminNoticeResponse adminNoticeResponse);
    }
}
