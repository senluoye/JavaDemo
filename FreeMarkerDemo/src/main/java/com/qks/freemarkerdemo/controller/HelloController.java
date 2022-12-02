package com.qks.freemarkerdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HelloController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-09 10:57
 */
@Controller
public class HelloController {
    @RequestMapping("/fm/index")
    public String fmIndex(ModelMap modelMap) {

        Map<String, String> map = new HashMap<>();

        map.put("name", "aoppp");
        map.put("desc", "描述");

        // 添加属性 给模版
        modelMap.addAttribute("user", map);

        return "fm/index";
    }

}
