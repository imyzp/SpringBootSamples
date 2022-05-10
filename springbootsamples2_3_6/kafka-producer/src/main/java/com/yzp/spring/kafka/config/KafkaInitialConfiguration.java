package com.yzp.spring.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 初始化topic的配置类
 * 看完docker在继续参考：https://blog.csdn.net/qq_34533957/article/details/108350369?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-0.pc_relevant_default&spm=1001.2101.3001.4242.1&utm_relevant_index=3
 * @Author yaozhenpeng
 * @Time 2022/5/5 22:52
 */
@Configuration
public class KafkaInitialConfiguration {
        // 创建一个名为testTopic的Topic并设置分区数partitions为8，分区副本数replication-factor为2
        @Bean
        public NewTopic initialTopic() {
            System.out.println("begin to init initialTopic........................");
            return new NewTopic("wtopic04",8, (short) 2 );
        }

        // 如果要修改分区数，只需修改配置值重启项目即可
        // 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
        @Bean
        public NewTopic updateTopic() {
            System.out.println("begin to init updateTopic........................");
            return new NewTopic("wtopic04",10, (short) 2 );
        }
}
