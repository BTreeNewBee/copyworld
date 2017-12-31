/*
 * Copyright (C), 1995-2017, 没钱有限公司
 * FileName: LoginController
 * Author:   Neo Geng
 * Date:     2017/12/25 19:30
 * Description: 登录控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.copyworld.killerwhale.sso.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 〈登录控制器〉
 *
 * @author Neo Geng
 * Date 2017/12/25
 * @since 1.0.0
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String authenticate(){
        return "login";
    }

}
