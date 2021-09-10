package com.lcy.base.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 用户登录token拦截器
 * @Author lcy
 * @Date 2021/5/6 15:26
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{

        //token解析
        //String accessToken = Tools.isNotEmpty(request.getHeader("token")) ? request.getHeader("token") : request.getParameter("token");
        //
        //if (StringUtils.isNotBlank(accessToken)) {
        //    //不为空
        //    Claims claims = JWTUtil.checkJWT(accessToken);
        //    //未登录
        //    if (claims == null) {
        //        CommonUtil.responseToJson(response,BizCodeEnum.ACCOUNT_UNLOGIN);
        //        return false;
        //    }
        //
        //    long userId = Long.parseLong(claims.get("id").toString());
        //    String headImg = (String)claims.get("head_img");
        //    String name = (String)claims.get("name");
        //    String mail = (String)claims.get("mail");
        //    UserToken userToken = UserToken.builder()
        //            .userId(userId)
        //            .userName(name)
        //            .userCode(mail)
        //            .build();
        //    Context.setTokenContext(userToken);
        //    return true;
        //
        //}
        //CommonUtil.responseToJson(response,BizCodeEnum.ACCOUNT_UNLOGIN);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{

    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
        //移除线程变量
        //Context.removeTokenContext();
    }
}
