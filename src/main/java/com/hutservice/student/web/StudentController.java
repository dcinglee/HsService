package com.hutservice.student.web;

import com.hutservice.application.exception.AppException;
import com.hutservice.application.vo.PageData;
import com.hutservice.application.vo.PageParam;
import com.hutservice.application.vo.ResultMessage;
import com.hutservice.student.entity.Student;
import com.hutservice.student.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生模块请求控制器
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/add")
    public ResultMessage addStudent(Student student) {
        logger.info("addStudent");
        try {
            studentService.addStudent(student);
            return ResultMessage.newSuccess("新增学生成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("新增学生异常：" + e.getMessage());
            return ResultMessage.newFailure("新增学生异常！");
        }
    }

    @RequestMapping("/delete")
    public ResultMessage deleteStudent(String studentID) {
        logger.info("deleteStudent");
        try {
            studentService.deleteStudent(studentID);
            return ResultMessage.newSuccess("删除学生成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("删除学生异常：" + e.getMessage());
            return ResultMessage.newFailure("删除学生异常！");
        }
    }

    @RequestMapping("/update")
    public ResultMessage updateStudent(Student student) {
        logger.info("updateStudent");
        try {
            studentService.updateStudent(student);
            return ResultMessage.newSuccess("更新学生成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("更新学生异常：" + e.getMessage());
            return ResultMessage.newFailure("更新学生异常！");
        }
    }

    @RequestMapping("/query")
    public ResultMessage queryStudent(String stuName, String stuNo, PageParam pageParam) {
        logger.info("queryStudent");
        try {
            Page<Student> studentPage = studentService.queryStudent(stuName, stuNo, pageParam.newPageable());
            return ResultMessage.newSuccess("查询学生成功！").setData(new PageData(studentPage));
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("查询学生异常：" + e.getMessage());
            return ResultMessage.newFailure("查询学生异常！");
        }
    }
}
