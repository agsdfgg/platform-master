package com.mcwl.star_coin_mall.controller;


import com.alibaba.fastjson.JSONObject;
import com.mcwl.star_coin_mall.dto.ResponseDto;
import com.mcwl.star_coin_mall.service.CoinServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//本类对5 积分商城中的三个方法进行了定义
@RestController
public class CoinController {
    @Autowired
    CoinServiceImpl coinServiceImpl;



    /**
     *@author:Davy
     *@date:2018/8/3
     *@description: 积分兑换列表查询
    */
    @PostMapping(value = "/property")
    public ResponseDto findCoinByList(@RequestBody JSONObject params){
        int page = Integer.parseInt(params.getString("page"));
        int size = Integer.parseInt(params.getString("size"));

        return coinServiceImpl.findCoinByList(page,size);
    }
    /**
     *@author:Davy
     *@date:2018/8/3
     *@description:兑换详情 查询   单个查询
    */
    @PostMapping(value = "/exchange_detail")
    public ResponseDto findSingCoin(@RequestBody JSONObject params){
        int prizeId=Integer.parseInt(params.getString("prizeId"));
        return coinServiceImpl.findSingleCoin(prizeId);
    }
    /**
     *@author:Davy
     *@date:2018/8/3
     *@description: 进行兑换哦
    */
    @PostMapping(value = "/exchange")
    public ResponseDto doExchange(@RequestBody JSONObject params){
        int UserId =params.getInteger("UserId");
        int PrizeId =params.getInteger("PrizeId");
        return coinServiceImpl.doExchange(UserId,PrizeId);
    }

}


