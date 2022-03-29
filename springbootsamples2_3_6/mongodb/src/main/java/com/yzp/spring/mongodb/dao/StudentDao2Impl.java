package com.yzp.spring.mongodb.dao;

import com.yzp.spring.mongodb.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description TODO
 * @Author yaozhenpeng
 * @Time 2022/3/29 23:44
 */
@Repository
public class StudentDao2Impl implements StudentDao2{
    //    使用MongoTemplate模板类实现数据库操作
    @Autowired
    private MongoTemplate mongoTemplate;

    //    增加一位学生
    @Override
    public void addOneStudent(Student student) {
        mongoTemplate.save(student);
    }

    //    根据id删除一位学生
    @Override
    public void deleteOneStudentByStudentId(String studentId) {
        Student student = mongoTemplate.findById(studentId, Student.class);
        if(student != null){
            mongoTemplate.remove(student);
        }
    }
    //    修改一位学生的信息
    @Override
    public void updateOneStudent(Student student) {
        mongoTemplate.save(student);
    }


    //    根据主键id获取一名学生
    @Override
    public Student getOneStudentByStudentId(String studentId) {
        return mongoTemplate.findById(studentId, Student.class);
    }

    //    获取全部学生
    @Override
    public List<Student> getAllStudent() {
        return mongoTemplate.findAll(Student.class);
    }
}
