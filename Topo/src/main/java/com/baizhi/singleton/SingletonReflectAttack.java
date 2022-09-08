package com.baizhi.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonReflectAttack {
    /**
     * 单例模式被java反射攻击
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */

    public static void attack() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException
    {

//        Class<?> classType = Singleton.class;
//        Constructor<?> constructor = classType.getDeclaredConstructor(null);
//        constructor.setAccessible(true);
//        Singleton singleton = (Singleton) constructor.newInstance();
//        Singleton singleton2 = Singleton.newInstance();
//        System.out.println(singleton == singleton2);  //false

//        Class<?> classType=Singleton.class;
//        Constructor<?> constructor=classType.getDeclaredConstructor(null);
//        //取消java的权限控制检查
//        constructor.setAccessible(true);
//        Singleton singleton=(Singleton) constructor.newInstance();
//        Singleton singleton2 = Singleton.newInstance();
//        System.out.println(singleton == singleton2);  //false

//            Class<SingletonNotAttackByReflect> classType = SingletonNotAttackByReflect.class;
//            Constructor<SingletonNotAttackByReflect> constructor = classType.getDeclaredConstructor(null);
//            constructor.setAccessible(true);
//            SingletonNotAttackByReflect singleton = (SingletonNotAttackByReflect) constructor.newInstance();
//            SingletonNotAttackByReflect singleton2 = SingletonNotAttackByReflect.getInstance();
//
//            System.out.println(singleton == singleton2);

        Class<Singleton6> classType = Singleton6.class;
                    Constructor<Singleton6> constructor =(Constructor<Singleton6>) classType.getDeclaredConstructor();
                   constructor.setAccessible(true);
                     constructor.newInstance();




    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("-------------单例模式被java反射攻击测试--------------");
        SingletonReflectAttack.attack();
        System.out.println("--------------------------------------------------");
    }
}
