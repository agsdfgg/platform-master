package com.mcwl.login.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mcwl.login.dto.ResponseDto;
import com.mcwl.login.entity.User;
import com.mcwl.login.repository.CheckRepository;
import com.mcwl.login.send.LoginSend;
import com.mcwl.login.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Jerry
 * @date 2018/7/25
 * 描述：
 * @description
 */
@Service
public class LoginServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    LoginSend loginSend;

    @Autowired
    CheckRepository checkRepository;

    User user = new User();

    public ResponseDto login(User user){
        ResponseDto responseDto = new ResponseDto();
        //用户密码加密后再去校验
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        List<User> list = checkRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        user = list.get(0);
        if (list.size()<1){
            //没有这个用户
            responseDto.setMessage("登陆失败，跳转去注册");
            responseDto.setCode(302);
        }else {
            //发消息去鉴权中心查询权限
            String jsonString = JSONObject.toJSONString(user);
            loginSend.send(jsonString);
            responseDto.setData(list);
        }
        return responseDto;
    }
}

