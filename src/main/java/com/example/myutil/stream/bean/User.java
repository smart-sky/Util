package com.example.myutil.stream.bean;

import lombok.Data;

import java.util.List;

@Data
public class User {
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
