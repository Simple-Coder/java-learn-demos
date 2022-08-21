package org.example.design.template.context;

import cn.hutool.json.JSONObject;
import lombok.Data;
import org.example.design.template.common.BizScenario;
import org.example.design.template.enums.BizCode;
import org.example.design.template.enums.FeedType;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2022/8/21 13:02
 */
@Data
public class FeedHandlerContext implements Serializable {
    private BizScenario bizScenario;

    private Object req;

    private JSONObject rsp=new JSONObject();
}
