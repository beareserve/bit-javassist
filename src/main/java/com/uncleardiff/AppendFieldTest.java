package com.uncleardiff;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javassist.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @param
 * @author ：by
 * @description：TODO
 * @date ：2021/11/7 20:57
 */
public class AppendFieldTest {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        StringUtil util = new StringUtil();
        util.setPhone("111");
        util.setId(1);

        Map<String, Object> newFieldMap = new HashMap<>();
        newFieldMap.put("phoneEncrypt", "111加密");

        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.get(util.getClass().getName());
            for (String fieldName : newFieldMap.keySet()) {
                String fieldType = newFieldMap.get(fieldName).getClass().getName();

                CtField ctField = new CtField(pool.get(fieldType), fieldName, ctClass);
                ctField.setModifiers(Modifier.PRIVATE);
                ctClass.addField(ctField);
            }

            Loader classLoader = new Loader(pool);
            Class<?> newClass = classLoader.loadClass(ctClass.getName());
            Object newObject = newClass.newInstance();
            BeanUtil.copyProperties(util, newObject); //赋值旧值

            for (String fieldName : newFieldMap.keySet()) {
                Field field = newClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(newObject,newFieldMap.get(fieldName));
            }

            /**
             * k1:新类添加了属性，但没有get/set方法，所以fastjson打印不出来
             * 原对象: {"id":1,"phone":"111","student":{"age":1,"name":"初始"}}
             * 添加属性：{"phoneEncrypt":"111加密"}
             * 终对象：{"id":1,"phone":"111","student":{"age":1,"name":"初始"}}
             */
            System.out.println("原对象: " + JSONObject.toJSONString(util));
            System.out.println("添加属性：" + JSON.toJSONString(newFieldMap));
            System.out.println("终对象：" + JSON.toJSONString(newObject));
        } catch (NotFoundException | CannotCompileException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
