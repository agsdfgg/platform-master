package com.mcwl.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcwl.login.dto.ResponseDto;
import com.mcwl.login.entity.User;
import com.mcwl.login.service.impl.LoginServiceImpl;
import com.mcwl.login.service.impl.UserServiceImpl;
import com.mcwl.login.utils.HttpClientUtils;
import com.mcwl.login.utils.SecurityCodeCreater;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Jerry
 * @date 2018/7/17
 * @description controller类
 */
@RestController
@RequestMapping
public class UserController{
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private LoginServiceImpl loginServiceImpl;
    @Autowired
    private UserServiceImpl userService;


    @RequestMapping(value = "/register", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResponseDto register(@RequestBody JSONObject params){
        String username = params.getString("username");
        String phonenumber = params.getString("phonenumber");
        userService.register(username,phonenumber);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("注册成功");
        return responseDto;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResponseDto login(@RequestBody JSONObject params) throws IOException, InterruptedException, ExecutionException {
        String username = params.getString("username");
        String password = params.getString("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        loginServiceImpl.login(user);
        ResponseDto responseDto = new ResponseDto();
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        Future<String> future = httpClientUtils.httpclientToMq("mq.login.return","localhost",15672,"/api/queues/%2F/mq.login.return/get");
        String message = future.get();
        responseDto.setData(message);
        logger.info("收到的消息"+message);
        return responseDto;
    }


}