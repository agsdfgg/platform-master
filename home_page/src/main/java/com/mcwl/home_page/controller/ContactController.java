package com.mcwl.home_page.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcwl.home_page.anntation.Check;
import com.mcwl.home_page.dto.ResponseDto;
import com.mcwl.home_page.service.ContactService;
import com.mcwl.home_page.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Jerry
 * @date 2018/8/8
 * @description 联系我们
 */
@RestController
public class ContactController {

    ResponseDto responseDto = new ResponseDto();

    @Autowired
    HttpClientUtils httpClientUtils;

    @Autowired
    ContactService contactService;

    @Check
    @RequestMapping(value = "/contact_us",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ResponseDto ContactUs(@RequestBody JSONObject params) throws IOException, ExecutionException, InterruptedException {
        int userId = Integer.parseInt(params.getString("userId"));
        String messageBody = params.getString("messageBody");
        Future<String> future =  httpClientUtils.httpclientToMq("mq.homepage.return","localhost",15672,"/api/queues/%2F/mq.homepage.return/get");
        String message = future.get();
        responseDto = contactService.contactUs(userId,messageBody);
        responseDto.setMessage(message);
        return responseDto;
    }
}