package com.mcwl.home_page.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Jerry
 * @date 2018/7/31
 * @description 常量类
 */
@Configuration
@ConfigurationProperties(prefix = "com.mcwl.homepage")
@PropertySource("classpath:enums.properties")
public class Enums {
    private int successCode;

    private int errorCode;

    private int rollbackCode;

    public int getRollbackCode() {
        return rollbackCode;
    }

    public void setRollbackCode(int rollbackCode) {
        this.rollbackCode = rollbackCode;
    }

    public int getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(int sucessCode) {
        this.successCode = sucessCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
