package com.baizhi.util;


//import com.baizhi.service.TopoService;
//import com.baizhi.service.TopoServiceImpl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//模拟ping实现类
public class PingUtil {

    public static final Object someObject = new Object();//同步标志

    public  List<String> theAllResultList=new ArrayList<String>();


    String ping = "ping";//模拟 ping 命令


    //模拟 tracert 命令
    public StringBuffer commandResult = null;


    public PingUtil(){


    }


//
//    private void command(String tracerCommand) throws IOException{
//        //第一步：创建进程(是接口不必初始化)
//
//        //1.通过Runtime类的getRuntime().exec()传入需要运行的命令参数
//
//        System.out.println();
//
//        System.out.println(InetAddress.getByName("localhost")+" is tracking the destination server...");
//        Process process = Runtime.getRuntime().exec(tracerCommand);
//
//
//        readResultIp(process.getInputStream());
//
//        process.destroy();
//    }
    //第二步：通过输入流来将命令执行结果输出到控制台

    private String readResultIp(InputStream inputStream) throws IOException{

        commandResult = new StringBuffer();  //初始化命令行

        String commandInfo = null; //定义用于接收命令行执行结果的字符串

        String getResult=null;

        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(inputStream,"GBK"));

        int count=0;
        while ( (commandInfo = bufferedReader.readLine()) != null)  {

            count++;
//            System.out.println(count);
//            System.out.println(commandInfo);
            if(count==9){
                getResult=commandInfo;
            }
            //System.out.println(commandResult);
        }
        bufferedReader.close();
        return  getResult;

    }



    public String getPing(String inputIp) throws IOException {

        String thisPing=ping+" "+inputIp;

        Process process = Runtime.getRuntime().exec(thisPing);

        String myResult=readResultIp(process.getInputStream());

        process.destroy();

        return myResult;
    }



//测试后端逻辑代码
//    public static void main(String args[]) throws IOException {
//
//        List<String> givenIpList=new ArrayList<String>();
//
//        givenIpList.add("10.208.99.5");
//       for(int i=1;i<7;i++){
//           givenIpList.add("10.208.64."+i);
//       }
//        TopoServiceImpl topoService=new TopoServiceImpl();
//
//        List<String> givenIpList2=topoService.getIpList(givenIpList);
//        System.out.println(givenIpList2);
//
//        System.out.println(topoService.getTracertList(givenIpList.get(0),givenIpList2));
//
//
//    }

    public static void main(String[] args) {

    }


}
