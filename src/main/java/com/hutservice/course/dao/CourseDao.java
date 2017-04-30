package com.hutservice.course.dao;

import com.hutservice.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
public interface CourseDao extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {

    Page<Course> findByCouName(String couName, Pageable pageable);
}
