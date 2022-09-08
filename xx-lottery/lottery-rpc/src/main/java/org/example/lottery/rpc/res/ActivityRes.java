package org.example.lottery.rpc.res;

import org.example.lottery.rpc.dto.ActivityDto;
import org.example.lottery.common.Result;
import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2022/9/8 21:37
 */
public class ActivityRes implements Serializable {

    private Result result;
    private ActivityDto activity;

    public ActivityRes() {
    }

    public ActivityRes(Result result) {
        this.result = result;
    }

    public ActivityRes(Result result, ActivityDto activity) {
        this.result = result;
        this.activity = activity;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public void setActivity(ActivityDto activity) {
        this.activity = activity;
    }
}
