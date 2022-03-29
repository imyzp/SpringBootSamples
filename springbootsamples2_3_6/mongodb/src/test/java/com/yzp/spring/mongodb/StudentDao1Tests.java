package com.yzp.spring.mongodb;

import com.yzp.spring.mongodb.dao.StudentDao1;
import com.yzp.spring.mongodb.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 测试
 * @Author yaozhenpeng
 * @Time 2022/3/29 23:28
 */
@SpringBootTest
public class StudentDao1Tests {
    @Autowired
    private StudentDao1 studentDao1;

    @Test
    void addOneStudent(){
        //        插入10行
        for (Integer count = 0; count < 10; count++) {
            Student student = new Student()
                    .setStudentId("student_"+count) //如果自己不去设置id则系统会分配给一个id
                    .setStudentName("Godfery"+count)
                    .setStudentAge(count)
                    .setStudentScore(98.5-count)
                    .setStudentBirthday(new Date());
            studentDao1.save(student);
        }
    }
    @Test
    void deleteOneStudentByStudentId(){
//        删除id为student_0的学生
        studentDao1.deleteById("student_0");
    }
    @Test
    void updateOneStudent(){
//        修改姓名为Godfery1的Student年龄为22
        Student student = studentDao1.getAllByStudentName("Godfery1");
        student.setStudentAge(22);
        studentDao1.save(student);

    }

    @Test
    void getOneStudentByStudentId(){
        System.out.println(studentDao1.findById("student_1"));
    }

    @Test
    void getAllStudent(){
        List<Student> studentList = studentDao1.findAll();
        studentList.forEach(System.out::println);
    }

}
