package com.uncleardiff;

import javassist.*;

/**
 * @param
 * @author ：by
 * @description：TODO
 * @date ：2021/11/7 17:10
 */
public class StringMonitor {

    public static void main(String[] args) throws NotFoundException, CannotCompileException {
        ClassPool pool = new ClassPool(true);
        String targetClassName = "com.uncleardiff.StringUtil";
        CtClass targetClass = pool.get(targetClassName);

        CtMethod ctMethod = targetClass.getDeclaredMethod("addString");
        CtMethod agentMethod = CtNewMethod.copy(ctMethod, ctMethod.getName() + "$agent", targetClass, null);
        targetClass.addMethod(agentMethod);

        String src = "{" +
                "long begin = System.nanoTime();" +
                "String result = " + ctMethod.getName() + "$agent($$);" +
                "long end = System.nanoTime();" +
                "System.out.println(end - begin);" +
                "return ($r)result;" +
                "}";
        ctMethod.setBody(src);
        Class newClass = targetClass.toClass();
        StringUtil stringUtil = new StringUtil();
        stringUtil.addString(1000); //这里调用方法是第一次加载StringUtil类，所以会有上面添加的代码逻辑
//        targetClass.

    }



}
