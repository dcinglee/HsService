package com.hutservice.score.dao;

import com.hutservice.score.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
public interface ScoreDao extends JpaRepository<Score, String>, JpaSpecificationExecutor<Score> {
}
