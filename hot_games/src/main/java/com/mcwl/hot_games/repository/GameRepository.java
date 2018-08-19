package com.mcwl.hot_games.repository;

import com.mcwl.hot_games.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Jerry
 * @date 2018/7/18
 * @description 游戏类dao接口
 */

public interface GameRepository extends CrudRepository<Game,Long> ,JpaSpecificationExecutor<Game>,PagingAndSortingRepository<Game,Long> {


    //查询所有游戏
    @Override
     Page<Game> findAll(Pageable pageable);
}
