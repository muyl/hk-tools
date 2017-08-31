package com.hk.utils;

import com.hk.utils.com.hk.entity.User;
import com.hk.utils.com.hk.entity.UserDetail;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        Map<String, Object> map = new HashMap<>();
        map.put("aa", null);
        map.put("bb", new ArrayList<String>());

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("cc", null);

        Map<String, Boolean> map2 = new HashMap<>();
        map2.put("dd", null);

        map.put("ee", map1);
        map.put("ff", map2);

        System.out.println(JsonUtil.toJSONNoFeatures(map));
    }

    @Test
    public void toList() {
        User user1 = new User(1, "张1", 11,new UserDetail("1","15134654765"));
        User user2 = new User(2, "张2", 12,new UserDetail("2","15234654765"));
        User user3 = new User(3, "张3", 13,new UserDetail("3","15334654765"));
        User user4 = new User(4, "张4", 14,new UserDetail("4","15434654765"));
        User[] users = {user1, user2, user3, user4};

        String userStr = JsonUtil.toJSONString(users);
        List<User> userList = JsonUtil.toList(userStr, User.class);
        for (User u : userList) {
            System.out.println(u.toString());
        }

    }

}
