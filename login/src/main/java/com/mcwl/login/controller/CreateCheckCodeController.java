package com.mcwl.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcwl.login.dto.ResponseDto;
import com.mcwl.login.utils.SecurityCodeCreater;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class CreateCheckCodeController {
    private static Logger logger = LoggerFactory.getLogger(CreateCheckCodeController.class);
    @Autowired
    SecurityCodeCreater securityCodeCreater;

    //生成校验码
    @RequestMapping(value = "/createCode", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ResponseDto createCheckCode( HttpServletResponse response, HttpSession session){
        ResponseDto responseDto = new ResponseDto();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JPEGImageEncoder jpegImageEncoder= JPEGCodec.createJPEGEncoder(byteArrayOutputStream);
        String randomCode=securityCodeCreater.getRandString();
        try {
            jpegImageEncoder.encode(securityCodeCreater.getImage(randomCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bts=byteArrayOutputStream.toByteArray();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("impge/jpeg");
        response.setBufferSize(2048);
        try {
            response.getOutputStream().write(bts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("code",randomCode);
        responseDto.setMessage("已生成校验码"+randomCode);
        responseDto.setData(bts);

        logger.info("已生成校验码--->"+randomCode);
        return responseDto;
    }

    //验证校验码
    @RequestMapping(value = "/checkCode", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ResponseDto checkCode(HttpSession session, @RequestBody JSONObject params){
        String fontCode=params.getString("fontCode");
        ResponseDto responseDto = new ResponseDto();
        fontCode=fontCode.toLowerCase();
        String backCode=(String) session.getAttribute("code");
        backCode=backCode.toLowerCase();
        String re;
        if (backCode.equals(fontCode)){
            re="校验码输入正确";
        }else {
            responseDto.setCode(201);
            re="校验码输入错误";
        }
        responseDto.setMessage(re);
        logger.info("验证校验码:"+re);
        return responseDto;
    }
}
