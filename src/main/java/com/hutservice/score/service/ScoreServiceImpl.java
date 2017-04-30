package com.hutservice.score.service;

import com.hutservice.application.exception.AppException;
import com.hutservice.score.dao.ScoreDao;
import com.hutservice.score.entity.Score;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 成绩服务类接口实现类
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {

    private final Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);

    private ScoreDao scoreDao;

    @Autowired
    public void setScoreDao(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    @Override
    public Score addScore(Score score) {
        logger.info("addScore");
        if(null == score){
            throw new AppException("请录入成绩");
        }

        //添加默认值
        // TODO: 2017/3/22
        //保存实体对象
        return scoreDao.save(score);
    }

    @Override
    public void deleteScore(String scoreID) {
        logger.info("deleteScore");
        if(!StringUtils.hasText(scoreID)){
            throw new AppException("请选择要删除的记录！");
        }
        scoreDao.delete(scoreID);
    }

    @Override
    public Score updateScore(Score score) {
        logger.info("updateScore");
        if(null == score){
            throw new AppException("请选择要修改的记录！");
        }

        //更新要修改的字段
        // TODO: 2017/3/22
        return scoreDao.save(score);
    }

    @Override
    public Page<Score> queryScore(Pageable pageable) {
        // TODO: 2017/3/22
        return scoreDao.findAll(pageable);
    }
}
