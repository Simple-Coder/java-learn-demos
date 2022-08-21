package org.example.design.template.extension.register;

import org.example.design.template.common.BizScenario;
import org.example.design.template.extension.Extension;
import org.example.design.template.extension.ExtensionCoordinate;
import org.example.design.template.extension.ExtensionPointI;
import org.example.design.template.extension.ExtensionRepository;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;

/**
 * Created by xiedong
 * Date: 2022/8/21 15:57
 */
@Component
public class ExtensionRegister {
    @Resource
    private ExtensionRepository extensionRepository;

    public final static String EXTENSION_EXTPT_NAMING = "ExtPt";


    public void doRegistration(ExtensionPointI extensionObject){
        Class<?>  extensionClz = extensionObject.getClass();
        if (AopUtils.isAopProxy(extensionObject)) {
            extensionClz = ClassUtils.getUserClass(extensionObject);
        }
        Extension extensionAnn = AnnotationUtils.findAnnotation(extensionClz, Extension.class);
        BizScenario bizScenario = BizScenario.valueOf(extensionAnn.bizId(), extensionAnn.useCase(), extensionAnn.scenario());
        ExtensionCoordinate extensionCoordinate = new ExtensionCoordinate(calculateExtensionPoint(extensionClz), bizScenario.getUniqueIdentity());
        ExtensionPointI preVal = extensionRepository.getExtensionRepo().put(extensionCoordinate, extensionObject);
        if (preVal != null) {
            throw new RuntimeException("Duplicate registration is not allowed for :" + extensionCoordinate);
        }
    }

    /**
     * @param targetClz
     * @return
     */
    private String calculateExtensionPoint(Class<?> targetClz) {
        Class<?>[] interfaces = ClassUtils.getAllInterfacesForClass(targetClz);
        if (interfaces == null || interfaces.length == 0)
            throw new RuntimeException("Please assign a extension point interface for "+targetClz);
        for (Class intf : interfaces) {
            String extensionPoint = intf.getSimpleName();
//            if (extensionPoint.contains(EXTENSION_EXTPT_NAMING))
                return intf.getName();
        }
        throw new RuntimeException("Your name of ExtensionPoint for "+targetClz+" is not valid, must be end of "+ EXTENSION_EXTPT_NAMING);
    }
}
