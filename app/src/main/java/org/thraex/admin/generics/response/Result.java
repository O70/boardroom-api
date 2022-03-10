package org.thraex.admin.generics.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author 鬼王
 * @date 2022/03/10 10:52
 */
public class Result<T> implements Serializable {

    private Integer code;

    private T data;

    private String message;

    public static <T> Result<T> ok(T data) {
        return ok(200, data);
    }

    public static <T> Result<T> ok(Integer code, T data) {
        return new Result<T>().setCode(code).setData(data);
    }

    public static Result fail(String message) {
        return fail(500, message);
    }

    public static Result fail(Integer code, String message) {
        return new Result().setCode(code).setMessage(message);
    }

    public Integer getCode() {
        return code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
