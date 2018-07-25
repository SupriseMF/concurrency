package com.mmall.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//声明注解需两个属性。此ThreadSafe只是一个**推荐写法**的标识
@Target(ElementType.TYPE)//给类做注解，其中target的其他值很多
@Retention(RetentionPolicy.SOURCE)//只在源码中起作用：标识。其他值详述。
public @interface Recommend {

    //通过value（）给其名字，并给出默认名称。
    String value() default "";
}
