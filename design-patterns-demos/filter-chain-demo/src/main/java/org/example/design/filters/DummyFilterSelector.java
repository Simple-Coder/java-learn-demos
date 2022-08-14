package org.example.design.filters;

import java.util.List;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:23
 */
public class DummyFilterSelector implements FilterSelector{
    @Override
    public boolean matchFilter(String clz) {
        return false;
    }

    @Override
    public List<String> getFilterNames() {
        return null;
    }
}
