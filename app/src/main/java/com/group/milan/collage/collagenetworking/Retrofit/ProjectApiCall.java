package com.group.milan.collage.collagenetworking.Retrofit;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;
import com.group.milan.collage.collagenetworking.ForumAnswerActivity.pojo.ForumAnswerListResponse;
import com.group.milan.collage.collagenetworking.ForumPostAnswerActivity.pojo.ForumPostAnswerResponse;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo.ForumQuestionListResponse;
import com.group.milan.collage.collagenetworking.LoginActivity.pojo.LoginResponse;
import com.group.milan.collage.collagenetworking.NoticeDetailsActivity.pojo.NoticeDetailsResponse;
import com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity.pojo.ParticipatedProjectResponse;
import com.group.milan.collage.collagenetworking.PostProjectActivity.pojo.PostProjectResponse;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.ParticipateButtonResponse;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.PublishProjectResponse;
import com.group.milan.collage.collagenetworking.SignUpActivity.pojo.SignUpPojo;
import com.group.milan.collage.collagenetworking.UpdateDetailsActivity.pojo.UpdateDetailsPojo;
import com.group.milan.collage.collagenetworking.VerificationActivity.pojo.VerificationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProjectApiCall {

    //------------------------------------------- login api ------------------------------------//
    @POST("auth/login/email")
    Call<LoginResponse> getLoginResponse(@Body JsonObject jsonObject);

    //------------------------------------------- sign up api ------------------------------------//
    @POST("auth/signup/email")
    Call<SignUpPojo> getSignUpResponse(@Body JsonObject jsonObject);

    //------------------------------------------- verification api ------------------------------------//
    @POST("auth/verification/email")
    Call<VerificationResponse> getVerificationResponse(@Body JsonObject jsonObject);

    //------------------------------------------- username api ------------------------------------//
    @POST("auth/update/username")
    Call<UpdateDetailsPojo> getUpdateDetailsResponse(@Body JsonObject jsonObject);

    //------------------------------------------- Admin Notice api ------------------------------------//
    @POST("notice/list")
    Call<AdminNoticeResponse> getNoticeListResponse(@Body JsonObject jsonObject);

    //-------------------------------------------  Notice details api ------------------------------------//
    @GET("notice/profile/{id}")
    Call<NoticeDetailsResponse> getNoticeDetailsResponse(@Path("id") String noticeId);

    //-------------------------------------------  forum Question list api ------------------------------------//
    @POST("forum/question/list")
    Call<ForumQuestionListResponse> getForumQuestionListResponse(@Body JsonObject jsonObject);

    //-------------------------------------------  forum Answer api ------------------------------------//
    @POST("forum/answer/list")
    Call<ForumAnswerListResponse> getForumAnswerListResponse(@Body JsonObject jsonObject);

    //-------------------------------------------  forum post Answer api ------------------------------------//
    @POST("forum/answer/post")
    Call<ForumPostAnswerResponse> getForumPostAnswerResponse(@Body JsonObject jsonObject);

    //-------------------------------------------  forum post Answer api ------------------------------------//
    @POST("forum/question/ask")
    Call<ForumPostAnswerResponse> getForumPostQuestionResponse(@Body JsonObject jsonObject);

    //-------------------------------------------  publish project list api ------------------------------------//
    @POST("project/list")
    Call<PublishProjectResponse> getPublishProjectListResponse(@Body JsonObject jsonObject);

    //-------------------------------------------  participated project list api ------------------------------------//
    @POST("project/participated/list")
    Call<ParticipatedProjectResponse> getParticipatedProjectListResponse(@Body JsonObject jsonObject);

    //-------------------------------------------  participated project list api ------------------------------------//
    @POST("project/post")
    Call<PostProjectResponse> getPostProjectResponse(@Body JsonObject jsonObject);

    //-------------------------------------------  participated project list api ------------------------------------//
    @POST("project/participated/button")
    Call<ParticipateButtonResponse> getParticipateButtonResponse(@Body JsonObject jsonObject);

}
