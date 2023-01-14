package org.francis.tianchin.channel.enums;

import lombok.Getter;
import org.francis.tianchin.common.enums.BaseEnum;

/**
 * @author Franc1s
 * @date 2022/12/24
 * @apiNote
 */
public enum ChannelCodeEnum implements BaseEnum {
    /**
     * 渠道名称已存在
     */
    CHANNEL_NAME_TAKEN(1, "渠道名称已存在"),
    CHANNEL_NOT_FOUND(2,"渠道不存在"),
    CHANNEL_LIST_EMPTY(3,"渠道列表为空")
    ;
    private final Integer code;
    private final String name;

    ChannelCodeEnum(Integer code, String name) {
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
