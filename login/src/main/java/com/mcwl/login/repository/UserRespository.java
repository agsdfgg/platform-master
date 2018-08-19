package com.mcwl.login.repository;

import com.mcwl.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRespository extends JpaRepository<User,String> {

    List<User> findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

}
