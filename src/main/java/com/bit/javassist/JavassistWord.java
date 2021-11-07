package com.bit.javassist;

import javassist.*;

/**
 * @param
 * @author ：by
 * @description：TODO
 * @date ：2021/11/7 16:19
 */
public class JavassistWord {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
        ClassPool pool = new ClassPool(true);
        LoaderClassPath cp = new LoaderClassPath(JavassistWord.class.getClassLoader());
        pool.insertClassPath(cp); //k1:1、插入类路径

        CtClass targetClass = pool.makeClass("com.xy.Hello");//k1:2、构建一个新的CtClass对象
        targetClass.addInterface(pool.get(IHello.class.getName()));//k1:3、实现一个接口

        /**
         * k1:4、方法构建
         */
        CtClass returnType = pool.get(void.class.getName());
        String methodName = "sayHello";
        CtClass[] parameters = new CtClass[]{pool.get(String.class.getName())};//k1:

        CtMethod method = new CtMethod(returnType, methodName, parameters, targetClass);

        String src = "{" +
                "System.out.println(\"hello \" + $1);" +
                "System.out.println(\"hello $args =\" + $args);" +
//                "System.out.println(\"hello \" + $r);" +
                "System.out.println(\"hello $type =\" + $type);" +
                "System.out.println(\"hello $class =\" + $class);" +
                "System.out.println(\"hello $0 =\" + $0);" +
                "}";
        method.setBody(src);

        targetClass.addMethod(method);

        Class cla = targetClass.toClass();
        IHello hello = (IHello) cla.newInstance();
        hello.sayHello("煞笔是你来了吗");
    }

    public interface IHello {
        void sayHello(String name);
    }
}
