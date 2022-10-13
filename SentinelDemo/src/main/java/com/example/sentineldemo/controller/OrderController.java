package com.example.sentineldemo.controller;

import com.alibaba.csp.sentinel.Entry;
import com.example.sentineldemo.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-08 21:13
 */
@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderQueryService orderQueryService;

    public OrderController(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    /**
     * 没有限流的接口
     * @param orderId
     * @return
     */
    @RequestMapping("/getOrder")
    public String queryOrder1(@RequestParam("orderId") String orderId) {
        return orderQueryService.queryOrderInfo(orderId);
    }

    /**
     * 被限流的接口
     * @param orderId
     * @return
     */
    @RequestMapping("/getOrder1")
    public String queryOrder2(@RequestParam("orderId") String orderId) {
        Entry entry = null;
        // 资源名
        String resourceName = "orderQueryService";
        try {
            // entry可以理解成入口登记
            entry = SphU.entry(resourceName);
            // 被保护的逻辑, 这里为订单查询接口
            return orderQueryService.queryOrderInfo(orderId);
        } catch (BlockException blockException) {
            // 接口被限流的时候, 会进入到这里
            log.warn("---getOrder1接口被限流了---, exception: ", blockException);
            return "接口限流, 返回空";
        } finally {
            // SphU.entry(xxx) 需要与 entry.exit() 成对出现,否则会导致调用链记录异常
            if (entry != null) {
                entry.exit();
            }
        }

    }
}
