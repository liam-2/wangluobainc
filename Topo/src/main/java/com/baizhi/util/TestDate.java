package com.baizhi.util;

import com.baizhi.entity.test;
import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDate {




   public static  void swap(int[] inputArray,int a,int b ){
         int temp=inputArray[a];
         inputArray[a]=inputArray[b];
         inputArray[b]=temp;
   }

    public static void xzsort(int[] test){
        int len=test.length;
        for(int i=0;i<len-1;i++){
            int f=i;
            for(int j=i+1;j<len;j++){
                if(test[f]>test[j]){
                    f=j;
                }
            }
            swap(test,i,f);

        }


    }

    public static void mpsort(int[] test){

        int len=test.length;
        boolean isSorted = false;
        for(int i=0;i<len-1&& !isSorted;i++){
            isSorted = true;
            for(int j=1;j<len-i;j++){
                if(test[j-1]>test[j]){
                    isSorted = false;
                    swap(test,j-1,j);
                }
            }

        }
    }



    public static void  crsort(int[] test) {
        int len = test.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && test[j]<test[j-1]; j--) {
                swap(test, j, j - 1);
            }
        }
    }

    public static void  xesort(int[] test) {
        int len = test.length;
        int h = 1;

        while (h < len / 3) {
            h = 3 * h + 1; // 1, 4, 13, 40, ...
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && test[j]<test[j-h]; j -= h) {
                    swap(test, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    //归并方法
    public static void gb(int[] test, int l, int m, int h){

       //l

        int i = l, j = m + 1;
        int[] aux=new int[test.length];
        for (int k = l; k <=h; k++) {
            aux[k] = test[k]; // 将数据复制到辅助数组
        }

        for (int k = l; k <= h; k++) {
            if (i > m) {
                test[k] = aux[j++];

            } else if (j > h) {
                test[k] = aux[i++];

            } else
                if (aux[i]<=aux[j]) {
                test[k] = aux[i++]; // 先进行这一步，保证稳定性

            } else {
                test[k] = aux[j++];
            }
        }
    }

    public static  void sort(int[] test) {
        int[] aux = new int[test.length];
        gbsort1(test,0, test.length - 1);
    }

    //自顶向下归并
    public static void gbsort1(int[] test, int s,int h){

        if (h <= s) {
            return;
        }
        int mid = s + (h - s) / 2;
        gbsort1(test, s, mid);
        gbsort1(test, mid + 1, h);
        gb(test, s, mid, h);
    }


    //自底向上归并排序
    public static void gbsort2(int[] test){

        int N = test.length;
        int[] aux = new int[N];

        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                gb(test, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }


    public static void kssort(int[] nums) {
//        shuffle(nums);
        kssort1(nums, 0, nums.length - 1);
    }


    private static void kssort1(int[] nums, int l, int h) {
        if (h <= l){
            return;
        }
        int j = partition(nums, l, h);
        kssort1(nums, l, j - 1);
        kssort1(nums, j + 1, h);
    }

    private static int partition(int[] nums, int l, int h) {
        int i = l, j = h + 1;
        int v = nums[l];


        while (true) {
            while (nums[++i]<=v && i != h){} ;
            while (v<=nums[--j] && j != l){} ;
            if (i >= j){
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;


    }

    /**
     * 得到匹配括号的位置
     *
     * 返回对应坐标为正常，
     * -1表示传进来的坐标不是左括号也不是空格，是空格会顺位向后匹配直到遇到左括号
     * -2表示不存在对应右括号（不应该出现，应该提前校验过括号配对）
     *
     * @date 2018年11月22日 上午10:54:50
     * @param checkedStr
     * @return
     */
    public static Integer bracketsMatchedSearch(char[] checkedCharArray, int leftIndex) {

        // 校验传进来的数组和索引是否为合法
        if (checkedCharArray.length < leftIndex) {
            return -1;
        }

        char left = checkedCharArray[leftIndex];
        // 非左括号
        if (!('(' == left || '[' == left || '{' == left)) {
            // 如果是空格则+1
            if (' ' == left) {
                return bracketsMatchedSearch(checkedCharArray, leftIndex + 1);
            } else {
                return -1;
            }
        }

        /*
         *  获取传进来的是第几个左括号
         */
        int index = 0;
        Matcher matcher = Pattern.compile("\\(|\\[|\\{").matcher(new String(checkedCharArray));
        while (matcher.find()) {
            index++;
            if (matcher.start() == leftIndex) {
                break;
            }
        }
        /*
         *  获取另一配对括号位置
         */
        Stack<Character> bracketsStack = new Stack<>();
        int count = 0;
        for (int i = 0; i < checkedCharArray.length; i++) {
            char c = checkedCharArray[i];

            // 左括号都压入栈顶，右括号进行比对
            if (c == '(' || c == '[' || c == '{') {
                count++;
                // 如果是目标，就插入*作为标记
                if (index == count) {
                    bracketsStack.push('*');
                } else {
                    bracketsStack.push(c);
                }
            } else if (c == ')') {
                // 栈非空校验，防止首先出现的是右括号
                if (bracketsStack.isEmpty()) {
                    return i;
                }
                Character popChar = bracketsStack.pop();
                if ('*' == popChar) {
                    return i;
                }
            } else if (c == ']') {
                if (bracketsStack.isEmpty()) {
                    return i;
                }
                Character popChar = bracketsStack.pop();
                if ('*' == popChar) {
                    return i;
                }
            } else if (c == '}') {
                if (bracketsStack.isEmpty()) {
                    return i;
                }
                Character popChar = bracketsStack.pop();
                if ('*' == popChar) {
                    return i;
                }
            }
        }

        return -2;
    }


    public static String sqlAdapter(String sql){
            sql = org.apache.commons.lang3.StringUtils.replaceIgnoreCase(sql,"nvl","ifnull");
            sql = org.apache.commons.lang3.StringUtils.replaceIgnoreCase(sql,"to_char","date_format");
            sql = org.apache.commons.lang3.StringUtils.replaceIgnoreCase(sql,"sysdate","sysdate()");
            sql = org.apache.commons.lang3.StringUtils.replaceIgnoreCase(sql,"regexp_like","regexp");
            sql = org.apache.commons.lang3.StringUtils.replaceIgnoreCase(sql,"yyyy","%Y");
            sql = org.apache.commons.lang3.StringUtils.replaceIgnoreCase(sql,"-MM","-%m");
            sql = org.apache.commons.lang3.StringUtils.replaceIgnoreCase(sql,"-dd","-%d");
            sql = org.apache.commons.lang3.StringUtils.replaceIgnoreCase(sql,"'dd'","'%d'");
            sql = org.apache.commons.lang3.StringUtils.replaceIgnoreCase(sql,"'mm'","'%m'");
            sql = StringUtils.replaceIgnoreCase(sql,"to_date","STR_TO_DATE");
            return sql;
    }





    public static void main(String[] args) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Long time=1322668800000L;

//        Date sjsj=new Date(" ");
//        BigDecimal bigDecimal;
//        test a=new test();
//
//
//        int a[]={3,5,7,1,11,50,9,10,12};
//       int a[]={6,5,7,4};
//        kssort(a);
//        for(int b:a){
//            System.out.print(b+" ");
//        }

//        String a = "sum((nvl(t.jbntmj, 0)) / 100000000 * 15 )";
//        Integer d = bracketsMatchedSearch(a.toCharArray(), a.indexOf("nvl")+3);
        System.out.println(sqlAdapter("nvl(t.zsid,0))"));




    }
}
