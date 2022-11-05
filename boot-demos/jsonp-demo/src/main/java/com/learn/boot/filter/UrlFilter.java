//package com.learn.boot.filter;
//
//import cn.hutool.core.util.StrUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * Created by dongxie on 2022/8/29.
// */
//@Slf4j
//@WebFilter(filterName = "urlFiler", urlPatterns = "/**")
//@Order(1)
//@Component
//public class UrlFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
////        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
//        String path = httpRequest.getRequestURI();
//        if (StrUtil.contains(path,"v2")) {
//            String newPath = StrUtil.replace(path, "v2", "v1");
//            httpRequest.getRequestDispatcher(newPath).forward(request, response);
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
