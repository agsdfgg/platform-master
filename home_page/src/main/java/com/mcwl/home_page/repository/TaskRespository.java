package com.mcwl.home_page.repository;

import com.mcwl.home_page.entity.Task;
import com.mcwl.home_page.entity.TaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author Jerry
 * @date 2018/7/18
 * 描述：
 * 首页任务dao接口
 * @description
 */
public interface TaskRespository extends JpaRepository<Task,Long> {

    public List<Task> findByUserId(int userId);

    @Query(value = "select * from t_task t where t.id = ?1 limit 1",nativeQuery = true)
    public List<Task> findByTaskId(int taskId);

    //更新任务表状态
    @Modifying
    @Query(value = "update t_task set t_task.status = 1 where t_task.id = ?1",nativeQuery = true)
    public int updateTask(int taskId);

    //插入任务领取记录
    @Modifying
    @Query(value = "insert into t_task_record(task_id,user_id,insert_time) values (?1,?2,?3)",nativeQuery = true)
    public int insertTaskRecord(int taskId, int userId, Date time);

}
