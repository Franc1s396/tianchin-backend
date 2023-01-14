package org.francis.tianchin.course.model.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.francis.tianchin.course.validator.CreateGroup;
import org.francis.tianchin.course.validator.EditGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Franc1s
 * @date 2023/1/7
 * @apiNote
 */
@Data
@ApiModel("课程参数")
public class CourseRequest {
    @ApiModelProperty(value = "课程id")
    @NotNull(message = "课程id不能为空",groups = EditGroup.class)
    private Long courseId;

    @ApiModelProperty(value = "课程类型(1:舞蹈类 2:游泳类 3:拳击类)")
    @NotNull(message = "课程类型不能为空",groups = {CreateGroup.class, EditGroup.class})
    private Integer type;

    @ApiModelProperty(value = "课程名称")
    @NotBlank(message = "课程名称不能为空",groups = {CreateGroup.class, EditGroup.class})
    private String courseName;

    @ApiModelProperty(value = "课程价格")
    @NotNull(message = "课程价格不能为空",groups = {CreateGroup.class, EditGroup.class})
    @Min(value = 0,message = "课程价格最低为0",groups = {CreateGroup.class, EditGroup.class})
    private BigDecimal price;

    @ApiModelProperty(value = "课程适用人群")
    @NotNull(message = "课程适用人群不能为空",groups = {CreateGroup.class, EditGroup.class})
    private Integer applyTo;

    @ApiModelProperty(value = "课程描述信息")
    @NotBlank(message = "课程描述信息不能为空",groups = {CreateGroup.class, EditGroup.class})
    private String info;
}
