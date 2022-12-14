package com.rensyuu.dot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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

    /**
     * @description: 1023 简单选择排序
     * @create by: linyuan
     * @create time: 2022/9/9 13:44
     */
    static List<Integer> selectSort(List<Integer> numList){
        for(int i=0;i<numList.size();i++){
            for(int j=i+1;j<numList.size();j++){
                if(numList.get(i)>numList.get(j)){
                    Collections.swap(numList,i,j);
                }
            }
        }
        return numList;
    }


    /**
     * @description: 1024 3*3矩阵对角线和
     * @create by: linyuan
     * @create time: 2022/9/9 14:23
     */
    static List<Integer> sumMatrixDiagonal(int[][] matrix){
        List<Integer> numList=new ArrayList<>();
        int sum1=0,sum2=0;
        for(int i=0;i<3;i++){
            sum1+=matrix[i][i];
            sum2+=matrix[i][2-i];
        }
        numList.add(sum1);
        numList.add(sum2);
        return numList;
    }

    /**
     * @description: 1025 数组顺序插入
     * @create by: linyuan
     * @create time: 2022/9/9 15:03
     */
    static  List<Integer> insertArray(List<Integer> numList,int n){
            int i=0;
            for(;i<numList.size()-1;i++){
                if(n>=numList.get(i)&&n<numList.get(i+1)){
                    i++;
                    break;
                }
            }
            if(n>=numList.get(numList.size()-1)){
                numList.add(n);
            }else{
                numList.add(i,n);
            }
        return numList;
    }

    /**
     * @description: 1026 逆序输出
     * @create by: linyuan
     * @create time: 2022/9/9 15:07
     */
    static  List<Integer> getBackwardList(List<Integer> numList){
        int temp=numList.size()-1;
        for(int i=0;i<temp/2;i++){
            Collections.swap(numList,i,temp-i);
        }
        return numList;
    }


    /**
     * @description: 1027 辗转相除法 公约数 公倍数
     * @create by: linyuan
     * @create time: 2022/9/9 15:16
     */
    static  List<Integer> getCommonDivisor(int m,int n){
        List<Integer> resultList=new ArrayList<>();
        if(m>n){
            resultList.add(gcd(m,n));
        }else{
            resultList.add(gcd(n,m));
        }
        resultList.add(m*n/resultList.get(0));
        return resultList;
    }
    static int gcd(int m,int n){
        if(n==1||m%n==0){
            return n;
        }else{
            return gcd(n,m%n);
        }
    }

    /**
     * @description: 1027 更减相损法 公约数 公倍数
     * @create by: linyuan
     * @create time: 2022/9/9 15:16
     */
    static  List<Integer> getCommonDivisor2(int m,int n){
        List<Integer> resultList=new ArrayList<>();
        resultList.add(gcd2(n,m));
        resultList.add(m*n/resultList.get(0));
        return resultList;
    }
    static int gcd2(int m,int n){
        if(n>m){
            return gcd2(n,m);
        }else if(n==1||m==n){
            return n;
        }else{
            return gcd2(n,m-n);
        }
    }

    /**
     * @description: 1027 移位更减相损法 公约数 公倍数
     * @create by: linyuan
     * @create time: 2022/9/9 15:16
     */
    static  List<Integer> getCommonDivisor3(int m,int n){
        List<Integer> resultList=new ArrayList<>();
        resultList.add(gcd3(n,m));
        resultList.add(m*n/resultList.get(0));
        return resultList;
    }
    static int gcd3(int m,int n){
        if(n>m){
            return gcd3(n,m);
        }else if(n==1||m==n){
            return n;
        }else{
            int a=m&1;
            int b=n&1;
            //偶数移位
            if(a==0&&b==0){
                return gcd3(m>>1,n>>1)<<1;
            }else if(a==0&&b==1){
                return gcd3(m>>1,n);
            }else if(a==1&b==0){
                return gcd3(m,n>>1);
            }else{
                return gcd3(n,m-n);
            }
        }
    }





    public static void main(String[] args) {
        System.out.println("请输入");
        Scanner input=new Scanner(System.in);
//        int m=input.nextInt();
//        int n=input.nextInt();

        String str=input.nextLine();
        String[] numstr=str.split(" ");
        List<Integer> numList=new ArrayList<>();
        for(String num:numstr){
            numList.add(Integer.valueOf(num));
        }
        //定义数组
//        int[][] arr = {{1,2,3},{1,1,1},{3,2,1}};
//        Integer[] arr={1,7,8,17,23,24,59,62,101};
//        List<Integer> list = new ArrayList<>(arr.length);
//        Collections.addAll(list, arr);

        long startTime_N=System.nanoTime();
        List<Integer> primeList=getCommonDivisor2(numList.get(0),numList.get(1));
        long endTime_N=System.nanoTime();
        System.out.println(primeList);
//        System.out.println("数量是"+primeList.size());
        long time=1000*endTime_N-1000*startTime_N;
        System.out.println("程序运行时间："+time);
    }
}
