package com.learn.boot.advice;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by dongxie on 2022/8/29.
 */
@RestControllerAdvice
@Slf4j
public class RestResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        HttpServletResponse response = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
        String callback = servletRequest.getParameter("callback");
        if (StrUtil.isNotBlank(callback)) {
            String jsonString = JSON.toJSONString(o);
            jsonString = new StringBuilder(callback).append("([").append(jsonString).append("])").toString();
            //根据前端需要返回对应的编码，默认均是utf-8
            try {
                byte[] bytes = jsonString.getBytes(StandardCharsets.UTF_8);
                @Cleanup OutputStream out = response.getOutputStream();
                out.write(bytes);

                response.setHeader("RestResponseBodyAdvice", "RestResponseBodyAdvice V");

                out.flush();
                out.close();
            } catch (Exception e) {
                log.error("beforeBodyWrite error", e);
            }
        }
        return o;
    }
}
