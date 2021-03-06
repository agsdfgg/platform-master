package com.mcwl.hot_games.service;

import com.mcwl.hot_games.dto.ResponseDto;
import com.mcwl.hot_games.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Jerry
 * @date 2018/7/18
 * 描述：
 * 游戏service实现类
 * @description
 */
@Service
public class GameServiceImpl {

    @Autowired
    private GameRepository gameRepository;

    ResponseDto responseDto = new ResponseDto();

    private static Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);
    //查询所有游戏
    public ResponseDto findAll(int page, int size) {
        responseDto.setPage(gameRepository.findAll(new PageRequest(page-1,size)));
        return responseDto;
    }



}
