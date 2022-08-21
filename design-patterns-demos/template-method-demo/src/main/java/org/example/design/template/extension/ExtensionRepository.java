package org.example.design.template.extension;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiedong
 * Date: 2022/8/21 15:55
 * 仓库
 */
@Component
@Slf4j
public class ExtensionRepository {
    public Map<ExtensionCoordinate, ExtensionPointI> getExtensionRepo() {
        return extensionRepo;
    }

    private Map<ExtensionCoordinate, ExtensionPointI> extensionRepo = new HashMap<>();

}
