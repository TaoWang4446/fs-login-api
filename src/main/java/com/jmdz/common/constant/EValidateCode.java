package com.jmdz.common.constant;

/**
 * 验证枚举类
 *
 * @author LiCongLu
 * @date 2020-01-10 14:45
 */
public enum EValidateCode {
    /**
     * 默认为空
     */
    EMPTY(0, ""),
    /**
     * 必填
     */
    REQUIRED(1, "必填"),

    /**
     * 不允许小于零
     */
    NO_LESS_ZERO(2, "不允许小于零"),

    /**
     * 必须大于零
     */
    GREATER_ZERO(3, "必须大于零");

    /**
     * 枚举值
     */
    private Integer code;
    /**
     * 枚举信息
     */
    private String message;

    private EValidateCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public EValidateCode setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public EValidateCode setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 按照Code获取枚举
     *
     * @param code 枚举值
     * @return EResultCode
     * @author LiCongLu
     * @date 2019-12-20 14:19
     */
    public static EValidateCode getValidateCode(Integer code) {
        if (code != null) {
            for (EValidateCode codeEnum : EValidateCode.values()) {
                if (codeEnum.getCode() != null) {
                    if (codeEnum.getCode().equals(code)) {
                        return codeEnum;
                    }
                }
            }
        }
        return EValidateCode.EMPTY;
    }


    /**
     * 判断枚举与Code值是否相等
     *
     * @param code 判断值
     * @return
     * @author LiCongLu
     * @date 2020-01-10 14:53
     */
    public boolean equalEnum(Integer code) {
        return this.getCode().equals(code);
    }

    /**
     * 获取验证信息
     *
     * @param message 外部设置信息
     * @return
     * @author LiCongLu
     * @date 2020-01-10 14:56
     */
    public String message(String message) {
        if (message != null && message.length() > 0) {
            return message;
        }
        return this.message;
    }

    @Override
    public String toString() {
        return "EValidateCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
