package com.mall.concurrcy.concurrent;

import com.mall.concurrcy.annotations.ThreadSafe;
import com.mall.concurrcy.autoincrease.ConcurrencyTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;

@ThreadSafe
public class ConcurrentSkipListMapExample {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyTest.class);

    private static int clientTotal=5000;

    private static int threadTotal=200;

    public static Map<Integer,Integer> map= new ConcurrentSkipListMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            final int count = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add(count);
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
        log.info("size:{}",map.size());
    }

    private static void add(int i){
        map.put(i,i);
    }
}
