package org.thraex.admin.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Set;

/**
 * https://bitbucket.org/b_c/jose4j/wiki/JWT%20Examples
 *
 * @author 鬼王
 * @date 2022/03/28 22:05
 */
@ConfigurationProperties("thraex.security.token")
public class TokenProperties {

    private final String POWER = "THRAEX";

    /**
     * Give the JWK a Key ID (kid), which is just the polite thing to do
     */
    private String keyId = String.format("%s-KEY", POWER);

    /**
     * Who creates the token and signs it
     */
    private String iss = POWER;

    /**
     * To whom the token is intended to be sent
     */
    private Set<String> aud = Collections.EMPTY_SET;

    /**
     * time when the token will expire (120 minutes from now)
     */
    private float exp = 120;

    /**
     * time before which the token is not yet valid (2 minutes ago)
     */
    private float nbf = 2;

    public String getKeyId() {
        return keyId;
    }

    public TokenProperties setKeyId(String keyId) {
        this.keyId = keyId;
        return this;
    }

    public String getIss() {
        return iss;
    }

    public TokenProperties setIss(String iss) {
        this.iss = iss;
        return this;
    }

    public Set<String> getAud() {
        return aud;
    }

    public TokenProperties setAud(Set<String> aud) {
        this.aud = aud;
        return this;
    }

    public float getExp() {
        return exp;
    }

    public TokenProperties setExp(float exp) {
        this.exp = exp;
        return this;
    }

    public float getNbf() {
        return nbf;
    }

    public TokenProperties setNbf(float nbf) {
        this.nbf = nbf;
        return this;
    }

}
