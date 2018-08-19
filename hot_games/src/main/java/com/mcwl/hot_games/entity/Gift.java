package com.mcwl.hot_games.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//礼包类
//
@Entity
@Table(name = "t_gift")
public class Gift {

    @Id
    @Column(name = "id")
    private int GiftId;

    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;


    @Column(name = "icon")
    private String icon;


    @Column(name = "number")
    private int number;



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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "id=" + GiftId +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", icon='" + icon + '\'' +
                ", number=" + number +
                '}';
    }
}