package com.mcwl.authentication;

import com.mcwl.authentication.utils.JwtToken;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationApplicationTests {
    @Test
    public void contextLoads() {

        Map map = new HashMap();
        map.put("id",1);
        map.put("username","Jerry");
        map.put("password","Jerry");
        String encodeStr = JwtToken.createJWT(map,"Tom");
        System.out.println(encodeStr);
        Claims decodeStr = JwtToken.parseJWT(encodeStr,"Tom");
        Object str1 = decodeStr.get("id");
        Object str2 = decodeStr.get("username");
        Object str3 = decodeStr.get("password");
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
    }
}
