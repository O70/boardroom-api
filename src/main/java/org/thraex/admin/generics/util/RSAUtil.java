package org.thraex.admin.generics.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2022/03/28 15:23
 */
public abstract class RSAUtil {

    public static final String ALGORITHM = "RSA";
    public static final int KEY_SIZE = 2048;
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static final Base64.Encoder ENCODER = Base64.getEncoder();
    public static final Base64.Decoder DECODER = Base64.getDecoder();

    public static Pairs generator() throws NoSuchAlgorithmException {
        return generator(KEY_SIZE);
    }

    public static Pairs generator(Integer keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
        generator.initialize(Optional.ofNullable(keySize).orElse(KEY_SIZE));
        KeyPair keyPair = generator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String pub = ENCODER.encodeToString(publicKey.getEncoded());
        String prv = ENCODER.encodeToString(privateKey.getEncoded());
        //String pub = new String(publicKey.getEncoded(), UTF_8);
        //String prv = new String(privateKey.getEncoded(), UTF_8);

        return new Pairs(pub, prv);
    }

    public static String encrypt(String raw, String publicKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        //byte[] decoded = DECODER.decode(publicKey);
        byte[] decoded = publicKey.getBytes(UTF_8);
        KeyFactory instance = KeyFactory.getInstance(ALGORITHM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        PublicKey pubKey = instance.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] bytes = cipher.doFinal(raw.getBytes(UTF_8));

        return ENCODER.encodeToString(bytes);
    }

    public static String decrypt(String encrypted, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //byte[] decoded = DECODER.decode(encrypted);

        byte[] decoded = DECODER.decode(privateKey);
        KeyFactory instance = KeyFactory.getInstance(ALGORITHM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        PrivateKey prvKey = instance.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, prvKey);

        //byte[] bytes = cipher.doFinal(encrypted.getBytes(UTF_8));
        byte[] bytes = cipher.doFinal(DECODER.decode(encrypted.getBytes(UTF_8)));

        return ENCODER.encodeToString(bytes);
    }

    public static class Pairs {

        private String pub;

        private String prv;

        public Pairs(String pub, String prv) {
            this.pub = pub;
            this.prv = prv;
        }

        public String pub() {
            return this.pub;
        }

        public String prv() {
            return this.prv;
        }

        @Override
        public String toString() {
            return "Keys{" +
                    "pub='" + pub + '\'' +
                    ", prv='" + prv + '\'' +
                    '}';
        }

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        Pairs pairs = generator();
        System.out.println("Public key:");
        System.out.println(pairs.pub());
        System.out.println("Private key:");
        System.out.println(pairs.prv());

        //String raw = "hanzo";
        //String encrypted = encrypt(raw, pairs.pub());
        //System.out.println("Encrypted:");
        //System.out.println(encrypted);
        //
        //System.out.println("Decrypted:");
        //System.out.println(decrypt(encrypted, pairs.prv()));
    }

}
