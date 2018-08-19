package com.mcwl.personal_center.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcwl.personal_center.dto.ResponseDto;
import com.mcwl.personal_center.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    ResponseDto responseDto =  new ResponseDto();

    /**
     *@author:Davy
     *@date:2018/8/3
     *@description:积分列表兑换查询
    */
    @PostMapping(value = "/property/all",produces = "application/json")
    public ResponseDto getUserPropertyByUserId(@RequestBody JSONObject params){
        int id = Integer.parseInt(params.getString("user_id"));
        int page = Integer.parseInt(params.getString("page"));
        int size = Integer.parseInt(params.getString("size"));
        return userServiceImpl.getUserPropertyByUserId(id, page, size);
    }
    /**
     *@author:Davy
     *@date:2018/8/3
     *@description:我的兑换查询列表
    */
    @PostMapping(value = "/point_exchange/all",produces = "application/json")
    public ResponseDto getMyExchangeByUserId(@RequestBody JSONObject params){
        int id = Integer.parseInt(params.getString("user_id"));
        int page = Integer.parseInt(params.getString("page"));
        int size = Integer.parseInt(params.getString("size"));
        return userServiceImpl.getMyExchangeByUserId(id, page, size);
    }
    /**
     *@author:Davy
     *@date:2018/8/3
     *@description:我的兑换列表查询卡密
    */
    @SuppressWarnings("JavaDoc")
    @PostMapping(value = "/point_exchange/pwd",produces = "application/json")
    public ResponseDto getGiftNumber(@RequestBody JSONObject params){
        int UserId = Integer.parseInt(params.getString("UserId")) ;
        int GiftId = Integer.parseInt(params.getString("GiftId"));
        responseDto.setData(userServiceImpl.getGiftNumber(UserId, GiftId)) ;
        return responseDto;
    }
    /**
     *@author:Davy
     *@date:2018/8/3
     *@description:我的奖品查询列表
    */
    @PostMapping(value="/prize/all",produces = "application/json")
    public ResponseDto getAllPrizById(@RequestBody JSONObject params){
         int id = Integer.parseInt(params.getString("id"));
         int page = Integer.parseInt(params.getString("page"));
         int size = Integer.parseInt(params.getString("size"));
        return userServiceImpl.getAllPrizById(id,page,size);
    }
    //3.5 我的奖品立即领取
    @PostMapping(value = "/prize/details",produces = "application/json")
    public ResponseDto acceptPrize(@RequestBody JSONObject params){
            int PrizeId = Integer.parseInt(params.getString("PrizeId"));
            int UserId = Integer.parseInt(params.getString("UserId"));
            responseDto.setData( userServiceImpl.acceptPrize(PrizeId,UserId));
            return responseDto;
    }

    //修改密码
    @PostMapping(value="/info/password", produces = "application/json")
    public ResponseDto changePassword(@RequestBody JSONObject params)
    {
        String password = params.getString("password");
        int userId = Integer.parseInt(params.getString("userId"));
        String newPassword = params.getString("newPassword");
        Integer msg = userServiceImpl.changePassword(newPassword,password,userId);
       if(msg!=0){
           responseDto.setMessage("修改密码成功!");
           responseDto.setData (msg);
       }else {
           responseDto.setMessage("修改密码失败!");
           responseDto.setData (msg);
       }
       return responseDto;
    }


    //个人信息修改保存
    @PostMapping(value = "/info",produces = "application/json")
    public ResponseDto saveInfo(@RequestBody JSONObject params){
        String username = params.getString("username");
        int sex = Integer.parseInt(params.getString("sex"));
        String password = params.getString("password");
        String phone = params.getString("phone");
        String email = params.getString("email");
         responseDto.setData(userServiceImpl.saveInfo(username, DigestUtils.md5DigestAsHex(password.getBytes()),sex,phone,email));
         return responseDto;
    }
    /**
     *@author:Davy
     *@date:2018/8/3
     *@description:查询个人信息
    */
    @PostMapping(value = "/personal_info",produces = "application/json")
    public ResponseDto getUserById(@RequestBody JSONObject params){
        int id = Integer.parseInt(params.getString("id"));
        responseDto.setData(userServiceImpl.getUserById(id));
        return responseDto;
    }

    // 按月来查询任务
    @PostMapping(value = "/task/month",produces = "application/json")
    public ResponseDto getTaskByMonth(){
        responseDto.setData(userServiceImpl.getAllTaskByMonth());
        return responseDto;
    }
    // 按晶来查询任务
    @PostMapping(value = "/task/day",produces = "application/json")
    public ResponseDto getTaskByDay(){
        responseDto.setData(userServiceImpl.getAllTaskByDay());
        return responseDto;
    }

    // 查看任务详情
    @PostMapping(value = "/task/details",produces = "application/json")
    public ResponseDto getTaskDetails(@RequestBody JSONObject params){
        int UserId = Integer.parseInt(params.getString("UserId"));

        int TaskId = Integer.parseInt(params.getString("TaskId"));

        responseDto.setData(userServiceImpl.getTaskById(UserId, TaskId));
        return responseDto;
    }
}
