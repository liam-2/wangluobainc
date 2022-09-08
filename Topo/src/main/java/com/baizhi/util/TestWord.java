package com.baizhi.util;

import cn.afterturn.easypoi.word.entity.WordImageEntity;
import com.baizhi.entity.slzhtjnbVo;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TestWord {


    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object o = field.get(obj);
                if (o != null) {
                    String name = field.getName();
                    if ("tnd".equals(name)) {
                        map.put(name, o.toString());
                    } else if ("tbsj".equals(name)) {
                        map.put(name, "   年   月   日");
                    } else if ("ygsjbdsm".equals(name)) {
                        String tbnr = String.valueOf(o);
                        tbnr=tbnr+"\r\n"+" ";
//                        int[] alen=TestWord.computeDisplayLen(tbnr);4
//                        int truelen=alen[0]/2+alen[1]+alen[0]/8+alen[1]/15;
                        System.out.println("字符串行数："+TestWord.countLines(tbnr));
                        System.out.println("字符串："+tbnr);
                        int count[]=TestWord.getCnChar(tbnr);
                        int tbnrlen=tbnr.length();
                        int truelen=(int)((tbnrlen-count[1])*0.7)+count[0]+(tbnrlen-count[1]/2)/15+count[1]/2;
                        System.out.println("其他字符个数"+(tbnrlen-count[0]));
                        System.out.println("汉字个数"+count[0]);
                        System.out.println("空格和数字个数"+count[1]);
                        System.out.println("真正长度"+truelen);
                        if(truelen<=474){
                        int len = 474- truelen;
                        for (int i = 0; i < len; i++) {
                            tbnr = tbnr + "  ";
                        }
                        }else{
                            int thepage=(truelen-474)/630+1;
                            int len = 630*thepage+474- truelen;
                            for (int i = 0; i < len; i++) {
                                tbnr = tbnr + "  ";
                            }
                        }
                        map.put(name, "    " + tbnr);
                    } else if (name.lastIndexOf("bh") == name.length() - 2) {
                        map.put(name, o.toString());
                    } else if ("bzslbhqtyy".equals(name) || "szslbhqtyy".equals(name) || "ncjzsgsslbhqtyy".equals(name) || "zyhddf".equals(name)) {
                        String tbnr = o.toString();
                        int len = (20 - tbnr.length() > 0 ? 20 - tbnr.length() : 0);
                        for (int i = 0; i < len; i++) {
                            tbnr = " " + tbnr + " ";
                        }
                        map.put(name, tbnr);

                    } else {
                        String tbnr = o.toString();
                        int tbnrlen = tbnr.length();
                        int len = (8 - tbnr.length() > 0 ? 8 - tbnr.length() : 0) / 2;
                        for (int i = 0; i < len; i++) {
                            tbnr = " " + tbnr + " ";
                        }
                        if (tbnrlen % 2 != 0) {
                            map.put(name, tbnr + " ");
                        } else {
                            map.put(name, tbnr);
                        }
                    }
                } else {
                    String name = field.getName();
                    if ("tnd".equals(name)) {
                        map.put(name, "     ");
                    } else if ("tbsj".equals(name)) {
                        map.put(name, "          ");
                    } else if ("ygsjbdsm".equals(name)) {
                        String tbnr = "";
                        for (int i = 0; i < 474; i++) {
                            tbnr = tbnr + "  ";
                        }
                        map.put(name, "    " + tbnr);
                    } else if (name.lastIndexOf("bh") == name.length() - 2) {
                        map.put(name, "增加/减少");
                    } else if ("bzslbhqtyy".equals(name) || "szslbhqtyy".equals(name) || "ncjzsgsslbhqtyy".equals(name) || "zyhddf".equals(name)) {
                        String tbnr = "";
                        for (int i = 0; i < 20; i++) {
                            tbnr = " " + tbnr + " ";
                        }
                        map.put(name, tbnr);
                    } else {
                        map.put(name, "        ");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public static void main(String[] args) throws IOException {

        //String templatePath = request.getSession().getServletContext().getRealPath("/")+"\\WEB-INF\\views\\common\\excel\\temp.xls";
        // OutputStream out = null;
        //TemplateExportParams params = new TemplateExportParams(templatePath);
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("department", "Easypoi");
//        map.put("person", "JueYue");
//        SimpleDateFormat format = new SimpleDateFormat("mm-ss");
//        map.put("time", format.format(new Date()));
//        map.put("me","JueYue");
//        map.put("date", "2015-01-03");
        // Student student=new Student("ly","56","湖南");
        slzhtjnbVo slzhtjnbVo = new slzhtjnbVo();
        slzhtjnbVo.setNd("9999");
        slzhtjnbVo.setTnd("2021");
        slzhtjnbVo.setSksl("100");
        slzhtjnbVo.setSkzrl("101");
        slzhtjnbVo.setSklxb1sl("102");
        slzhtjnbVo.setSkrlb1("103");
        slzhtjnbVo.setSklxb2sl("104");
        slzhtjnbVo.setSkrlb2("105");
        slzhtjnbVo.setSklxmsl("106");
        slzhtjnbVo.setSkrlm("107");
        slzhtjnbVo.setSklxs1sl("108");
        slzhtjnbVo.setSkrls1("109");
        slzhtjnbVo.setSklxs2sl("109.5");
        slzhtjnbVo.setSkrls2("110");
        slzhtjnbVo.setSdzsl("111");
        slzhtjnbVo.setSdzlx("112");
        slzhtjnbVo.setBzsl("113");
        slzhtjnbVo.setBzlxb1sl("114");
        slzhtjnbVo.setBzlxb2sl("115");
        slzhtjnbVo.setBzlxmsl("116");
        slzhtjnbVo.setBzlxs1sl("117");
        slzhtjnbVo.setBzlxs2sl("118");
        slzhtjnbVo.setBzbh("增加");
        slzhtjnbVo.setBzbhsl("120");
        slzhtjnbVo.setBzxjcsl("121");
        slzhtjnbVo.setBzbzbfsl("122");
        slzhtjnbVo.setBzslbhqtyy("123");
        slzhtjnbVo.setSzsl("124");
        slzhtjnbVo.setSzlxb1sl("125");
        slzhtjnbVo.setSzlxb2sl("126");
        slzhtjnbVo.setSzlxmsl("127");
        slzhtjnbVo.setSzlxs1sl("128");
        slzhtjnbVo.setSzlxs2sl("129");
        slzhtjnbVo.setSzbh("增加");
        slzhtjnbVo.setSzbhsl("131");
        slzhtjnbVo.setSzxjcsl("132");
        slzhtjnbVo.setSzbfsl("133");
        slzhtjnbVo.setSzslbhqtyy("134");
        slzhtjnbVo.setSlgcncjzsgssl("135");
        slzhtjnbVo.setNcjzsgswrgcsl("136");
        slzhtjnbVo.setNcjzsgsqrgcsl("137");
        slzhtjnbVo.setNcjzsgsqryxgcsl("138");
        slzhtjnbVo.setNcjzsgsbh("增加");
        slzhtjnbVo.setNcjzsgsbhsl("140");
        slzhtjnbVo.setNcjzsgsjcsl("141");
        slzhtjnbVo.setNcjzsgsbfsl("142");
        slzhtjnbVo.setNcjzsgsslbhqtyy("143");
        slzhtjnbVo.setJdjsl("144");
        slzhtjnbVo.setJdjsl("145");
        slzhtjnbVo.setGmysjdjbh("增加");
        slzhtjnbVo.setGmysjdjbhsl("147");
        slzhtjnbVo.setGmysqcjdjsl("148");
        slzhtjnbVo.setGmysscjdjsl("149");
        slzhtjnbVo.setGmyxjdjsl("150");
        slzhtjnbVo.setGmyxqcjdjsl("151");
        slzhtjnbVo.setGmyxscjdjsl("152");
        slzhtjnbVo.setSlgczgsl("153");
        slzhtjnbVo.setZgsnlbh("154");
        slzhtjnbVo.setZgsnlbhl("155");
        slzhtjnbVo.setKqygsl("156");
        slzhtjnbVo.setBqygsl("157");
        slzhtjnbVo.setGmysslgcgsl("158");
        slzhtjnbVo.setGmyxslgcgsl("159");

        slzhtjnbVo.setQnslgczgsl("160");
        slzhtjnbVo.setQnslgczgslbh("161");
        slzhtjnbVo.setQnslgczgslbhsl("162");
        slzhtjnbVo.setDbsygcgsl("163");
        slzhtjnbVo.setDxsygcgsl("164");
        slzhtjnbVo.setQtsygcgsl("165");
        slzhtjnbVo.setQyngsl("166");
        slzhtjnbVo.setNygsl("167");
        slzhtjnbVo.setGyscgsl("168");
        slzhtjnbVo.setCzshgsl("169");
        slzhtjnbVo.setXcshgsl("170");
        slzhtjnbVo.setRgsthjgsl("171");
        slzhtjnbVo.setQtgsl("172");
        slzhtjnbVo.setXqywgsl("173");

        slzhtjnbVo.setCsgsgcsl("174");
        //  slzhtjnbVo.setCsgsgcbh("175");
        slzhtjnbVo.setCsgsgcbhsl("176");
        slzhtjnbVo.setCszlscsl("177");
        slzhtjnbVo.setCxgsbzncjzsgssl("178");
        slzhtjnbVo.setNcfssgsgcsl("179");

        slzhtjnbVo.setCsgsgcsyrk("180");
        //slzhtjnbVo.setCsgsgcsyrkbh("181");
        slzhtjnbVo.setCsgsgcsyrkbhsl("182");
        slzhtjnbVo.setCszlscsyrk("183");
        slzhtjnbVo.setNcjzsgsgcsyrk("184");
        slzhtjnbVo.setNcfssgsgcsyrk("185");

        slzhtjnbVo.setCsgsgcsjshejingsl("186");
        // slzhtjnbVo.setCsgsgcshejibh("187");
        slzhtjnbVo.setCsgsgcshejibhsl("188");
        slzhtjnbVo.setCsgsgcsjngsl("189");
        // slzhtjnbVo.setCsgsgcsjngslbh("190");
        slzhtjnbVo.setCsgsgcsjngslbhsl("191");

        slzhtjnbVo.setNcgsyjcgcsl("192");
        slzhtjnbVo.setNcgsgcsyrksl("193");
        slzhtjnbVo.setNczlsgsrksl("194");

        slzhtjnbVo.setGgmj("195");
        slzhtjnbVo.setGgmjbh("增加");
        slzhtjnbVo.setGgmjbhsl("197");
        slzhtjnbVo.setGdggmj("198");
        slzhtjnbVo.setLdggmj("199");
        slzhtjnbVo.setYdggmj("200");

        slzhtjnbVo.setJsgggcmj("201");
        //slzhtjnbVo.setJsgggcmjbh("202");
        slzhtjnbVo.setJsgggcmjbhsl("203");
        slzhtjnbVo.setPgmj("204");
        slzhtjnbVo.setWgmj("205");
        slzhtjnbVo.setDyggmj("206");
        slzhtjnbVo.setQdfsmj("207");
        slzhtjnbVo.setQtmj("208");

        slzhtjnbVo.setGqmjsl_2000("209");
        slzhtjnbVo.setGqmjsl_50("210");
        slzhtjnbVo.setGdggmj_50("211");
        slzhtjnbVo.setGqmjsl_30to50("212");
        slzhtjnbVo.setGdggmj_30to50("213");
        slzhtjnbVo.setGqmjsl_10to30("214");
        slzhtjnbVo.setGdggmj_10to30("215");
        slzhtjnbVo.setGqmjsl_5to10("216");
        slzhtjnbVo.setGdggmj_5to10("217");
        slzhtjnbVo.setGqmjsl_1to5("218");
        slzhtjnbVo.setGdggmj_1to5("219");
        slzhtjnbVo.setGqmjsl_2to1("220");
        slzhtjnbVo.setGdggmj_2to1("221");

        slzhtjnbVo.setZyhddf("222");
        slzhtjnbVo.setDfzcd("223");
        slzhtjnbVo.setYjdfcd("224");
        slzhtjnbVo.setEjdfcd("225");
        slzhtjnbVo.setSjdfcd("226");
        slzhtjnbVo.setS_jdfcd("227");
        // slzhtjnbVo.setDfcdbh("228");
        slzhtjnbVo.setDfcdbhsl("229");

        slzhtjnbVo.setZyhddbdfcd("230");
        //slzhtjnbVo.setZyhddbdfcdbh("231");
        slzhtjnbVo.setZyhddbdfcdbhsl("232");

        slzhtjnbVo.setFhrwhdcd("233");
        slzhtjnbVo.setYzlhdcd("234");
        //slzhtjnbVo.setYzlhdcdbh("235");
        slzhtjnbVo.setYzlhdcdbhsl("236");
        slzhtjnbVo.setZldbhdcd("237");
        slzhtjnbVo.setHlhdcd("238");
        slzhtjnbVo.setHdhhsgnqsl("239");

        slzhtjnbVo.setClmj("240");
        //slzhtjnbVo.setClmjbh("241");
        slzhtjnbVo.setClmjbhsl("242");
        slzhtjnbVo.setClbzmj_3to5("243");
        slzhtjnbVo.setClbzmj_5to10("244");
        slzhtjnbVo.setClbzmj_10("245");

        //第七段
        slzhtjnbVo.setStlszhzlmj("246");
        //slzhtjnbVo.setStlszhzlmjbh("247");
        slzhtjnbVo.setStlszhzlmjbhsl("248");
        slzhtjnbVo.setXzstlszlmj("249");

        slzhtjnbVo.setYgsjbdsm(TestWord.readString4());
        //slzhtjnbVo.setYgsjbdsm("");
        slzhtjnbVo.setBm("251");
        slzhtjnbVo.setTbsj("2021年09月22日");

        slzhtjnbVo.setStamp("D:\\data\\test\\test2.PNG");


        Map<String, Object> map2 = object2Map(slzhtjnbVo);

        // map.put("slzhtjnbVo",slzhtjnbVo);
        WordImageEntity image = new WordImageEntity();
        image.setHeight(175);
        image.setWidth(175);
        image.setUrl("D:\\data\\test\\test2.PNG");
        image.setType(WordImageEntity.URL);

        map2.put("stamp", image);
       // map2.put()
        try {
//            XWPFDocument doc = WordExportUtil.exportWord07( "D:\\data\\test\\水利综合统计年报说明.docx", map);
//            FileOutputStream fos = new FileOutputStream("D:/data/thetest.docx");
//            doc.write(fos);
//            fos.close();

            FileInputStream fis = new FileInputStream("D:\\data\\test\\水利综合统计年报说明.docx");
//         FileOutputStream fos = new FileOutputStream("D:/data/thetest.docx");
//         MyXWPFDocument doc = new MyXWPFDocument(fis);
//         WordExportUtil.exportWord07(doc, map);
//         doc.write(fos);
//         fos.close();
            WordExportUtil.exportWord(fis, "D:\\data", "thetest.docx", map2);


            System.out.println("输出成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int a[]=TestWord.getCnChar("输出成功 8888   厉害了");
       System.out.println("汉字个数"+a[0]);
        

    }

    private static int[]  getCnChar(String text) {

        int count[]=new int[2];
        int amount = 0;//创建统计汉字的计数器
        int spcount=0;
        for (int i = 0; i < text.length(); i++) {//遍历字符串每一个字符

//使用正则表达式，判断字符是否为汉字编码

            boolean matches = Pattern.matches("^[\u4E00-\u9FA5]{0,}$",""

                    + text.charAt(i));

            if (matches) {//如果是汉字

                amount++;//则累加

            }
            if(' '==text.charAt(i)||(((text.charAt(i) >= '0') && (text.charAt(i) <= '9')))){
                 spcount++;
            }

        }
        count[0]=amount;
        count[1]=spcount;
        return count;

    }

    /* 按行读对于要处理的格式化数据是一种读取的好方式 */

    private static String readString4()

    {

        int len=0;



        StringBuffer str=new StringBuffer("");

        File file=new File("D:\\data\\test.txt");

        try {

            FileInputStream is=new FileInputStream(file);

            InputStreamReader isr= new InputStreamReader(is);

            BufferedReader in= new BufferedReader(isr);

            String line=null;

            while( (line=in.readLine())!=null )

            {

                if(len != 0)  // 处理换行符的问题

                {

                    str.append("\r\n"+line);

                }

                else

                {

                    str.append(line);

                }

                len++;

            }

            in.close();

            is.close();

        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return str.toString();

    }


    public static int countLines(String str) {
        int count = 1;

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i)=='\n') {

                count++;
            }


        }
        return count;
    }




}
