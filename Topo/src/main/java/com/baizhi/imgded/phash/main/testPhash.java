package com.baizhi.imgded.phash.main;


import com.baizhi.imgded.imgeded;
import com.baizhi.imgded.phash.algorithm.Phash;
import com.baizhi.imgded.phash.data.CImage;
import com.baizhi.study.User;
import org.apache.commons.lang3.StringEscapeUtils;
import org.quartz.CronExpression;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

public class testPhash extends imgeded {

	public static int parallelSum(int[] arr){
		return Arrays.stream(arr).parallel().reduce(0, Integer::sum);
	}

	public static void main(String[] args) {
//		System.out.println("hello");
//		Phash phash = new Phash();
//		StringBuilder sb = new StringBuilder();
//		if(sb.indexOf(","+"3".substring(3)+",")!=-1){
//			System.out.println("6666");
//		}
//		CImage imA=new CImage("D:\\data\\snap\\"+imgeded.Str1);
//		CImage imB=new CImage("D:\\data\\snap\\"+imgeded.Str2);
//		System.out.println(phash._ph_compare_images(imA, imB));
//		List<String> aList=new ArrayList<>();
//		System.out.println(aList.size());
//		String str="0 0/2 10,11,12,13,14,15 ? 4 * *";
//		System.out.println(CronExpression.isValidExpression(str));
//		Calendar calendar=Calendar.getInstance();
//		SimpleDateFormat f1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date=new Date();
//		Date date2=calendar.getTime();
//		calendar.add(Calendar.DATE,2);
//		calendar.add(Calendar.MONTH,3);
//		Date date3=calendar.getTime();
//		System.out.println(f1.format(date));
//		System.out.println(f1.format(date2));
//		System.out.println(f1.format(date3));
//		System.out.println(f1.format(System.currentTimeMillis()));

//		String str="{\"data\":\"{\\\"pbxm\\\":[{\\\"pbGdMj\\\":2.9048,\\\"pbWlydMj\\\":0.1507,\\\"pbJsydMj\\\":4.2919,\\\"xmData\\\":[{\\\"xmLb\\\":\\\"1\\\",\\\"xmMc\\\":\\\"越秀路（补）\\\",\\\"xmLah\\\":\\\"15202111496\\\",\\\"xmBh\\\":\\\"c8bb113bb35e46cd87e8151802029812\\\",\\\"xmCj\\\":\\\"2\\\",\\\"xmMj\\\":4.2919,\\\"zdxmBh\\\":\\\"\\\"}],\\\"xzqdm\\\":\\\"320115\\\",\\\"pbNydMj\\\":4.1412,\\\"pbZmj\\\":4.2919,\\\"ptMc\\\":\\\"江宁区本级\\\"}],\\\"xmJhlx\\\":\\\"1\\\",\\\"bz\\\":\\\"\\\",\\\"pbId\\\":\\\"32011522370000000\\\",\\\"xmlx\\\":\\\"批次城镇建设用地\\\",\\\"hjry\\\":\\\"徐东伟\\\",\\\"pbMc\\\":\\\"南京市江宁区2022年度第1批次城市建设用地\\\",\\\"zjsj\\\":\\\"2022-06-17\\\",\\\"formYear\\\":\\\"2022\\\"}\"}";
//
//
//
//		String s = StringEscapeUtils.unescapeJava(str);
//		System.out.println(s);

//		User user=new User();
//		user.setName("111");
//		if(user.getTest()==null){
//			user.setTest(BigDecimal.ZERO);
//		}
////		BigDecimal lyxzmj=new BigDecimal(" ");
//		System.out.println(user.getTest());
//		int[] a = new int[]{3, 9, 20, 15, 7};
//		System.out.println(parallelSum(a));
		System.out.println(123.3356%120.0);



	}
}
