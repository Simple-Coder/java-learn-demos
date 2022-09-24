package org.example.tp.current;

import lombok.Data;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by xiedong
 * Date: 2022/9/24 18:33
 */
@Data
public class UserContext {
    private String reqName;
    private boolean success;

    private RspBean rspBean;

}
