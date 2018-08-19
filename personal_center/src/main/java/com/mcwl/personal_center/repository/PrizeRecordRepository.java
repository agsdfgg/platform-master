package com.mcwl.personal_center.repository;

import com.mcwl.personal_center.entity.Prize;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PrizeRecordRepository extends CrudRepository<Prize,Long>,JpaSpecificationExecutor<Prize>,PagingAndSortingRepository<Prize,Long> {

    // 将user_id和prize_id插入t_prize_record记录表中
    @Modifying
    @Query(value = "INSERT INTO t_prize_record (prize_id,user_id) VALUES(?1,?2)",nativeQuery = true)
    Integer updatePrizeRecord(int PrizeId, int UserId);

}
