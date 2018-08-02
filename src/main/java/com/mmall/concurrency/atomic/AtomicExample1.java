package com.mmall.concurrency.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

//为输出方便，加log注解
@Slf4j
@ThreadSafe
public class AtomicExample1 {

    //请求总数量
    public static int clientTotal = 5000;
    //并发数量
    public static int threadTotal = 200;
    //用来计数
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量,参数为并发数量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //参数为请求总数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                //核心方法执行前后需获取/释放信号量
                //由于使用信号量可能会因执行中断而产生异常
                //只有当semaphore.acquire()执行完（根据目前的并发数判断该线程是否允许被执行，否则会被阻塞）有返回效果，线程才会执行其核心方法
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    log.error("中断异常InterruptedException:", e);
                }
                //执行闭锁，进行请求总数减一
                countDownLatch.countDown();
            });
        }
        //调用countDownLatch.await()方法保证目前已经**减为零**。
        try {
            countDownLatch.await();
            //闭锁执行完毕，关闭线程池
            executorService.shutdown();
            log.info("计数count:{}",count.get());
        } catch (InterruptedException e) {
//            e.printStackTrace();
            log.error("计数产生中断异常InterruptedException:", e);
        }
    }

    //计数方法
    private static void add() {
        count.incrementAndGet();
    }
}
