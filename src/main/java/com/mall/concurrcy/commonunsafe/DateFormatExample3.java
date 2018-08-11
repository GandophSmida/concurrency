package com.mall.concurrcy.commonunsafe;

import com.mall.concurrcy.annotations.Recommend;
import com.mall.concurrcy.annotations.ThreadSafe;
import com.mall.concurrcy.autoincrease.ConcurrencyTest;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@ThreadSafe
@Recommend
public class DateFormatExample3 {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyTest.class);

    private static int clientTotal=5000;

    private static int threadTotal=200;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            final int count = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
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

    private static void update(int i){
        log.info("{},{}",i,DateTime.parse("20180208",dateTimeFormatter).toDate()); //推荐使用，性能更好
    }
}
