package com.jmdz.common.annotation;

import com.jmdz.common.constant.EValidateCode;

import java.lang.annotation.*;

/**
 * AnValidate
 *
 * @author LiCongLu
 * @date 2020-01-10 10:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface AnValidate {
    /**
     * 可填写默认值
     *
     * @return
     */
    String value() default "";

    /**
     * 名称
     *
     * @return
     */
    String name() default "";

    /**
     * 必填
     *
     * @return
     */
    boolean required() default false;

    /**
     * 验证枚举编码
     *
     * @return
     */
    EValidateCode code() default EValidateCode.EMPTY;

    /**
     * 提示信息
     *
     * @return
     */
    String message() default "";

}
