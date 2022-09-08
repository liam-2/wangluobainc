package com.baizhi.study;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * 去重效率测试
 */
public class Depulication {

    public static void  printTime(String str,long t1,long t2){
        double t3=((double)(t2-t1))/1000000;
        System.out.println(t3);
        System.out.println(str+"时间花了"+t3+"毫秒");
    }

    public static double  getTime(long t1,long t2){
        double t3=((double)(t2-t1))/1000000;
        return t3;
    }

    public static void getDepulicationTime(ArrayList<Integer> list){
        ArrayList<Integer> numbersList=new ArrayList<>(list);
        ArrayList<Double> timeList=new ArrayList<Double>();
        //使用LinkedHashSet删除arraylist中的重复数据
        long stime = System.nanoTime();
        ArrayList<Integer> setToList=new ArrayList<>(new LinkedHashSet(numbersList));
        long etime = System.nanoTime();
        timeList.add(getTime(stime,etime));

//        System.out.println(setToList);
//        printTime("setToList",stime,etime);


        //使用java8新特性stream进行List去重
        long stime1 = System.nanoTime();
        List<Integer> java8MethodList=numbersList.stream().distinct().collect(Collectors.toList());
        long etime1 = System.nanoTime();
        timeList.add(getTime(stime1,etime1));
//        System.out.println(java8MethodList);
//        printTime("java8MethodList",stime1,etime1);


        //利用HashSet不能添加重复数据的特性 由于HashSet不能保证添加顺序，
        long stime2 = System.nanoTime();
        HashSet<Integer> set = new HashSet<Integer>(numbersList.size());
        List<Integer> result = new ArrayList<Integer>(numbersList.size());
        for (Integer curr : numbersList) {
            if (set.add(curr)) {
                result.add(curr);
            }
        }
        long etime2 = System.nanoTime();
        timeList.add(getTime(stime2,etime2));
//        System.out.println(result);
//        printTime("result",stime2,etime2);

        //利用List的contains方法循环遍历,重新排序,只添加一次数据,避免重复
        long stime3 = System.nanoTime();
        List<Integer> result2 = new ArrayList<Integer>(numbersList.size());
        for (Integer curr : numbersList) {
            if (!result2.contains(curr)) {
                result2.add(curr);
            }
        }
        long etime3 = System.nanoTime();
        timeList.add(getTime(stime3,etime3));
//        System.out.println(result);
//        printTime("result2",stime3,etime3);


//        System.out.println(numbersList);

        //双重for循环去重,i必须做判断，当最后一个元素有重复元素会导致越界
        long stime4 = System.nanoTime();
        try{
            for (int i = 0; i < numbersList.size(); i++) {
                for (int j = 0; j < numbersList.size(); j++) {
                    if(i!=j&&numbersList.get(i>=numbersList.size()?numbersList.size()-1:i).equals(numbersList.get(j))) {
                        numbersList.remove(numbersList.get(j));
                    }
                }
            }
        }catch(Exception e){
            System.out.println("出错的list");
            System.out.println(list);
        }
        long etime4 = System.nanoTime();
        timeList.add(getTime(stime4,etime4));
        System.out.println("去重时间");
        System.out.println(timeList);
//        System.out.println(result);
//        printTime("numbersList双重for循环去重",stime4,etime4);
    }



    public static void main(String[] args)  {


        ArrayList<Integer> numbersList=new ArrayList<>();
        for(int i=0;i<1000;i++){
            numbersList.add((int)(Math.random()*(30)));
            System.out.println("第"+(i+1)+"次");
            getDepulicationTime(numbersList);
        }
//        ArrayList<Integer> numbersList=new ArrayList<>(Arrays.asList(25, 28, 12, 14, 23, 17, 23, 0, 10, 0, 7, 6, 20, 17, 18, 26, 13, 7, 13, 7));
//
//        for (int i = 0; i < numbersList.size(); i++) {
//            for (int j = 0; j < numbersList.size(); j++) {
//                if(i!=j&&numbersList.get(i>=numbersList.size()?numbersList.size()-1:i).equals(numbersList.get(j))) {
//                    numbersList.remove(numbersList.get(j));
//                }
//            }
//        }

    }

}
