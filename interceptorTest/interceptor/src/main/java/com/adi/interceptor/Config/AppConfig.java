package com.adi.interceptor.Config;

import com.adi.interceptor.Filter.AdiCustomFilter1;
import com.adi.interceptor.Filter.AdiCustomFilter2;
import com.adi.interceptor.Interceptor.AdiCustomInterceptor1;
import com.adi.interceptor.Interceptor.AdiCustomInterceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    AdiCustomInterceptor1 adiCustomInterceptor1;

    @Autowired
    AdiCustomInterceptor2 adiCustomInterceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(adiCustomInterceptor1)
                .addPathPatterns("/api/**")  // Apply to these URL Patterns
                .excludePathPatterns("/api/updateUser" , "/api/deleteUser");    // Exclude URL Patterns

        registry.addInterceptor(adiCustomInterceptor2)
                .addPathPatterns("/api/**")  // Apply to these URL Patterns
                .excludePathPatterns("/api/updateUser" , "/api/deleteUser");    // Exclude URL Patterns
    }

    @Bean
    public FilterRegistrationBean<AdiCustomFilter1> myFirstFilter(){
        FilterRegistrationBean<AdiCustomFilter1> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AdiCustomFilter1());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdiCustomFilter2> mySecondFilter(){
        FilterRegistrationBean<AdiCustomFilter2> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AdiCustomFilter2());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }


}
