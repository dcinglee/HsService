package com.hutservicewx.controller;

import com.hutservice.application.config.AppConfig;
import com.hutservice.application.vo.ResultMessage;
import com.hutservice.user.entity.User;
import com.hutservice.user.service.UserService;
import com.hutservicewx.config.WeixinConfig;
import com.hutservicewx.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * 微信业务入口控制器。
 *
 * @author lid
 * @date 2017.2.26
 */
@Controller
@RequestMapping("/entrance")
public class EntranceController {
    private final Logger logger = LoggerFactory.getLogger(EntranceController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 个人中心入口
     *
     * @param req
     * @param res
     * @return
     * @throws Exception
     * @author lid
     */
    @ResponseBody
    @RequestMapping("index")
    public void getCodeByPageIndex(HttpServletRequest req, HttpServletResponse res) throws Exception {

        User user = (User) req.getSession().getAttribute(AppConfig.USER_SESSION_KEY);
        if (null != user) {
            String jumpUrl = WeixinConfig.HOST + "/wechat/index.html";
            logger.info("jumpUrl :" + jumpUrl);
            res.sendRedirect(jumpUrl);
            return;
        }

        logger.info("getCodeByPageIndex");
        String jumpUrl = WeixinConfig.HOST + "/entrance/userInfo.do";
        logger.info("jumpUrl:" + jumpUrl);

        String location = WeixinConfig.CODE_URL.replaceFirst("APPID", WeixinConfig.APPID)
                .replaceFirst("REDIRECT_URI", URLEncoder.encode(jumpUrl, "UTF-8"))
                .replaceFirst("SCOPE", "snsapi_base").replaceFirst("STATE", "HsService");

        logger.info("location:" + location);
        res.sendRedirect(location);
    }

    @ResponseBody
    @RequestMapping("userInfo")
    public void pcenter(String code, HttpServletRequest req, HttpServletResponse res) throws Exception {
        logger.info("pcenter");
        User user = (User) req.getSession().getAttribute(AppConfig.USER_SESSION_KEY);

        if (null != user) {
            logger.info("null != user");
            String jumpUrl = WeixinConfig.HOST + "/wechat/index.html";
            logger.info("jumpUrl :" + jumpUrl);
            res.sendRedirect(jumpUrl);
        }

        if (!StringUtils.hasText(code)) {
            logger.info("code == null");
            return;
        }
        logger.info("code:" + code);
        String openid = HttpUtil.getAuthedUserInfo(code, true);
        if (null == openid) {
            logger.info("网页授权获取用户信息失败");
            return;
        }
        user = userService.getUserByOpenid(openid);
        //缓存用户信息
        req.getSession().setAttribute(AppConfig.USER_SESSION_KEY, user);

        logger.info("网页授权获取用户信息成功 ：" + user.getNickName());

        String jumpUrl = WeixinConfig.HOST + "/wechat/index.html";
        logger.info("jumpUrl :" + jumpUrl);
        res.sendRedirect(jumpUrl);
    }

    @ResponseBody
    @RequestMapping("getCurrentUser")
    public ResultMessage getCurrentUser(HttpServletRequest req, HttpServletResponse res) throws Exception {
        User user = (User) req.getSession().getAttribute(AppConfig.USER_SESSION_KEY);
        if (null != user) {
            return ResultMessage.newSuccess("获取当前用户成功！").setData(user);
        }

        String jumpUrl = WeixinConfig.HOST + "/entrance/index.do";
        res.sendRedirect(jumpUrl);
        return ResultMessage.newFailure("获取当前用户失败，页面已跳转！");
    }
}
