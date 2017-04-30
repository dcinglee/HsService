package com.hutservice.teacher.service;

import com.hutservice.teacher.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 教师服务类接口
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
public interface TeacherService {
    Teacher addTeacher(Teacher teacher);

    void deleteTeacher(String teacherID);

    Teacher updateTeacher(Teacher teacher);

    Page<Teacher> queryTeacher(String teaName, String teaNo, Pageable pageable);
}
