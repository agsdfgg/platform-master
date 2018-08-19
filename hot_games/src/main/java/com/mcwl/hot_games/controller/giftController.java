package com.mcwl.hot_games.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcwl.hot_games.dto.ResponseDto;
import com.mcwl.hot_games.service.GiftServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GiftController {
    @Autowired
    GiftServiceImpl giftService;
    //响应实体类
    ResponseDto responseDto = new ResponseDto();
    //查询所有游戏礼包
    @PostMapping(value = "/gift")
    public ResponseDto getAllGift(@RequestBody JSONObject params){
        int page= Integer.parseInt(params.getString("page"));
        int size= Integer.parseInt(params.getString("size"));
        responseDto.setData(giftService.findAll(page,size));
        return responseDto;
    }

}
