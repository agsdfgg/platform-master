package com.mcwl.hot_games.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcwl.hot_games.dto.ResponseDto;
import com.mcwl.hot_games.service.OneGiftServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//游戏礼包领取
@RestController
public class OneGiftController {

    public static Logger logger = LoggerFactory.getLogger(OneGiftController.class);

    @Autowired
    private OneGiftServiceImpl oneGiftServiceImpl;

    //响应实体类
    ResponseDto responseDto = new ResponseDto();



    //游戏礼包领取
    @PostMapping(value = "/onegift")
    public ResponseDto getOneGift(@RequestBody JSONObject params){
        int gift_id= Integer.parseInt(params.getString("gift_id"));
        int user_id=Integer.parseInt(params.getString("user_id"));
        return  oneGiftServiceImpl.getOneGift(gift_id,user_id);


    }






}
