package org.francis.tianchin.channel.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.francis.tianchin.common.annotation.Excel;

/**
 * @author francis
 * @since 2022-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tianchin_channel")
@ApiModel(value="Channel对象", description="")
public class Channel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "渠道编号")
    @TableId(value = "channel_id", type = IdType.AUTO)
    @Excel(name = "渠道编号", cellType = Excel.ColumnType.NUMERIC)
    private Integer channelId;

    @ApiModelProperty(value = "渠道名称")
    @Excel(name = "渠道名称")
    private String channelName;

    @ApiModelProperty(value = "渠道状态 0可用；1禁用")
    @Excel(name = "渠道状态", readConverterExp = "0=禁用,1=正常")
    private Integer status;

    @ApiModelProperty(value = "渠道类型")
    @Excel(name = "渠道类型", readConverterExp = "0=线上渠道,1=线下渠道")
    private Integer type;

    @ApiModelProperty(value = "备注")
    @Excel(name = "渠道备注")
    private String remark;

    @ApiModelProperty(value = "创建者")
    @Excel(name = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    @TableLogic
    private String delFlag;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
