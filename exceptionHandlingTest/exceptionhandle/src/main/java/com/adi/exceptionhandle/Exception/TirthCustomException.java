package com.adi.exceptionhandle.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Invalid Request Passed")
public class TirthCustomException extends RuntimeException{
    HttpStatus status;

    public TirthCustomException(String message,HttpStatus status){
        super(message);
        this.status = status;
    }
}

/*
Controller
   ↓
Throw TirthCustomException
   ↓
Spring sees @ResponseStatus(BAD_REQUEST)
   ↓
Spring sends HTTP 400 Bad Request to client
 */