package com.hutservicewx.util;

import com.alibaba.fastjson.JSONObject;
import com.hutservice.application.cache.Cache;
import com.hutservice.application.cache.CacheFactory;
import com.hutservice.user.entity.User;
import com.hutservicewx.config.WeixinConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 这个Https协议工具类，采用HttpsURLConnection实现。
 * 提供get和post两种请求静态方法
 *
 * @author lid
 * @date 2017年2月23日
 */
public class HttpUtil {

    private static final Cache cache = CacheFactory.getAccessTokenCache();
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static TrustManager myX509TrustManager = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    public static String sendHttpsPOST(String url, String data) {
        String result = null;
        try {
            // 设置SSLContext
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{myX509TrustManager}, null);

            // 打开连接
            // 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
            URL requestUrl = new URL(url);
            HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl.openConnection();

            // 设置套接工厂
            httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

            // 加入数据
            httpsConn.setRequestMethod("POST");
            httpsConn.setDoOutput(true);
            OutputStream out = httpsConn.getOutputStream();

            if (data != null)
                out.write(data.getBytes("UTF-8"));
            out.flush();
            out.close();

            // 获取输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(httpsConn.getInputStream(), "utf-8"));
            int code = httpsConn.getResponseCode();
            if (HttpsURLConnection.HTTP_OK == code) {
                String temp = in.readLine();
                /* 连接成一个字符串 */
                while (temp != null) {
                    if (result != null)
                        result += temp;
                    else
                        result = temp;
                    temp = in.readLine();
                }
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String sendHttpsGET(String url) {
        String result = null;
        try {
            // 设置SSLContext
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{myX509TrustManager}, null);

            // 打开连接
            // 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
            URL requestUrl = new URL(url);
            HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl.openConnection();

            // 设置套接工厂
            httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

            // 加入数据
            httpsConn.setRequestMethod("GET");
            //httpsConn.setDoOutput(true);

            // 获取输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(httpsConn.getInputStream()));
            int code = httpsConn.getResponseCode();
            if (HttpsURLConnection.HTTP_OK == code) {
                String temp = in.readLine();
                /* 连接成一个字符串 */
                while (temp != null) {
                    if (result != null)
                        result += temp;
                    else
                        result = temp;
                    temp = in.readLine();
                }
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送https请求
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET,POST）
     * @param outputStr     提交的数据
     * @return Map
     * @author lidu
     * @date 2017.2.23
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            //使用指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);

            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            //从输入流读取返回结果
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 获取公众号accessToken
     *
     * @return String
     * @author lid
     * @date 2017.2.24
     */
    public static String getAccessToken() {
        String accessToken = null;
        String requestUrl = WeixinConfig.URL_ACCESSTOKEN.replace("CREDENTIAL", WeixinConfig.GRANTTYPE).replace("APPID", WeixinConfig.APPID).replace("APPSECRET", WeixinConfig.SECRET);

        logger.info("requestUrl:"+requestUrl);

        JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
        	logger.info("jsonObject:"+jsonObject.toJSONString());
            accessToken = jsonObject.getString("access_token");
        }
        return accessToken;
    }

    /**
     * 根据openid获取用户信息
     *
     * @param openid
     * @return
     */
    public static User getUserInfo(String openid) {
        logger.info("getUserInfo,openid:" + openid);
        //保存用户信息
        //先从缓存获取accessToken
        String accessToken = cache.get("accessToken");
        if (!StringUtils.hasText(accessToken)) {
            //如果缓存中没有accessToken，则重新获取并保存到缓存
            accessToken = HttpUtil.getAccessToken();
            if (null == accessToken) {
                logger.info("accessToken获取失败");
                return null;
            }
            cache.put("accessToken", accessToken);
        }
        //获取用户信息
        String requestUrl = WeixinConfig.GET_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replaceAll("OPENID", openid);
        JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "GET", null);

        if (null == jsonObject) {
            logger.info("用户信息获取失败");
            return null;
        }

        if (null != jsonObject.getString("errcode")) {
            logger.info("用户信息获取失败");
            return null;
        }

        //设置用户信息
        User user = new User();
        user.setSubscribeType(Integer.valueOf(jsonObject.getString("subscribe")));
        user.setOpenid(jsonObject.getString("openid"));
        user.setNickName(jsonObject.getString("nickname"));
        user.setGender(Integer.valueOf(jsonObject.getString("sex")));
        user.setLanguage(jsonObject.getString("language"));
        user.setCity(jsonObject.getString("city"));
        user.setProvince(jsonObject.getString("province"));
        user.setCountry(jsonObject.getString("country"));
        user.setHeadimgUrl(jsonObject.getString("headimgurl"));
        user.setSubscribeTime(jsonObject.getString("subscribe_time"));
        user.setGroupId(jsonObject.getString("groupid"));
//        user.setRemark(jsonObject.getString("remark"));
        if (null != jsonObject.getString("unionid")) {
            user.setUnionid(jsonObject.getString("unionid"));
        }
        return user;
    }

    /**
     * 根据code获取token，进而获得用户基本信息，只适用于网页版授权
     *
     * @param code     换取token值的code值
     * @param fullinfo 是否取用户完整数据的标识
     * @return user
     * @author lid
     */
    public static String getAuthedUserInfo(String code, boolean fullinfo) {
        logger.info("getAuthedUserInfo,code:" + code + ",fullinfo=" + fullinfo);
        //通过code换取网页授权access_token
        String requestUrl = WeixinConfig.WEBPAGE_TOKEN_URL.replace("APPID", WeixinConfig.APPID)
                .replace("SECRET", WeixinConfig.SECRET)
                .replace("CODE", code);
        JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "GET", null);
        logger.info("jsonObject" + jsonObject.toJSONString() );

        if ((null != jsonObject) && (null == jsonObject.getString("errcode"))) {

            //成功返回json数据包   获取 access_token 和 openid
            String access_token = jsonObject.getString("access_token");

            logger.info("access_token:"+access_token);

            String openid = jsonObject.getString("openid");

            logger.info("openid:"+openid);

            //如果没有获取到openid直接返回空
            if (StringUtils.hasText(openid)) {
                return openid;
            }
        }
        return null;
    }
}
