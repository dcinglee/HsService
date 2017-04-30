package com.hutservice.express.web;

import com.hutservice.application.vo.ResultMessage;
import com.hutservice.express.service.ExpressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 快递模块请求控制器
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@RestController
@RequestMapping("/express")
public class ExpressController {

    private final Logger logger = LoggerFactory.getLogger(ExpressController.class);

    private ExpressService expressService;

    @Autowired
    public void setExpressService(ExpressService expressService) {
        this.expressService = expressService;
    }

    @RequestMapping("/query")
    public ResultMessage queryExpress(String type, String number){

        return null;
    }
}
