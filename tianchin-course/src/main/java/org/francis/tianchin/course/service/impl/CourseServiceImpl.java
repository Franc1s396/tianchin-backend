package org.francis.tianchin.course.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.francis.tianchin.common.core.page.PageRequest;
import org.francis.tianchin.common.exception.ServiceException;
import org.francis.tianchin.common.utils.SecurityUtils;
import org.francis.tianchin.course.model.Course;
import org.francis.tianchin.course.mapper.CourseMapper;
import org.francis.tianchin.course.model.request.CourseRequest;
import org.francis.tianchin.course.model.request.CourseSearchRequest;
import org.francis.tianchin.course.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * 课程管理 服务实现类
 * </p>
 *
 * @author francis
 * @since 2023-01-07
 */
@Slf4j
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public IPage<Course> selectCoursePage(PageRequest pageRequest, CourseSearchRequest courseSearchRequest) {
        Page<Course> coursePage = new Page<>(pageRequest.getPageNo(), pageRequest.getPageSize());
        return courseMapper.selectCoursePage(coursePage, courseSearchRequest);
    }

    @Override
    public Course selectCourseById(Integer courseId) {
        return courseMapper.selectById(courseId);
    }

    @Override
    public void createCourse(CourseRequest courseRequest) {
        String courseName = courseRequest.getCourseName();
        if (Objects.nonNull(courseMapper.selectByName(courseName))) {
            throw new ServiceException("课程名称已被占用");
        }
        Course course = new Course();
        BeanUtils.copyProperties(courseRequest, course);
        course.setCreateBy(SecurityUtils.getUsername());
        courseMapper.insert(course);
        log.info("添加课程" + course);
    }

    @Override
    public void editCourse(CourseRequest courseRequest) {
        Course course = Optional
                .ofNullable(courseMapper.selectById(courseRequest.getCourseId()))
                .orElseThrow(() -> new ServiceException("课程不存在"));
        BeanUtils.copyProperties(courseRequest, course);
        course.setUpdateBy(SecurityUtils.getUsername());
        courseMapper.updateById(course);
        log.info("更新课程" + course);
    }

    @Override
    public void removeCourse(Integer[] courseIds) {
        courseMapper.deleteBatchIds(Arrays.asList(courseIds));
    }

    @Override
    public List<Course> selectCourseList(CourseSearchRequest courseSearchRequest) {
        return courseMapper.selectCourseList(courseSearchRequest);
    }
}
