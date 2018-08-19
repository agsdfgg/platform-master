package com.mcwl.home_page.service;

import com.mcwl.home_page.entity.Task;
import com.mcwl.home_page.repository.TaskRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Jerry
 * @date 2018/7/18
 * 描述：
 * 首页任务service实现类
 * @description
 */
@Service
public class TaskServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskRespository taskRespository;

    public List<Task> getTaskByUserId(int userId){

        return taskRespository.findByUserId(userId);
    }

    @Transactional(rollbackFor=Exception.class)
    public Boolean reward(int userId,int taskId) {
        List<Task> list = taskRespository.findByTaskId(taskId);
        if (list.size() > 0) {
            taskRespository.updateTask(taskId);
            Date date = new Date();
            taskRespository.insertTaskRecord(userId,taskId,date);
            return true;
            } else {
            return false;
        }
    }
}
