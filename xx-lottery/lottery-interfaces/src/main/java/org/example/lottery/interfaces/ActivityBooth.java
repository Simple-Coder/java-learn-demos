package org.example.lottery.interfaces;

import org.apache.dubbo.config.annotation.Service;
import org.example.lottery.common.Constants;
import org.example.lottery.common.Result;
import org.example.lottery.infrastructure.dao.IActivityDao;
import org.example.lottery.infrastructure.po.Activity;
import org.example.lottery.rpc.IActivityBooth;
import org.example.lottery.rpc.dto.ActivityDto;
import org.example.lottery.rpc.req.ActivityReq;
import org.example.lottery.rpc.res.ActivityRes;

import javax.annotation.Resource;

/**
 * Created by xiedong
 * Date: 2022/9/8 21:35
 * 活动展台
 * @author xiedong
 */
@Service
public class ActivityBooth implements IActivityBooth {

    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {

        Activity activity = activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo()), activityDto);
    }
}
