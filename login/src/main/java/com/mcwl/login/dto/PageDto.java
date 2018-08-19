package com.mcwl.login.dto;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

/**
 * @author Jerry
 * @date 2018/7/23
 * 描述：
 * @description
 */
public class PageDto extends _RequestDto {
    private int page = 0;

    private int rows = 20;

    private String sort;

    public int getPage() {
        return page > 0 ? page - 1 : 0;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Sort getSort() {
        if (StringUtils.isEmpty(this.sort)) {
            return new Sort(Sort.Direction.DESC, "id");  // 默认id降序
        }
        String[] sorts = this.sort.split(",");
        return new Sort(sorts[1].toUpperCase().equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, sorts[0]);
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
