package com.adi.interceptor.Service;

import com.adi.interceptor.Annotation.AdiCustomAnnotation;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void getUser(){
        System.out.println("User fetched");
    }

    @AdiCustomAnnotation(name = "adi")
    public void updateUser(){
        System.out.println("User updated");
    }

    /*
    When this method is called:
        AdiCustomAspect.invoke() is triggered before the actual method.
        It reads the annotation value (name = "adi").
        Proceeds to execute updateUser().
        Then logs something after method execution.
     */

    /*
    🔄 Execution Order (Simplified):
        Client Request
            ↓
        Filter: AdiCustomFilter1
            ↓
        Interceptor: preHandle()
            ↓
        Controller: getUser()
            ↓
        Interceptor: postHandle()
            ↓
        View Rendering
            ↓
        Interceptor: afterCompletion()
            ↓
        Filter: AdiCustomFilter1 (post-doFilter)
     */
}
