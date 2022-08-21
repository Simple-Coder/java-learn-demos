package org.example.design.template.extension;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.design.template.common.BizScenario;

/**
 * Created by xiedong
 * Date: 2022/8/21 15:56
 * 扩展坐标
 */
@ToString
@EqualsAndHashCode
public class ExtensionCoordinate {
    private String extensionPointName;
    private String bizScenarioUniqueIdentity;



    //Wrapper
    private Class extensionPointClass;
    private BizScenario bizScenario;

    public Class getExtensionPointClass() {
        return extensionPointClass;
    }

    public BizScenario getBizScenario() {
        return bizScenario;
    }

    public static ExtensionCoordinate valueOf(Class extPtClass, BizScenario bizScenario){
        return new ExtensionCoordinate(extPtClass, bizScenario);
    }

    public ExtensionCoordinate(Class extPtClass, BizScenario bizScenario){
        this.extensionPointClass = extPtClass;
        this.extensionPointName = extPtClass.getName();
        this.bizScenario = bizScenario;
        this.bizScenarioUniqueIdentity = bizScenario.getUniqueIdentity();
    }

    /**
     * @param extensionPoint
     * @param bizScenario
     */
    public ExtensionCoordinate(String extensionPoint, String bizScenario){
        this.extensionPointName = extensionPoint;
        this.bizScenarioUniqueIdentity = bizScenario;
    }
}
