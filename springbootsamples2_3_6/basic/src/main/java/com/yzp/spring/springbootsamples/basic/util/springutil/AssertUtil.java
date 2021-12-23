package com.yzp.spring.springbootsamples.basic.util.springutil;

import org.springframework.util.Assert;

public class AssertUtil {


    public static void notNull(Object obj){
        Assert.notNull(obj,"不能为空");
    }

    public static void main(String[] args) {
        notNull(null);
    }
}
