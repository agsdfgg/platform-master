package com.mcwl.personal_center.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_prize_record")
public class PrizeRecord {
    @Id
    @Column(name = "id")
     private int id;
    @Column(name = "prize_id")
     private int prize_id;
    @Column(name = "user_id")
     private int user_id;
    @Column(name = "insert_time")
     private Date insert_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrize_id() {
        return prize_id;
    }

    public void setPrize_id(int prize_id) {
        this.prize_id = prize_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public PrizeRecord(int id, int prize_id, int user_id, Date insert_time) {

        this.id = id;
        this.prize_id = prize_id;
        this.user_id = user_id;
        this.insert_time = insert_time;
    }

    public PrizeRecord() {

    }
}
