package org.thraex.admin.generics.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2022/03/10 10:52
 */
public class Result<T> implements Serializable {

    private Integer code;

    private T data;

    private String message;

    public Result() {}

    public Result(ResultStatus status, T data, String message) {
        Assert.notNull(status, "status must not be null.");

        this.code = status.value();
        this.data = data;
        this.message = message;
    }

    public static Result of() {
        return new Result();
    }

    public static Result of(ResultStatus status) {
        return of(status, null, null);
    }

    public static <T> Result<T> of(ResultStatus status, T data) {
        return of(status, data, null);
    }

    public static Result of(ResultStatus status, String message) {
        return of(status, null, message);
    }

    public static <T> Result<T> of(ResultStatus status, T data, String message) {
        return new Result<>(status, data, message);
    }

    public static Result ok() {
        return of(ResultStatus.OK);
    }

    public static <T> Result<T> ok(T data) {
        return ok().setData(data);
    }

    public static <T> Result<T> ok(Optional<T> data) {
        return ok().setData(data);
    }

    public static <T> Result<PageWrapper> ok(Page<T> page) {
        Assert.notNull(page, "page must not be null.");

        int pages = page.getTotalPages();
        long elements = page.getTotalElements();
        int number = page.getNumber();
        int size = page.getSize();
        List<T> content = page.getContent();

        return ok(new PageWrapper(pages, elements, number, size, content));
    }

    public static Result fail() {
        return fail(null);
    }

    public static Result fail(String message) {
        return fail(ResultStatus.INTERNAL_SERVER_ERROR, message);
    }

    public static Result fail(ResultStatus status, String message) {
        return of(status, message);
    }

    public Integer getCode() {
        return code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> setCode(ResultStatus status) {
        Assert.notNull(status, "status must not be null.");

        this.code = status.value();
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
