package com.rensyuu.dot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class test {

    /**
     * @description: 保留几位小数
     * @create by: linyuan
     * @create time: 2022/9/8 15:16
     */
    static double formatDouble(double value,int length){
        BigDecimal bd=new BigDecimal(value);
        bd=bd.setScale(length, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

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
     * @description: 1021 迭代求平方根 n初始为0，o初始为 1 公式  X[n+1]=(X[n]+a/X[n])/2 要求前后两次求出的差的绝对值少于0.00001
     * @create by: linyuan
     * @create time: 2022/9/8 13:51
     */
    static double getSqureRoot(double m){
        return getSqureRoot(m,0,1);
    }
    static double getSqureRoot(double m,double n,double o){
        if(Math.abs(o-n)<0.00001){
            return formatDouble(o,3);
        }else{
            n=o;
            o=(o+m/o)/2;
            return getSqureRoot(m,n,o);
        }
    }

    /**
     * @description: 1022 求素数 暴力枚举
     * @create by: linyuan
     * @create time: 2022/9/8 15:20
     */
    static List<Integer> getPrimeNumber(int n){
        List<Integer> primeList=new ArrayList<>();
        int startNum;
        if(n<=1){
            return primeList;
        }else if(n==2){
            primeList.add(2);
        }else {
            primeList.add(2);
            for(int i=3;i<=n;i=i+2){
                startNum=1;
                //j*j<=i省时间
                for(int j=3;j*j<=i;j=j+2){
                    if(i%j==0){
                        startNum=j;
                        break;
                    }
                }
                if(startNum==1){
                    primeList.add(i);
                }
            }
        }
        return primeList;
    }

    /**
     * @description: 1022 求素数 埃筛
     * @create by: linyuan
     * @create time: 2022/9/8 17:05
     */
    static List<Integer> getPrimeNumber2(int n){
        List<Integer> primeList=new ArrayList<>();
        if(n<=1){
            return primeList;
        }else if(n==2){
            primeList.add(2);
        }else {
            primeList.add(2);
            boolean[] numer=new boolean[n+1];
            numer[0]=true;//0是合数
            numer[1]=true;//1是合数
            //+2是跳过偶数操作，需要求出 1 11 到 n nn 的所有质数，并不需要取质数进行筛选，只需要取小于sqrt{n}即可
            for(int i=3;i*i<=n;i=i+2){
                if(!numer[i]){
                    //+2是跳过偶数操作
                    for(int j=i;i*j<=n;j=j+2){
                            numer[i*j]=true;
                    }
                }
            }
            //+2是跳过偶数操作
            for(int a=3;a<=n;a=a+2){
                if(!numer[a]){
                    primeList.add(a);
                }
            }
        }
        return primeList;
    }

    /**
     * @description: 1022 欧拉线性筛选
     * @create by: linyuan
     * @create time: 2022/9/9 9:25
     */
    static List<Integer> getPrimeNumber3(int n){
        List<Integer> primeList=new ArrayList<>();
        if(n<=1){
            return primeList;
        }else if(n==2){
            primeList.add(2);
        }else {
            primeList.add(2);
            boolean[] numer=new boolean[n+1];
//            Vector<Integer> primeVector=new Vector<>();
            List<Integer> primeVector=new ArrayList<>();
            //跳过偶数
            for(int i=3;i<=n;i=i+2){
                if(!numer[i]){
                    // 如果i没有被前面的数筛掉，则i是素数
                    primeVector.add(i);
                }
                // 筛掉i的素数倍，即i的prime[j-1]倍
                // j循环枚举现在已经筛出的素数（内层循环）用while
                int j=1;
                while (j<=primeVector.size()){
                    int temp=primeVector.get(j-1);
                    if(i*temp>n){
                        break;
                    }
                    numer[i*temp]=true;
                    if(i%temp==0){
                        break;
                    }
                    j++;
                }
            }
            primeList.addAll(primeVector);
        }
        return primeList;
    }





    public static void main(String[] args) {
        System.out.println("请输入");
        Scanner input=new Scanner(System.in);
        int m=input.nextInt();
//        int n=input.nextInt();
        long startTime_N=System.currentTimeMillis();
        List<Integer> primeList=getPrimeNumber3(m);
        long endTime_N=System.currentTimeMillis();
        System.out.println(primeList);
        System.out.println("数量是"+primeList.size());
        long time=1000*endTime_N-1000*startTime_N;
        System.out.println("程序运行时间："+time/1000+"ms");
    }
}
