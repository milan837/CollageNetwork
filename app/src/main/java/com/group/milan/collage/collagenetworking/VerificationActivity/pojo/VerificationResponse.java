package com.group.milan.collage.collagenetworking.VerificationActivity.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationResponse {

        @SerializedName("response")
        @Expose
        private Response response;

        public Response getResponse() {
            return response;
        }

        public void setResponse(Response response) {
            this.response = response;
        }

    }
