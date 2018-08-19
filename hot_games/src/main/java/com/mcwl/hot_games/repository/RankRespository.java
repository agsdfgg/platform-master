package com.mcwl.hot_games.repository;

import com.mcwl.hot_games.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

//本类用来实现根据今日下载量来排行来查询下载排行的方法
public interface RankRespository extends CrudRepository<Game,Long>,JpaSpecificationExecutor<Game>,PagingAndSortingRepository<Game,Long> {


    //查询所有分页

    //@Query(value = "select * from t_game order by votes_number desc ",nativeQuery = true)
     Page<Game> findAll(Pageable pageable);
    //查询新游期待榜
    @Query(value = "select * from t_game order by insert_time desc  ",nativeQuery = true)
    Page<Game> findNewRank(Pageable pageable);

    //查询下载排行榜
    @Query(value = "select * from t_game order by yes_number desc ",nativeQuery = true)
    Page<Game> findDownloadsRank(Pageable Pageable);

    //通过Id查询游戏详细信息
    Game findById(int id);
}
