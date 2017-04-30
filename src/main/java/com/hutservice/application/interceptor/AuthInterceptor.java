//package com.hutservice.application.interceptor;
//
//import com.hutservice.application.cache.Cache;
//import com.hutservice.application.cache.CacheFactory;
//import com.hutservice.application.config.AppConfig;
//import com.hutservice.manager.entity.Admin;
//import com.hutservice.manager.entity.Authority;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//public class AuthInterceptor extends HandlerInterceptorAdapter {
//    private static final String noneFail = "{\"code\":2,\"success\":false,\"data\":\"\",\"message\":\"无效的服务地址。\"}";
//    private static final String ajaxFail = "{\"code\":2,\"success\":false,\"data\":\"\",\"message\":\"您没有足够的权限！\"}";
//    private final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
//    private final Cache sessionCache = CacheFactory.getSessionCache();
//
//    /**
//     * 在业务处理器处理请求之前被调用。
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 未定义的服务请求禁止通过。
//        if (handler instanceof DefaultServletHttpRequestHandler) {
//            logger.debug("拦截到未定义的url：" + request.getRequestURL().toString());
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/javascript;charset=utf-8");
//            response.getWriter().write(noneFail);
//            return false;
//        }
//        Authorize authMethod = ((HandlerMethod) handler).getMethodAnnotation(Authorize.class);
//        String type = AppConfig.AUTHORITY_NONE;
//        String[] authorities = {};
//        if (authMethod != null) { // 方法上的注解优先。
//            type = authMethod.type();
//            authorities = authMethod.value();
//        } else { // 方法上没有注解才考虑类上的注解。
//            Authorize authClass = ((HandlerMethod) handler).getBeanType().getAnnotation(Authorize.class);
//            if (authClass != null) {
//                type = authClass.type();
//                authorities = authClass.value();
//            }
//        }
//
//        // 用户权限拦截，基于Session。
//        if (AppConfig.AUTHORITY_USER.equals(type)) {
//            // TODO 放行
//            if (true) return true;
//            @SuppressWarnings("unused")
//			Object user = request.getSession().getAttribute(AppConfig.USER_SESSION_KEY);
//            if (user == null) {
//                dealFailResponse(response);
//                return false;
//            }
//            // 商户、服务人员权限拦截，基于Token。
//        } else if (AppConfig.AUTHORITY_MERCHANT.equals(type)
//                || AppConfig.AUTHORITY_SERVICE_PROVIDER.equals(type)) {
//            // TODO 放行
//            if (true) return true;
//            // 验证Header中的token令牌。
//            @SuppressWarnings("unused")
//			String token = request.getHeader(AppConfig.TOKEN_KEY);
//            if (token == null || sessionCache.get(token) == null) {
//                dealFailResponse(response);
//                return false;
//            }
//            // 管理员权限拦截，基于Session。
//        } else if (AppConfig.AUTHORITY_ADMIN.equals(type)) {
//            // TODO 放行
//            if (true) return true;
//            @SuppressWarnings("unused")
//			Admin manager = (Admin) request.getSession().getAttribute(AppConfig.ADMIN_SESSION_KEY);
//            if (manager == null || manager.getAuthoritys() == null
//                    || !checkAuthorities(authorities, manager.getAuthoritys())) {
//                dealFailResponse(response);
//                return false;
//            }
//        }
//
//        // 没有拦截注解或拦截类型不支持的默认不拦截。
//        return true;
//    }
//
//    /**
//     * 检查权限编码数组是否完全具有。
//     *
//     * @param authorities   需要的权限编码。
//     * @param authorityList 提供的权限对象。
//     */
//    private boolean checkAuthorities(String[] authorities, List<Authority> authorityList) throws IOException {
//        int countAuthority = 0;
//        for (String authorityCode : authorities) {
//            for (Authority manager : authorityList) {
//                if (authorityCode.equals(manager.getCode())) {
//                    countAuthority++;
//                    break; // 找到权限，本次循环结束。
//                }
//            }
//        }
//        return countAuthority == authorities.length;
//    }
//
//    /**
//     * 处理鉴权失败的请求，异步调用返回Json。
//     */
//    private void dealFailResponse(HttpServletResponse response) throws IOException {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/javascript;charset=utf-8");
//        response.getWriter().write(ajaxFail);
//    }
//
//}
//
