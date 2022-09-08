package com.baizhi.singleton;

public class Singleton {
//    private static boolean flag = false;
    private static final Singleton INSTANCE = new Singleton();

    private static boolean flag = true;
    private Singleton()
    {
    }

    public static Singleton newInstance()
    {
        return INSTANCE;
    }

}
