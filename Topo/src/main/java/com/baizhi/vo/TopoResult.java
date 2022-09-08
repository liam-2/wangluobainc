package com.baizhi.vo;


import com.baizhi.entity.IpResult;
import com.baizhi.entity.RelationResult;
import com.baizhi.entity.TracertResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

//前端返回结果包装类
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TopoResult {
    private List<IpResult> ipList;

    private List<RelationResult> relationList;

    public static void main(String[] args) {
        TopoResult topoResult=new TopoResult();
        System.out.println(topoResult==null);
    }



}
