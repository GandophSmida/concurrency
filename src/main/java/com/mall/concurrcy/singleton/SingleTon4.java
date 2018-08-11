package com.mall.concurrcy.singleton;

import com.mall.concurrcy.annotations.NotThreadSafe;

/**
 * 双重同步锁单利模式
 */
@NotThreadSafe
public class SingleTon4 {
    //私有构造方法
    private SingleTon4(){

    }

    //单利实例
    private static SingleTon4 instance = null;

    /**
     * instance = new SingleTon4()主要分三步实现：
     * 第一步：memory=allocate()  分配对象的内存空间
     * 第二步：ctorInstance()  初始化对象
     * 第三步：instance = memory  设置instance指向刚刚分配的内存
     * 多线程情况下：JVM和CPU优化发生了指令重排（第二步和第三步顺序交换）
     * @return
     */

    //静态工厂方法
    public static SingleTon4 getInstance(){
        if(instance == null){ //双重检测机制
            synchronized (SingleTon4.class){ //同步锁
                if(instance == null){
                    instance = new SingleTon4();
                }
            }
        }
        return instance;
    }
}
