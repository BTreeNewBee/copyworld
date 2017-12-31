/*
 * Copyright (C), 1995-2017, 没钱有限公司
 * FileName: WebSecurityConfiguration
 * Author:   Neo Geng
 * Date:     2017/12/25 18:17
 * Description: WebSecurity配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.copyworld.killerwhale.sso.configure;

import com.copyworld.killerwhale.sso.authentication.JWTAuthenticationFilter;
import com.copyworld.killerwhale.sso.authentication.JWTAuthenticationProvider;
import com.copyworld.killerwhale.sso.authentication.JWTLoginFilter;
import com.copyworld.killerwhale.sso.entrypoint.WebAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * 〈WebSecurity配置〉
 *
 * @author Neo Geng
 * Date 2017/12/25
 * @since 1.0.0
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.csrf().disable()// 对请求进行认证
                .authorizeRequests()
                //允许这些页面
                .antMatchers("/webjars/**", "/login").permitAll()
                // 权限检查
                .antMatchers("/hello").hasAuthority("AUTH_WRITE")
                // 角色检查
                .antMatchers("/world").hasRole("ADMIN")
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        LogoutFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(),
                        LogoutFilter.class)
                //认证异常入口点
                .exceptionHandling().authenticationEntryPoint(new WebAuthenticationEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new JWTAuthenticationProvider());
    }
}
