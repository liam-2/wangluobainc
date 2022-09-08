package com.baizhi.singleton;

public enum Singleton6 {
    INSTANCE;

    private Resource instance;

    Singleton6()
    {
        instance = new Resource();
    }

    public Resource getInstance()
    {
        return instance;
    }
}

