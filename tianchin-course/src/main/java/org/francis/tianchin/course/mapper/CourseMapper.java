package org.francis.tianchin.course.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.tianchin.course.model.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.francis.tianchin.course.model.request.CourseSearchRequest;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程管理 Mapper 接口
 * </p>
 *
 * @author francis
 * @since 2023-01-07
 */
public interface CourseMapper extends BaseMapper<Course> {

    default IPage<Course> selectCoursePage(Page<Course> coursePage, CourseSearchRequest courseSearchRequest) {
        return selectPage(coursePage, null);
    }

    default Course selectByName(String courseName) {
        return selectOne(Wrappers.lambdaQuery(Course.class).eq(Course::getCourseName, courseName));
    }

    default List<Course> selectCourseList(CourseSearchRequest courseSearchRequest){
        String courseName = courseSearchRequest.getCourseName();
        Integer type = courseSearchRequest.getType();
        Integer applyTo = courseSearchRequest.getApplyTo();
        LambdaQueryWrapper<Course> queryWrapper = Wrappers.lambdaQuery(Course.class);
        queryWrapper
                .like(StringUtils.hasText(courseName),Course::getCourseName,courseName)
                .eq(Objects.nonNull(type),Course::getType,type)
                .eq(Objects.nonNull(applyTo),Course::getApplyTo,applyTo);
        return selectList(queryWrapper);
    }
}
