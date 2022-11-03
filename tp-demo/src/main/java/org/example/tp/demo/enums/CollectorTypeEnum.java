package org.example.tp.demo.enums;

import lombok.Getter;

/**
 * CollectorTypeEnum related
 *
 * @author yanhom
 * @since 1.0.0
 **/
@Getter
public enum CollectorTypeEnum {

    /**
     * Metrics collect type.
     */
    LOGGING,

    MICROMETER,

    INTERNAL_LOGGING
}
