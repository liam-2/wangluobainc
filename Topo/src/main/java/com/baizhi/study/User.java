package com.baizhi.study;

import com.google.gson.Gson;
import lombok.Data;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 深度拷贝测试
 */

public class User implements Serializable,Cloneable{

    private String name;
    private Address address;
    private BigDecimal test;

    public User(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public User() {

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTest() {
        return test;
    }

    public void setTest(BigDecimal test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address=" + this.address.toString() +
                '}';
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        user.setAddress(this.address.clone());
        return user;
    }
    // constructors, getters and setters

    public static void main(String[] args) throws CloneNotSupportedException {

        Address address = new Address("杭州", "中国");
//        User user = new User("大山", address);

//        // 调用构造函数时进行深拷贝
//        User copyUser = new User(user.getName(), new Address(address.getCity(), address.getCountry()));
//
//        // 修改源对象的值
//        user.getAddress().setCity("深圳");

        // 检查两个对象的值不同.
        // 调用重写的clone()方法进行深拷贝
//        User copyUser = user.clone();




        // 使用Apache Commons Lang序列化进行深拷贝
//        User copyUser = (User) SerializationUtils.clone(user);


        // 使用Gson序列化进行深拷贝
//        Gson gson = new Gson();
//        User copyUser = gson.fromJson(gson.toJson(user), User.class);



        //
//        User copyUser=new User();
//        BeanUtils.copyProperties(user, copyUser);
//
//
//        copyUser.setName("修改测试");
//        System.out.println((user==copyUser));
//        System.out.println((user.equals(copyUser)));
//        System.out.println(user.toString());
//        System.out.println(copyUser.toString());

        Map map = new HashMap();
        User user = new User("大山", address);
        User user2 = new User("大山", address);
        List<User> list=new ArrayList<>();
        list.add(user);
        list.add(user2);
        map.put("userList",list);

//        List<Object> list2=map.get("userList");




    }

}
