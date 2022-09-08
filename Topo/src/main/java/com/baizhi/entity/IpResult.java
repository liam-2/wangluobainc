package com.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


//ip列表实体
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IpResult {


    public int id;

    public String label;
}
