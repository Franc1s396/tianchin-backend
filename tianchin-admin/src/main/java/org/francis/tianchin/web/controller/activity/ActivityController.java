package org.francis.tianchin.web.controller.activity;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.tianchin.activity.enums.ActivityCodeEnum;
import org.francis.tianchin.activity.model.dto.ActivityDTO;
import org.francis.tianchin.activity.model.request.ActivityImportRequest;
import org.francis.tianchin.activity.model.request.ActivityRequest;
import org.francis.tianchin.activity.model.request.ActivitySearchRequest;
import org.francis.tianchin.activity.service.ActivityService;
import org.francis.tianchin.activity.validator.CreateGroup;
import org.francis.tianchin.activity.validator.EditGroup;
import org.francis.tianchin.channel.model.Channel;
import org.francis.tianchin.channel.model.request.ChannelImportRequest;
import org.francis.tianchin.channel.model.request.ChannelSearchRequest;
import org.francis.tianchin.common.annotation.Log;
import org.francis.tianchin.common.core.domain.AjaxResult;
import org.francis.tianchin.common.core.page.PageRequest;
import org.francis.tianchin.common.enums.BusinessType;
import org.francis.tianchin.common.exception.ServiceException;
import org.francis.tianchin.common.utils.SecurityUtils;
import org.francis.tianchin.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author francis
 * @since 2023-01-03
 */
@Api(tags = "活动管理")
@RestController
@RequestMapping("/tianchin/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/page")
    @PreAuthorize("hasPermission('tianchin:activity:list')")
    @ApiOperation("活动查询")
    public AjaxResult activityPage(PageRequest pageRequest, ActivitySearchRequest activitySearchRequest) {
        IPage<ActivityDTO> activityPage = activityService.selectActivityDTOPage(pageRequest, activitySearchRequest);
        return AjaxResult.success(activityPage);
    }

    @GetMapping("/{activityId}")
    @PreAuthorize("hasPermission('tianchin:activity:list')")
    @ApiOperation("活动详情查询")
    public AjaxResult activityInfo(@PathVariable Integer activityId) {
        ActivityDTO activity = activityService.selectActivityById(activityId);
        return AjaxResult.success(activity);
    }

    @PostMapping("/create")
    @PreAuthorize("hasPermission('tianchin:activity:create')")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @ApiOperation("添加活动")
    public AjaxResult createActivity(@Validated(CreateGroup.class) @RequestBody ActivityRequest activityRequest) {
        // 时间校验
        LocalDateTime beginTime = activityRequest.getBeginTime();
        LocalDateTime endTime = activityRequest.getEndTime();
        if (beginTime.isAfter(endTime)) {
            throw new ServiceException(ActivityCodeEnum.ACTIVITY_TIME_ERROR.getName());
        }
        activityService.createActivity(activityRequest);
        return AjaxResult.success("添加成功");
    }

    @PostMapping("/export")
    @PreAuthorize("hasPermission('tianchin:activity:export')")
    @Log(title = "活动管理", businessType = BusinessType.EXPORT)
    @ApiOperation("活动导出")
    public void exportActivity(@ApiIgnore HttpServletResponse response, ActivitySearchRequest activitySearchRequest) {
        List<ActivityDTO> activityList = activityService.selectActivityList(activitySearchRequest);
        ExcelUtil<ActivityDTO> util = new ExcelUtil<>(ActivityDTO.class);
        util.exportExcel(response, activityList, "活动数据");
    }

    @PutMapping("/edit")
    @PreAuthorize("hasPermission('tianchin:activity:edit')")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @ApiOperation("更新活动")
    public AjaxResult editActivity(@Validated(EditGroup.class) @RequestBody ActivityRequest activityRequest) {
        // 时间校验
        LocalDateTime beginTime = activityRequest.getBeginTime();
        LocalDateTime endTime = activityRequest.getEndTime();
        if (beginTime.isAfter(endTime)) {
            throw new ServiceException(ActivityCodeEnum.ACTIVITY_TIME_ERROR.getName());
        }
        activityService.editActivity(activityRequest);
        return AjaxResult.success("更新成功");
    }

    @DeleteMapping("/{activityIds}")
    @PreAuthorize("hasPermission('tianchin:activity:remove')")
    @Log(title = "活动管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除活动")
    public AjaxResult removeActivity(@PathVariable Integer[] activityIds) {
        activityService.removeActivity(activityIds);
        return AjaxResult.success("删除成功");
    }
}

