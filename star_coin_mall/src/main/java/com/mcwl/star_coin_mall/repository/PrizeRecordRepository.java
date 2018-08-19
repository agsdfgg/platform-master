package com.mcwl.star_coin_mall.repository;

import com.mcwl.star_coin_mall.entity.Prize;
import com.mcwl.star_coin_mall.entity.PrizeRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;


@Transactional
public interface PrizeRecordRepository extends CrudRepository<PrizeRecord,Long>,JpaSpecificationExecutor<PrizeRecord>,PagingAndSortingRepository<PrizeRecord,Long> {


    // 将用户名id 和奖品id 存入中间表 prize_record中
    @Modifying
    @Query(value = "insert into t_prize_record (prize_id, user_id,insert_time) values (?1,?2,?3)",nativeQuery = true)
    int savePrizeRecord(int PrizeId, int UserId,String date);


}
