package org.thraex.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author 鬼王
 * @date 2021/12/24 17:35
 */
public class CryptoAesCbc {

    private static final int IV_NUM = 16;

    public static void main(String[] args) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String message = "THRAEX";

        Charset charset = Charset.forName("UTF-8");
        byte[] key = "1234567890abcdef1234567890abcdef".getBytes(charset);
        byte[] data = message.getBytes(charset);

        byte[] encrypted = encrypt(key, data);
        System.out.println(String.format("Encrypted: %s", Base64.getEncoder().encodeToString(encrypted)));

        byte[] decrypted = decrypt(key, encrypted);
        System.out.println(String.format("Decrypted: %s", new String(decrypted, charset)));
    }

    private static byte[] encrypt(byte[] key, byte[] input) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");

        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] iv = sr.generateSeed(IV_NUM);
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivps);

        byte[] data = cipher.doFinal(input);

        return join(iv, data);
    }

    private static byte[] decrypt(byte[] key, byte[] input) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] iv = new byte[IV_NUM];
        byte[] data = new byte[input.length - IV_NUM];
        System.arraycopy(input, 0, iv, 0, IV_NUM);
        System.arraycopy(input, IV_NUM, data, 0, data.length);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");

        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivps);

        return cipher.doFinal(data);
    }

    private static byte[] join(byte[] b1, byte[] b2) {
        byte[] result = new byte[b1.length + b2.length];
        System.arraycopy(b1, 0, result, 0, b1.length);
        System.arraycopy(b2, 0, result, b1.length, b2.length);

        return result;
    }

}
