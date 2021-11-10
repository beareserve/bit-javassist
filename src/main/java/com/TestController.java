package com;

import com.alibaba.fastjson.JSON;
import com.pojo.Father;

/**
 * @author lixiangyu
 * @title: TestControllor
 * @projectName bit-javassist
 * @description: TODO
 * @date 2021/11/915:29
 */
public class TestController {

    public static void main(String[] args) {
        TestService service = new TestService();
        Father father = service.getIdentity();
        System.out.println(JSON.toJSONString(father));
    }
}
