package com.uncleardiff;

/**
 * @param
 * @author ：by
 * @description：TODO
 * @date ：2021/11/7 20:50
 */
public class Student {

    private String name;
    private int age;

    public Student() {
        this.name = "初始";
        this.age = 1;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Student setAge(int age) {
        this.age = age;
        return this;
    }
}
