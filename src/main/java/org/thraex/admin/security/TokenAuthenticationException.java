package org.thraex.admin.security;

import org.springframework.security.core.AuthenticationException;
import org.thraex.admin.generics.response.ResponseStatus;

/**
 * @author 鬼王
 * @date 2022/03/30 18:40
 */
public class TokenAuthenticationException extends AuthenticationException {

    private ResponseStatus status;

    public TokenAuthenticationException(ResponseStatus status) {
        super(status.phrase());
        this.status = status;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public static TokenAuthenticationException of(ResponseStatus status) {
        return new TokenAuthenticationException(status);
    }

}
