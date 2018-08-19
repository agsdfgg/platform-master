package com.mcwl.activity_center.repository;

import com.mcwl.activity_center.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Jerry
 * @date 2018/8/8
 * 描述：
 * @description
 */
public interface RankRepository extends CrudRepository<User,Long>,JpaSpecificationExecutor<User>,PagingAndSortingRepository<User,Long> {


    @Override
    public Page<User> findAll(Specification<User> specification, Pageable pageable);

    @Override
    public Page<User> findAll(Pageable pageable);

}
