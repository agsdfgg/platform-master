package com.mcwl.personal_center.repository;

import com.mcwl.personal_center.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Transactional
public interface UserRepository extends CrudRepository<User,Long>,JpaSpecificationExecutor<User>,PagingAndSortingRepository<User,Long> {

    //3.1 个人积分记录查询： 根据用户id,页码, 每页数 来查出用户, 用户名和用户资产

    //查询所有分页
    @Override
    Page<User> findAll(Pageable pageable);


    //查询用户的property
     @Query(value = "select property from t_user where id =?1",nativeQuery = true)
    Page<User> getAllById(int id, Pageable pageable);

    // 3.2我的兑换查询列表  返回的参数有所不同, 这里全部返回, 由前端自己拣选
    @Query(value = "select * from t_user where id =?1",nativeQuery = true)
    Page<User> getMyExchangeById(int id, Pageable pageable);







    // 3.3我的兑换列表查询卡密?

    //先根据userId查有没有相应的用户
    @Query(value = "select count(*) from t_user where id = ?1",nativeQuery = true)
    Integer checkUserExists(int UserId);
    //再根据giftId查对应的卡号
    @Query(value = "select card_number from t_gift where id=?1",nativeQuery = true)
    List getCardNumber(int GiftId);





    //3.5我的奖品立即领取或查看详情?



    //3.6修改密码确认
    //首先检查旧密码输入是否正确(是否与相关用户及其密码匹配上)
    // 如果为1 证明匹配成功, 如果为0 ,证明匹配失败
     @Query(value = "select  ?1 <=> (select password from t_user where id = ?2 limit 1)",nativeQuery = true)
        Integer checkOldPassword(String password, int userId);



    //然后更新对应密码及用户的密码值
     @Modifying
     @Query(value = "update t_user set password = ?1 where password =?2 and id =?3",nativeQuery = true)
     Integer updateNewPassword(String newPassword, String password, int userId);







    //3.8 个人信息查询


    @Query(value = "select * from t_user where id = ?1",nativeQuery = true)
    List getUserById(int id);

    //3.7个人信息保存
    @Modifying
    @Query(value = "insert into t_user (username, password, sex, phone, email, avatar, level, property, experience, insert_time, update_time)\n" +
            "values ( ?1, ?2, ?3, ?4, ?5,\"可爱\", 1, 0,0, '0000-00-00 00:00:00', '0000-00-00 00:00:00')",
            nativeQuery = true)
    Integer saveInfo(String username,String password,  int sex, String phone, String email);
}
