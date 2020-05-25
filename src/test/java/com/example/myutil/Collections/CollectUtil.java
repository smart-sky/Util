package com.example.myutil.Collections;

import com.example.myutil.base.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectUtil extends BaseTest {

    @Test
    public void reverse() {
        // 测试reverse方法
        List<Integer> list = new ArrayList<>();

        list.add(2121);
        list.add(-1);
        list.add(21);
        list.add(0);
        list.add(45);

        //反转
        Collections.reverse(list);
        System.out.println(list);//[45, 0, 21, -1, 2121]
    }

    @Test
    public void sort() {
        // 测试sort方法
        List<Integer> list = new ArrayList<>();

        list.add(2121);
        list.add(-1);
        list.add(21);
        list.add(0);
        list.add(45);
        //按升序排序
        Collections.sort(list);
        System.out.println(list);//[-1, 0, 21, 45, 2121]
    }

    @Test
    public void test1() {
        // 测试sort方法
        List<Integer> list = new ArrayList<>();

        list.add(2121);
        list.add(-1);
        list.add(21);
        list.add(0);
        list.add(45);
        // 降序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o2 - o1);
                return o2 - o1; // 此时o2-o1是大于0的
            }
        });
        System.out.println(list);//[-1, 0, 21, 45, 2121]
    }
}
