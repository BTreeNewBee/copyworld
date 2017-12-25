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

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
        http
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/webjars/**", "/login")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
