package org.example.design.filters.subscribe;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.design.filters.context.SubscribeContext;
import org.example.design.service.IFacedeService;
import org.example.design.filters.abs.AbstractFeedContext;
import org.example.design.filters.abs.AbstractFeedHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by xiedong
 * Date: 2022/8/14 20:01
 */
@Service
@Slf4j
public class ForwardFeedHandler extends AbstractFeedHandler<SubscribeContext> {
    @Resource
    private IFacedeService facedeService;

    @Override
    protected void handle(SubscribeContext ctx) {
        log.info("ForwardFeedFilter execute:{},ctx:{}", this.getClass().getSimpleName(), JSONUtil.toJsonStr(ctx));
        log.info("转发处理了...");
        if(StrUtil.isBlank(Objects.toString(ctx.getRsp()))){
            ctx.setRsp("转发处理");
        }else {
            ctx.setRsp(Objects.toString(ctx.getRsp()).concat("->转发处理Ok"));
        }
    }
}
