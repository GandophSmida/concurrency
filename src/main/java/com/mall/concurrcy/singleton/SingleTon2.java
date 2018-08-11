package com.mall.concurrcy.singleton;

import com.mall.concurrcy.annotations.ThreadSafe;

/**
 * 恶汉单利模式，在类装载时静态域直接初始化对象，线程安全；如果构造方法中存在过多的处理则会导致类加载特别慢，可能会引起性能问题
 */
@ThreadSafe
public class SingleTon2 {
    //私有构造方法
    private SingleTon2(){

    }

    //单利实例
    private static SingleTon2 instance = new SingleTon2();

    //静态工厂方法
    public static SingleTon2 getInstance(){
        return instance;
    }
}
