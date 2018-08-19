package com.mcwl.home_page.repository;

import com.mcwl.home_page.entity.Prize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

/**
 * @author Jerry
 * @date 2018/7/18
 * @description dao接口
 */
public interface PrizeRespopsitory extends CrudRepository<Prize,Long> ,JpaSpecificationExecutor<Prize>,PagingAndSortingRepository<Prize,Long>{

    //查询前6条
    @Query(value = "select t.id,t.name,t.details,t.price,t.count,t.avatar,t.date,t.expiry_date,t.status, t.type from t_prize t order by t.insert_time DESC limit 6",nativeQuery = true)
    public List<Prize> getTopSix();

    //查询所有分页
    public Page<Prize> findAll(Pageable pageable);
}
