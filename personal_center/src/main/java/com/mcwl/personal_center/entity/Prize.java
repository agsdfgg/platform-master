package com.mcwl.personal_center.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jerry
 * @date 2018/7/18
 * 描述：
 * 礼品实体类（即时商城物品）
 * @description
 */
@Entity
@Table(name = "t_prize")
public class Prize {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "details")
    private String details;


    @Column(name = "price")
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
