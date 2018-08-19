package com.mcwl.home_page.repository;

import com.mcwl.home_page.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


/**
 * @author Jerry
 * @date 2018/8/8
 * 描述：
 * @description
 */
public interface ContactRepository extends JpaRepository<Contact,Long> {

    @Modifying
    @Query(value = "insert into t_contact(user_id,message_body) values (?1,?2)",nativeQuery = true)
    public int contactUs(int userId,String messageBody);
}
