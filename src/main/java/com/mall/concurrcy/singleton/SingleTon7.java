package com.mall.concurrcy.singleton;

import com.mall.concurrcy.annotations.Recommend;
import com.mall.concurrcy.annotations.ThreadSafe;

/**
 * 枚举模式：最安全的，不会造成资源的浪费
 */
@ThreadSafe
@Recommend
public class SingleTon7 {
    //私有构造方法
    private SingleTon7(){

    }

    //静态工厂方法
    public static SingleTon7 getInstance(){
        return SingleTon.INSTANCE.getSingleTon();
    }

    private enum SingleTon{
        INSTANCE;

        private SingleTon7 singleTon;

        //JVM保证这个方法只调用一次
        SingleTon(){
            singleTon = new SingleTon7();
        }

        public SingleTon7 getSingleTon(){
            return singleTon;
        }
    }
}
