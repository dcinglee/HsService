package com.hutservice.notice.dao;

import com.hutservice.notice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
public interface NoticeDao extends JpaRepository<Notice, String>, JpaSpecificationExecutor<Notice> {

}
