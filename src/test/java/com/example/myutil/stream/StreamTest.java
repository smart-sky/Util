package com.example.myutil.stream;

import com.example.myutil.base.BaseTest;
import com.example.myutil.stream.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamTest extends BaseTest {

    /**
     *   无限流创建对象
     */
    @Test
    public void iterate(){
        Stream.generate(Math::random).limit(5).forEach(System.out::print);
        List<Integer> collect = Stream.iterate(0, m -> m + 1).limit(7).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     *   去重流
     */
    @Test
    public void distinct(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A"));
        users.add(new User(25,"B"));
        users.add(new User(21,"C"));
        users.stream().distinct().forEach(user -> System.out.println(user.getName()));
    }

    /**
     *   过滤流
     */
    @Test
    public void filter(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A"));
        users.add(new User(25,"B"));
        users.add(new User(21,"C"));
        users.stream()
                .filter(user -> user.getAge()>15)
                .forEach(user -> System.out.println(user.getName()));
    }

    /**
     *   排序流
     */
    @Test
    public void sorted(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A"));
        users.add(new User(25,"B"));
        users.add(new User(21,"C"));
        users.stream().sorted(Comparator.comparing(User::getAge))
                .forEach(user -> System.out.println(user.getName()));
    }

    /**
     *   截断流
     */
    @Test
    public void limit(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A"));
        users.add(new User(25,"B"));
        users.add(new User(21,"C"));
        users.stream()
                .sorted(Comparator.comparing(User::getAge).reversed())
                .limit(1)
                .forEach(user -> System.out.println(user.getName()));
    }

    /**
     *   跳过流 跳过users.size()-1前面的
     */
    @Test
    public void skip(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A"));
        users.add(new User(25,"B"));
        users.add(new User(21,"C"));
        users.stream()
                .sorted(Comparator.comparing(User::getAge).reversed())
                .skip(users.size()-1)
                .forEach(user -> System.out.println(user.getName()));
    }

    /**
     *   遍历装饰
     */
    @Test
    public void forEach(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A"));
        users.add(new User(25,"B"));
        users.add(new User(21,"C"));
        users.stream()
                .map(user -> "欢迎："+ user.getName())
                .forEach(System.out::println);
    }

    /**
     *   获取集合流
     */
    @Test
    public void forEach1(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A", Arrays.asList("1元","5元")));
        users.add(new User(25,"B",Arrays.asList("10元","50元")));
        users.add(new User(21,"C", Collections.singletonList("100元")));
        users.stream()
                .map(User::getMoney)
                .forEach(System.out::println);
        // 结果
        //        [1元, 5元]
        //[10元, 50元]
        //[100元]

    }

    /**
     *   相对于map的数据分开流
     */
    @Test
    public void flatMap(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A", Arrays.asList("1元","5元")));
        users.add(new User(25,"B",Arrays.asList("10元","50元")));
        users.add(new User(21,"C", Collections.singletonList("100元")));
        users.stream()
                .flatMap(user -> user.getMoney().stream())
                .forEach(System.out::println);
//        1元
//        5元
//        10元
//        50元
//        100元
    }

    /**
     *   手机名称到List
     */
    @Test
    public void getList(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A", Arrays.asList("1元","5元")));
        users.add(new User(25,"B",Arrays.asList("10元","50元")));
        users.add(new User(21,"C", Collections.singletonList("100元")));
        List<String> nameList = users.stream().map(User::getName).collect(Collectors.toList());
        System.out.println(nameList);
    }

    /**
     * 收集名称到Set
     */
    @Test
    public void getSet(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A", Arrays.asList("1元","5元")));
        users.add(new User(25,"B",Arrays.asList("10元","50元")));
        users.add(new User(21,"C", Collections.singletonList("100元")));
        Set<String> nameSet = users.stream().map(User::getName).collect(Collectors.toSet());
        System.out.println(nameSet);
    }
    /**
     * 收集到map，名字作为key,user对象作为value
     */
    @Test
    public void getMap(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A", Arrays.asList("1元","5元")));
        users.add(new User(25,"B",Arrays.asList("10元","50元")));
        users.add(new User(21,"C", Collections.singletonList("100元")));
        Map<String,User> userMap = users.stream()
                .collect(Collectors.toMap(User::getName, Function.identity(),(k1, k2)->k2));
        System.out.println(userMap);
    }

    /**
     * 获取数量
     */
    @Test
    public void count(){
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A", Arrays.asList("1元","5元")));
        users.add(new User(25,"B",Arrays.asList("10元","50元")));
        users.add(new User(21,"C", Collections.singletonList("100元")));

        System.out.println(users.stream().count());
    }

}
