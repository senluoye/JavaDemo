package com.qks.freemarker.service.impl;

import com.qks.freemarker.model.DataForm;
import org.springframework.stereotype.Service;

/**
 * @ClassName WebDataService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-14 11:18
 */
@Service
public class WebDataService {
    public DataForm getUnUserdAccount() {
        return new DataForm("qks", "12312312", "2020.1.1", "asd");
    }

    public boolean updateRemarksByWebData(DataForm webData) {
        System.out.println("更新成功");
        return true;
    }
}
