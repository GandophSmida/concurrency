package com.mall.concurrcy.commonunsafe;

import com.mall.concurrcy.annotations.NotThreadSafe;
import com.mall.concurrcy.autoincrease.ConcurrencyTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class DateFormatExample1 {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyTest.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    private static int clientTotal=5000;

    private static int threadTotal=200;

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
        executorService.shutdown();
    }

    private static void update(){
        try {
            sdf.parse("20180208");
        } catch (ParseException e) {
            log.error("parseException",e);
        }
    }
}
