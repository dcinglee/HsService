package com.hutservice.student.service;

import com.hutservice.student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 学生服务类接口
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
public interface StudentService {
    Student addStudent(Student student);

    void deleteStudent(String studentID);

    Student updateStudent(Student student);

    Page<Student> queryStudent(String stuName, String stuNo, Pageable pageable);
}
