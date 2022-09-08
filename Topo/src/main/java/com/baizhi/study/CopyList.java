package com.baizhi.study;

import com.google.gson.Gson;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.MappingStrategy;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.MapperFacadeImpl;
import ma.glasnost.orika.metadata.Type;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * list深度拷贝测试
 */
public class CopyList {



    public static void main(String[] args) {
        List<User> list1=new ArrayList<>();
        for(int i=1;i<10;i++){
            Address address = new Address("杭州"+i, "中国"+i);
            User user = new User("学号"+i, address);
            list1.add(user);
        }

//        直接赋值法 同一个引用和对象，浅拷贝比直接赋值要更进一步。如果直接赋值的话，list1和list2两个对象是相等的，是同一个引用
//        List<User> list2 = list1;


//        clone法  同一个引用不同对象，是浅拷贝，list.add(), list.remove()这些操作，不会影响到另一个list。
//        ArrayList<User> list2 = (ArrayList<User>) ((ArrayList<User>) list1).clone();

        //克隆失败
//        List<User> list2= new ArrayList<>();;
//        BeanUtils.copyProperties(list1, list2);


        // 必须手动把list2的长度增加到10，否则拷贝了之后list2长度仍是0，同一个引用不同对象，是浅拷贝
//        List<User> list2= new ArrayList<>();;
//        for (int i = 0; i < 9; i++) {
//            list2.add(null);
//        }
//        Collections.copy(list2, list1);


        // 是深度拷贝
        List<User> list2= new ArrayList<>();;
        for (int i = 0; i < list1.size(); i++) {
            User copyUser = (User) SerializationUtils.clone(list1.get(i));
            list2.add(copyUser);
        }



        //同一个引用不同对象，是浅拷贝
//        List<User>  list2 = new ArrayList<>(list1);


        //同一个引用不同对象，是浅拷贝
//        List<User>  list2= new ArrayList<>();
//        list2.addAll(list1);


        //拷贝后类型不对
//        Gson gson = new Gson();
//        List<User>  list2 = gson.fromJson(gson.toJson(list1), list1.getClass());

        //orika深度拷贝成功
//       MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
//       List<User>  list2 = mapperFactory.getMapperFacade().mapAsList(list1, User.class);

       list2.get(0).setName("修改1");
       System.out.println((list1==list2));
       System.out.println(list1);
       System.out.println(list2);

    }

}
