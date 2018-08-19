package com.mcwl.home_page.service;

import com.mcwl.home_page.dto.ResponseDto;
import com.mcwl.home_page.entity.Game;
import com.mcwl.home_page.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jerry
 * @date 2018/7/18
 * 描述：
 * 游戏service实现类
 * @description
 */
@Service
public class GameServiceImpl {

    public static Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private GameRepository gameRepository;
    ResponseDto responseDto = new ResponseDto();
    //查询所有游戏
    public ResponseDto findAll(int page,int size) {
        responseDto.setPage(gameRepository.findAll(new PageRequest(page-1,size)));
        return responseDto;
    }
    //查询前6条
    public List<Game> findSix(){
        List<Game> list = gameRepository.getTopSix();
        return list;
    }
}
