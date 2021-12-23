package com.yzp.spring.springbootsamples.poi.excel.util;

import java.util.Objects;
import java.util.function.BiConsumer;

public class ForEachUtils {
    /**
     *
     * @param startIndex 开始遍历的索引
     * @param elements 集合
     * @param action
     * @param <T>
     */
    public static<T> void forEach(int startIndex, Iterable<? extends T> elements, BiConsumer<Integer,? super T> action){
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);
        if(startIndex < 0)
        {
            startIndex =0;
        }
        int index = 0;
        for(T element: elements){
            index ++;
            if(index<=startIndex){
                continue;
            }
            action.accept(index -1,element);
        }
    }
}
