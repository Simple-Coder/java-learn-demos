package metrics.em;

import lombok.Getter;

/**
 * Created by xiedong
 * Date: 2022/9/21 22:25
 */
@Getter
public enum CollectorTypeEnum {

    /**
     * Metrics collect type.
     */
    LOGGING,

    MICROMETER,

    INTERNAL_LOGGING
}