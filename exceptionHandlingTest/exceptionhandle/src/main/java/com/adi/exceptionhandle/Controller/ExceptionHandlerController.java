package com.adi.exceptionhandle.Controller;

import com.adi.exceptionhandle.Exception.AdiCustomException;
import com.adi.exceptionhandle.Exception.CustomException;
import com.adi.exceptionhandle.POJO.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api/exceptionHandler")
public class ExceptionHandlerController {

    @GetMapping("/test1")
    public String testException1() throws CustomException {
        throw new CustomException(HttpStatus.BAD_REQUEST,"Request from Custom Not Correct");
    }

    @GetMapping("/test2")
    public String testException2() throws AdiCustomException {
        throw new AdiCustomException(HttpStatus.BAD_REQUEST,"Request from Adi Not Correct");
    }

//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<String> handleCustomException(CustomException ex){
//        return new ResponseEntity<>(ex.getMessage(),ex.getStatus());
//    }

//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<Object> handleCustomException(CustomException ex){
//        ErrorResponse errorResponse = new ErrorResponse(new Date(),ex.getMessage(),ex.getStatus().value());
//        return new ResponseEntity<>(errorResponse,ex.getStatus());
//    }
//
//    @ExceptionHandler(AdiCustomException.class)
//    public ResponseEntity<Object> handleCustomException(AdiCustomException ex){
//        ErrorResponse errorResponse = new ErrorResponse(new Date(),ex.getMessage(),ex.getStatus().value());
//        return new ResponseEntity<>(errorResponse,ex.getStatus());
//    }

    @ExceptionHandler({AdiCustomException.class,CustomException.class})
    public ResponseEntity<String> handleCustomException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public void handleCustomException(HttpServletResponse response,CustomException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }



}
