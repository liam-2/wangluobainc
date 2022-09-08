package com.baizhi.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.word.WordExportUtil;
import cn.afterturn.easypoi.word.entity.WordImageEntity;
import com.alibaba.fastjson.JSON;
import com.baizhi.entity.*;
import com.baizhi.service.TopoService;
import com.baizhi.vo.TopoResult;


import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;


import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;



@RestController
@CrossOrigin
@RequestMapping("topo")
public class TopoController {

    @Resource
    private TopoService topoService;


    //构造topo图
    @PostMapping("gettopo")
    public TopoResult saveOrUpdate(@RequestBody Ips ips) {

        String [] arr=ips.getGivenIps().split("\\s+");

        List<String> givenIpList= new ArrayList<String>(arr.length);

        Collections.addAll(givenIpList,arr);

        //获取可达ip列表
        List<String> givenIpList2=topoService.getIpList(givenIpList);

        System.out.println("givenIpList2"+givenIpList2);
        List<TracertResult> tracertList=topoService.getTracertList(givenIpList.get(0),givenIpList2);

        HashSet<String> sites = new HashSet<String>();
        for(int i=0;i<tracertList.size();i++){
            sites.add(tracertList.get(i).from);
            sites.add(tracertList.get(i).to);
        }
        //givenIpList2.add(arr[0]);
        //System.out.println("tracertlist"+tracertList);

        List<String> newIpList= new ArrayList<>(sites);
        List<IpResult> ipList=new ArrayList<IpResult>();

        for(int i=0;i<=newIpList.size();i++){
            if(i==0){
                ipList.add(new IpResult(i+1,arr[0]));
            }else{
                if(!arr[0].equals(newIpList.get(i-1))){
                    ipList.add(new IpResult(i+1,newIpList.get(i-1)));
                }
            }

        }

        //Map<String,Integer> ipMap=ipList.stream().collect(Collectors.toMap(IpResult::getLabel,IpResult::getId));
        //构造map，用于根据ip获取id
        Map<String,Integer> ipMap=new HashMap<>();
        for(IpResult the:ipList){

            ipMap.put(the.getLabel(),the.getId());
        }
        //System.out.println("map"+ipMap);




        List<RelationResult> relationList=new ArrayList<>();
        for(int j=0;j<tracertList.size();j++){


            relationList.add(new RelationResult(String.valueOf(tracertList.get(j).id),
                    ipMap.get(tracertList.get(j).from),
                    ipMap.get(tracertList.get(j).to)));
            //tracertList.get(j).setFrom(ipMap.get(tracertList.get(j).getFrom()));
        }


      return new TopoResult(ipList,relationList);

    }

    @SneakyThrows
    @PostMapping("test")
    public void test() {



//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("text/plain");
//        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "");
//        Request request = new Request.Builder()
//                .url("http://192.168.2.198:8002/account/oauth/token?grant_type=authorization_code&code=vwu6XL&redirect_uri=http://www.baidu.com&client_id=insightClientId&client_secret=a8rOGGEVQ6ffTMPKlhag")
//                .method("POST", body)
//                .addHeader("Cookie", "SESSION=N2IxMThkN2QtZTJiYi00YTA5LTk4NzEtMDM2OGY2MjQzMzdj")
//                .build();
//        Response response = client.newCall(request).execute();
//        JSONObject object = JSONObject.parseObject(response.body().string());
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url("http://192.168.2.198:8002/account/rest/v1/users/current-user?access_token=891f8aef-4f79-47ea-a30b-2a965d9aba8b")
//                .method("GET", null)
//                .addHeader("Cookie", "SESSION=ZTRhYTc3OTctM2IyOC00NWNlLTliZTEtMGY2NDQ5MWRlZmI5")
//                .build();
//
       // Response response = client.newCall(request).execute();
//        String url="http://192.168.2.198:8002/account/oauth/token";
//        String url="http://192.168.2.198:8002/account/oauth/authorize?client_id=insightClientId&redirect_uri=http://www.baidu.com&response_type=code&state=gtmap";
//        HttpClient client = new HttpClient();
//        GetMethod method = new GetMethod(url);
//        method.addParameter("grant_type","authorization_code");
//        method.addParameter("code","mPTbNP");
//        method.addParameter("redirect_uri","http://www.baidu.com");
//        method.addParameter("client_id","insightClientId");
//        method.addParameter("client_secret","a8rOGGEVQ6ffTMPKlhag");
//        method.releaseConnection();
//        int code = client.executeMethod(method);
//        JSONObject object = JSONObject.parseObject(method.getResponseBodyAsString());
//
        String url="http://192.168.2.198:8002/account/rest/v1/users/current-user";
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url+"?access_token=99f12374-8526-433d-8275-e8df6ff9a08a");
        method.releaseConnection();
        client.executeMethod(method);

