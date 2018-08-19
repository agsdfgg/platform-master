package com.mcwl.authentication.dto;

import java.io.Serializable;

/**
 * @author Jerry
 * @date 2018/7/23
 * 描述：
 * @description
 */
public class _ResultDto implements Serializable {
    private String message = "";

    private long total = 0;

    private int pageNum = 1;

    private int pageSize = 20;

    private int pages;

    private int code = 200;

    private Object data = "{}";

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum + 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getMessage() {
        return message;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
