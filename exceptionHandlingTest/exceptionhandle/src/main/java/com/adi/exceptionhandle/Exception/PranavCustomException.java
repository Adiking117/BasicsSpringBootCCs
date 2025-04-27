package com.adi.exceptionhandle.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Invalid Request Passed")
public class PranavCustomException extends RuntimeException{
    HttpStatus status;

    public PranavCustomException(String message,HttpStatus status){
        super(message);
        this.status = status;
    }
}