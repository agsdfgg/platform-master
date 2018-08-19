package com.mcwl.personal_center.repository;

import com.mcwl.personal_center.entity.Prize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PrizeRepository  extends CrudRepository<Prize,Long>,JpaSpecificationExecutor<Prize>,PagingAndSortingRepository<Prize,Long> {
    //3.4我的奖品查询列表?
    @Query(value = "select * from t_prize where id =?1", nativeQuery = true)
    Page<Prize> getAllPrizById(int id, Pageable pageable) ;


    // 查询奖品详情
    @Query(value = "select * from t_prize where id=?1",nativeQuery = true)
    List getPrizeDetailById(int PrizeId);

    // 改变奖品状态
    @Modifying
    @Query(value = "update t_prize set status = 0 where id = ?1",nativeQuery = true)
    void updatePrizeStatus(int PrizeId);

    @Query(value = "select 1 from t_prize where id=?1 limit 1", nativeQuery = true)
    Integer checkPrizeExists(int PrizeId);
}
