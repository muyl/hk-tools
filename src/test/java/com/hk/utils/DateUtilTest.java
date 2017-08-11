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
    public void getCurrTime() throws Exception {
        log.info("当前时间：" + DateUtil.getCurrTime());
        Assert.assertNotNull(DateUtil.getCurrTime());
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
    public void oddDayOfDate(){
        log.info("今年剩余秒数：" + DateUtil.oddDayOfDate("2017-08-15"));
    }

}
