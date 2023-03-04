package com.manish.example.pojo;

import java.util.ArrayList;
import java.util.List;

public class Person {
    String name;
    int age;
    List<Person> friends;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();
    }

    public Person() {
        this("", 0);
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }
}
