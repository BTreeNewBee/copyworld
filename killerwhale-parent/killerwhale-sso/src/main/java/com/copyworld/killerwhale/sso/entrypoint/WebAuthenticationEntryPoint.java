/*
 * Copyright (C), 1995-2017, 没钱有限公司
 * FileName: WebAuthenticationEntryPoint
 * Author:   Neo Geng
 * Date:     2017/12/27 11:31
 * Description: 处理ExceptionTranslationFilter过滤器捕获到的访问拒绝异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.copyworld.killerwhale.sso.entrypoint;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈处理ExceptionTranslationFilter过滤器捕获到的访问拒绝异常〉
 *
 * @author Neo Geng
 * Date 2017/12/27
 * @since 1.0.0
 */
@Component
public class WebAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendRedirect("/login?error");
    }

}
