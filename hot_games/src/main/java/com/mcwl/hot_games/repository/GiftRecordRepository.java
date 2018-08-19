package com.mcwl.hot_games.repository;

import com.mcwl.hot_games.entity.GiftRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author: zengdavy
 * @date: 2018/8/10
 * @description:
 */
@Transactional
public interface GiftRecordRepository extends CrudRepository<GiftRecord,Long>, JpaSpecificationExecutor<GiftRecord>, PagingAndSortingRepository<GiftRecord,Long> {
    // 最后
    @Modifying
    @Query(value = "insert into t_gift_record (gift_id,user_id,insert_time) values (?1,?2,?3)", nativeQuery = true)
    Integer updateUserProperty(int giftId, int userId, Date insertTime);
}
