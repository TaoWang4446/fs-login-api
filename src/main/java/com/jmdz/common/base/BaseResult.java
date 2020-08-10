package com.jmdz.common.base;

/**
 * BaseResult
 *
 * @author LiCongLu
 * @date 2020-05-29 13:54
 */
public class BaseResult<T> extends BaseBean {

    /**
     * 0.请求服务失败，(包含访问数据库失败、处理过程异常等操作)；
     * 1.请求服务成功完成，若有其他状态，依照具体接口而定
     */
    private int code;

    /**
     * 具体错误信息，用服务端提示
     */
    private String message="";

    /**
     * 返回数据
     */
    private T data;

    public BaseResult(){
    }

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public BaseResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
