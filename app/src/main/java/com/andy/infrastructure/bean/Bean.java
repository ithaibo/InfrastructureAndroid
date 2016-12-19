package com.andy.infrastructure.bean;

import java.io.Serializable;

/**
 * Created by Andy on 2016/9/21.
 */
public class Bean implements Serializable {
    private String message;
    private String statusCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
