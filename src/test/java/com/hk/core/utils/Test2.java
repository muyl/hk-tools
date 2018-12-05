package com.hk.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * User: hk
 * Date: 2017/8/11 下午12:45
 * version: 1.0
 */
@Slf4j
public class Test2 {
    public static void main(String[] args) {
        PropUtil.use(new File("/Users/muyl/Desktop/22.properties"));
        log.info(PropUtil.get("password"));
    }
}
