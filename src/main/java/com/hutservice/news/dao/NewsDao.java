package com.hutservice.news.dao;

import com.hutservice.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
public interface NewsDao extends JpaRepository<News, String>, JpaSpecificationExecutor<News> {

}
