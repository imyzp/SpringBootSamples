package com.yzp.spring.mongodb.dao;

import com.yzp.spring.mongodb.model.Student;

import java.util.List;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description dao层写法二 写法二需要进行实现
 * @Author yaozhenpeng
 * @Time 2022/3/29 23:43
 */
public interface StudentDao2 {
    //    增加一位学生
    void addOneStudent(Student student);

    //    根据id删除一位学生
    void deleteOneStudentByStudentId(String studentId);

    //    修改一位学生的信息
    void updateOneStudent(Student student);

    //    根据主键id获取一名学生
    Student getOneStudentByStudentId(String studentId);

    //    获取全部学生
    List<Student> getAllStudent();
}
