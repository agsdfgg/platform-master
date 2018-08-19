package com.mcwl.home_page.service;

import com.mcwl.home_page.dto.ResponseDto;
import com.mcwl.home_page.repository.PrizeRespopsitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Jerry
 * @date 2018/7/18
 * 描述：
 * 礼品实现类
 * @description
 */
@Service
public class PrizeServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(PrizeServiceImpl.class);

    @Autowired
    private PrizeRespopsitory prizeRespopsitory;

    ResponseDto responseDto = new ResponseDto();

    //查询所有礼品商城物品
    public ResponseDto queryAll(int page,int size){
        responseDto.setPage(prizeRespopsitory.findAll(new PageRequest(page-1,size)));
        return responseDto;
    }
    //查询前6条数据
    public ResponseDto getSix(){
        responseDto.setData(prizeRespopsitory.getTopSix());
        return responseDto;
    }
}
