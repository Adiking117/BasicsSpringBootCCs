package com.adi.Async.Controller;

import com.adi.Async.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/async/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUser")
    public String getUserMethod(){
        System.out.println("inside getUserMethod : "+ Thread.currentThread().getName());
        userService.asyncMethodTest();
        return "NormalAsyncTask";
    }

    @GetMapping("/getUser/sp/pool")
    public String getUserPoolExecutorSP(){
        System.out.println("inside getUserPoolExecutor : "+ Thread.currentThread().getName());
        userService.asyncMethodTestPoolExecutorSP();
        return "Spring Boot Thread Task Pool";
    }

    @GetMapping("/getUser/pj/pool")
    public String getUserPoolExecutorPJ(){
        System.out.println("inside getUserPoolExecutorPJ : "+ Thread.currentThread().getName());
        userService.asyncMethodTestPoolExecutorPJ();
        return "Plain Java Thread Pool";
    }

    @GetMapping("/getUser/common/pool")
    public String getUserPoolExecutorCommon(){
        System.out.println("inside getUserPoolExecutorCommon : "+ Thread.currentThread().getName());
        userService.getAsyncExecutor();
        return "Combo JAVASP Thread Pool";
    }

    @GetMapping("/getUser/asyncSameClass")
    public String getUserAsyncSameClass(){
        System.out.println("inside getUserAsyncSameClass : "+ Thread.currentThread().getName());
        asyncMethodSameClass();
        return "Async SameClass Method";
    }

    @Async
    public void asyncMethodSameClass(){
        System.out.println("inside asyncMethodSameClass : "+ Thread.currentThread().getName());
    }

    /*
        When you annotate a method with @Async, Spring creates a proxy
        around that bean to execute that method in a separate thread
        (typically via a TaskExecutor). But in your case:
        This is just a normal method call inside the same class —
        it bypasses the Spring proxy. Hence, it does not execute asynchronously.
        Both System.out.println will print the same thread name (typically http-nio-8080-exec-1),
        showing that both methods ran in the same thread.
    */

    /*
        When Spring sees @Async, it:
        1. Wraps the bean in a proxy.
        2. Intercepts the annotated method.
        3. Submits it to a TaskExecutor (like a thread pool).
        4. Returns immediately to the caller.
        But if you skip the proxy (as with a self-internal call), steps 1–3 never happen.
    */

    // UseCase1
    // Transaction Context do not transfer from Caller Thread to new Thread which got created by Async
    @PostMapping("/updateUser/usecase/1/{id}/{balance}")
    public String updateUserMethod1(@PathVariable int id,@PathVariable double balance){
        userService.updateUserMethod1(id,balance);
        return "useCase1 balance updated";
    }

    // UseCase2
    // A new thread will be created and have transaction management
    // but context not same as parent thread so propagation doesnt work
    @PostMapping("/updateUser/usecase/2/{id}/{balance}")
    public String updateUserMethod2(@PathVariable int id,@PathVariable double balance){
        userService.updateUserMethod2(id,balance);
        return "useCase2 balance updated";
    }


    // UseCase3
    @PostMapping("/updateUser/usecase/3/{id}/{balance}")
    public String updateUserMethod3(@PathVariable int id,@PathVariable double balance){
        userService.updateUserMethod3(id,balance);
        return "useCase3 balance updated";
    }


    // ExceptionHandling
//    @PostMapping("/updateUser/debit/{id}/{balance}")
//    public String debitMoney(@PathVariable int id,@PathVariable double balance){
//        userService.debitBalance(id,balance);
//        return "Balance Debited";
//    }

    @PostMapping("/debit/{id}/{amount}")
    public ResponseEntity<String> debitBalance(@PathVariable int id, @PathVariable double amount) {
        try {
            userService.debitBalance(id, amount).get();  // Wait for async to complete
            return ResponseEntity.ok("✅ Balance debited");
        } catch (ExecutionException e) {
            Throwable cause = e.getCause(); // unwrap the exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ " + cause.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Unknown error");
        }
    }
    // Async void	Returns immediately, cannot notify controller of errors
    // @Async CompletableFuture + .get()	Lets controller wait and catch exception



}
