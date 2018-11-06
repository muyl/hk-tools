package com.hk.utils;

import com.google.common.primitives.Ints;

/**
 * User: hk
 * Date: 2017/8/30 上午9:40
 * version: 1.0
 */
public final class NumberUtil {

    public static int toInt(String str){
        if (StringUtil.isEmpty(str)){
            return 0;
        }
        return Ints.tryParse(str);
    }
}
