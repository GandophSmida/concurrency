package com.mall.concurrcy.syncconttiner;

import java.util.Iterator;
import java.util.Vector;

/**
 * 但我们在对同步容器进行遍历时，尽量避免对容器元素进行增删操作，如果非要操作建议使用简单for循环来处(或者对要删除的元素进行标记，
 * 变量完成后统一删除)，这样可以避免ConcurrentModificationException(容器初始容量和遍历时容量不等)
 */
public class VectorExample2 {
    // java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1) { // foreach
        for(Integer i : v1) {
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    // java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v1) { // iterator
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    // success
    private static void test3(Vector<Integer> v1) { // for
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)) {
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }

}
