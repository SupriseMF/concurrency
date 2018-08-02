package com.mmall.concurrency.threadlocal;

//该类存放需要绑定的信息
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    /**
     * add操作是在请求进入后端服务器，但还未进行实际处理时，调用该方法，写入相关信息
     * （通过filter：先拦截对应的URL，当前台访问该URL时，将相关信息写入ThreadLocal中；当URL实际被处理时，
     * 可直接从ThreadLocal中取出信息）。
     * @param id
     */
    public static void add(Long id) {
        //默认取出当前线程的id
        //ThreadLocal中的map的key存放当前线程id，value为id值
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    /**
     * 在接口处理完之后进行处理（通过intercepter实现）。
     */
    public static void remove() {
        //定义移除方法，防止内存泄漏。
        requestHolder.remove();
    }
}

