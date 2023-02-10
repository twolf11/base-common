package com.lcy.common.core.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lcy.common.core.exception.BusinessException;

/**
 * @Description 校验枚举值自定义注解
 * @Author lcy
 * @Date 2021/05/4 18:18
 */
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumCheck.Validator.class)
public @interface EnumCheck {

    /**
     * 提示信息
     */
    String message() default "";

    /**
     * 分组
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 枚举类class
     */
    Class<? extends Enum<?>> enumClass();

    /**
     * 枚举类校验方法
     */
    String enumMethod();

    class Validator implements ConstraintValidator<EnumCheck,Object> {

        private static final Logger log = LoggerFactory.getLogger(Validator.class);

        private Class<? extends Enum<?>> enumClass;

        private String enumMethod;

        @Override
        public void initialize(EnumCheck enumCheck){
            enumMethod = enumCheck.enumMethod();
            enumClass = enumCheck.enumClass();
        }

        @Override
        public boolean isValid(Object value,ConstraintValidatorContext constraintValidatorContext){
            if (value == null) {
                return Boolean.TRUE;
            }
            if (enumClass == null || enumMethod == null) {
                return Boolean.TRUE;
            }
            Class<?> valueClass = value.getClass();

            try {
                //方法一定要加上public声明，否则获取不到
                Method method = enumClass.getMethod(enumMethod,valueClass);
                if (!Boolean.TYPE.equals(method.getReturnType()) && !Boolean.class.equals(method.getReturnType())) {
                    log.error("校验注解返回值类型必须是boolean类型,enumMethod:{},enumClass:{} ",enumMethod,enumClass);
                    throw new BusinessException("自定义校验异常");
                }
                //判断静态方法
                if (!Modifier.isStatic(method.getModifiers())) {
                    log.error("校验方法必须是静态方法,enumMethod:{},enumClass:{} ",enumMethod,enumClass);
                    throw new BusinessException("自定义校验异常");
                }

                Boolean result = (Boolean)method.invoke(null,value);
                return result != null && result;
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException | SecurityException e) {
                log.error("方法{}不存在类{}当中",enumMethod,enumClass,e);
                throw new BusinessException("自定义校验异常");
            }
        }

    }
}
