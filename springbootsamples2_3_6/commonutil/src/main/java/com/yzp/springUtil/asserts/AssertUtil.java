package com.yzp.springUtil.asserts;

import org.springframework.util.Assert;

public class AssertUtil {


    public static void notNull(Object obj){
        Assert.notNull(obj,"不能为空");
    }

    public static void main(String[] args) {
        notNull(null);
    }
}
