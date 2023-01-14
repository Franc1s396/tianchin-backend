package org.francis.tianchin.activity.model.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.francis.tianchin.common.annotation.Excel;

import java.time.LocalDateTime;

/**
 * @author Franc1s
 * @date 2023/1/3
 * @apiNote
 */
@Data
public class ActivityDTO {
    @ApiModelProperty(value = "id")
    @Excel(name = "活动编号", cellType = Excel.ColumnType.NUMERIC)
    private Integer activityId;

    @ApiModelProperty(value = "活动名称")
    @Excel(name = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "渠道来源")
    @Excel(name = "渠道编号", cellType = Excel.ColumnType.NUMERIC)
    private Integer channelId;

    @ApiModelProperty(value = "渠道名称")
    @Excel(name = "渠道名称")
    private String channelName;

    @ApiModelProperty(value = "活动简介")
    @Excel(name = "活动简介")
    private String info;

    @ApiModelProperty(value = "活动类型(1-折扣券 2-代金券)")
    @Excel(name = "活动类型", readConverterExp = "1=折扣券,2=代金券")
    private Integer type;

    @ApiModelProperty(value = "年费折扣")
    @Excel(name = "年费折扣")
    private Double discount;

    @ApiModelProperty(value = "年费代金券")
    @Excel(name = "年费代金券")
    private Double voucher;

    @ApiModelProperty(value = "活动状态(0-禁用 1-正常)")
    @Excel(name = "活动状态", readConverterExp = "0=禁用,1=正常")
    private Integer status;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @Excel(name = "开始时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @Excel(name = "结束时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "备注信息")
    private String remark;
}
