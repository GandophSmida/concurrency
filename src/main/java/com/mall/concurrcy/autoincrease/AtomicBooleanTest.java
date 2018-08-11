package com.mall.concurrcy.autoincrease;

import com.mall.concurrcy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@ThreadSafe
public class AtomicBooleanTest { //让某段代码只执行一次
    private static final Logger log = LoggerFactory.getLogger(AtomicBooleanTest.class);

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    private static int clientTotal=5000;

    private static int threadTotal=200;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("InterruptedException",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("isHappened:{}",isHappened.get());
        executorService.shutdown();
    }

    private static void test(){
        if(isHappened.compareAndSet(false,true)){
            log.info("excute");
        }
    }
}
