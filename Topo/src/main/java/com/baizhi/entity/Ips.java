package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//前端传入ip列表实体
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ips {
    private String givenIps;
}
