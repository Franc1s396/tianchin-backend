package org.francis.tianchin.course.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.francis.tianchin.common.core.page.PageRequest;
import org.francis.tianchin.course.model.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.tianchin.course.model.request.CourseRequest;
import org.francis.tianchin.course.model.request.CourseSearchRequest;

import java.util.List;

/**
 * <p>
 * 课程管理 服务类
 * </p>
 *
 * @author francis
 * @since 2023-01-07
 */
public interface CourseService extends IService<Course> {

    IPage<Course> selectCoursePage(PageRequest pageRequest, CourseSearchRequest courseSearchRequest);

    Course selectCourseById(Integer courseId);

    void createCourse(CourseRequest courseRequest);

    void editCourse(CourseRequest courseRequest);

    void removeCourse(Integer[] courseIds);

    List<Course> selectCourseList(CourseSearchRequest courseSearchRequest);
}
