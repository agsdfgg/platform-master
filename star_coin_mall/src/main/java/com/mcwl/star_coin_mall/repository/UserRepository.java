package com.mcwl.star_coin_mall.repository;

import com.mcwl.star_coin_mall.entity.Prize;
import com.mcwl.star_coin_mall.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User,Long>,JpaSpecificationExecutor<User>,PagingAndSortingRepository<User,Long> {
   //查询该用户是否存在以及它的资产
    User findById(int id);
    //根据奖品价格更新该用户的资产property=property-price
    @Modifying
    @Query(value = "update t_user set property = property-?1 where id = ?2", nativeQuery = true)
    int updateProperty(int price,int userId);
}
