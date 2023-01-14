package org.francis.tianchin.channel.model.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.francis.tianchin.common.annotation.Excel;

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
public class ChannelImportRequest {
    @ApiModelProperty(value = "渠道编号")
    @Excel(name = "渠道编号", cellType = Excel.ColumnType.NUMERIC)
    private Integer channelId;

    @ApiModelProperty(value = "渠道名称")
    @NotBlank(message = "渠道名称不能为空")
    @Excel(name = "渠道名称")
    private String channelName;

    @ApiModelProperty(value = "渠道状态 0可用；1禁用")
    @Max(value = 1, message = "非法渠道状态值")
    @Min(value = 0, message = "非法渠道状态值")
    @Excel(name = "渠道状态", readConverterExp = "0=禁用,1=正常")
    private Integer status;

    @ApiModelProperty(value = "渠道类型")
    @NotNull(message = "渠道类型不能为空")
    @Excel(name = "渠道类型", readConverterExp = "1=线上渠道,2=线下渠道")
    private Integer type;

    @ApiModelProperty(value = "备注")
    @Excel(name = "渠道备注")
    private String remark;
}
