package com.yzp.spring.springbootsamples2_3_6.redis.controller;

import com.yzp.spring.springbootsamples2_3_6.redis.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class redisController {
    //操作的是复杂类型，User
    @Autowired
    RedisTemplate redisTemplate;

    //针对的都是操作字符串
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //自定义的json序列化器
    @Autowired
    RedisTemplate jsonRedisTemplate;


    /**
     * 五大数据类型
     *      stringRedisTemplate.opsForValue();//操作字符串
     *      stringRedisTemplate.opsForList()；//操作List
     *      stringRedisTemplate.opsForSet();//操作Set
     *      stringRedisTemplate.opsForZSet();//操作ZSet
     *      stringRedisTemplate.opsForHash();//操作Hash
     */
    @GetMapping("test")
    public void test()
    {
        try {
            System.out.println("dkjfs");
            stringRedisTemplate.opsForValue().set("name","yzp");
            String name=stringRedisTemplate.opsForValue().get("name");
            System.out.println(name);//yzp

            //stringRedisTemplate.opsForList().leftPush("myList","a");
            //stringRedisTemplate.opsForList().leftPushAll("myList","b","c");
            List<String> myList=stringRedisTemplate.opsForList().range("myList",0,-1);
            System.out.println(myList);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @GetMapping("test1")
    public void test1() throws Exception
    {
        //当我们导入了redis的启动器之后，springboot会采用redis作为默认缓存
        Users users=new Users();
        //保存的数据对象必须序列化  implements Serializable
        //因为redisTemplate默认采用的是jdk序列化器

        //redisTemplate.opsForValue().set("user",users);
        Users users1=(Users)redisTemplate.opsForValue().get("user");
        System.out.println(users1);
        jsonRedisTemplate.opsForValue().set("user2",users);
    }
}
