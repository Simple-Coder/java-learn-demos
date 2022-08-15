package org.example.design.selector;

import java.util.List;

/**
 * Created by xiedong
 * Date: 2022/8/15 21:11
 */
public interface Selector {
    boolean match(String clz);

    List<String> getNames();
}
