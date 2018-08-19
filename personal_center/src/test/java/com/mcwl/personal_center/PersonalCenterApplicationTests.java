package com.mcwl.personal_center;

import com.mcwl.personal_center.dto.Level;
import com.mcwl.personal_center.utils.LevelUtils;
import com.mcwl.personal_center.utils.TokenUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalCenterApplicationTests {

    @Test
    public void contextLoads() throws InterruptedException {
        Map map = new HashMap();
        map.put("username","Jerry");
        TokenUtils.createJWT(map,TokenUtils.SECRET);
    }
}
