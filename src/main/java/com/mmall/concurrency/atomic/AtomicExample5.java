package com.mmall.concurrency.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

//为输出方便，加log注解
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    //指明更新的类为AtomicExample5
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            //newUpdater()的第一个参数为该指明的类的class，第二参数为作用的字段区域的名字filedName（该字段必须被volatile标识声明且不能被static声明）
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    //添加get方法
    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        //创建该类的实例
        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 1, {}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 2, {}", example5.getCount());
        } else {
            log.info("update failed, {}", example5.getCount());
        }
    }
}
