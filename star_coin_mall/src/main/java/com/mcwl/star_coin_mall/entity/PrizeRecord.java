package com.mcwl.star_coin_mall.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PrizeRecord {
    @javax.persistence.Id
   @Column(name = "id")
    private int Id;
    @Column(name = "user_id")
    private Long UserId;
    @Column(name = "prize_id")
    private Long PrizeId;
    @Column(name="insert_time")
    private Date insertTime;

    public PrizeRecord() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getPrizeId() {
        return PrizeId;
    }

    public void setPrizeId(Long prizeId) {
        PrizeId = prizeId;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public PrizeRecord(Long userId, Long prizeId, Date insertTime) {

        UserId = userId;
        PrizeId = prizeId;
        this.insertTime = insertTime;
    }
}
