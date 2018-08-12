package com.mall.concurrcy.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CyclicBarrierExample2 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            final int num = 1;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race(num);
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        try {
            cyclicBarrier.await(2000,TimeUnit.MILLISECONDS);
        }catch (Exception e){
            log.warn("CyclicBarrier Exception",e);
        }
        log.info("{} continue",threadNum);
    }
}
