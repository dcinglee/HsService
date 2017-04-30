package com.hutservice.common.web;

import com.hutservice.application.cache.Cache;
import com.hutservice.application.cache.CacheFactory;
import com.hutservice.application.config.AppConfig;
import com.hutservice.application.util.RSAEncryptUtil;
import com.hutservice.application.util.RandomString;
import com.hutservice.application.vo.LoginMessage;
import com.hutservice.application.vo.ResultMessage;
import com.hutservice.application.vo.RsaKeyData;
import com.hutservice.common.entity.Dictionary;
import com.hutservice.common.entity.ImageInfo;
import com.hutservice.common.entity.Location;
import com.hutservice.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务控制器。
 *
 * @author Ewing
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private final Logger logger = LoggerFactory.getLogger(CommonController.class);

    private Cache sessionCache = CacheFactory.getSessionCache();

    private CommonService commonService;

    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    /**
     * 登陆时获取公钥。
     */
    @ResponseBody
    @RequestMapping("/rsaKey")
    public ResultMessage getRsaKey() {
        logger.info("登陆前获取RsaKey");
        try {
            RsaKeyData rsaKeyData = RSAEncryptUtil.getPublicKeyData();
            return ResultMessage.newSuccess().setData(rsaKeyData);
        } catch (Exception e) {
            logger.error("获取公钥异常：" + e.getMessage());
            return ResultMessage.newFailure("获取公钥失败！");
        }
    }

    /**
     * 登陆时获取随机盐。
     */
    @ResponseBody
    @RequestMapping("/sessionSalt")
    public ResultMessage getSessionSalt(HttpServletRequest request) {
        logger.info("getSessionSalt");
        try {
            String salt = RandomString.randomString(10);
            request.getSession().setAttribute("sessionSalt", salt);
            return ResultMessage.newSuccess().setData(new LoginMessage(salt));
        } catch (Exception e) {
            logger.error("获取随机盐异常：" + e.getMessage());
            return ResultMessage.newFailure("获取随机盐异常！");
        }
    }

    /**
     * 登陆时获取随机盐带令牌。
     */
    @ResponseBody
    @RequestMapping("/tokenSalt")
    public ResultMessage getTokenSalt(HttpServletRequest request) {
        logger.info("登陆前获取加密盐，来自IP：" + request.getRemoteAddr());
        try {
            String salt = RandomString.randomString(10);
            // 使用token记录盐，登陆时需要带上token。
            String token = RandomString.generate64UUID();
            sessionCache.put(token + "tokenSalt", salt);
            return ResultMessage.newSuccess().setData(new LoginMessage(salt, token));
        } catch (Exception e) {
            logger.error("获取随机盐异常：" + e.getMessage());
            return ResultMessage.newFailure("获取随机盐异常！");
        }
    }

    /**
     * 令牌退出登陆。
     *
     * @author Ewing
     * @date 2017.2.14
     */
    @ResponseBody
    @RequestMapping("/tokenLogout")
    public ResultMessage tokenLogout(HttpServletRequest request) {
        logger.info("公共控制器：退出登陆");
        try {
            String token = request.getHeader(AppConfig.TOKEN_KEY);
            if (StringUtils.hasText(token))
                sessionCache.remove(token);
            return ResultMessage.newSuccess();
        } catch (Exception e) {
            logger.error("令牌退出登陆异常：" + e.getMessage());
            return ResultMessage.newFailure("令牌退出登陆异常！");
        }
    }

    /**
     * Session退出登陆。
     *
     * @return ResultMessage
     * @author xuy
     * @date 2017.2.14
     */
    @ResponseBody
    @RequestMapping("/sessionLogout")
    public ResultMessage sessionLogout(HttpServletRequest request) {
        logger.info("sessionLogout：" + request.getRemoteAddr());
        try {
            request.getSession().removeAttribute(AppConfig.ADMIN_SESSION_KEY);
            return ResultMessage.newSuccess();
        } catch (Exception e) {
            logger.error("Session退出登陆异常：" + e.getMessage());
            return ResultMessage.newFailure("退出登陆异常！");
        }
    }

    /**
     * 根据字典类型获取字典值。
     */
    @ResponseBody
    @RequestMapping("/dictionary/{type}")
    public List<Dictionary> dictionary(@PathVariable("type") String type) {
        try {
            return commonService.findDictionaryByType(type);
        } catch (Exception e) {
            logger.error("获取数据字典异常：" + e.getMessage());
            return new ArrayList<>(0);
        }
    }

    /**
     * 根据上级位置获取下级位置列表。
     */
    @ResponseBody
    @RequestMapping("/location/{parent}")
    public List<Location> location(@PathVariable("parent") String parent) {
        try {
            return commonService.findLocationByParentId(parent);
        } catch (Exception e) {
            logger.error("获取地理位置数据异常：" + e.getMessage());
            return new ArrayList<>(0);
        }
    }

    /**
     * 添加图片的方法。
     */
    @ResponseBody
    @RequestMapping("/addImage")
    public ResultMessage addImage(MultipartFile imageFile) {
        try {
        	ImageInfo img = commonService.addImage(imageFile.getInputStream());
            return ResultMessage.newSuccess("添加图片成功！").setData(img);
        } catch (Exception e) {
            logger.error("添加图片异常：" + e.getMessage());
            return ResultMessage.newFailure("添加图片失败！");
        }
    }

    /**
     * 根据图片ID查询图片。
     */
    @ResponseBody
    @RequestMapping("/getImageUrl")
    public ResultMessage getImageUrl(String imageId) {
        try {
            String url = commonService.getImageUrl(imageId);
            return ResultMessage.newSuccess().setData(url);
        } catch (Exception e) {
            logger.error("查询图片异常：" + e.getMessage());
            return ResultMessage.newFailure("查询图片失败！");
        }
    }

}
