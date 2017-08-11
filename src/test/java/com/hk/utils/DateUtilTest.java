package com.hk.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: hk
 * Date: 2017/8/10 下午5:24
 * version: 1.0
 */
public class DateUtilTest {

    @Test
    public void getCurrDate() throws Exception {
        Assert.assertNotNull(DateUtil.getCurrDate());
    }

    @Test
    public void getCurrShortDate() throws Exception {

        Assert.assertNotNull(DateUtil.getCurrShortDate());
    }

    @Test
    public void getCurrDateTime() throws Exception {

        Assert.assertNotNull(DateUtil.getCurrDateTime());
    }

    @Test
    public void getCurrTime() throws Exception {

        Assert.assertNotNull(DateUtil.getCurrTime());
    }

    @Test
    public void oddSecondOfDay() throws Exception {

        Assert.assertNotNull(DateUtil.oddSecondOfDay());
    }

    @Test
    public void oddSecondOfWeek() throws Exception {

        Assert.assertNotNull(DateUtil.oddSecondOfWeek());
    }

    @Test
    public void oddSecondOfMonth() throws Exception {

        Assert.assertNotNull(DateUtil.oddSecondOfMonth());
    }

    @Test
    public void oddSecondOfYear() throws Exception {

        Assert.assertNotNull(DateUtil.oddSecondOfYear());
    }

}
