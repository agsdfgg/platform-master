package com.mcwl.home_page.repository;

import com.mcwl.home_page.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jerry
 * @date 2018/7/18
 * 描述：
 * 游戏类dao接口
 * @description
 */
@Repository
public interface GameRepository extends CrudRepository<Game,Long> ,JpaSpecificationExecutor<Game>,PagingAndSortingRepository<Game,Long> {

    //查询前六条
    @Query(value = "select t.id,t.name,t.avatar,t.type,t.details,t.score,t.address,t.op from t_game t order by t.insert_time DESC limit 6",nativeQuery = true)
    public List<Game> getTopSix();

    //查询所有分页
    @Override
    public Page<Game> findAll(Pageable pageable);
}
