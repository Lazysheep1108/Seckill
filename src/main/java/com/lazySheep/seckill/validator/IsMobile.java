package com.lazySheep.seckill.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.validator
 * @date 2025/6/13 19:17
 * @project seckill
 * @description Develop a custom annotation
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class})
public @interface IsMobile {
    String message() default "手机号码格式错误";

    boolean required() default true;

    Class<?>[] groups() default {};//默认参数

    Class<? extends Payload>[] payload() default {};//默认参数
}