package com.mmall.concurrency.singleton;

import com.mmall.concurrency.annotations.Recommend;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式：最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    // 私有构造函数
    private SingletonExample7() {
    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    //枚举类
    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        //通过JVM保证这个方法在被调用前初始化，并绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}