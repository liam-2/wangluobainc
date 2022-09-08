package com.baizhi.study;

public class clc {

    public static void main(String[] args) {

//        虚拟机会将确定的字符串常量池化到常量池中
//        intern方法返回规则：如果字符串在常量池中存在，那么返回该字符串在常量池中的引用，否则，先放入常量池，再返回String类型对象引用
        String str1 = new StringBuilder("计算机").append("软件").toString();//新建放入堆，不放入常量池
        String str2= "计算机软件";//常量池和堆同时新建对象
        String str3 = new StringBuilder("计算机").append("软件").toString();//新建放入堆，不放入常量池
        String strl=str1.intern();//用的其实是str2的常量池对象
        String str4= new String("计算机软件");//常量池复用str2的
        String str5 = new StringBuilder("计算机软件").toString();//新建放入堆，不放入常量池
        System.out.println(str1);
//        System.out.println(str1.intern());
        System.out.println(str1==str2);
        System.out.println(str1==str3);
        System.out.println(str1==str4);
        System.out.println(str1==str5);
//        System.out.println(str1==str2);
        System.out.println(str1==strl);
        System.out.println(str2==strl);
        System.out.println(str3==strl);
        System.out.println(str4==strl);
        System.out.println(str5==strl);

//        String str2 = new StringBuilder("ja").append("va").toString();
//        System.out.println(str2);
//        System.out.println(str2.intern());

//        常量池中的 "java" 是Version类初始化时放入的，它占用了一个引用，称之为A。而自己new出来的str2 = "java" 虽然也放入了堆，但它占用了一个新的引用，称之为B。String.intern()
//        方法的用法是，判断值是否相同，如果相同，返回常量池中的那个变量的引用。所以此处返回的是常量池中的引用A，而它自然和str2的引用B是不相同的，所以返回false。
    }

}
