package org.example.design.filters.context;

import lombok.Getter;
import lombok.Setter;
import org.example.design.enums.BizEnum;
import org.example.design.selector.FilterSelector;
import org.example.design.filters.abs.AbstractFeedContext;
import org.example.design.selector.Selector;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:41
 */
public class SubscribeContext extends AbstractFeedContext {

    private boolean continueFlag =true;

    @Getter
    @Setter
    private Object req;

    @Getter
    @Setter
    private Object rsp;

    public SubscribeContext(BizEnum bizEnum, Selector filterSelector){
        super(filterSelector, bizEnum);
    }


    @Override
    public boolean getContinueFlag() {
        return this.continueFlag;
    }
}
