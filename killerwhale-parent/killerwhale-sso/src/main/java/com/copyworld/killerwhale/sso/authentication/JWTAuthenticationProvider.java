/*
 * Copyright (C), 1995-2017, 没钱有限公司
 * FileName: JWTAuthenticationProvider
 * Author:   Neo Geng
 * Date:     2017/12/28 17:01
 * Description: 
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.copyworld.killerwhale.sso.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

/**
 * 〈自定义验证组件，支持解析UsernamePasswordAuthenticationToken〉
 *
 * @author Neo Geng
 * Date 2017/12/28
 * @since 1.0.0
 */
public class JWTAuthenticationProvider implements AuthenticationProvider{

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //判断认证令牌类型
        /*Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
                messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.onlySupports",
                        "Only UsernamePasswordAuthenticationToken is supported"));*/
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        //缓存
        //UserDetails user = this.userCache.getUserFromCache(username);
        // 认证逻辑--
        if (name.equals("admin") && password.equals("123456")) {
            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new SimpleGrantedAuthority("ROLE_ADMIN") );
            authorities.add( new SimpleGrantedAuthority("AUTH_WRITE") );
            // 重新生成令牌-AbstractAuthenticationToken-private final Collection<GrantedAuthority> authorities;
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
            return auth;
        }else {
            throw new BadCredentialsException("密码错误~");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
