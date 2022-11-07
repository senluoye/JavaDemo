package com.example.sentineldemo.service;

import org.springframework.stereotype.Service;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-08 21:14
 */
@Service
public class OrderQueryService {
    public String queryOrderInfo(String orderId) {
        System.out.println("获取订单信息:" + orderId);
        return "return OrderInfo :" + orderId;
    }
}
