package com.learn.boot.controller;

import cn.hutool.json.JSONUtil;
import com.learn.boot.common.ReqInfo;
import com.learn.boot.common.RspInfo;
import com.learn.boot.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dongxie on 2022/8/29.
 */
@RestController
@Slf4j
@RequestMapping("/test/v1")
public class TestV1Controller {
    @GetMapping("test")
    public Response<RspInfo> testV2(ReqInfo reqInfo) {
        log.info("v21 accept :{}", JSONUtil.toJsonStr(reqInfo));
        RspInfo rspInfo = new RspInfo();
        rspInfo.setData("ok v1 data");
        return Response.ok(rspInfo);
    }
}
