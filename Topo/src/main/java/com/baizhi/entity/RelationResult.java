package com.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//节点关系实体
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RelationResult {

    public String id;

    public int from;

    public int to;
}
