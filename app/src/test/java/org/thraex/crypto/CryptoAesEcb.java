package org.thraex.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author 鬼王
 * @date 2021/12/24 16:40
 */
public class CryptoAesEcb {

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException,
            NoSuchAlgorithmException, NoSuchPaddingException {
        String message = "THRAEX";

        Charset charset = Charset.forName("UTF-8");
        byte[] key = "1234567890abcdef".getBytes(charset);
        byte[] data = message.getBytes(charset);

        byte[] encrypted = encrypt(key, data);
        System.out.println(String.format("Encrypted: %s", Base64.getEncoder().encodeToString(encrypted)));

        byte[] decrypted = decrypt(key, encrypted);
        System.out.println(String.format("Decrypted: %s", new String(decrypted, charset)));

        System.out.println("########################################");
        byte[] encrypted1 = crypto(Cipher.ENCRYPT_MODE, key, data);
        System.out.println(String.format("Encrypted: %s", Base64.getEncoder().encodeToString(encrypted1)));

        byte[] decrypted1 = crypto(Cipher.DECRYPT_MODE, key, encrypted1);
        System.out.println(String.format("Decrypted: %s", new String(decrypted1, charset)));
    }

    private static byte[] crypto(int mode, byte[] key, byte[] input) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(mode, keySpec);

        return cipher.doFinal(input);
    }

    private static byte[] encrypt(byte[] key, byte[] input) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        return cipher.doFinal(input);
    }

    private static byte[] decrypt(byte[] key, byte[] input) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        return cipher.doFinal(input);
    }

}
