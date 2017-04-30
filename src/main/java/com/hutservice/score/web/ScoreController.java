package com.hutservice.score.web;

import com.hutservice.application.exception.AppException;
import com.hutservice.application.vo.PageData;
import com.hutservice.application.vo.PageParam;
import com.hutservice.application.vo.ResultMessage;
import com.hutservice.score.entity.Score;
import com.hutservice.score.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 成绩模块请求控制器
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    private final Logger logger = LoggerFactory.getLogger(ScoreController.class);

    private ScoreService scoreService;

    @Autowired
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @RequestMapping("/add")
    public ResultMessage addScore(Score score) {
        logger.info("addScore");
        try {
            scoreService.addScore(score);
            return ResultMessage.newSuccess("添加成绩成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("添加成绩异常：" + e.getMessage());
            return ResultMessage.newFailure("添加成绩异常！");
        }
    }

    @RequestMapping("/delete")
    public ResultMessage deleteScore(String scoreID) {
        logger.info("deleteScore");
        try {
            scoreService.deleteScore(scoreID);
            return ResultMessage.newSuccess("删除成绩成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("删除成绩异常：" + e.getMessage());
            return ResultMessage.newFailure("删除成绩异常！");
        }
    }

    @RequestMapping("/update")
    public ResultMessage updateScore(Score score) {
        logger.info("updateScore");
        try {
            scoreService.updateScore(score);
            return ResultMessage.newSuccess("修改成绩成功！");
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("修改成绩异常：" + e.getMessage());
            return ResultMessage.newFailure("修改成绩异常！");
        }
    }

    @RequestMapping("/query")
    public ResultMessage queryScore(PageParam pageParam) {
        logger.info("queryScore");
        try {
            Page<Score> scorePage = scoreService.queryScore(pageParam.newPageable());
            return ResultMessage.newSuccess("查询成绩成功！").setData(new PageData(scorePage));
        } catch (AppException ae) {
            return ResultMessage.newFailure(ae.getMessage());
        } catch (Exception e) {
            logger.error("查询成绩异常：" + e.getMessage());
            return ResultMessage.newFailure("查询成绩异常！");
        }
    }
}
