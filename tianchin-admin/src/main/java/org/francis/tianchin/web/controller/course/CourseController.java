package org.francis.tianchin.web.controller.course;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.tianchin.common.annotation.Log;
import org.francis.tianchin.common.core.domain.AjaxResult;
import org.francis.tianchin.common.core.page.PageRequest;
import org.francis.tianchin.common.enums.BusinessType;
import org.francis.tianchin.common.utils.poi.ExcelUtil;
import org.francis.tianchin.course.model.Course;
import org.francis.tianchin.course.model.request.CourseRequest;
import org.francis.tianchin.course.model.request.CourseSearchRequest;
import org.francis.tianchin.course.service.CourseService;
import org.francis.tianchin.course.validator.CreateGroup;
import org.francis.tianchin.course.validator.EditGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程管理 前端控制器
 * </p>
 *
 * @author francis
 * @since 2023-01-07
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/tianchin/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/page")
    @PreAuthorize("hasPermission('tianchin:course:list')")
    @ApiOperation("课程查询")
    public AjaxResult coursePage(PageRequest pageRequest, CourseSearchRequest courseSearchRequest) {
        IPage<Course> coursePage = courseService.selectCoursePage(pageRequest, courseSearchRequest);
        return AjaxResult.success(coursePage);
    }

    @GetMapping("/{courseId}")
    @PreAuthorize("hasPermission('tianchin:course:list')")
    @ApiOperation("课程详情查询")
    public AjaxResult courseInfo(@PathVariable Integer courseId) {
        Course course = courseService.selectCourseById(courseId);
        return AjaxResult.success(course);
    }

    @PostMapping("/create")
    @PreAuthorize("hasPermission('tianchin:course:create')")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @ApiOperation("添加课程")
    public AjaxResult createCourse(@Validated(CreateGroup.class) @RequestBody CourseRequest courseRequest) {
        courseService.createCourse(courseRequest);
        return AjaxResult.success("添加成功");
    }

    @PostMapping("/export")
    @PreAuthorize("hasPermission('tianchin:course:export')")
    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
    @ApiOperation("课程导出")
    public void exportChannel(@ApiIgnore HttpServletResponse response, CourseSearchRequest courseSearchRequest) {
        List<Course> courseList = courseService.selectCourseList(courseSearchRequest);
        ExcelUtil<Course> util = new ExcelUtil<>(Course.class);
        util.exportExcel(response, courseList, "活动数据");
    }

    @PutMapping("/edit")
    @PreAuthorize("hasPermission('tianchin:course:edit')")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @ApiOperation("更新课程")
    public AjaxResult editCourse(@Validated(EditGroup.class) @RequestBody CourseRequest courseRequest) {
        courseService.editCourse(courseRequest);
        return AjaxResult.success("更新成功");
    }

    @DeleteMapping("/{courseIds}")
    @PreAuthorize("hasPermission('tianchin:course:remove')")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除课程")
    public AjaxResult removeCourse(@PathVariable Integer[] courseIds) {
        courseService.removeCourse(courseIds);
        return AjaxResult.success("删除成功");
    }
}

