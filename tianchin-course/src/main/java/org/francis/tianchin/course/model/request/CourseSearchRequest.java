package org.francis.tianchin.course.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author Franc1s
 * @date 2023/1/7
 * @apiNote
 */
@Data
@ApiModel("可曾查询参数")
public class CourseSearchRequest {
    @ApiModelProperty(value = "课程类型(1:舞蹈类 2:游泳类 3:拳击类)")
    private Integer type;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程适用人群")
    private Integer applyTo;
}
