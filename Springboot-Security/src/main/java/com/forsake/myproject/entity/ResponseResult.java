package com.forsake.myproject.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName ResponseResult
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-09 11:21
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;

    /**
     * 查询到的结果数据，
     */
    private T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
}
