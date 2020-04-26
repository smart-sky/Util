package com.example.myutil.Util;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Data;
public class StreamUtil {

    public static void main(String[] args) {

        // 无限流创建对象
        Stream.generate(Math::random).limit(5).forEach(System.out::print);
        List<Integer> collect = Stream.iterate(0, m -> m + 1).limit(7).collect(Collectors.toList());
        System.out.println(collect);
        // 去重流
        List<User> users = new ArrayList<>();
        users.add(new User(15,"A"));
        users.add(new User(25,"B"));
        users.add(new User(21,"C"));


        users.stream().distinct().forEach(user -> System.out.println(user.getName()));
        // 过滤流
        users.stream()
                .filter(user -> user.getAge()>15)
                .forEach(user -> System.out.println(user.getName()));
        // 排序流
        users.stream().sorted(Comparator.comparing(User::getAge))
                .forEach(user -> System.out.println(user.getName()));
        // 截断流
        users.stream()
                .sorted(Comparator.comparing(User::getAge).reversed())
                .limit(1)
                .forEach(user -> System.out.println(user.getName()));

        // 跳过流 跳过users.size()-1前面的
        users.stream()
                .sorted(Comparator.comparing(User::getAge).reversed())
                .skip(users.size()-1)
                .forEach(user -> System.out.println(user.getName()));

        //Stream<R> map(Function<? super T, ? extends R> mapper)：
        users.stream()
                .map(user -> "欢迎："+ user.getName())
                .forEach(System.out::println);

        users.add(new User(15,"A", Arrays.asList("1元","5元")));
        users.add(new User(25,"B",Arrays.asList("10元","50元")));
        users.add(new User(21,"C", Collections.singletonList("100元")));

        users.stream()
                .map(User::getMoney)
                .forEach(System.out::println);

        // 相对于map的数据分开流
        users.stream()
                .flatMap(user -> user.getMoney().stream())
                .forEach(System.out::println);

        // 手机名称到List
        List<String> nameList = users.stream().map(User::getName).collect(Collectors.toList());
        // 收集名称到Set
        Set<String> nameSet = users.stream().map(User::getName).collect(Collectors.toSet());
        // 收集到map，名字作为key,user对象作为value
        Map<String,User> userMap = users.stream()
                .collect(Collectors.toMap(User::getName, Function.identity(),(k1,k2)->k2));

       users.stream().count();

    }
}

@Data
class User {
    int age;
    String name;
    List<String> money;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public User(int age, String name, List<String> money) {
        this.age = age;
        this.name = name;
        this.money = money;
    }
}
