package org.thraex.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.thraex.admin.generics.response.ResponseResult;
import org.thraex.admin.generics.response.ResponseStatus;

/**
 * @author 鬼王
 * @date 2022/03/10 11:13
 */
@RestControllerAdvice
public class RestExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(EmptyResultDataAccessException.class)
    ResponseResult handler(EmptyResultDataAccessException e) {
        logger.warn("EmptyResultDataAccessException: {}", e.getMessage());

        // TODO: Opt
        return ResponseResult.fail(ResponseStatus.TARGET_NOT_EXIST, "Target does not exist.");
    }

    @ExceptionHandler(Exception.class)
    ResponseResult handler(Exception e) {
        logger.error("Handling exception", e);

        return ResponseResult.fail(e.getMessage());
    }

}
