package com.gxk.shiro.util;

import com.gxk.shiro.POJO.VO.ResultVO;
import com.gxk.shiro.constant.ResultEnum;
import lombok.Data;

import javax.xml.transform.Result;

/**
 * 展现给客户端界面的类
 */
@Data
public class ResultVOUtil {

    /**
     * 成功操作，传入数据对象
     * @param o
     * @return 返回ResultVO对象
     */
    public static ResultVO success(Object o) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMessage(ResultEnum.SUCCESS.getMessage());
        resultVO.setObject(o);
        return resultVO;
    }

    /**
     * 不必传入实体对象的方法
     * @return
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 操作失败，传入错误代码以及错误信息
     * @param code
     * @param message
     * @return
     */
    public static ResultVO error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }
}
