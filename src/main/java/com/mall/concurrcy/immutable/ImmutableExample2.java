package com.mall.concurrcy.immutable;

import com.google.common.collect.Maps;
import com.mall.concurrcy.annotations.ThreadSafe;
import com.mall.concurrcy.autoincrease.AtomicBooleanTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

@ThreadSafe
public class ImmutableExample2 {
    private static final Logger log = LoggerFactory.getLogger(AtomicBooleanTest.class);
    //一旦初始化就不能再将引用指向另外一个对象，但是可以修改引用类型的值
    private static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,3);
        log.info("{}",map.get(1));
    }
}
