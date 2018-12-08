package com.hk.core.utils;

import com.hk.core.text.UUID;
import com.xiaoleilu.hutool.system.SystemUtil;
import com.xiaoleilu.hutool.util.ReUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * User: hk
 * Date: 2017/8/11 下午12:45
 * version: 1.0
 */
@Slf4j
public class Test2 {
    public static void main(String[] args) {

        System.out.println(SystemUtil.getUserInfo());
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString(true));
        String content = "ENC["+uuid+"]";
        String regex = "ENC[\\w\\W*]";
        List list = ReUtil.findAll(regex,content,0);
        list.forEach(str -> System.out.println(str));
        System.out.println(ReUtil.get(regex, content, 0));
    }
}
