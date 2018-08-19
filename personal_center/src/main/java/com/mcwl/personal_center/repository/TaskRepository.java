package com.mcwl.personal_center.repository;

import com.mcwl.personal_center.entity.Task;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TaskRepository extends CrudRepository<Task,Long>,JpaSpecificationExecutor<Task>,PagingAndSortingRepository<Task,Long>
{




    //3.9每月任务
    @Query(value = "select * from t_task",nativeQuery = true)
   List<Task> findTaskByMonth();

    //3.10每日任务
    @Query(value = "select * from t_task", nativeQuery = true)
    List<Task> findTaskByDay();

    //3.11任务详情

/**
 *@author:Davy
 *@date:2018/8/3
 *@description: 之前写的语句是:"select count(*) from t_task where id =?1"
 * 这种方法性能上有些浪费，没必要把全部记录查出来。
 *
 * select 1 from t_task where id=?1 limit 1
 * 执行这条sql语句，所影响的行数不是0就是1。
 *
 * 特别解释下limit 1，mysql在找到一条记录后就不会往下继续找了。性能提升很多。
*/

    // 先根据礼包id 查询是否有礼包
    @Query(value = "select 1 from t_task where id =?1 limit 1",nativeQuery = true)
    Integer checkExistsTask(int taskId);
    // 再根据用户id 查询是否有对应的用户

    @Query(value = "select 1 from t_user where id=?1 limit 1",nativeQuery = true)
    Integer checkExistsUser(int userId);

    // 最后
    @Modifying
    @Query(value = "insert into t_task_record (task_id,user_id) values (?1,?2)", nativeQuery = true)
    Integer updateUserProperty(int taskId, int userId);

    @Query(value = "select * from t_task where id =?1",nativeQuery = true)
    List findTaskById(int taskId);


}
