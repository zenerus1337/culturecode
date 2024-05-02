package com.culturecode.main.app.connection;

import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("Status")
    private  String status;

    @SerializedName("result_code")
    private int result_code;

    public String getStatus() {
        return status;
    }

    public int getResult_code() {
        return result_code;
    }
}
