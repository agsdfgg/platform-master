package com.mcwl.hot_games.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Entity
public class GiftRecord {
    @javax.persistence.Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long Id;
    @Column(name = "user_id")
    private Long UserId;
    @Column(name = "gift_id")
    private Long GiftId;
    @Column(name="insert_time")
    private Date insertTime;

    public GiftRecord() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getGiftId() {
        return GiftId;
    }

    public void setGiftId(Long giftId) {
        GiftId = giftId;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public GiftRecord(Long userId, Long giftId, Date insertTime) {

        UserId = userId;
        GiftId = giftId;
        this.insertTime = insertTime;
    }

}
