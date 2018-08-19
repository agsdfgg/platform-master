package com.mcwl.hot_games.repository;

import com.mcwl.hot_games.entity.Gift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

// 礼包类dao接口
@Transactional
public interface GiftRepository extends CrudRepository<Gift,Long>,JpaSpecificationExecutor<Gift>,PagingAndSortingRepository<Gift,Long>  {


    //查询所有分页
    @Override
     Page<Gift> findAll(Pageable pageable);


  // 先根据礼包id 查询是否有礼包
   @Query(value = "select 1 from t_gift where id =?1 limit 1",nativeQuery = true)
    Integer checkExistsGift(int giftId);
    // 再根据用户id 查询是否有对应的用户

    @Query(value = "select 1 from t_user where id=?1 limit 1",nativeQuery = true)
    Integer checkExistsUser(int userId);

    // 最后
    @Modifying
    @Query(value = "insert into t_gift_record (gift_id,user_id) values (?1,?2)", nativeQuery = true)
    Integer updateUserProperty(int giftId, int userId);

    @Query(value = "select * from t_gift where id =?1",nativeQuery = true)
    Gift findOne(int Id);

    @Modifying
    @Query(value = "update t_gift set number=?1 where id=?2",nativeQuery = true)
    Integer updateGiftNumber(int Number ,int Id);


}
