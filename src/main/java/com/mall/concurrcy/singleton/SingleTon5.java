package com.mall.concurrcy.singleton;

import com.mall.concurrcy.annotations.ThreadSafe;

/**
 * 双重同步锁单利模式
 */
@ThreadSafe
public class SingleTon5 {
    //私有构造方法
    private SingleTon5(){

    }

    //单利实例 volatile+双重检测机制 → 来禁止指令重排序
    private static volatile SingleTon5 instance = null;

    /**
     * instance = new SingleTon4()主要分三步实现：
     * 第一步：memory=allocate()  分配对象的内存空间
     * 第二步：ctorInstance()  初始化对象
     * 第三步：instance = memory  设置instance指向刚刚分配的内存
     * @return
     */

    //静态工厂方法
    public static SingleTon5 getInstance(){
        if(instance == null){ //双重检测机制
            synchronized (SingleTon5.class){ //同步锁
                if(instance == null){
                    instance = new SingleTon5();
                }
            }
        }
        return instance;
    }
}
