package com.yzp.spring.mongodb;

import com.yzp.spring.mongodb.dao.StudentDao2;
import com.yzp.spring.mongodb.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description TODO
 * @Author yaozhenpeng
 * @Time 2022/3/29 23:46
 */
@SpringBootTest
public class StudentDao2Tests {
    @Autowired
    private StudentDao2 studentDao2;
    @Test
    void addOneStudent(){
//        插入10行
        for (Integer count = 0; count < 10; count++) {
            Student student = new Student()
                    .setStudentId("study_"+count) //如果自己不去设置id则系统会分配给一个id
                    .setStudentName("Echo"+count)
                    .setStudentAge(count)
                    .setStudentScore(98.5-count)
                    .setStudentBirthday(new Date());
            studentDao2.addOneStudent(student);
        }
    }

    @Test
    void deleteOneStudentByStudentId(){
//        删除id为study_0的学生
        studentDao2.deleteOneStudentByStudentId("study_0");
    }

    @Test
    void updateOneStudent(){
//        修改id为study_1的Student年龄为21
        Student student = studentDao2.getOneStudentByStudentId("study_1");
        student.setStudentAge(21);
        studentDao2.updateOneStudent(student);

    }

    @Test
    void getOneStudentByStudentId(){
        System.out.println(studentDao2.getOneStudentByStudentId("study_1"));
    }

    @Test
    void getAllStudent(){
        List<Student> studentList = studentDao2.getAllStudent();
        studentList.forEach(System.out::println);
    }
}
