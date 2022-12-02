package com.qks.freemarker.controller;

import com.qks.freemarker.model.DataForm;
import com.qks.freemarker.service.impl.WebDataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName IndexController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-14 11:10
 */
@Controller
public class IndexController {

    @Resource
    WebDataService service;

    @GetMapping("/")
    public String getIndex(Map<String, Object> map){
        map.put("userName", "131******1232");
        map.put("password", "abc*******123");
        map.put("createTime", "2019-12-08 20:36:32");
        map.put("remarks", "如：可以使用（禁止带空格）can_be_used");

        return "index";
    }

    @PostMapping("/getRecord")
    public String getRecord(Map<String, Object> map){

        DataForm unUserdAccount = service.getUnUserdAccount();
        if(unUserdAccount != null){
            map.put("userName", unUserdAccount.getUserName());
            map.put("password", unUserdAccount.getPassword());
            map.put("createTime",unUserdAccount.getCreateTime());
            map.put("remarks", "");
        }
        else{

            //数据库中无数据的情况下：
            map.put("userName", "无数据，请联系站长补充！");
            map.put("password", "无数据，请联系站长补充！");
            map.put("createTime", "无数据，请联系站长补充！");
            map.put("remarks", "无数据，请联系站长补充！");
        }
        return "index";
    }

    @PostMapping("/updateWebData")
    public String updateWebData(@ModelAttribute("form") DataForm form){

        DataForm webData = new DataForm();
        webData.setUserName(form.getUserName());
        webData.setPassword(form.getPassword());
        webData.setRemarks(form.getRemarks());

        if(service.updateRemarksByWebData(webData)){
            return "redirect:/index";
        }

        System.out.println("有异常");
        return "redirect:/index";
    }
}
