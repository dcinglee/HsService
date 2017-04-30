package com.hutservice.teacher.dao;

import com.hutservice.teacher.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 教师数据库访问接口
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
public interface TeacherDao extends JpaRepository<Teacher, String>, JpaSpecificationExecutor<Teacher> {

    Teacher findByTeaNo(String teaNo);

    @Query("select t.teacherID, t.teaAdress, t.teaAge, t.teaBirthday, t.teaIdCard, t.teaName, t.teaNation, t.teaNo, t.teaPassword, t.teaPhoneNo from Teacher t where t.teaName = :teaName and t.teaNo= :teaNo")
    Page<Teacher> findByTeaName(@Param("teaName") String teaName, @Param("teaNo") String teaNo, Pageable pageable);
}
