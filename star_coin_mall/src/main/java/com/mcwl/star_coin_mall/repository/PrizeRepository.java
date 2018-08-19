package com.mcwl.star_coin_mall.repository;

import com.mcwl.star_coin_mall.entity.Prize;
import com.mcwl.star_coin_mall.entity.PrizeRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface PrizeRepository extends CrudRepository<Prize,Long>,JpaSpecificationExecutor<Prize>,PagingAndSortingRepository<Prize,Long> {
   //查询该奖品是否存在以及它的价格
    Prize findById(int id);
    //根据PrizeId 更新对应奖品的count=count-1
    @Modifying
    @Query(value = "update t_prize set count = count-1 where id = ?1", nativeQuery = true)
    int updateCount(int PrizeId);
}
