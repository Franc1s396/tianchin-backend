package org.francis.tianchin.common.core.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Franc1s
 * @date 2022/12/17
 * @apiNote
 */
@Data
public class PageRequest {
    @ApiModelProperty("页数")
    private Integer pageNo=1;
    @ApiModelProperty("大小")
    private Integer pageSize=10;
}
