package com.gxk.shiro.POJO.VO;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 展现给客户端界面的类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO {

    /**错误码*/
    private Integer code;
    /**错误信息*/
    private String message;
    /**返回对象*/
    private Object object;

}
