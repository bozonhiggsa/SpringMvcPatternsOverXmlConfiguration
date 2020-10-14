package com.application.springMvc.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor for requests handling
 * @author Ihor Savchenko
 * @version 1.0
 */
public class MyInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("---------------------------");
        System.out.println("PreHandler executed");
        System.out.println("---------------------------");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {

        System.out.println("---------------------------");
        System.out.println("PostHandler executed");
        System.out.println("---------------------------");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {

        System.out.println("Request finished and AfterCompletion executed");
        System.out.println("---------------------------");
    }
}
