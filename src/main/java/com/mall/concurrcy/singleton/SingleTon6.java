package com.mall.concurrcy.singleton;

import com.mall.concurrcy.annotations.ThreadSafe;

/**
 * 恶汉单利模式，在类装载时静态块直接初始化对象，线程安全；注意静态域和静态块的执行顺序很重要——顺序执行，故要注意书写的方式
 */
@ThreadSafe
public class SingleTon6 {
    //私有构造方法
    private SingleTon6(){

    }

    //单利实例
    private static SingleTon6 instance = null;

    static {
        instance = new SingleTon6();
    }

    //静态工厂方法
    public static SingleTon6 getInstance(){
        return instance;
    }
}
