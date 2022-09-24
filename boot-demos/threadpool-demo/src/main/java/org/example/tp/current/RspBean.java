package org.example.tp.current;

import lombok.Data;

import java.util.Map;

/**
 * Created by xiedong
 * Date: 2022/9/24 18:36
 */
@Data
public class RspBean {
    private Map addressInfos;
    private Map schoolInfos;
    private Map montherInfos;

    private String rspName;
}
