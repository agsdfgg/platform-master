package com.mcwl.authentication.repository;

import com.mcwl.authentication.entity.DBUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Jerry
 * @date 2018/8/13
 * @description 用户查询是否存在权限dao
 */

public interface UserRepository extends CrudRepository<DBUser,Long> {

    public DBUser findByIdAndAndUsernameAndAuth(long id,String user,String auth);
}
