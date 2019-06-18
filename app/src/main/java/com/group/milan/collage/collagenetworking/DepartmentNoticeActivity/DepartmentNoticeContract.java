package com.group.milan.collage.collagenetworking.DepartmentNoticeActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;

public interface DepartmentNoticeContract {
    public interface Views{
        void displayResponseData(AdminNoticeResponse adminNoticeResponse);
    }

    public interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(AdminNoticeResponse adminNoticeResponse);
    }
}
