package com.mcwl.hot_games.service;


import com.mcwl.hot_games.dto.ResponseDto;
import com.mcwl.hot_games.entity.Game;
import com.mcwl.hot_games.repository.RankRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//下载排行榜查询 实现类
@Service
public class RankServiceImpl {
    @Autowired
    private RankRespository rankRespository;



    ResponseDto responseDto= new ResponseDto();


    private static Logger logger= LoggerFactory.getLogger(RankServiceImpl.class);
    //查询所有游戏
    public ResponseDto findAll(int page,int size) {

        responseDto.setPage(rankRespository.findAll(new PageRequest(page-1,size,new Sort(Sort.Direction.DESC,"score"))));
        return responseDto;
    }

    //查询新游期待榜
    public ResponseDto findNewRank(int page, int size) {
        responseDto.setPage(rankRespository.findNewRank(new PageRequest(page-1,size)));
        return responseDto;
    }

    //查询下载排行榜

    public ResponseDto findDownloadsRank(int page ,int size){
        responseDto.setPage(rankRespository.findDownloadsRank(new PageRequest(page-1,size)));
        return responseDto;
    }

    //查询单条游戏详情
    public Game findOneGame(int Id){
        return rankRespository.findById(Id);
    }
}
