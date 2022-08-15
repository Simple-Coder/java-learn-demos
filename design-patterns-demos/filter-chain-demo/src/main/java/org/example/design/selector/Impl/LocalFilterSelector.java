package org.example.design.selector.Impl;

import org.assertj.core.util.Lists;
import org.example.design.selector.FilterSelector;

import java.util.List;
import java.util.Objects;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:20
 */
public class LocalFilterSelector extends AbstractSelector {
    private List<String> filterNames = Lists.newArrayList();
    @Override
    public boolean match(String clz) {

        return this.filterNames.stream().anyMatch((s)->{
            return Objects.equals(s,clz);
        });
    }

    @Override
    public List<String> getNames() {
        return this.filterNames;
    }

    public void addFilter(String clz){
        this.filterNames.add(clz);
    }

    public LocalFilterSelector() {
    }

    public LocalFilterSelector(List<String> filterNames) {
        this.filterNames = filterNames;
    }
}
