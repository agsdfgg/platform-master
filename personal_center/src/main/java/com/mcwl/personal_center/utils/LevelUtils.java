package com.mcwl.personal_center.utils;

import com.mcwl.personal_center.dto.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jerry
 * @date 2018/8/8
 * @description 等级规则(每级升级比上级多的经验:10*(i-2)*2),判断是否需要升级
 */
public class LevelUtils {


    private static Logger logger = LoggerFactory.getLogger(LevelUtils.class);

    //一级升级经验
    private static long level_first_exp = 20;
    //等差公式累加值
    private static long level_weight = 2;
    //等差公式初始累加值
    private static long level_init = 10;
    //最高等级
    private static int maxLevel = 100;
    //最低等级
    private static int minLevel = 1;

    //大于1级时计算升级所需经验
    public long getUpExp(int level){
        long numMax = level_first_exp;
        for (int i=2;i<=level;i++){
            long num = level_init+((i-2)*level_weight);
            numMax = num+numMax;
        }
        logger.info("当前等级"+level+"级，"+"升级所需经验"+numMax);
        return numMax;
    }

    public Level isLevel(Level lv,long addExp){

        //初始化等级经验list
        List<Long> list = new ArrayList();
        list.add(1,level_first_exp);
        for (int i=minLevel+1;i<maxLevel+1;i++){
            list.add(i,getUpExp(i));
        }
        //当前等级
        int localLevel = lv.getLevel();
        //当前经验
        long localExp = lv.getExp();
        //判断可以升到那一级
        int j = localLevel;
        long overplus =localExp;
        for (j=localLevel;j<maxLevel+1;j++){
            long count = 0;
            long num = list.get(j);
            count = count +num;
            if (localExp+addExp<count){
                //经验不够，升级到此
                //剩余经验
                overplus = count - localExp - addExp;
                break;
            }
        }
        lv.setLevel(j);
        lv.setExp(overplus);
        return lv;
    }
}