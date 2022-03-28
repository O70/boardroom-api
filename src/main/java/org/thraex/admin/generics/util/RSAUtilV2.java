package org.thraex.admin.generics.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public abstract class RSAUtilV2 {

    public static final String ALGORITHM = "RSA";
    public static final int KEY_SIZE = 2048;

    public static void generator() throws NoSuchAlgorithmException {
        generator(KEY_SIZE);
    }

    public static void generator(Integer keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
        generator.initialize(Optional.ofNullable(keySize).orElse(KEY_SIZE));
        KeyPair keyPair = generator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        try (
                FileOutputStream pub = new FileOutputStream("public.key");
                FileOutputStream prv = new FileOutputStream("private.key")
        ) {
            pub.write(publicKey.getEncoded());
            prv.write(privateKey.getEncoded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String raw) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Path path = Paths.get("public.key");
        byte[] keyBytes = Files.readAllBytes(path);

        KeyFactory instance = KeyFactory.getInstance(ALGORITHM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        PublicKey pubKey = instance.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] rawBytes = raw.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = cipher.doFinal(rawBytes);

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encrypted) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Path path = Paths.get("private.key");
        byte[] keyBytes = Files.readAllBytes(path);

        KeyFactory instance = KeyFactory.getInstance(ALGORITHM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        PrivateKey prvKey = instance.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, prvKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(encrypted);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        generator();

        String raw = "hanzo";
        String encrypted = encrypt(raw);
        System.out.println("Encrypted:");
        System.out.println(encrypted);

        System.out.println("Decrypted:");
        System.out.println(decrypt(encrypted));
    }

}
