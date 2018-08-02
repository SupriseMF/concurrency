package com.mmall.concurrency.publish;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class NotSafePublish {

    private String[] states = {"a", "b", "c"};

    //通过类的非私有方法，返回对象的引用
    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        NotSafePublish unsafePublish = new NotSafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        //通过上面的非法发布，修改其私有变量
        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}