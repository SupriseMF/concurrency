package com.mmall.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@ThreadSafe
public class ImmutableExample3 {

    //通过ImmutableList.of(a,b,c,xxxx)来填充数据
    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

    //copyOf(xx)方法直接拷贝其他集合中数据
    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);

    //通过builder().put(a, b)..put(x, x).(...).build()填充数据
    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder()
            .put(1, 2).put(3, 4).put(5, 6).build();

    public static void main(String[] args) {
        System.out.println(map2.get(3));
    }
}
