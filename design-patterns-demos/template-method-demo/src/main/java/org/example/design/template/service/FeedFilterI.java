package org.example.design.template.service;

import org.example.design.template.extension.ExtensionPointI;

/**
 * Created by xiedong
 * Date: 2022/8/21 13:13
 */
public interface FeedFilterI<T> extends ExtensionPointI {
    void preFilter(T ctx);

    void postFilter(T ctx);
}
