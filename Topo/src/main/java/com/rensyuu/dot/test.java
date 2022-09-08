package com.rensyuu.dot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {


    /**
     * @description:1017完数判断
     * @create by: linyuan
     * @create time: 2022/9/7 14:41
     */
    static List<Integer> getWanshuList(int n){
        List wanshuList=new ArrayList();
        if(n<=5){
            return wanshuList;
        }else{
            //先计算约数
            for(int i=6;i<n;i++){
                List<Integer> yueshu=new ArrayList<>();
                for(int j=1;j<=i/2;j++){
                    if(i%j==0){
                        yueshu.add(j);
                    }
                }
                if(yueshu.stream().reduce(Integer::sum).orElse(0)==i){
                    wanshuList.add(i);
                }
            }
            return wanshuList;
        }
    }

    /**
     * @description: 1018数列求和
     * @create by: linyuan
     * @create time: 2022/9/7 15:20
     */
    static double getShuListSum(int n) {
        if (n <= 0) {
            return 0;
        }else if(n==1){
            return 2;
        }  else{
            double sum = 2.0;
            double a=1.0;
            double b=2.0;
            for (int i = 2; i <= n; i++) {
                //按位与运算判断奇偶，0为偶
                if((i&1)==0){
                    sum+=(a+=b)/b;
                }else{
                    sum+=(b+=a)/a;
                }
            }
            return sum;
        }
    }

    /**
     * @description: 1019 自由落体距离
     * @create by: linyuan
     * @create time: 2022/9/7 16:23
     */
    static double getGHeight(double m, int n) {
        double sum = 0;
        sum = m;
        while (--n>0){
            m = m / 2;
            sum = sum + 2 * m;
        }
//        sum=m*(3-2*Math.pow(0.5,n-1));
        return sum;
    }

    /**
     * @description: 1020 桃子总数
     * @create by: linyuan
     * @create time: 2022/9/7 16:53
     */
    static long getMomoSum(int n){
        long a=1;
        while(--n>0){
            a=2*(a+1);
        }
        return a;
    }

    /**
     * @description: 1021 迭代求平方根
     * @create by: linyuan
     * @create time: 2022/9/8 13:51
     */
    static double getSqureRoot(double m,int n){
        double squreRoot=0.0;

        return squreRoot;
    }


    public static void main(String[] args) {
        System.out.println("请输入");
        Scanner input=new Scanner(System.in);
        int m=input.nextInt();
        int n=input.nextInt();
        long startTime_N=System.currentTimeMillis();
        System.out.println(getSqureRoot(m,n));
        long endTime_N=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime_N-startTime_N)+"ms");

    }
}
