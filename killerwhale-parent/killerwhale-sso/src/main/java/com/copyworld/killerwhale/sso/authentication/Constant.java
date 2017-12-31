/*
 * Copyright (C), 1995-2017, 没钱有限公司
 * FileName: Constant
 * Author:   Neo Geng
 * Date:     2017/12/28 15:20
 * Description: 
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.copyworld.killerwhale.sso.authentication;

/**
 * 〈〉
 *
 * @author Neo Geng
 * Date 2017/12/28
 * @since 1.0.0
 */
public class Constant {
    /**
     * jwt
     *
     */
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "hong1mu2zhi3ruan4jian5";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
}
