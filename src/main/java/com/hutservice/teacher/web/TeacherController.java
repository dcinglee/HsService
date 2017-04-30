package com.hutservice.teacher.web;

import com.hutservice.application.exception.AppException;
import com.hutservice.application.vo.PageData;
import com.hutservice.application.vo.PageParam;
import com.hutservice.application.vo.ResultMessage;
import com.hutservice.teacher.entity.Teacher;
import com.hutservice.teacher.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    private TeacherService teacherService;

    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping("/add")
    public ResultMessage addTeacher(Teacher teacher) {
        logger.info("addTeacher");
        try {
            teacherService.addTeacher(teacher);
            return ResultMessage.newSuccess("新增教师成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("新增教师异常：" + e.getMessage());
            return ResultMessage.newFailure("新增教师异常！");
        }
    }

    @RequestMapping("/delete")
    public ResultMessage deleteTeacher(String teacherID) {
        logger.info("deleteTeacher");
        try {
            teacherService.deleteTeacher(teacherID);
            return ResultMessage.newSuccess("删除教师成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("删除教师异常：" + e.getMessage());
            return ResultMessage.newFailure("删除教师异常！");
        }
    }

    @RequestMapping("/update")
    public ResultMessage updateTeacher(Teacher teacher) {
        logger.info("updateTeacher");
        try {
            teacherService.updateTeacher(teacher);
            return ResultMessage.newSuccess("修改教师成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("修改教师异常：" + e.getMessage());
            return ResultMessage.newFailure("修改教师异常！");
        }
    }

    @RequestMapping("/query")
    public ResultMessage queryTeacher(String teaName, String teaNo, PageParam pageParam) {
        logger.info("queryTeacher");
        try {
            Page<Teacher> teacherPage = teacherService.queryTeacher(teaName, teaNo, pageParam.newPageable());
            return ResultMessage.newSuccess("查询教师成功！").setData(new PageData(teacherPage));
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("查询教师异常：" + e.getMessage());
            return ResultMessage.newFailure("查询教师异常！");
        }
    }

}
