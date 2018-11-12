package com.hk.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.hk.utils.SortUtil.ASC;
import static com.hk.utils.SortUtil.DESC;

public class SortTest {
    public static void main(String[] args) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(new OrderDetail("aaa", "10",1.0, 1.0));
        orderDetailList.add(new OrderDetail("bbb", "10",1.0, 0.5));
        orderDetailList.add(new OrderDetail("ccc", "10",3.0, 1.0));
        orderDetailList.add(new OrderDetail("ddd", "10",1.0, 0.8));
        orderDetailList.add(new OrderDetail("ddd", "10",1.8, 5.8));
        orderDetailList.add(new OrderDetail("ddd", "10",1.0, 4.8));



    }
}
