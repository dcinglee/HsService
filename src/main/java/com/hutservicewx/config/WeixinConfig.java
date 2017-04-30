package com.hutservicewx.config;

public final class WeixinConfig {

    // TOKEN 微信平台开发模式中设置
    public static final String TOKEN = "HsService";

    // 赋权类型
    public static final String GRANTTYPE = "client_credential";

    // 修改为开发者申请的appid
//    public static final String APPID = "wx808b264d507c5cc5";
    public static final String APPID = "wx4fa87ea708bb7d4c";

    // 修改为开发者申请的secret密钥
//    public static final String SECRET = "af917c5469c3a32a1ab38d63a74a36c5";
    public static final String SECRET = "f03082d0f09737a35256a6025fa32cc1";

    //原始ID（公众号）
//    public static final String APPNAME = "gh_a380273bfe60";
    public static final String APPNAME = "gh_e461e8fad3e8";
//
//    //安全密钥
//    public static final String KEY = "18d53930aacfd12f21870097bedb3e33";

    //消息加密密钥
    public static final String EncodingAESKey = "aCdG2lHDAVgwdx5A5TxBOxBwTeJFl2GtBpVv2CLNLm9";

    //正式环境下的域名访问
    public static final String HOST = "http://www.lidc.net.cn/HsService";

    //获取用户信息微信接口
    public static final String GET_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    // 获取tokenURL
    public static final String URL_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=CREDENTIAL&appid=APPID&secret=APPSECRET";

    // 创建菜单URL
    public static final String URL_MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create";

    // 获取菜单URL
    public static final String URL_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get";

    // 删除菜单URL
    public static final String URL_MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete";

    //授权获取用户信息
    public static final String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    //通过code换取网页授权access_token
    public static final String WEBPAGE_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    //获取用户授权后的信息
    public static final String GET_AUTHED_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
}
