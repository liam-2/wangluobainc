package com.baizhi.singleton;

import javax.management.RuntimeErrorException;

public class SingletonNotAttackByReflect
{
    private static boolean flag = false;
    private static final SingletonNotAttackByReflect INSTANCE = new SingletonNotAttackByReflect();

    //保证其不被java反射攻击
    private SingletonNotAttackByReflect()
    {
        synchronized (SingletonNotAttackByReflect.class)
        {
            if(false == flag)
            {
                flag = !flag;
            }
            else
            {
                throw new RuntimeException("单例模式正在被攻击");
            }

        }
    }

    public static SingletonNotAttackByReflect getInstance()
    {
        return INSTANCE;
    }


}
