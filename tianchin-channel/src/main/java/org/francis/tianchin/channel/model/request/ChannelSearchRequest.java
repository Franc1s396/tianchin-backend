package org.francis.tianchin.channel.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Franc1s
 * @date 2023/1/2
 * @apiNote
 */
@Data
@ApiModel("渠道查询参数")
public class ChannelSearchRequest {
    @ApiModelProperty(value = "渠道名称")
    private String channelName;

    @ApiModelProperty(value = "渠道状态 0可用；1禁用")
    private Integer status;

    @ApiModelProperty(value = "渠道类型")
    private Integer type;

    @ApiModelProperty(value = "时间参数")
    private Map<String,Object> params=new HashMap<>();
}
