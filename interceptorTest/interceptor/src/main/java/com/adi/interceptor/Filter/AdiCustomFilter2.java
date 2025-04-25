package com.adi.interceptor.Filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdiCustomFilter2 implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Adi Filter2 inside");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Adi Filter2 completed");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
