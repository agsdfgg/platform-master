package com.mcwl.authentication.receive;

import com.mcwl.authentication.dao.TokenDao;
import com.mcwl.authentication.entity.DBUser;
import com.mcwl.authentication.repository.UserRepository;
import com.mcwl.authentication.send.HomepageSend;
import com.mcwl.authentication.utils.JwtToken;
import io.jsonwebtoken.Claims;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Jerry
 * @date 2018/8/10
 * @description 接收来自homepage的消息并处理
 */
@Component
@RabbitListener(queues = "mq.homepage")
public class HomepageReceive {

    public static final String SECRET = "jerryAndTom";

    public static final String TESTAUTH = "hello";

    @Autowired
    private HomepageSend homepageSend;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private UserRepository userRepository;

    @RabbitHandler
    @Async
    public void process(String message){
        System.out.println("鉴权中心准备发送消息:"+message);
        Claims claims = JwtToken.parseJWT(message,SECRET);
        String auth = (String) claims.get("auth");
        String username = (String) claims.get("username");
        long id = Integer.valueOf(claims.get("id").toString());
        //先去redis里去看是否有相应，没有再去数据库查询
        Boolean b = tokenDao.exists(id);
        // 若redis里面没有则表示过期，再去数据库查询验证，然后更新redis
        if (!b){
            //1.去数据库查询
           DBUser dbUser = userRepository.findByIdAndAndUsernameAndAuth(id,username,auth);
            if (null!=dbUser){
            //2.如果数据库有记录，更新redis记录
            tokenDao.set(id,message,24);
            }
        }
        //先去权限检查处理,然后返回消息给首页
        if (auth == TESTAUTH){

        }
        homepageSend.send(message);
    }
}
