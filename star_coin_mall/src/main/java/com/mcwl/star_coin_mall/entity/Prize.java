package com.mcwl.star_coin_mall.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


// 该类定义了t_prize表中的所有字段
@Entity
@Table(name = "t_prize")
public class Prize {
    @Id
    @Column(name = "id")
   private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private    Date date;
    @Column(name = "expiry_date")
     private      String expiry_date;
    @Column(name = "status")
    private    int status;
    @Column(name = "details")
    private    String details;
    @Column(name = "type")
    private     int type;
    @Column(name = "price")
    private      int price;
    @Column(name = "insert_time")
    private         Date insert_time;
    @Column(name = "update_time")
    private        Date update_time;
    @Column(name = "count")
    private        int count;
    @Column(name = "avatar")
    private       String avatar;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Prize(String name, Date date, String expiry_date, int status, String details, int type, int price, Date insert_time, Date update_time, int count, String avatar) {

        this.name = name;
        this.date = date;
        this.expiry_date = expiry_date;
        this.status = status;
        this.details = details;
        this.type = type;
        this.price = price;
        this.insert_time = insert_time;
        this.update_time = update_time;
        this.count = count;
        this.avatar = avatar;
    }

    public Prize() {

    }
}
