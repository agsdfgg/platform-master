package com.mcwl.hot_games.service;


import com.mcwl.hot_games.dto.ResponseDto;
import com.mcwl.hot_games.entity.Gift;
import com.mcwl.hot_games.repository.GiftRecordRepository;
import com.mcwl.hot_games.repository.GiftRepository;
import com.mcwl.hot_games.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

// 领取礼包 service 实现类
@Service
public class OneGiftServiceImpl {
    @Autowired
    private GiftRepository giftRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GiftRecordRepository giftRecordRepository;

    private static final int ERROR_CODE = 404;

    private static final int SUCCESS_CODE = 200;

    ResponseDto responseDto = new ResponseDto();

    private static Logger logger = LoggerFactory.getLogger(OneGiftServiceImpl.class);

    //实现领取礼包的方法
    public ResponseDto getOneGift(int giftId, int userId) {
        if ((userRepository.checkExistsUser(userId) != 0) && ((giftRepository.checkExistsGift(giftId) != 0))) {
            Calendar c = Calendar.getInstance();
            Date insertTime = c.getTime();
            responseDto.setData(giftRecordRepository.updateUserProperty(giftId, userId, insertTime));

            Gift gift = giftRepository.findOne(giftId);
            if (gift != null) {
                int Number = gift.getNumber()-1;
                giftRepository.updateGiftNumber(Number, giftId);
            }
            responseDto.setMessage("更新成功!");
            responseDto.setCode(SUCCESS_CODE);
            return responseDto;

        } else {
            responseDto.setCode(ERROR_CODE);
            responseDto.setMessage("更新失败,没有对应用户或礼品!");

            return responseDto;
        }

    }
}




