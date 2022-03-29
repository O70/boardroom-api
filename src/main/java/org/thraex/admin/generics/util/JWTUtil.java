package org.thraex.admin.generics.util;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.List;

/**
 * @author 鬼王
 * @date 2022/03/28 21:12
 */
@Deprecated
public abstract class JWTUtil {

    public static void main(String[] args) {
        //hello();
        example();
    }

    public static void example() {
        try {
            String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgVt4gqPbRI6I+hx3bX9pOPQzpoRNXO+zA5el5gTmhOE6mpfJFmg+097/Msn879H/REZfn2GGT+jGug4WbR5gMKA2G/73M0gfK69Uo6vdS0zMeUeDreAsYG5appgBt3P5zziG/1HCzwjw4Wft57owhDVJFOpkO65n6gKiNR3q6pbelVm2UZcLCSJDF/tt4DhKdyaBwK0xFJ/wRGPKBVBmu1uJKN4KbIQQjhr52BelUdrMJAI+d53ZjNkudK4TVkQikmSpHHYGji/rQ1OKEjSEEanqoGy5oDfzP6Xr//oMMSEAPLFOweJEx77omRKUkmwp4RYKUfy0F1ln8OxAWNrQfQIDAQAB";
            String privateKeyStr = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCBW3iCo9tEjoj6HHdtf2k49DOmhE1c77MDl6XmBOaE4Tqal8kWaD7T3v8yyfzv0f9ERl+fYYZP6Ma6DhZtHmAwoDYb/vczSB8rr1Sjq91LTMx5R4Ot4CxgblqmmAG3c/nPOIb/UcLPCPDhZ+3nujCENUkU6mQ7rmfqAqI1Herqlt6VWbZRlwsJIkMX+23gOEp3JoHArTEUn/BEY8oFUGa7W4ko3gpshBCOGvnYF6VR2swkAj53ndmM2S50rhNWRCKSZKkcdgaOL+tDU4oSNIQRqeqgbLmgN/M/pev/+gwxIQA8sU7B4kTHvuiZEpSSbCnhFgpR/LQXWWfw7EBY2tB9AgMBAAECggEAGYHq+n/LDfK5SfBxEFNucT+eK7is9KWDfDLcEMZomk07XB6QFW4K7YZsdhxkVvnmxxTFaE03yewRu8BpZaz2tL/yy4R0RFV1aAzTuM57/YwwSb3zVkb9GSrJj04sEHu7B8SSwifiLScLMLdorygExx0mwwwRJb+XWAqa57R1jTpXnQjv4Nft6PVifMVnGymIjHHfVXUJkqXzmlbeWhdJhEwOfJRUMFwTp2Gi1v15Z5FHuIh5/NGxRsPje0s4YMSTbu4FDiMwlzFCy9zkDk/BZsLK9sYEcs+92Ay69z55ZjFWX7xXL7CyPLgTQ4y9bQE1TmqaAyVjyh64Bg/gKM6GYQKBgQDc5k8UDX/ld1i3452LtX8XaHpVY0yQGnbKzp4Zob1YQLUtVpuiTZHzhbM5pSVQFlCXkzhSiPyQvsmaArr6jFriWEUhbYgmIDlFa2StS0ZH0Gcw8rdf6q0vzujchZrRm546oQO/FgdWNz2K85olTVqoM2+4Hbpi8oFvFHSED1hFdQKBgQCV6W3w+Vo6IlUYBzkFKMoLGgjyPJreWotHP+FyzTTjqK0UE/i9P8PbjYpGTEHSOwfzg7DP/qR93arnAhIV6qM4RfrsDj+TuBMD9OaknNvRbl0EpZY5nvSNwqjp62CVPVLSQ9kBW7UtGGSuiVxJHxm9v6N/6PRjGeYNzejdLW0V6QKBgFo+pNPWaAfA6DfH/5cSAOf5QPEdbiv5A8r6+lASaZ5iYSIyncaC1jucxYmpVEMRur8R4BKn8DbaGtaWgvjU2lRaJ3PuoY6h34PiyfCaLg4sr9upbQz8fOBpMWzWEFfNsajWGwe34itwye24c6MFpSHOUbfwPTMrS4Gr46YH9tH5AoGAAS4pU3BjKXoDuYC1DjlX/eZik6WugnmsBw+VstWyyOgXFMVje/n4jM38fLk0+3bDhUNQLRMQMH2CTvdRNSL3zgWfCCTEk2ErpShUeI9Tm76GtPaozCNYQZV6xvy3cfVdpZIrLzuNnaFHiahDNcAs77WGkAdBhVY63Xj1kGg/J6ECgYBAdm6guGXpNCuTnryDN/w+Ufj3BKJm83d+qfBGAw7C3VhtTeARZzaUGugMSZTYC48Xx6K1dgwzYsIJtqkDZVuBLgRI39lXZbiKPGUSWhupzjNymW6Qkjw+SeULXPvwlAVGscm3K0zhf/ovgJiCXc1mzozO0yXWTZaqqHp0jnMZOQ==";
            KeyPair keyPair = RSAUtil.parser(publicKeyStr, privateKeyStr);

            RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
            rsaJsonWebKey.setKeyId("THRAEX-KEY-ID");
            rsaJsonWebKey.setPrivateKey(keyPair.getPrivate());

            JwtClaims claims = new JwtClaims();
            claims.setIssuer("Issuer");
            claims.setAudience("Audience", "Audience1");
            claims.setExpirationTimeMinutesInTheFuture(60 * 2);
            claims.setGeneratedJwtId();
            claims.setIssuedAtToNow();
            claims.setNotBeforeMinutesInThePast(2);
            claims.setSubject("subject");
            claims.setClaim("email","mail@example.com");
            List<String> groups = Arrays.asList("group-one", "other-group", "group-three");
            claims.setStringListClaim("groups", groups);

            JsonWebSignature jws = new JsonWebSignature();
            jws.setPayload(claims.toJson());
            jws.setKey(rsaJsonWebKey.getPrivateKey());
            jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
            String jwt = jws.getCompactSerialization();

            System.out.println("JWT: " + jwt);
            System.out.println(".....");

            JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime() // the JWT must have an expiration time
                    .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                    .setRequireSubject() // the JWT must have a subject claim
                    .setExpectedIssuer("Issuer") // whom the JWT needs to have been issued by
                    .setExpectedAudience("Audience1") // to whom the JWT is intended for
                    //.setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                    .setVerificationKey(keyPair.getPublic())
                    .setJwsAlgorithmConstraints( // only allow the expected signature algorithm(s) in the given context
                            AlgorithmConstraints.ConstraintType.PERMIT, AlgorithmIdentifiers.RSA_USING_SHA256) // which is only RS256 here
                    .build(); // create the JwtConsumer instance

            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            System.out.println("JWT validation succeeded! " + jwtClaims);
        } catch (JoseException | InvalidJwtException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public static void hello() {
        try {
            Key key = new AesKey(ByteUtil.randomBytes(16));
            JsonWebEncryption jwe = new JsonWebEncryption();
            jwe.setPayload("Hello World!");
            jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
            jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
            jwe.setKey(key);
            String serializedJwe = jwe.getCompactSerialization();
            System.out.println("Serialized Encrypted JWE: " + serializedJwe);

            jwe = new JsonWebEncryption();
            jwe.setAlgorithmConstraints(new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT,
                    KeyManagementAlgorithmIdentifiers.A128KW));
            jwe.setContentEncryptionAlgorithmConstraints(new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT,
                    ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256));
            jwe.setKey(key);
            jwe.setCompactSerialization(serializedJwe);
            System.out.println("Payload: " + jwe.getPayload());
        } catch (JoseException e) {
            e.printStackTrace();
        }
    }

}
