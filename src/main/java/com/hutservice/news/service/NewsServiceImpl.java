package com.hutservice.news.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程服务类接口实现类
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);


}
