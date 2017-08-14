package com.hk.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * User: hk
 * Date: 2017/8/11 下午11:58
 * version: 1.0
 */
public class JsonUtilTest {
    @Test
    public void toJSONString() throws Exception {
        Student student = new Student();
        student.setAge(null);
        student.setIsMale(null);
        student.setAge(null);
        student.setGf(null);

        System.out.println(JsonUtil.toJSONString(student));

    }

    @Test
    public void toJSONNoFeatures() throws Exception {

        Map<String,Object> map = new HashMap<>();
        map.put("aa",null);
        map.put("bb",new ArrayList<String>());

        Map<String,Integer> map1 = new HashMap<>();
        map1.put("cc",null);

        Map<String,Boolean> map2 = new HashMap<>();
        map2.put("dd",null);

        map.put("ee",map1);
        map.put("ff",map2);

        System.out.println(JsonUtil.toJSONNoFeatures(map));
    }

}
