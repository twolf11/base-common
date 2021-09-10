package com.lcy.base.common.response;

/**
 * @Description 返回值接口
 * @Author lcy
 * @Date 2019/12/26 16:21
 */
public interface ResultCode {

    /**
     * 操作是否成功,true为成功，false操作失败
     *
     * @return boolean
     * @author lcy
     * @date 2019/12/26 16:21
     **/
    boolean isSuccess();

    /**
     * 操作代码
     *
     * @return java.lang.String
     * @author lcy
     * @date 2019/12/26 16:22
     **/
    String getCode();

    /**
     * 提示信息
     *
     * @return java.lang.String
     * @author lcy
     * @date 2019/12/26 16:22
     **/
    String getMessage();

}