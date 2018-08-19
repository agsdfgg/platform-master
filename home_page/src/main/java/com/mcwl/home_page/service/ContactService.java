package com.mcwl.home_page.service;

import com.mcwl.home_page.dto.ResponseDto;
import com.mcwl.home_page.entity.Contact;
import com.mcwl.home_page.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jerry
 * @date 2018/8/8
 * @description
 */
@Service
public class ContactService {

    private static Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    ContactRepository contactRepository;

    ResponseDto responseDto = new ResponseDto();

    @Transactional
    public ResponseDto contactUs(int userId,String messageBody){

       int flag =  contactRepository.contactUs(userId,messageBody);

       if (flag==0){
           responseDto.setCode(201);
           responseDto.setMessage("用户id"+userId+"消息发送失败");
           logger.info("用户id"+userId+"消息发送失败,内容是"+messageBody);
       }else{
           responseDto.setCode(200);
           responseDto.setMessage("用户id"+userId+"消息发送成功");
           logger.info("用户id"+userId+"消息发送成功,内容是"+messageBody);
       }
       return responseDto;
    }
}
