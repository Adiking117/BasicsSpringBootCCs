package com.adi.exceptionhandle.Exception;

import org.springframework.http.HttpStatus;

public class AdiCustomException extends Exception{
    HttpStatus status;

    public AdiCustomException(HttpStatus status, String message){
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
