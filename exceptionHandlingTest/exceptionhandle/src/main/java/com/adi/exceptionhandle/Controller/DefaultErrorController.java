package com.adi.exceptionhandle.Controller;

import com.adi.exceptionhandle.Exception.CustomException;
import com.adi.exceptionhandle.POJO.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/exception")
public class DefaultErrorController {

    @GetMapping("/test1")
    public String testException1(){
        throw new NullPointerException("Throwing NPE");
    }

    @GetMapping("/test2")
    public String testException2() throws CustomException {
        throw new CustomException(HttpStatus.BAD_REQUEST,"Request Not Correct");
    }

    @GetMapping("/test3")
    public ResponseEntity<?> testException3(){
        try{
            throw new CustomException(HttpStatus.BAD_REQUEST,"Exception Occured");
        }
        catch (CustomException e){
            ErrorResponse errorResponse = new ErrorResponse(new Date(),e.getMessage(),e.getStatus().value());
            return new ResponseEntity<>(errorResponse,e.getStatus());
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(new Date(),e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
