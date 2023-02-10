package com.lcy.common.core.enums;

/**
 * @Description 基础枚举接口
 * @Author lcy
 * @Date 2022/2/24 19:48
 */
public interface BaseEnum {

    /**
     * 获取类型
     * @return java.lang.Integer
     * @author lcy
     * @date 2022/2/24 19:49
     **/
    Integer getType();

    /**
     * 校验支付类型是否存在
     * @param enumType 枚举对象
     * @param type     类型
     * @return boolean
     * @author lcy
     * @date 2022/1/5 9:36
     **/
    static boolean checkExist(Class<? extends BaseEnum> enumType,Integer type){
        BaseEnum[] values = enumType.getEnumConstants();
        for (BaseEnum baseEnum : values) {
            if (baseEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

}
