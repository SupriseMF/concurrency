package com.mmall.concurrency.publish;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    //定义可能会逸出的私有变量
    private int thisCanEscape = 0;

    //返回一内部类的引用
    public Escape () {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCanEscape);
        }
    }

    public static void main(String[] args) {
        //调用公有方法
        new Escape();
    }
}
