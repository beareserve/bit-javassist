package com.pojo;

/**
 * @author lixiangyu
 * @title: Son
 * @projectName bit-javassist
 * @description: TODO
 * @date 2021/11/915:32
 */
public class Son extends Father{

    private String name;

    public Son(int id) {
        super(id);
    }

    public Son(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
