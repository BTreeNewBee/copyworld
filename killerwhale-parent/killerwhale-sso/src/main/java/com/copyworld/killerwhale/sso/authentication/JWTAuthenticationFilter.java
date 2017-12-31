/*
 * Copyright (C), 1995-2017, 没钱有限公司
 * FileName: JWTAuthenticationFilter
 * Author:   Neo Geng
 * Date:     2017/12/28 17:50
 * Description: 拦截请求，解析出JWT
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.copyworld.killerwhale.sso.authentication;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 〈拦截请求，解析出JWT〉
 *
 * @author Neo Geng
 * Date 2017/12/28
 * @since 1.0.0
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = this.buildAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    protected Authentication buildAuthentication(HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization");
        if (jwtToken == null) {
            return null;
        }
        Claims claims = JwtUtils.parseJWT(jwtToken);
        // 拿用户名
        String user = claims.getSubject();
        // 得到 权限（角色）
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
        // 返回验证令牌
        return user != null ?
                new UsernamePasswordAuthenticationToken(user, null, authorities) :
                null;
    }

}
