package com.mall.concurrcy.singleton;

import com.mall.concurrcy.annotations.NotRecommend;
import com.mall.concurrcy.annotations.ThreadSafe;

/**
 * synchronized加锁来保证同步，会带来性能开销，故不推荐用来实现单利模式
 */
@ThreadSafe
@NotRecommend
public class SingleTon3 {
    //私有构造方法
    private SingleTon3(){

    }

    //单利实例
    private static SingleTon3 instance = null;

    //静态工厂方法
    public static synchronized SingleTon3 getInstance(){
        if(instance == null){
            instance = new SingleTon3();
        }
        return instance;
    }
}
