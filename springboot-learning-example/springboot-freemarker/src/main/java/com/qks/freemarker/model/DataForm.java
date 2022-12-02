package com.qks.freemarker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DataForm
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-14 11:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataForm {

    private String userName;
    private String password;
    private String createTime;
    private String remarks;
}
