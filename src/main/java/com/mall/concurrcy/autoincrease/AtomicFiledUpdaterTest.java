package com.mall.concurrcy.autoincrease;

import com.mall.concurrcy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicFiledUpdaterTest {
    private static final Logger log = LoggerFactory.getLogger(AtomicFiledUpdaterTest.class);

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
