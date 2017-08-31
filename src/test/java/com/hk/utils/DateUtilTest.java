package com.hk.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.*;

/**
 * User: hk
 * Date: 2017/8/10 下午5:24
 * version: 1.0
 */
@Slf4j
public class DateUtilTest {

    @Test
    public void getCurrDate() throws Exception {
        log.info("当前日期（标准）：" + DateUtil.getCurrDate());
        Assert.assertNotNull(DateUtil.getCurrDate());
    }

    @Test
    public void getCurrTime() throws Exception {
        log.info("当前时间：" + DateUtil.getCurrTime());
        Assert.assertNotNull(DateUtil.getCurrTime());
    }

    @Test
    public void getCurrShortTime() throws Exception {
        log.info("当前短时间：" + DateUtil.getCurrShortTime());
        Assert.assertNotNull(DateUtil.getCurrShortTime());
    }

    @Test
    public void getCurrShortDate() throws Exception {
        log.info("当前日期（短）：" + DateUtil.getCurrShortDate());
        Assert.assertNotNull(DateUtil.getCurrShortDate());
    }

    @Test
    public void getCurrDateTime() throws Exception {
        log.info("当前日期时间：" + DateUtil.getCurrDateTime());
        Assert.assertNotNull(DateUtil.getCurrDateTime());
    }



    @Test
    public void oddSecondOfDay() throws Exception {
        log.info("今天剩余秒数：" + DateUtil.oddSecondOfDay());
        Assert.assertNotNull(DateUtil.oddSecondOfDay());
    }

    @Test
    public void oddSecondOfWeek() throws Exception {
        log.info("本周剩余秒数：" + DateUtil.oddSecondOfWeek());
        Assert.assertNotNull(DateUtil.oddSecondOfWeek());
    }

    @Test
    public void oddSecondOfMonth() throws Exception {
        log.info("本月剩余秒数：" + DateUtil.oddSecondOfMonth());
        Assert.assertNotNull(DateUtil.oddSecondOfMonth());
    }

    @Test
    public void oddSecondOfYear() throws Exception {
        log.info("今年剩余秒数：" + DateUtil.oddSecondOfYear());
        Assert.assertNotNull(DateUtil.oddSecondOfYear());
    }

    @Test
    public void oddDayOfDate() {
        log.info("到指定日期剩余秒数：" + DateUtil.oddDayOfDate("2017-08-15"));
    }

    @Test
    public void intervalDate() {
        log.info("今天是否在指定的区间范围内：" + DateUtil.intervalDate("2017-08-14", "2017-09-01"));
    }

    @Test
    public void intervalDateTime() {
        log.info("今天是否在指定的区间范围内："
            + DateUtil.intervalDateTime("2017-08-14 00:00:00", "2017-09-01 23:59:59"));
    }

    @Test
    public void intervalOfTime() {
        log.info("今天是否在指定的区间范围内：" + DateUtil.intervalOfTime("10:00:00", "13:59:59"));
    }

    @Test
    public void isAfterNow(){
        log.info("2017-08-08 12:12:12是否在当前日期之后：" +DateUtil.isAfterNow("2017-08-08 12:12:12"));
    }

    @Test
    public void isBeforeNow(){
        log.info("2017-08-08 12:12:12是否在当前日期之前：" +DateUtil.isBeforeNow("2017-08-08 12:12:12"));
    }

}
