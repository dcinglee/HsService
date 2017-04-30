package com.hutservice.teacher.service;

import com.hutservice.application.exception.AppException;
import com.hutservice.teacher.dao.TeacherDao;
import com.hutservice.teacher.entity.Teacher;
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
 * 教师服务类接口实现类
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private TeacherDao teacherDao;

    @Autowired
    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        logger.info("addTeacher");
        if (null == teacher) {
            throw new AppException("该教师不存在！");
        }

        //设置默认值
        teacher.setCreateDate(new Date());

        //保存实体对象
        return teacherDao.save(teacher);
    }

    @Override
    public void deleteTeacher(String teacherID) {
        logger.info("deleteTeacher");
        if (!StringUtils.hasText(teacherID)) {
            throw new AppException("请选择要删除的记录！");
        }
        teacherDao.delete(teacherID);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        logger.info("updateTeacher");
        if (null == teacher) {
            throw new AppException("请选择要跟新的记录！");
        }
        Teacher newTeacher = teacherDao.findByTeaNo(teacher.getTeaNo());
        if (null == newTeacher) {
            throw new AppException("该教师已存在！");
        }

        //修改需要修改的字段
        newTeacher.setTeaPassword(teacher.getTeaPassword());
        newTeacher.setTeaPhoneNo(teacher.getTeaPhoneNo());
        newTeacher.setTeaAdress(teacher.getTeaAdress());

        //保存实体对象
        return teacherDao.save(newTeacher);
    }

    @Override
    public Page<Teacher> queryTeacher(String teaName, String teaNo, Pageable pageable) {
        logger.info("queryTeacher");
        if (!StringUtils.hasText(teaName) && !StringUtils.hasText(teaNo)) {
            return teacherDao.findAll(pageable);
        }
        return teacherDao.findByTeaName(teaName, teaNo, pageable);
    }
}
