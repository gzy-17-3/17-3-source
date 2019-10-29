package com.company;

import java.awt.image.ImageProducer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Main {

//    Json


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // write your code here
        // 反射
        Map<String, Object> map = new HashMap<>();

        map.put("name", "lxf1");
        map.put("pwd", "123456");
        map.put("age", 18);

//        Account account = new Account();
//
//        account.setAge((Integer) map.get("age"));
//        account.setAge((Integer) map.get("age"));
//        account.setAge((Integer) map.get("age"));

        try {
            Account transf = transf(Account.class, map);
            System.out.println(transf.getAge());
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }


    static <T> T transf(Class<T> tClass, Map<String, Object> map) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        T res = null;

        Constructor<T> constructor;

        constructor = tClass.getConstructor();

        res = constructor.newInstance();

        Field[] fields = tClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            field.set(res, map.get(field.getName()));
        }

        return res;
    }


    // 获取方法列表
    void getmethod() {
        Account account = new Account();

        Class<?> aClass = account.getClass();

        Method[] declaredMethods = aClass.getDeclaredMethods();

//        Method setName = aClass.getDeclaredMethod("setName", String.class);
//
//        setName.invoke(account, "lxf1");
//
//        Method getName = aClass.getDeclaredMethod("getName");
//        Object invoke = getName.invoke(account);

//        System.out.println(invoke);
    }

    void getFieldList() {
        Account account = new Account();

        Class<?> aClass = Account.class;
//        Field[] fields = aClass.getFields();
        Field[] fields = aClass.getDeclaredFields();

        // 获取字段列表

        for (Field field : fields) {
            System.out.println("" + field.getName());
        }
        try {
            Field name = aClass.getDeclaredField("name");
            Field pwd = aClass.getDeclaredField("pwd");

            name.setAccessible(true);
            pwd.setAccessible(true);

            name.set(account, "lxf");
            pwd.set(account, "123456");

            System.out.println("");

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return;
        }

    }
}
