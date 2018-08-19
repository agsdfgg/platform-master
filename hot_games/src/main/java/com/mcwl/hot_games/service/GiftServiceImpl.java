package com.mcwl.hot_games.service;


import com.mcwl.hot_games.dto.ResponseDto;
import com.mcwl.hot_games.repository.GiftRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


//礼包service实现类
@Service
public class GiftServiceImpl {
    @Autowired
    private GiftRepository giftRepository;

    ResponseDto responseDto = new ResponseDto();

    private static Logger logger = LoggerFactory.getLogger(GiftServiceImpl.class);
    //查询所有礼包
    public ResponseDto findAll(int page, int size) {
        responseDto.setPage(giftRepository.findAll(new PageRequest(page-1,size)));
        return responseDto;
    }

    //查询某个礼包
    public ResponseDto findOne(int Id){
        responseDto.setData(giftRepository.findOne(Id));
        return responseDto;
    }
}
