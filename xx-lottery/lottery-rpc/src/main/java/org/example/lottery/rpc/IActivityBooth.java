package org.example.lottery.rpc;

import org.example.lottery.rpc.req.ActivityReq;
import org.example.lottery.rpc.res.ActivityRes;

/**
 * Created by xiedong
 * Date: 2022/9/8 21:36
 * <p>
 * * 活动展台
 * * 1. 创建活动
 * * 2. 更新活动
 * * 3. 查询活动
 * @author xiedong
 */
public interface IActivityBooth {
    ActivityRes queryActivityById(ActivityReq req);
}
