package com.hutservice.application.util;

import com.hutservice.application.config.AppConfig;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信访问类。
 *
 * @author Ewing
 */
public class MicroAccessor {
    private static Logger logger = LoggerFactory.getLogger(MicroAccessor.class);

    private MicroAccessor() {
    }

    /**
     * 发送 GET 请求（HTTP）带参数。
     */
    public static String httpGet(String url, Map<String, Object> params) {
        String apiUrl = url;
        String result = null;
        StringBuilder param = new StringBuilder();
        try {
            boolean first = true;
            for (String key : params.keySet()) {
                if (first && !apiUrl.contains("?")) {
                    param.append("?");
                    first = false;
                } else {
                    param.append("&");
                }
                param.append(key).append("=").append(params.get(key));
            }
            apiUrl += param.toString();
            HttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, "UTF-8");
            }
        } catch (Exception e) {
            logger.error("Http访问异常：" + e.getMessage());
        }
        return result;
    }

    /**
     * 授权码登陆到微信。
     */
    public static MicroResult authToMicro(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("appid", AppConfig.WEIXIN_APPID);
        params.put("secret", AppConfig.WEIXIN_SECRET);
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        String result = httpGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);
        return JSonUtils.readValue(result, MicroResult.class);
    }

    /**
     * 获取微信用户信息。
     */
    public static MicroResult getMicroUserInfo(String accessToken, String openid) {
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("openid", openid);
        String result = httpGet("https://api.weixin.qq.com/sns/userinfo", params);
        return JSonUtils.readValue(result, MicroResult.class);
    }

}
