package com.mcwl.personal_center.service;

import com.mcwl.personal_center.dto.ResponseDto;
import com.mcwl.personal_center.repository.PrizeRecordRepository;
import com.mcwl.personal_center.repository.PrizeRepository;
import com.mcwl.personal_center.repository.TaskRepository;
import com.mcwl.personal_center.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;


//个人中心 服务类
@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PrizeRepository prizeRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
     private PrizeRecordRepository prizeRecordRepository;
    private static   Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    ResponseDto responseDto = new ResponseDto();

    //3.1 个人积分记录查询： 根据用户id,页码, 每页数 来查出用户, 用户名和用户资产


    public ResponseDto getUserPropertyByUserId(int id, int page, int size) {
        responseDto.setPage(userRepository.getAllById(id, new PageRequest(page - 1, size)));
        return responseDto;
    }

    //3.2我的兑换查询列表

    public ResponseDto getMyExchangeByUserId(int id, int page, int size) {
        responseDto.setPage(userRepository.getMyExchangeById(id, new PageRequest(page - 1, size)));
        return responseDto;
    }

    //3.3 我的兑换列表查询卡密

    public List getGiftNumber(int UserId, int GiftId) {
        if (userRepository.checkUserExists(UserId) != 0) {
            return userRepository.getCardNumber(GiftId);
        }
        return null;
    }


    //3.4 我的奖品列表

    public ResponseDto getAllPrizById(int id, int page, int size) {
        responseDto.setPage(prizeRepository.getAllPrizById(id, new PageRequest(page - 1, size)));
        return responseDto;
    }


    //3.6修改密码确认

    public Integer changePassword(String newPassword,String password, int userId) {
        if (userRepository.checkOldPassword(DigestUtils.md5DigestAsHex(password.getBytes()), userId) !=0) {
            logger.info("修改id为"+userId+"密码");
            return userRepository.updateNewPassword(DigestUtils.md5DigestAsHex( newPassword.getBytes()),DigestUtils.md5DigestAsHex(password.getBytes()), userId);
        }
        return 0;

    }


    // 3.8 个人信息查询

    public List getUserById(int id) {
        return userRepository.getUserById(id);
    }

    //3.9
    public List getAllTaskByMonth() {
        return taskRepository.findTaskByMonth();
    }

    // 3.10
    public List getAllTaskByDay() {
        return taskRepository.findTaskByDay();
    }

    //3.11
    public List getTaskById(int UserId, int TaskId) {
        if ((taskRepository.checkExistsUser(UserId) != 0) && ((taskRepository.checkExistsTask(TaskId) != 0))) {
            taskRepository.updateUserProperty(TaskId, UserId);
            return taskRepository.findTaskById(TaskId);
        }
        return null;
    }

    //保存用户信息
    public Integer saveInfo(String username, String password, int sex, String phone, String email) {
        logger.info("保存用户名为"+username+"的信息");
        return userRepository.saveInfo(username, password,sex, phone, email);
    }

    // 用户领取 并查看用户奖品
    public List acceptPrize(int PrizeId,int UserId) {

         // 插入record表中
        // 领取 插记录  记录表  改状态
        if(userRepository.checkUserExists(UserId)!=0 && prizeRepository.checkPrizeExists(PrizeId)!=0){
            // 更新prizeRecord记录
        prizeRecordRepository.updatePrizeRecord(PrizeId, UserId);
        // 更新prize的状态
        prizeRepository.updatePrizeStatus(PrizeId);
        // 通过prizeId 查询所有prize
       return  prizeRepository.getPrizeDetailById(PrizeId);}
       return null;

    }



}