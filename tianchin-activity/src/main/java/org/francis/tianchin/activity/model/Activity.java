package org.francis.tianchin.activity.model;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 活动管理
 * </p>
 *
 * @author francis
 * @since 2023-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tianchin_activity")
@ApiModel(value="Activity对象", description="活动管理")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "activity_id", type = IdType.AUTO)
    private Integer activityId;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "渠道来源")
    private Integer channelId;

    @ApiModelProperty(value = "活动简介")
    private String info;

    @ApiModelProperty(value = "活动类型(1-折扣券 2-代金券)")
    private Integer type;

    @ApiModelProperty(value = "年费折扣")
    private Double discount;

    @ApiModelProperty(value = "年费代金券")
    private Double voucher;

    @ApiModelProperty(value = "活动状态(0-禁用 1-正常)")
    private Integer status;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    @TableLogic
    private String delFlag;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "备注信息")
    private String remark;


}
