package com;

import com.pojo.Father;
import com.pojo.Son;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lixiangyu
 * @title: TestService
 * @projectName bit-javassist
 * @description: TODO
 * @date 2021/11/915:31
 */
public class TestService {

    public Father getIdentity() {
        return new Son(1,"儿子");
    }


    public static void main(String[] args) {
        Map<String, String> a = new HashMap();
        a.put("a", "a");
        System.out.println(handler(a));
    }

    public static Object handler(Object obj) {
        Map<String, String> map = (Map<String, String>) obj;
        map.put("zhuijia", "zhuijia");
        return obj;
    }
}
