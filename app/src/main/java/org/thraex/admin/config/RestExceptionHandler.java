package org.thraex.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.thraex.admin.generics.response.Result;

/**
 * @author 鬼王
 * @date 2022/03/10 11:13
 */
@RestControllerAdvice
public class RestExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(EmptyResultDataAccessException.class)
    Result handler(EmptyResultDataAccessException e) {
        logger.warn("EmptyResultDataAccessException: {}", e.getMessage());

        return Result.fail("Target does not exist.");
    }

    @ExceptionHandler(Exception.class)
    Result handler(Exception e) {
        logger.error("Handling exception", e);

        return Result.fail(e.getMessage());
    }

}
