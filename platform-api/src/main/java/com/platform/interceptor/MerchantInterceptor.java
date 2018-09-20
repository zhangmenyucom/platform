package com.platform.interceptor;

import javassist.util.proxy.MethodHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 解析商户
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-23 15:38
 */
@Component
public class MerchantInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURL().toString();
        if (url.indexOf("api") != -1) {
            String temp = url.substring(url.indexOf("api") + 4, url.length());
            Long merchantId = Long.valueOf(temp.substring(0, temp.indexOf("/")));
            request.setAttribute("merchantId", merchantId);
        }
        return true;
    }
}
