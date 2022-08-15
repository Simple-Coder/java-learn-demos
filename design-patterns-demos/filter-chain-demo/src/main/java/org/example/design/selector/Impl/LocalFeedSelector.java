package org.example.design.selector.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by xiedong
 * Date: 2022/8/15 21:14
 */
@Service
@Slf4j
public class LocalFeedSelector extends AbstractSelector {
    private List<String> feedHandlers = new ArrayList<>();

    @Override
    public boolean match(String clz) {
        return this.feedHandlers.stream().anyMatch((s)->{
            return Objects.equals(s,clz);
        });
    }

    @Override
    public List<String> getNames() {
        return this.feedHandlers;
    }
    public void addFeedHandler(String clz){
        this.feedHandlers.add(clz);
    }

    public LocalFeedSelector() {
    }

    public LocalFeedSelector(List<String> feedHandlers) {
        this.feedHandlers = feedHandlers;
    }
}
