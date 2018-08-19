package com.mcwl.home_page.utils;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * @author Jerry
 * @date 2018/8/10
 * 描述：
 * token生成类
 * @description
 */
public class TokenUtils {

    private static Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    /**
     *  签名密钥
     */
    public static final String SECRET = "TOM";

    /**
     * 解密
     * @param jsonWebToken
     * @param SECRET
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String SECRET) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * 前三个参数为自己用户token的一些信息比如id，权限，名称等。
     * 不要将隐私信息放入
     * @param map
     * @param SECRET
     * @return
     */
    public static String createJWT(Map<String, Object> map, String SECRET) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setPayload(new Gson().toJson(map))
                //估计是第三段密钥
                .signWith(signatureAlgorithm,SECRET.getBytes());
        //生成JWT
        return builder.compact();
    }
}