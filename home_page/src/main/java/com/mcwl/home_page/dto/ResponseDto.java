package com.mcwl.home_page.dto;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Jerry
 * @date 2018/7/23
 * 描述：
 * @description
 */
public class ResponseDto extends  _ResultDto{
    public void setPage(Page<?> page) {
        this.setPages(page.getTotalPages());
        this.setPageNum(page.getNumber());
        this.setPageSize(page.getSize());
        this.setTotal(page.getTotalElements());
        this.setData(page.getContent());
    }

    public void setData(List<Object> list, int page, int size, long total) {
        this.setData(list);
        this.setPageNum(page);
        this.setPageSize(size);
        this.setTotal(total);
    }

}
