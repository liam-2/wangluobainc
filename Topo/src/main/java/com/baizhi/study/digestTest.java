package com.baizhi.study;

import cn.hutool.Hutool;
import cn.hutool.crypto.digest.MD5;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class digestTest {
    //获得某天最大时间
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    //获得某天最小时间
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    //获得某月最大时间
    public static Date getEndOfMonth(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        //获得本月最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getEndOfDay(calendar.getTime());
    }

    //获得某月最小时间
    public static Date getStartOfMonth(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(calendar.getTime());
    }

    //根据当前日期获取本季度最小时间
    public static Date getCurrentQuarterStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;
        try {
            if (currentMonth <= 3) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth <= 6) {
                c.set(Calendar.MONTH, 3);
            } else if (currentMonth <= 9) {
                c.set(Calendar.MONTH, 6);
            } else if (currentMonth <= 12) {
                c.set(Calendar.MONTH, 9);
            }
            c.set(Calendar.DATE, 1);
            now=getStartOfDay(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    //根据当前日期获取本季度最大时间
    public static Date getCurrentQuarterEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentQuarterStartTime(date));
        cal.add(Calendar.MONTH, 2);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getEndOfDay(cal.getTime());
    }


    public static void main(String[] args) {

//        String username="gtlyqly";
//        String realm="allcam";
////        String realm2="realm@host.com";
//        String password="Gtly@2019";
////        String passwd="key";
//
//
//        String nonce="9cuBIn3XfoUFaAshFldx3cEdHUCxRihoUP";
//        String nc="00000001";
//        String cnonce="00000001";
//        String qop="auth";
//
//        String method="POST";
//        String uri="/dcs/v1/api/preset/get";
//
//        String ha1=username+":"+realm+":"+password;
//        String md1= MD5.create().digestHex(ha1);
//        System.out.println(ha1);
//        System.out.println("md1是"+md1);
//
//        String ha2=method+":"+uri;
//        String md2=MD5.create().digestHex(ha2);
//        System.out.println(ha2);
//        System.out.println("md2是"+md2);
//
//        String ha3=md1+":"+nonce+":"+nc+":"+cnonce+":"+qop+":"+md2;
//        String md3=MD5.create().digestHex(ha3);
//        System.out.println(ha3);
//        System.out.println("md3是"+md3);





//        String a="0 0/2 14,15 22 * ?";
//        int b=a.lastIndexOf(" ");
//        System.out.println(a.substring(0,b));

//        List<String> list=new ArrayList<>();
//        Map params = new HashMap();
//        list.add("001");
//        list.add("002");
//        list.add("003");
//        list.addAll(null);
//        System.out.println(list);
//        params.put("ids",list);
//        System.out.println(params.get("ids"));


        System.out.println(digestTest.getStartOfDay(new Date()));
        System.out.println(digestTest.getEndOfDay(new Date()));
        System.out.println(digestTest.getStartOfMonth(new Date()));
        System.out.println(digestTest.getEndOfMonth(new Date()));
        System.out.println(digestTest.getCurrentQuarterStartTime(new Date()));
        System.out.println(digestTest.getCurrentQuarterEndTime(new Date()));








    }
}
