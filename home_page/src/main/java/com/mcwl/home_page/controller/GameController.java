package com.mcwl.home_page.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcwl.home_page.dto.ResponseDto;
import com.mcwl.home_page.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jerry
 * @date 2018/7/18
 * @description 手游controller
 */
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameServiceImpl gameService;

    //查询所有游戏
    @RequestMapping(value = "",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ResponseDto getAllGame(@RequestBody JSONObject params){
       int page = Integer.parseInt(params.getString("page"));
       int size = Integer.parseInt(params.getString("size"));
        return gameService.findAll(page,size);
    }

    //查询前6条数据
    @RequestMapping(value = "/six",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ResponseDto getSix(){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(gameService.findSix());
        return responseDto;
    }
}
