package com.mcwl.home_page.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Jerry
 * @date 2018/7/31
 * 描述：
 * 任务记录表
 * @description
 */
@Entity
@Table(name = "t_task_record")
public class TaskRecord {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "task_id")
    private int taskId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "insert_time")
    private Date insertTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}
