/*
 * Copyright (C), 1995-2017, 没钱有限公司
 * FileName: JWTUtils
 * Author:   Neo Geng
 * Date:     2017/12/28 15:21
 * Description: 
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.copyworld.killerwhale.sso.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * 〈〉
 *
 * @author Neo Geng
 * Date 2017/12/28
 * @since 1.0.0
 */
public class JwtUtils {

    private JwtUtils(){

    }

    //签名算法
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    /**
     * 生成subject信息
     *
     * @param user
     * @return
     */
    /*public static String generalSubject(t_user user) {
        JSONObject jo = new JSONObject();
        jo.put("userId", user.getId());
        jo.put("mobile", user.getMobile());
        return jo.toJSONString();
    }*/

    /**
     * 由字符串生成加密key
     *
     * @param jcaName
     * @return
     */
    public static Key generalKey(String jcaName) {
        //byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("xsd");
        String apiKeySecretBytes = Constant.JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(apiKeySecretBytes);
        return new SecretKeySpec(encodedKey, jcaName);
    }

    /**
     * 创建jwt
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public static String createJWT(String id, String subject, long ttlMillis,Map<String,Object> claims){

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Key key = generalKey(signatureAlgorithm.getJcaName());
        //生成Jwt
        JwtBuilder builder = Jwts.builder()
                //jwt-id
                .setId(id)
                //自定义属性
                .setClaims(claims)
                //签发时间
                .setIssuedAt(now)
                //主题-面向的用户
                .setSubject(subject)
                //签名算法，秘钥
                .signWith(signatureAlgorithm, key);
        //过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt){
        Key key = generalKey(signatureAlgorithm.getJcaName());
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

}
