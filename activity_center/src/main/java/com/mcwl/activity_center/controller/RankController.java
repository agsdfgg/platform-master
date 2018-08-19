package com.mcwl.activity_center.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcwl.activity_center.dto.ResponseDto;
import com.mcwl.activity_center.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jerry
 * @date 2018/8/8
 * @description 排行榜controller
 */
@RestController
@RequestMapping(value = "/rank")
public class RankController {

    @Autowired
    RankService rankService;

    @RequestMapping(value = "/today",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ResponseDto todayRanking(@RequestBody JSONObject params){
        int page = Integer.parseInt(params.getString("page"));
        int size = Integer.parseInt(params.getString("size"));
        return rankService.rankToday(page,size);
    }

    @PostMapping(value = "",produces="application/json;charset=UTF-8")
    public ResponseDto ranking(@RequestBody JSONObject params){
        int page = Integer.parseInt(params.getString("page"));
        int size = Integer.parseInt(params.getString("size"));
        return rankService.rank(page,size);
    }
}
