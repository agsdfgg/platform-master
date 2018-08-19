package com.mcwl.star_coin_mall.repository;

import com.mcwl.star_coin_mall.entity.PrizeRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CoinExchangeRepository extends CrudRepository<PrizeRecord,Long>,JpaSpecificationExecutor<PrizeRecord>,PagingAndSortingRepository<PrizeRecord,Long> {
    // 进行兑换  这个SQL语句比较难

    //先查询表中是否有这个userId
    @Query( value="select count(*) from t_user  where id =?1 ", nativeQuery=true)
    Integer checkExistsUser(Long UserId);
    //根据PrizeId 更新对应奖品的status
    @Modifying
    @Query(value = "update t_prize set status = 0 where id = ?1", nativeQuery = true)
    int updateStatus(Long PrizeId);
    // 将用户名id 和奖品id 存入中间表 prize_record中
    @Modifying
    @Query(value = "insert into t_prize_record (prize_id, user_id) values (?1,?2)",nativeQuery = true)
    int savePrizeRecord(Long PrizeId, Long UserId);

}
