package com.mcwl.home_page;

import com.mcwl.home_page.repository.GameRepository;
import com.mcwl.home_page.send.HomepageSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomePageApplicationTests {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    HomepageSend homepageSend;
    @Test
    public void contextLoads() {
        homepageSend.send("你好");
    }
}
