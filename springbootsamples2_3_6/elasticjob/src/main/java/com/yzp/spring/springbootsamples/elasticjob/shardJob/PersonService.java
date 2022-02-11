package com.yzp.spring.springbootsamples.elasticjob.shardJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void doSomething(Integer sex){
        logger.info("开始处理任务：sex:{}",sex==1?"男":"女");
        String sql= "select * from person where sex=? and state=0 limit 1";
        List<Person> personList = jdbcTemplate.query(sql, new Object[]{sex}, new BeanPropertyRowMapper<>(Person.class));
        logger.info("查询条数：num:{}",personList.size());
        if(!CollectionUtils.isEmpty(personList)){
            String sql2="update person set state=1 where id = ?";
            Person person = personList.get(0);
            logger.info("处理任务：name:{},id:{},age:{}",person.getName(),person.getId(),person.getAge());
            jdbcTemplate.update(sql2,new Object[]{person.getId()});
        }
    }
}
