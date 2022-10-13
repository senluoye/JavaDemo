package com.example.sentineldemo.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Dessert
 * @Description 最简单的 QPS 限流，当 QPS 超过2时，该接口就会返回限流信息
 * <p>QPS：每秒访问量</>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-08 20:39
 */
@RestController
public class test {
    @RequestMapping("sentinel")
    public String sentinel() {
        initFlowRules();
        String resourceName = "testSentinel";
        Entry entry = null;
        String retVal;
        try {
            entry = SphU.entry(resourceName, EntryType.IN);
            retVal = "passed";
        }catch(Exception e) {
            retVal = "blocked";
        }finally {
            if(entry != null) {
                entry.exit();
            }
        }

        return retVal;

    }

    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("testSentinel");// 资源名称
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); //qps 降级
        rule.setCount(2); // 每秒 2次，超过2次就失败
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
