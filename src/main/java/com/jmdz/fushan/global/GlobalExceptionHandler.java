package com.jmdz.fushan.global;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.GsonUtil;
import com.jmdz.fushan.model.config.Constants;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


/**
 * GlobalExceptionHandler
 *
 * @author LiCongLu
 * @date 2020-07-08 10:52
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 运行时异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public String runtimeExceptionHandler(RuntimeException ex) {
        return resultFormat(0, ex);
    }

    /**
     * 空指针异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException ex) {
        return resultFormat(2, ex);
    }

    /**
     * 类型转换异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    public String classCastExceptionHandler(ClassCastException ex) {
        return resultFormat(3, ex);
    }

    /**
     * IO异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IOException.class)
    public String iOExceptionHandler(IOException ex) {
        return resultFormat(4, ex);
    }

    /**
     * 未知方法异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return resultFormat(5, ex);
    }

    /**
     * 数组越界异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return resultFormat(6, ex);
    }

    /**
     * 400错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public String requestNotReadable(HttpMessageNotReadableException ex) {
        System.out.println("400..requestNotReadable");
        return resultFormat(7, ex);
    }

    /**
     * 400错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class})
    public String requestTypeMismatch(TypeMismatchException ex) {
        System.out.println("400..TypeMismatchException");
        return resultFormat(8, ex);
    }

    /**
     * 400错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public String requestMissingServletRequest(MissingServletRequestParameterException ex) {
        System.out.println("400..MissingServletRequest");
        return resultFormat(9, ex);
    }

    /**
     * 405错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public String request405(HttpRequestMethodNotSupportedException ex) {
        return resultFormat(10, ex);
    }

    /**
     * 406错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public String request406(HttpMediaTypeNotAcceptableException ex) {
        System.out.println("406...");
        return resultFormat(11, ex);
    }

    /**
     * 500错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public String server500(RuntimeException ex) {
        System.out.println("500...");
        return resultFormat(12, ex);
    }

    /**
     * 栈溢出
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({StackOverflowError.class})
    public String requestStackOverflow(StackOverflowError ex) {
        return resultFormat(13, ex);
    }

    /**
     * 其他错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public String exception(Exception ex) {
        return resultFormat(14, ex);
    }

    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return GsonUtil.toJson(new BaseResult(Constants.FAILURE, "参数错误：" + e.getBindingResult().getFieldError().getDefaultMessage()));
    }

    private <T extends Throwable> String resultFormat(Integer code, T ex) {

        String message = "引发异常类，" + code + "；异常信息，" + ex.getMessage();
        LogUtil.error(message, ex);
        return GsonUtil.toJson(new BaseResult(code, ex.getMessage()));
    }
}