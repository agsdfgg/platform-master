package com.mcwl.login.service.impl;

import com.mcwl.login.entity.User;
import com.mcwl.login.repository.UserRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author Jerry
 * @date 2018/7/18
 * 描述：
 * 用户登陆相关service实现类
 * @description
 */
@Service
public class UserServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRespository userRespository;


    public User register(String username,String phonenumber) {
        User user = new User();
        user.setUsername(username);
        user.setPhoneNumber(phonenumber);
        user.setPassword(crypto("123456"));
        userRespository.save(user);
        logger.info("=============用户" + user.toString() + "注册=============");
        return user;
    }

    private String crypto(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
