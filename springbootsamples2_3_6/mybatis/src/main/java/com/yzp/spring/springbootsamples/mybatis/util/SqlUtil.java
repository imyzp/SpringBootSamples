package com.yzp.spring.springbootsamples.mybatis.util;

import org.springframework.util.StringUtils;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description sql
 * @Author yaozhenpeng
 * @Time 2021/12/12 23:35
 */
public class SqlUtil {
    /**
     * sql关键字搜索过滤特殊字符
     * @param keyWord
     * @return
     */
    public static String cleanSqlKeyWord(String keyWord)
    {
        if(!StringUtils.isEmpty(keyWord)){
            keyWord=keyWord.replaceAll("/", "//").replaceAll("%", "/%").replaceAll("_", "/_");
            return keyWord;
        }
        return keyWord;
    }
}
