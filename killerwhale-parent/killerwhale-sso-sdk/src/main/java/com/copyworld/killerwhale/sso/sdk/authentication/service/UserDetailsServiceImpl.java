/*
 * Copyright (C), 1995-2017, 没钱有限公司
 * FileName: UserDetailsServiceImpl
 * Author:   Neo Geng
 * Date:     2017/12/27 15:24
 * Description: 提供认证所需的用户信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.copyworld.killerwhale.sso.sdk.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 〈提供认证所需的用户信息〉
 *
 * @author Neo Geng
 * Date 2017/12/27
 * @since 1.0.0
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    protected static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 根据用户标识获取用户
        //找SSO要凭证OR自己去找no-sql？

        /*if (user == null) {
            logger.debug("can not find user: " + username);
            throw new UsernameNotFoundException("can not find user.");
        }

        // 2. 获取用户权限*/

        /*UserDetails userDetails = new JWTUserDetails(userId, username, password,
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);*/

        return null;
    }

}
