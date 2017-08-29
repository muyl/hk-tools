package com.hk.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: hk
 * Date: 2017/8/11 下午11:00
 * version: 1.0
 */
@Slf4j
public class FTPUtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void downloadFile() throws Exception {
        try (FTPUtil ftpUtil = new FTPUtil()) {
            ftpUtil.connectServer("10.211.55.6", 21, "ftpuser", "123qwe");
            boolean isSucc = ftpUtil.downloadFile("2.txt", "/Users/muyl/Desktop/23.txt");
            log.info("isSucc:" + isSucc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void uploadFile() throws Exception {
        try (FTPUtil ftpUtil = new FTPUtil()) {
            ftpUtil.connectServer("10.211.55.6", 21, "ftpuser", "123qwe");
            boolean isSucc = ftpUtil.uploadFile("3.txt", "/Users/muyl/Desktop/Mycat 安装步骤.docx");
            log.info("isSucc:" + isSucc);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
