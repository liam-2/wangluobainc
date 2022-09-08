package com.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


//tracert结果实体
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TracertResult {

    public int id;

    public String from;

    public String to;



}
