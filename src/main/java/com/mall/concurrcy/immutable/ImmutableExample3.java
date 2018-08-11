package com.mall.concurrcy.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mall.concurrcy.annotations.ThreadSafe;
import com.mall.concurrcy.autoincrease.AtomicBooleanTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ThreadSafe
public class ImmutableExample3 {
    private static final Logger log = LoggerFactory.getLogger(AtomicBooleanTest.class);
    //一旦初始化就不能再将引用指向另外一个对象，但是可以修改引用类型的值
    private static ImmutableList list = ImmutableList.of(1,2,3);

    private static ImmutableSet set = ImmutableSet.copyOf(list);

    private static ImmutableMap map = ImmutableMap.of(1,2,3,4);

    private static ImmutableMap mapBuild = ImmutableMap.builder().put(1,2).put(3,4).
            put(5,6).build();

    public static void main(String[] args) {
        mapBuild.put(5,6);
    }
}
