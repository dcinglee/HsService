package com.hutservice.score.service;

import com.hutservice.score.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 成绩服务类接口
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
public interface ScoreService {
    Score addScore(Score score);

    void deleteScore(String scoreID);

    Score updateScore(Score score);

    Page<Score> queryScore(Pageable pageable);
}
