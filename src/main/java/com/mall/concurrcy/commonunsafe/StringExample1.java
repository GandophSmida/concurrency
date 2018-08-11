package com.mall.concurrcy.commonunsafe;

import com.mall.concurrcy.annotations.NotThreadSafe;
import com.mall.concurrcy.autoincrease.ConcurrencyTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class StringExample1 {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyTest.class);

    private static int clientTotal=5000;

    private static int threadTotal=200;

    public static StringBuilder stringBuilder=new StringBuilder(); //非线程安全

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("InterruptedException",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("size:{}",stringBuilder.length());
        executorService.shutdown();
    }

    private static void update(){
        stringBuilder.append(1);
    }
}
