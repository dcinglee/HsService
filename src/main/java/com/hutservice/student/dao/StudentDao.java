package com.hutservice.student.dao;

import com.hutservice.student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 学生数据库访问接口
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
public interface StudentDao extends JpaRepository<Student, String>, JpaSpecificationExecutor<Student> {

    Student findByStuNo(String stuNo);

    @Query("select s.studentID, s.stuAdress, s.stuAge, s.stuBirthday, s.stuIdCard, s.stuName, s.stuNation, s.stuNo, s.stuPassword, s.stuPhoneNo, s.stuSex from Student s where s.stuName = :stuName and s.stuNo = :stuNo")
    Page<Student> findStudents(@Param("stuName") String stuName, @Param("stuNo") String stuNo, Pageable pageable);
}
