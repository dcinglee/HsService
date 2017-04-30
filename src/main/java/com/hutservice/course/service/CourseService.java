package com.hutservice.course.service;

import com.hutservice.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 课程服务类接口
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
public interface CourseService  {

    Course addCourse(Course course);

    void deleteCourse(String courseID);

    Course updateCourse(Course course);

    Page<Course> queryCourse(String couName, Pageable pageable);
}
