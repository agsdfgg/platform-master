package com.mcwl.activity_center.service;

import com.mcwl.activity_center.dto.ResponseDto;
import com.mcwl.activity_center.entity.User;
import com.mcwl.activity_center.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Jerry
 * @date 2018/8/8
 * 描述：
 * @description
 */
@Service
public class RankService {

    @Autowired
    RankRepository rankRepository;

    ResponseDto responseDto = new ResponseDto();

    public ResponseDto rankToday(int page,int size){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date startTime = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH,1);
        Date endTime = calendar.getTime();
        SimpleDateFormat sdfmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strStart = sdfmat.format(startTime);
        String strEnd = sdfmat.format(endTime);

        Specification<User> querySpecification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("insertTime").as(String.class), strStart));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("insertTime").as(String.class), strEnd));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        responseDto.setPage(rankRepository.findAll(querySpecification,new PageRequest(page-1,size,new Sort(Sort.Direction.DESC,"property"))));

        return responseDto;
    }

    public ResponseDto rank(int page,int size){

        responseDto.setPage(rankRepository.findAll(new PageRequest(page-1,size,new Sort(Sort.Direction.DESC,"property"))));

        return responseDto;
    }

}
