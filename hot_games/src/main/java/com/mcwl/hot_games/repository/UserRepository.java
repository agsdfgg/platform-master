package com.mcwl.hot_games.repository;

import com.mcwl.hot_games.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author: zengdavy
 * @date: 2018/8/10
 * @description:
 */
public interface UserRepository  extends CrudRepository<User,Long>, JpaSpecificationExecutor<User>, PagingAndSortingRepository<User,Long> {
    // 再根据用户id 查询是否有对应的用户

    @Query(value = "select count(*) from t_user where id=?1 limit 1",nativeQuery = true)
    Integer checkExistsUser(int userId);
}
