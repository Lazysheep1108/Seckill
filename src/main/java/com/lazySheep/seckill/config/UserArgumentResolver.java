package com.lazySheep.seckill.config;

import com.lazySheep.seckill.pojo.User;
import com.lazySheep.seckill.service.UserService;
import com.lazySheep.seckill.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.config
 * @date 2025/6/23 17:34
 * @project seckill
 * @description
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserService userService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        //获取参数是不是user类型
        Class<?> aClass = parameter.getParameterType();
        //如果为t, 就执行resolveArgument
        return aClass == User.class;
    }

    /**
     *  这个方法，类似拦截器，将传入的参数，取出cookie值，然后获取对应的User对象
     * * 并把这个User对象作为参数继续传递
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String ticket = CookieUtil.getCookieValue(request, "userTicket");
        if (!StringUtils.hasText(ticket)) {
            return null;
        }
        //根据cookie-ticket 到 Redis 获取 User
        return userService.getUserByCookie(ticket, request, response);
    }
}
