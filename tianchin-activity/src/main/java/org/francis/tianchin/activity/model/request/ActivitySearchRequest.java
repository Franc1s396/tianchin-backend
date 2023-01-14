package org.francis.tianchin.activity.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Franc1s
 * @date 2023/1/3
 * @apiNote
 */
@Data
@ApiModel("活动查询参数")
public class ActivitySearchRequest {
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    @ApiModelProperty(value = "渠道id")
    private Integer channelId;
    @ApiModelProperty(value = "活动类型")
    private Integer type;
    @ApiModelProperty(value = "活动状态")
    private Integer status;
}
