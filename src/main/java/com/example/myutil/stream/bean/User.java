package com.example.myutil.stream.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class User implements Comparable<User>{
    Integer age;
    String name;
    List<String> money;

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public User( String name,int age) {
        this.age = age;
        this.name = name;
    }

    public User(int age, String name, List<String> money) {
        this.age = age;
        this.name = name;
        this.money = money;
    }
    @Override
    public int compareTo(User o) {

        //首先比较年龄，如果年龄相同，则比较名字
        int flag = this.getAge().compareTo(o.getAge());
        if (flag == 0) {
            return this.getName().compareTo(o.getName());
        } else {
            return flag;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
