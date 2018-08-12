package com.mall.concurrcy.autoincrease;

import com.mall.concurrcy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicFiledUpdaterTest {

    public static AtomicIntegerFieldUpdater<AtomicFiledUpdaterTest> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicFiledUpdaterTest.class,"count");

    public volatile int count = 100; //必须是volatile非static

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        AtomicFiledUpdaterTest filedUpdaterTest = new AtomicFiledUpdaterTest();

        if(updater.compareAndSet(filedUpdaterTest,100,120)){
            log.info("update success one,count:{}",filedUpdaterTest.getCount());
        }

        if(updater.compareAndSet(filedUpdaterTest,100,120)){
            log.info("update success two,count:{}",filedUpdaterTest.getCount());
        }else{
            log.info("update failed one,count:{}",filedUpdaterTest.getCount());
        }
    }
}
