package com.yzp.spring.springbootsamples.elasticjob.shardJobDataFlow;

import com.yzp.spring.springbootsamples.elasticjob.shardJob.Person;
import com.yzp.spring.springbootsamples.elasticjob.shardJob.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class Person2Service {
    private static final Logger logger = LoggerFactory.getLogger(Person2Service.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void doSomething(List<Person2> personList){
        logger.info("开始处理任务：person2List:{}",personList);
        for(Person2 p: personList){
            String sql2="update person set state=1 where id = ?";
            logger.info("处理任务：name:{},id:{},age:{}",p.getName(),p.getId(),p.getAge());
            jdbcTemplate.update(sql2,new Object[]{p.getId()});
        }
    }

    public List<Person2> findPersonBySex(Integer sex, int n) {
        String sql = "select * from person where sex=? and state=0 limit ?";
        List<Person2> personList = jdbcTemplate.query(sql, new Object[]{sex, n}, new BeanPropertyRowMapper<>(Person2.class));
        logger.info("查询条数：num:{}",personList.size());
        return personList;
    }
}
