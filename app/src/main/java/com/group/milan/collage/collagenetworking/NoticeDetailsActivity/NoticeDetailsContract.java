package com.group.milan.collage.collagenetworking.NoticeDetailsActivity;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;
import com.group.milan.collage.collagenetworking.NoticeDetailsActivity.pojo.NoticeDetailsResponse;

public interface NoticeDetailsContract {
    public interface Views{
        void displayResponseData(NoticeDetailsResponse noticeDetailsResponse);
    }

    public interface Presenter{
        void sendDataToApi(String noticeId);
        void getDataFromApi(NoticeDetailsResponse noticeDetailsResponse);
    }
}
