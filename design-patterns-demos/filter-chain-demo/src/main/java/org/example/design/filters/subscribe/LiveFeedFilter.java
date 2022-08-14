package org.example.design.filters.subscribe;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.design.service.IFacedeService;
import org.example.design.filters.abs.AbstractFeedContext;
import org.example.design.filters.abs.AbstractFeedFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:55
 */
@Service
@Slf4j
public class LiveFeedFilter extends AbstractFeedFilter<AbstractFeedContext> {
    @Resource
    private IFacedeService facedeService;

    @Override
    protected void handle(AbstractFeedContext ctx) {
        log.info("LiveFeedFilter execute:{},current ctx is :{}", this.getClass().getSimpleName(), JSONUtil.toJsonStr(ctx));
    }
}
