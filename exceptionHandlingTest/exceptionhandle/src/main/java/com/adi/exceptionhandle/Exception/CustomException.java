package com.adi.exceptionhandle.Exception;

import org.springframework.http.HttpStatus;

import java.net.http.HttpClient;

public class CustomException extends Exception{
    HttpStatus status;

    public CustomException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
