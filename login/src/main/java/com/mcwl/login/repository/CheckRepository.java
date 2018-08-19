package com.mcwl.login.repository;

import com.mcwl.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jerry
 * @date 2018/7/25
 * 描述：
 * @description
 */
public interface CheckRepository extends JpaRepository<User,String> {
    List<User> findByUsernameAndPassword(String username, String password);
}
