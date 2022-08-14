package org.example.design.filters;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Objects;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:20
 */
public class LocalFilterSelector implements FilterSelector{
    private List<String> filterNames = Lists.newArrayList();
    @Override
    public boolean matchFilter(String clz) {

        return this.filterNames.stream().anyMatch((s)->{
            return Objects.equals(s,clz);
        });
    }

    @Override
    public List<String> getFilterNames() {
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
