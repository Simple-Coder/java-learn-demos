package org.example.design.filters.subscribe;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.design.filters.abs.AbstractFeedContext;
import org.example.design.filters.abs.AbstractFeedHandler;
import org.example.design.filters.context.SubscribeContext;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by xiedong
 * Date: 2022/8/15 21:07
 */
@Service
@Slf4j
public class VideoFeedHandler extends AbstractFeedHandler<SubscribeContext> {
    @Override
    protected void handle(SubscribeContext ctx) {
        log.info("视频处理了...");
        if(StrUtil.isBlank(Objects.toString(ctx.getRsp()))){
            ctx.setRsp("视频处理");
        }else {
            ctx.setRsp(Objects.toString(ctx.getRsp()).concat("->视频处理Ok"));
        }
    }
}
