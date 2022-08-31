package com.revature.models;

public class JsonResponse {
    boolean successful;
    String message;
    Object data;

    public JsonResponse() {
    }


    public JsonResponse(boolean successful, String message, Object data) {
        this.successful = successful;
        this.message = message;
        this.data = data;
    }


    public boolean isSuccessful() {
        return this.successful;
    }

    public boolean getSuccessful() {
        return this.successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "{" +
            " successful='" + isSuccessful() + "'" +
            ", message='" + getMessage() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }


    
}
