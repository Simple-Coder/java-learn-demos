package org.example.lottery.rpc.req;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2022/9/10 11:20
 */
public class ActivityReq implements Serializable {

    private Long activityId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}

