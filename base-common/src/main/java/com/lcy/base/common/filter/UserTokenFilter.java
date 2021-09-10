package com.lcy.base.common.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.lcy.base.common.entity.Context;
import com.lcy.base.common.entity.UserToken;
import com.lcy.base.common.util.Log;
import com.lcy.base.common.util.Tools;
import lombok.AllArgsConstructor;

/**
 * @Description 过滤器
 * @Author lcy
 * @Date 2021/5/17 14:37
 */
@WebFilter(urlPatterns = "/*", filterName = "userTokenFilter")
//@Component
@AllArgsConstructor
public class UserTokenFilter implements Filter {

    @Override public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String token = request.getHeader("token");
        UserToken userToken = UserToken.builder().build();
        if (Tools.isNotEmpty(token)) {
            try {
                //token解析
            } catch (Exception e) {
                Log.error("转换token失败，token = {}",token,e);
            }
        }
        Context.setTokenContext(userToken);

        filterChain.doFilter(servletRequest,servletResponse);

    }

}
