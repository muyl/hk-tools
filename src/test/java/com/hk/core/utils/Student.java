package com.hk.core.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * User: hk
 * Date: 2017/8/12 上午12:17
 * version: 1.0
 */
@Data
public class Student implements Serializable{
    private String name;
    private Integer age;
    private Boolean isMale;
    private Student gf;

}
