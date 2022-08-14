package org.example.design.filters;

import java.util.List;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:15
 */
public interface FilterSelector {
    boolean matchFilter(String clz);

    List<String> getFilterNames();
}
