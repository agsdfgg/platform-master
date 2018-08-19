package com.mcwl.home_page.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcwl.home_page.dto.ResponseDto;
import com.mcwl.home_page.service.PrizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jerry
 * @date 2018/7/18
 * @description积分商城controller
 */
@RestController
@RequestMapping("/prize")
public class PrizeController {
    @Autowired
    private PrizeServiceImpl prizeServiceImpl;
    @RequestMapping(value = "/all",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    private ResponseDto findAll(@RequestBody JSONObject params ){
        int page = Integer.parseInt(params.getString("page"));
        int size = Integer.parseInt(params.getString("size"));
        return prizeServiceImpl.queryAll(page, size);
    }

    @RequestMapping(value = "/six",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    private ResponseDto findSix(){
        return prizeServiceImpl.getSix();
    }
}
