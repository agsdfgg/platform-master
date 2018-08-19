package com.mcwl.star_coin_mall.service;


import com.mcwl.star_coin_mall.dto.ResponseDto;
import com.mcwl.star_coin_mall.entity.Prize;
import com.mcwl.star_coin_mall.entity.User;
import com.mcwl.star_coin_mall.repository.PrizeRecordRepository;
import com.mcwl.star_coin_mall.repository.CoinRepository;
import com.mcwl.star_coin_mall.repository.PrizeRepository;
import com.mcwl.star_coin_mall.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 该服务类负责对兑换的几个业务进行书写

@Service
public class CoinServiceImpl {
    @Autowired
    CoinRepository coinRepository;
    @Autowired
    PrizeRecordRepository prizeRecordRepository;
    @Autowired
    PrizeRepository prizeRepository;
    @Autowired
    UserRepository userRepository;

    ResponseDto responseDto=new ResponseDto();

    private static Logger logger= LoggerFactory.getLogger(CoinServiceImpl.class);

    // 查询兑换列表
    public ResponseDto findCoinByList(int page, int size){
        responseDto.setMessage("积分兑换列表");
        responseDto.setPage(coinRepository.getCoinByList(new PageRequest(page-1,size)));
        return responseDto;

    }

    //查询单个兑换的详情
    public ResponseDto findSingleCoin(int prizeId) {
        responseDto.setMessage("单个兑换详情");
        responseDto.setData(coinRepository.findById(prizeId));
        return responseDto;
    }

    // 进行兑换
    public ResponseDto doExchange(int  UserId,int  PrizeId) {
        logger.info("进行兑换操作");
        User uu=userRepository.findById(UserId);
        Prize prize=prizeRepository.findById(PrizeId);
        if (null!=uu&&null!= prize){

            //奖品数量是否为0
            if (prize.getCount()<=0){
                System.out.println("兑换失败");
                responseDto.setCode(201);
                responseDto.setMessage("兑换失败");
            }else {
                //用户积分大于奖品价格
                if (uu.getProperty()>=prize.getPrice()){
                    //修改奖品数量
                    int a=prizeRepository.updateCount(PrizeId);
                    //修改用户资产
                    int b=userRepository.updateProperty(prize.getPrice(),UserId);
                    //将奖品兑换记录插入PrizeRecord表
                    SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    int c=prizeRecordRepository.savePrizeRecord(UserId,PrizeId,simp.format(new Date()));
                    if (a==1&&b==1&&c==1){
                        System.out.println("兑换成功");
                        responseDto.setMessage("兑换成功");
                    }else {
                        System.out.println("兑换失败");
                        responseDto.setCode(201);
                        responseDto.setMessage("兑换失败");
                    }
                }else {
                    System.out.println("兑换失败");
                    responseDto.setCode(201);
                    responseDto.setMessage("兑换失败");
                }
            }



        }
        return responseDto;
    }
}




