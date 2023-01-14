package org.francis.tianchin.activity.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.francis.tianchin.activity.validator.CreateGroup;
import org.francis.tianchin.activity.validator.EditGroup;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * @author Franc1s
 * @date 2023/1/3
 * @apiNote
 */
@Data
@ApiModel("活动添加参数")
public class ActivityRequest {
    @ApiModelProperty(value = "活动id")
    @NotNull(message = "活动id不能为空",groups = EditGroup.class)
    private Integer activityId;

    @ApiModelProperty(value = "活动名称")
    @NotBlank(message = "活动名称不能为空",groups = {CreateGroup.class, EditGroup.class})
    @Size(max = 20,message = "活动名称过长")
    private String activityName;

    @ApiModelProperty(value = "渠道来源")
    @NotNull(message = "渠道来源不能为空",groups = {CreateGroup.class, EditGroup.class})
    private Integer channelId;

    @ApiModelProperty(value = "活动简介")
    @NotBlank(message = "活动简介不能为空",groups = {CreateGroup.class, EditGroup.class})
    private String info;

    @ApiModelProperty(value = "活动类型(1-折扣券 2-代金券)")
    @NotNull(message = "活动类型不能为空",groups = {CreateGroup.class, EditGroup.class})
    private Integer type;

    @ApiModelProperty(value = "活动状态(0-禁用 1-正常)")
    @NotNull(message = "活动状态不能为空",groups = {CreateGroup.class, EditGroup.class})
    private Integer status;

    @ApiModelProperty(value = "年费折扣")
    @Min(value = 0,groups = {CreateGroup.class, EditGroup.class})
    @Max(value = 10,groups = {CreateGroup.class, EditGroup.class})
    private Double discount;

    @ApiModelProperty(value = "年费代金券")
    private Double voucher;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @NotNull(groups = {CreateGroup.class, EditGroup.class})
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @NotNull(groups = {CreateGroup.class, EditGroup.class})
    private LocalDateTime endTime;

    @ApiModelProperty(value = "备注信息")
    private String remark;
}
