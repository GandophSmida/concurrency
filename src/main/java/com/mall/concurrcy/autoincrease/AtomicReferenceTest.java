package com.mall.concurrcy.autoincrease;

import com.mall.concurrcy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicReferenceTest {
    private static final Logger log = LoggerFactory.getLogger(AtomicReferenceTest.class);

    public static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2); //2
        count.compareAndSet(0,1); //no
        count.compareAndSet(1,3); //no
        count.compareAndSet(2,4); //4
        count.compareAndSet(3,5); //no
        log.info("count:{}",count.get());
    }
}
