package org.francis.tianchin.activity.enums;

import org.francis.tianchin.common.enums.BaseEnum;

/**
 * @author Franc1s
 * @date 2022/12/24
 * @apiNote
 */
public enum ActivityCodeEnum implements BaseEnum {
    /**
     * 渠道名称已存在
     */
    ACTIVITY_EXISTS(1, "活动已存在"),
    ACTIVITY_TIME_ERROR(2,"活动开始结束时间异常，请检查")
    ;
    private final Integer code;
    private final String name;

    ActivityCodeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
