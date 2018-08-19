package com.mcwl.hot_games.controller;


import com.alibaba.fastjson.JSONObject;
import com.mcwl.hot_games.dto.ResponseDto;
import com.mcwl.hot_games.entity.Game;
import com.mcwl.hot_games.service.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rankings")
public class RankController {
    @Autowired
    RankServiceImpl rankService;


    //响应实体类
    ResponseDto responseDto = new ResponseDto();


    //查询所有游戏
    @PostMapping(value = "/downloads",produces = "application/json")
    public ResponseDto getTopRank(@RequestBody JSONObject params){
        int page= Integer.parseInt(params.getString("page"));
        int size=Integer.parseInt(params.getString("size"));
        responseDto.setData( rankService.findAll(page,size));
        return responseDto;
    }


    //新游期待榜查询
    @PostMapping(value = "/new_games",produces = "application/json")
    public ResponseDto getTopNewRank(@RequestBody JSONObject params){
        int page= Integer.parseInt(params.getString("page"));
        int size=Integer.parseInt(params.getString("size"));
        responseDto.setData( rankService.findNewRank(page,size));
        return responseDto;
    }

      //游戏下載排行榜
    @PostMapping(value ="/downloadsRank",produces = "application/json")
    @Scheduled(cron="0 0 0 * * ?")
    public ResponseDto getTopDownloadsRank(@RequestBody JSONObject params){
        int page= Integer.parseInt(params.getString("page"));
        int size=Integer.parseInt(params.getString("size"));
        responseDto.setData(rankService.findDownloadsRank(page,size));
        return  responseDto;
    }

    //查询单个游戏-游戏详情
    @PostMapping(value = "gameDetails",produces = "application/json")
    public ResponseDto getOneGameDetail(@RequestBody JSONObject params){
        int Id=Integer.parseInt(params.getString("id"));
        Game game =rankService.findOneGame(Id);
        responseDto.setData(game);
        return  responseDto;
    }
}
