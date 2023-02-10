package com.lcy.common.core.config;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lcy.common.core.enums.BaseEnum;

/**
 * @Description 转换BaseEnum枚举json序列化
 * @Author lcy
 * @Date 2022/2/25 15:22
 */
public class InstanceBaseEnumJsonSerializer<T extends BaseEnum> extends JsonSerializer<BaseEnum> implements Serializable {

    @Override public void serialize(BaseEnum baseEnum,JsonGenerator jsonGenerator,SerializerProvider serializerProvider) throws IOException{
        jsonGenerator.writeString(baseEnum == null ? null : baseEnum.getType().toString());
    }
}
