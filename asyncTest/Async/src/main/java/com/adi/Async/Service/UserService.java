package com.adi.Async.Service;

import com.adi.Async.DAO.UserDAO;
import com.adi.Async.Exceptions.InsufficeintBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Component
public class UserService {

    @Async // By default SpringBoot uses Simple Async TaskExecutor
    public void asyncMethodTest(){
        System.out.println("inside asyncMethodTest: " + Thread.currentThread().getName());
    }
    // To use this comment all Beans Created in App Config

    @Async("adiSpringBootThreadPoolExecutor") // No need to give name
    public void asyncMethodTestPoolExecutorSP(){
        System.out.println("inside asyncMethodTestPoolExecutorSP: " + Thread.currentThread().getName());
        try{
            Thread.sleep(50000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Async("adiJavaThreadPoolExecutor") // Compulsory Name must be given
    public void asyncMethodTestPoolExecutorPJ(){
        System.out.println("inside asyncMethodTestPoolExecutorPJ: " + Thread.currentThread().getName());
        try{
            Thread.sleep(50000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Async // No need to give name
    public void getAsyncExecutor(){
        System.out.println("inside getAsyncExecutorMethod: " + Thread.currentThread().getName());
        try{
            Thread.sleep(50000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // UseCase1
    @Autowired
    UserDAO userDAO;

    @Transactional
    public void updateUserMethod1(int id,double balance){
        userDAO.updateUserBalance1(id,balance);
    }

    /*
        The new thread spawned by @Async does NOT inherit the transaction context from the parent thread.
        updateUserMethod1() starts a transaction.
        It calls updateUserBalance(), but that runs in another thread because it's marked @Async.
        The transaction doesn’t apply to the async method — it runs outside the transaction scope.
        So any DB operations inside updateUserBalance() are NOT transactional.
        Let’s say updateUserBalance() fails:
            Since it's outside the transaction, any partial DB update it does will not be rolled back.
            The @Transactional on updateUserMethod1() has no control over the async update.
    */

    // UseCase2
    @Transactional
    @Async
    public void updateUserMethod2(int id,double balance){
        userDAO.updateUserBalance2(id,balance);
    }

    /*
        @Async → The entire updateUserMethod2 runs in a new thread (non-blocking from controller).
        @Transactional → Since it's the first method in the new thread, a new transaction context is created.
        So, yes, this method runs in a new thread and is transactional!
        That means if updateUserBalance2 fails, the transaction will roll back correctly within that async thread.

        Because @Async is used:
        The controller immediately returns "useCase2 balance updated"
        The actual DB operation is still running in the background thread
        That means you won't know in the controller whether the DB update succeeded or failed.
     */


    // UseCase3
    @Async
    public void updateUserMethod3(int id,double balance){
        userDAO.updateUserBalance3(id,balance);
    }


    // ExceptionHandling
//    @Async
//    public void debitBalance(int id,double balanceToDebit) {
//        try {
//            userDAO.debitBalance(id, balanceToDebit);
//        } catch (InsufficeintBalanceException e) {
//            throw new RuntimeException("Wrapped exception: " + e.getMessage(), e);
//        }
//    }

    /*
        When you call userService.debitBalance(...), Spring runs it in a new thread, asynchronously.
        The controller thread does not wait for it to complete.
        So, the return "Balance debited" executes immediately,
        even before the actual debit logic is completed — and even if it throws an exception.
     */

    @Async
    public CompletableFuture<Void> debitBalance(int id, double balanceToDebit) throws InsufficeintBalanceException {
        userDAO.debitBalance(id, balanceToDebit);
        return CompletableFuture.completedFuture(null);
    }


}
