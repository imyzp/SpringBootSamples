package com.yzp.spring.mongodb.dao;

import com.yzp.spring.mongodb.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description
 *  dao层写法一：这里的用法其实和SpringDataJPA相似，课根据需要来自定义方法，这种写法不需要实现类
 *  MongoRepository<行对应的对象类型,主键列类型>
 * @Author yaozhenpeng
 * @Time 2022/3/29 23:22
 */
public interface StudentDao1 extends MongoRepository<Student,String> {
    // 课根据需求自己定义方法，无需对方法进行实现
    Student getAllByStudentName(String studentName);
}
