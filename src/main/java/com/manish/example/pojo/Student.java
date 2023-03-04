package com.manish.example.pojo;

import java.util.UUID;

public class Student extends Person{
    static Long initialId = 101L;
    Long id;
    public Student(String name, int age) {
        super(name, age);
    }

    public Student() {
        super();
    }

    public Student(String name) {
        this(initialId++, name);
    }

    public Student(Long id, String name) {
        this(name, 20);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