        JSONObject object = JSONObject.parseObject(method.getResponseBodyAsString());
        Map<String, Object> returnMap = (Map<String, Object>) JSON.parseObject(method.getResponseBodyAsString(), Map.class);
        List<JSONObject> roleObjectList = (List<JSONObject>)returnMap.get("roleRecordList");
        List<String> roleList = new ArrayList<String>(roleObjectList.size());
        for (JSONObject thisObject : roleObjectList) {
            roleList.add(thisObject.getString("alias"));
        }
        boolean get=roleList.contains("管理员");



        System.out.println(object.toJSONString());
    }

    @SneakyThrows
    @PostMapping("tohtml")
    public void wordToHtml(){
        try {
            InputStream in = new FileInputStream(new File("D:\\data\\天津市水利综合统计年报说明.docx"));//要转化的word
            XWPFDocument document = new XWPFDocument(in);
            OutputStream baos = new ByteArrayOutputStream();
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, baos,null);
            String content = baos.toString();//转化好的html代码
            baos.close();
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/export")
    public void exportJhxdAndSy(HttpServletRequest request, HttpServletResponse response)throws Exception{
//        if(StringUtils.isBlank(jldw)){
//            jldw = TdjhConstants.Jldw.mu.getMc();
//        }
        //String templatePath = request.getSession().getServletContext().getRealPath("/")+"\\WEB-INF\\views\\common\\excel\\temp.xls";
        OutputStream out = null;
        //TemplateExportParams params = new TemplateExportParams(templatePath);
        Map<String, Object> map = new HashMap<String, Object>(); map.put("department", "Easypoi");
        map.put("person", "JueYue");
        SimpleDateFormat format = new SimpleDateFormat("mm-ss");
        map.put("time", format.format(new Date()));
        map.put("me","JueYue");
        map.put("date", "2015-01-03");
        Student student=new Student("ly","56","湖南");
        map.put("user",student);
        WordImageEntity image = new WordImageEntity();
        image.setHeight(349);
        image.setWidth(350);
        image.setUrl("C:\\TEMP\\wangluobainc\\Topo\\src\\main\\java\\com\\baizhi\\util\\test2.PNG");
        image.setType(WordImageEntity.URL);
        map.put("testCode", image);

        try {
            out = response.getOutputStream();
            //response.setContentType("application/vnd.ms-excel");
            response.setContentType("application/vnd.ms-word");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("测试.docx", "utf-8");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
            XWPFDocument doc = WordExportUtil.exportWord07( "C:\\TEMP\\wangluobainc\\Topo\\src\\main\\java\\com\\baizhi\\util\\simple2.docx", map);
            doc.write(out);
            out.flush();
        } catch (Exception e) {

        }finally {
            out.close();
        }

    }

    public static List<Student> getStudentList(){
        Student student=new Student("001","56","4000+");
        List<Student> list=new ArrayList<>();
        list.add(student);
        return list;
    }


//    @RequestMapping("/word")
//    @ResponseBody
//    public ResultJson uploadImg() {
//        Map<String, Object> data = new HashMap<>();//通过map存放要填充的数据
//        data.put("province","浙江省");//把每项数据写进map，key的命名要与word里面的一样
//        data.put("temp",27.8);
//        data.put("shidu",33.3);
//
//        data.put("water",220);
//        data.put("windir","西北风");
//        data.put("winforce","7-8");
//        data.put("chuanyi","天气热，适合T恤短裤");
//        data.put("chuxing","太阳光强，宜做好防晒");
//        data.put("advice1","适合海边游玩降温");
//        data.put("advice2","适合洗车");
//        data.put("advice3","不宜长时间吹空调");
//        data.put("date", DateUtils.getCurDate());
//        XWPFTemplate template = XWPFTemplate.compile("D:\\test.docx").render(data);//调用模板，填充数据
//        try {
//            FileOutputStream out = new FileOutputStream("D:\\天气预报.docx");//要导出的文件名
//            template.write(out);
//            out.flush();
//            out.close();
//            template.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
