package com.baizhi.service;

import com.baizhi.entity.TracertResult;

import java.util.List;

//service层
public interface TopoService {

    List<String> getIpList(List<String> givenIpList);

    List<TracertResult> getTracertList(String startIp,List<String> givenIpList);

}
