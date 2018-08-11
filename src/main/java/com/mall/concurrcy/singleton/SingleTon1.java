package com.mall.concurrcy.singleton;

import com.mall.concurrcy.annotations.NotThreadSafe;

/**
 * 懒汉单利模式，在调用对象时才实例化对象，非线程安全
 */
@NotThreadSafe
public class SingleTon1 {
    //私有构造方法
    private SingleTon1(){

    }

    //单利实例
    private static SingleTon1 instance = null;

    //静态工厂方法
    public static SingleTon1 getInstance(){
        if(instance == null){
            instance = new SingleTon1();
        }
        return instance;
    }
}
