package org.thraex.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @ExceptionHandler(Exception.class)
    Result handler(Exception e) {
        logger.debug("Handling exception", e);

        return Result.fail(e.getMessage());
    }

}
