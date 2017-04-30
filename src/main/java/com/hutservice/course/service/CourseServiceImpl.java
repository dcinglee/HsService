package com.hutservice.course.service;

import com.hutservice.application.exception.AppException;
import com.hutservice.course.dao.CourseDao;
import com.hutservice.course.entity.Course;
import com.hutservice.course.web.CourseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 课程服务类接口实现类
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private Logger logger = LoggerFactory.getLogger(CourseController.class);

    private CourseDao courseDao;

    @Autowired
    public void setCourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course addCourse(Course course) {
        logger.info("addCourse");
        if(null == course){
            throw new AppException("该课程不存在！");
        }
        //添加默认值
        course.setCreateDate(new Date());

        //保存实体对象
        return courseDao.save(course);
    }

    @Override
    public void deleteCourse(String courseID) {
        logger.info("deleteCourse");
        if(!StringUtils.hasText(courseID)){
            throw new AppException("请选择要删除的记录！");
        }
        courseDao.delete(courseID);
    }

    @Override
    public Course updateCourse(Course course) {
        logger.info("updateCourse");
        if(null == course){
            throw new AppException("请选择要修改的记录！");
        }
        //更新要修改的字段
        course.setCouName(course.getCouName());
        course.setCouNo(course.getCouNo());
        course.setCouType(course.getCouType());

        //保存实体对象
        return courseDao.save(course);
    }

    @Override
    public Page<Course> queryCourse(String couName, Pageable pageable) {
        logger.info("queryCourse");
        if(!StringUtils.hasText(couName)){
            return courseDao.findAll(pageable);
        }
        return courseDao.findByCouName(couName,pageable);
    }
}
