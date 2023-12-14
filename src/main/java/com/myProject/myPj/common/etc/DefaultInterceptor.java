package com.myProject.myPj.common.etc;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class DefaultInterceptor implements HandlerInterceptor {

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 요청이 Controller에 도달하기 전에 실행되는 메서드
//        // true를 반환하면 요청이 계속 진행되고, false를 반환하면 요청을 중단합니다.
//    	Map<String, String[]> parameterMap = request.getParameterMap();
//
//        // Iterate through the Map and print the parameters
//        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
//            String paramName = entry.getKey();
//            String[] paramValues = entry.getValue();
//
//            System.out.println("Parameter: " + paramName);
//
//            for (String paramValue : paramValues) {
//                System.out.println("  Value: " + paramValue);
//            }
//        }
//        System.out.println("CustomInterceptor preHandle");
//        return true;
//    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        // Controller가 실행된 후, View가 렌더링되기 전에 실행되는 메서드
//        System.out.println("CustomInterceptor postHandle");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        // View가 렌더링된 후에 실행되는 메서드
//        System.out.println("CustomInterceptor afterCompletion");
//    }
}