package com.example.myutil.Collections;

import com.example.myutil.base.BaseTest;
import com.example.myutil.stream.bean.User;
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
    public void reSort() {
        // 测试sort方法
        List<Integer> list = new ArrayList<>();

        list.add(2121);
        list.add(-1);
        list.add(21);
        list.add(0);
        list.add(45);
        // 降序
        list.sort((o1, o2) -> {
            System.out.println(o2 - o1);
            return o2 - o1; // 此时o2-o1是大于0的
        });
        System.out.println(list);//[-1, 0, 21, 45, 2121]
    }

    // 自定义对象的排序，比如对User对象按照年龄排序,再按照name排序
    @Test
    public void testSort() {
        User f1 = new User("tony", 19);
        User f2 = new User("jack", 16);
        User f3 = new User("tom", 80);
        User f4 = new User("jbson", 44);
        User f5 = new User("jason", 44);
        User f6 = new User("tom", 12);

        List<User> list = new ArrayList<User>();
        list.add(f1);
        list.add(f3);
        list.add(f4);
        list.add(f2);
        list.add(f5);
        list.add(f6);
        Collections.sort(list);

        for (User o : list) {
            System.out.println(o.getAge() + "-->" + o.getName());
        }
    }
}
