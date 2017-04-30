package com.hutservice.course.web;

import com.hutservice.application.exception.AppException;
import com.hutservice.application.vo.PageParam;
import com.hutservice.application.vo.ResultMessage;
import com.hutservice.course.entity.Course;
import com.hutservice.course.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程模块请求控制器
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    private Logger logger = LoggerFactory.getLogger(CourseController.class);

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/add")
    public ResultMessage addCourse(Course course){
        logger.info("addCourse");
        try {
            courseService.addCourse(course);
            return ResultMessage.newSuccess("查询课程成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("查询课程异常：" + e.getMessage());
            return ResultMessage.newFailure("查询课程异常！");
        }
    }

    @RequestMapping("/delete")
    public ResultMessage deleteCourse(String courseID){
        logger.info("deleteCourse");
        try {
            courseService.deleteCourse(courseID);
            return ResultMessage.newSuccess("删除课程成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("删除课程异常：" + e.getMessage());
            return ResultMessage.newFailure("删除课程异常！");
        }
    }

    @RequestMapping("/update")
    public ResultMessage updateCourse(Course course){
        logger.info("updateCourse");
        try {
            courseService.updateCourse(course);
            return ResultMessage.newSuccess("修改课程成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("修改课程异常：" + e.getMessage());
            return ResultMessage.newFailure("修改课程异常！");
        }
    }

    @RequestMapping("/query")
    public ResultMessage queryCourse(String couName, PageParam pageParam){
        logger.info("queryCourse");
        try {
            courseService.queryCourse(couName, pageParam.newPageable());
            return ResultMessage.newSuccess("查询课程成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("查询课程异常：" + e.getMessage());
            return ResultMessage.newFailure("查询课程异常！");
        }
    }
}
