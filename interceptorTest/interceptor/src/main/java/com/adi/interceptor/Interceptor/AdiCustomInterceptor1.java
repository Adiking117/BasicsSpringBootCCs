package com.adi.interceptor.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AdiCustomInterceptor1 implements org.springframework.web.servlet.HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        System.out.println("Inside Pre Handle Method 1");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView){
        System.out.println("Inside Post Handle Method 1");
    }

    // postHandle() – Called: After the controller method executes, but before the view (HTML/JSON/XML) is rendered.

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Inside After Completion Method 1");
    }

    // afterCompletion() - Called: After the entire request is completely finished — response is rendered and sent to the client.


    /*
    Request
         ➡️ preHandle()
         ➡️ Controller Method
         ➡️ postHandle()
         ➡️ View Rendered
         ➡️ afterCompletion()
     */
}
