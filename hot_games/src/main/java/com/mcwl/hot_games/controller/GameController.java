package com.mcwl.hot_games.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcwl.hot_games.dto.ResponseDto;
import com.mcwl.hot_games.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author davy
 * @date 2018/7/18
 * 描述：
 * 手游controller
 * @description
 */
@RestController

public class GameController {

    @Autowired
    GameServiceImpl gameService;
    //响应实体类
    ResponseDto responseDto=new ResponseDto();
    //查询所有游戏
    @PostMapping(value = "/game")
    public ResponseDto getAllGame(@RequestBody JSONObject params){
        int page=Integer.parseInt(params.getString("page"));
        int size=Integer.parseInt(params.getString("size"));
        responseDto.setData(gameService.findAll(page,size));
        return responseDto;
    }


}
