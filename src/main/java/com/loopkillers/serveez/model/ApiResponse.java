package com.loopkillers.serveez.model;

public class ApiResponse {
    private String mMessage;

    public ApiResponse(String message) {
        mMessage = message;
    }

    public ApiResponse() {
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
