package com.loopkillers.serveez.model;

public class ErrorResponse {
    private String mMessage;
    private String mDetails;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String details) {
        mMessage = message;
        mDetails = details;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details) {
        mDetails = details;
    }
}
