package com.mall.concurrcy.syncconttiner;

import com.mall.concurrcy.annotations.ThreadSafe;
import com.mall.concurrcy.autoincrease.ConcurrencyTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@ThreadSafe
public class HashTableExample {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyTest.class);

    private static int clientTotal=5000;

    private static int threadTotal=200;

    public static Map<Integer,Integer> hashtable= new Hashtable<>();

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
        log.info("size:{}",hashtable.size());
    }

    private static void add(int i){
        hashtable.put(i,i);
    }
}
