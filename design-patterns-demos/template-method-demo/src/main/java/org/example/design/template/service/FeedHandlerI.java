package org.example.design.template.service;

import org.example.design.template.extension.ExtensionPointI;

/**
 * Created by xiedong
 * Date: 2022/8/21 13:00
 */
public interface FeedHandlerI<T>  extends ExtensionPointI {
    void handle(T ctx);
}
