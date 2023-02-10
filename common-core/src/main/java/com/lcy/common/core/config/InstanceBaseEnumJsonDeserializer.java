package com.lcy.common.core.config;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.lcy.common.core.enums.BaseEnum;
import com.lcy.common.core.util.Tools;

/**
 * @Description 转换BaseEnum枚举json反序列化
 * @Author lcy
 * @Date 2022/2/25 15:22
 */
@SuppressWarnings("unchecked")
public class InstanceBaseEnumJsonDeserializer<T extends BaseEnum> extends JsonDeserializer<BaseEnum> implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(InstanceBaseEnumJsonSerializer.class);

    @Override public BaseEnum deserialize(JsonParser jsonParser,DeserializationContext deserializationContext) throws IOException, JacksonException{
        final String text = jsonParser.getText();
        if (Tools.isEmpty(text)) {
            return null;
        }
        JsonStreamContext parsingContext = jsonParser.getParsingContext();
        //获取枚举相关信息，最终获取反射对象
        String currentName = parsingContext.getCurrentName();
        Object currentValue = parsingContext.getCurrentValue();
        Field declaredField;
        try {
            declaredField = currentValue.getClass().getDeclaredField(currentName);
        } catch (NoSuchFieldException e) {
            log.error("No such field warning: " + currentName,e);
            return null;
        }
        Class<?> targetType = declaredField.getType();
        if (!BaseEnum.class.isAssignableFrom(targetType)) {
            return null;
        }
        T[] values = (T[])targetType.getEnumConstants();
        for (T t : values) {
            if (text.equals(t.getType().toString())) {
                return t;
            }
        }
        return null;
    }
}
