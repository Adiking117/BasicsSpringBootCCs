package com.adi.exceptionhandle.Controller;

import com.adi.exceptionhandle.Exception.CustomException;
import com.adi.exceptionhandle.Exception.PranavCustomException;
import com.adi.exceptionhandle.Exception.TirthCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/responseBody")
public class ResponseStatusExceptionResolverController {

    @GetMapping("/test1")
    public ResponseEntity<?> getExceptionTest1(){
        throw new TirthCustomException("Tirth Exception", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/test2")
    public ResponseEntity<?> getExceptionTest2(){
        throw new PranavCustomException("Pranav Exception", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PranavCustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,reason="Invalid Request Sent")
    public ResponseEntity<Object> handleCustomException(PranavCustomException e){
        return new ResponseEntity<>("You are not authorized",HttpStatus.FORBIDDEN);
    }

    /*
        🧹 Now, what happens to @ResponseStatus?
        Even though you wrote @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Request Sent") on the method,
         it is ignored when you return a custom ResponseEntity.
        Priority is always given to what you returned manually.
        👉 @ResponseStatus is mainly used when your method returns void or an Object directly without a ResponseEntity.
        Since you're returning a ResponseEntity, @ResponseStatus is not used.
     */



}

/*
[Controller Method]
     ↓
Throws Exception
     ↓
[DispatcherServlet]
     ↓
Tries:
    ├── 1. ExceptionHandlerExceptionResolver → Check for @ExceptionHandler method
    │          ↳ Found? Handle and return response
    │          ↳ Not found? Go next
    ↓
    ├── 2. ResponseStatusExceptionResolver → Check for @ResponseStatus
    │          ↳ Found? Handle and return response
    │          ↳ Not found? Go next
    ↓
    ├── 3. DefaultHandlerExceptionResolver → Handle basic errors
    ↓
[Send final response to Client]

 */