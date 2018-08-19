package com.mcwl.star_coin_mall.repository;

import com.mcwl.star_coin_mall.entity.Prize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CoinRepository extends CrudRepository<Prize,Long>,JpaSpecificationExecutor<Prize>,PagingAndSortingRepository<Prize,Long>{
    //查询所有积分兑换列表
    @Query(value = "select * FROM t_prize t ", nativeQuery = true)
     Page<Prize> getCoinByList(Pageable pageable);

    //兑换奖品详情
    @Query(value = "select * from t_prize where id=?1", nativeQuery = true)
    Prize findById(int id);




}