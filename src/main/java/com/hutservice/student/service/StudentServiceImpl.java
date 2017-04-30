package com.hutservice.student.service;

import com.hutservice.application.exception.AppException;
import com.hutservice.student.dao.StudentDao;
import com.hutservice.student.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 学生服务类接口实现类
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentDao studentDao;

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student addStudent(Student student) {
        logger.info("addStudent");
        if (null == student) {
            throw new AppException("该学生不存在！");
        }

        //添加默认值
        student.setCreateDate(student.getCreateDate());

        //保存实体对象
        return studentDao.save(student);
    }

    @Override
    public void deleteStudent(String studentID) {
        logger.info("deleteStudent");
        if (!StringUtils.hasText(studentID)) {
            throw new AppException("请选择要删除的记录");
        }
        studentDao.delete(studentID);
    }

    @Override
    public Student updateStudent(Student student) {
        logger.info("updateStudent");
        if (null == student) {
            throw new AppException("请选择要修改的记录！");
        }
        Student newstudent = studentDao.findByStuNo(student.getStuNo());
        if (null == newstudent) {
            throw new AppException("该学生已存在！");
        }
        //更新需要修改的字段
        newstudent.setStuPhoneNo(student.getStuPhoneNo());
        newstudent.setStuPassword(student.getStuPassword());
        newstudent.setStuAdress(student.getStuAdress());

        //保存实体对象
        return studentDao.save(newstudent);
    }

    @Override
    public Page<Student> queryStudent(String stuName, String stuNo, Pageable pageable) {
        logger.info("queryStudent");
        if (!StringUtils.hasText(stuName) && !StringUtils.hasText(stuNo)) {
            return studentDao.findAll(pageable);
        }
        return studentDao.findStudents(stuName, stuNo, pageable);
    }
}
