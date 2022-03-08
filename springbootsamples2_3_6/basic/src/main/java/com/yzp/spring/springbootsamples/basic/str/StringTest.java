package com.yzp.spring.springbootsamples.basic.str;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description TODO
 * @Author yaozhenpeng
 * @Time 2022/3/8 16:10
 */
public class StringTest {
    public static void main(String[] args) {
        matchStringByRegularExpression("asdfddddaafdsgfdgdsg","aa");
    }
    //方法2、通过正则表达式
    private static void matchStringByRegularExpression( String parent,String child )
    {

        int count = 0;
        Pattern p = Pattern.compile( child );
        Matcher m = p.matcher(parent);
        while( m.find() )
        {
            count++;
            System.out.println( "匹配项" + count+"：" + m.group() ); //group方法返回由以前匹配操作所匹配的输入子序列。
        }
        System.out.println( "匹配个数为"+count );                              //结果输出
    }
}
