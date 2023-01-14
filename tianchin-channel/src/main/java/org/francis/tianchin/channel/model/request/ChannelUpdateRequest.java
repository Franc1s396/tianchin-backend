package org.francis.tianchin.channel.model.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Franc1s
 * @date 2023/1/2
 * @apiNote
 */
@Data
@ApiModel("渠道更新参数")
public class ChannelUpdateRequest {
    @ApiModelProperty(value = "渠道编号")
    @NotNull(message = "渠道id不能为空")
    private Integer channelId;

    @ApiModelProperty(value = "渠道名称")
    @NotBlank(message = "渠道名称不能为空")
    private String channelName;

    @ApiModelProperty(value = "渠道状态 0可用；1禁用")
    @Max(value = 1, message = "非法渠道状态值")
    @Min(value = 0, message = "非法渠道状态值")
    private Integer status = 0;

    @ApiModelProperty(value = "渠道类型")
    @NotNull(message = "渠道类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "备注")
    private String remark;
}
