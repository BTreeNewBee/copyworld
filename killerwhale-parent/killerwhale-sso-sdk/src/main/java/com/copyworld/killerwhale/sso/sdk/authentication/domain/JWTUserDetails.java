/*
 * Copyright (C), 1995-2017, 没钱有限公司
 * FileName: JWTUserDetails
 * Author:   Neo Geng
 * Date:     2017/12/27 15:16
 * Description: JWT保存的用户信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.copyworld.killerwhale.sso.sdk.authentication.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 〈JWT保存的用户信息〉
 *
 * @author Neo Geng
 * Date 2017/12/27
 * @since 1.0.0
 */
public class JWTUserDetails implements UserDetails {

    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private Long userId;
    private String password;

    public JWTUserDetails(long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(userId, username, password, true, true, true, true, authorities);
    }

    public JWTUserDetails(long userId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        if (username != null && !"".equals(username) && password != null) {
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonLocked = accountNonLocked;
            this.authorities = authorities;
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
