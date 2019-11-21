package algorithms;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

public class AES {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public AES() {
    }

    public static SecretKeySpec setKey(String password) {

        MessageDigest shared = null;
        try {
            key = password.getBytes("UTF-8");
            shared = MessageDigest.getInstance("SHA-1");
            key = shared.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            return secretKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String encrypt1(String stringToEncrypt1, String secretVariable) {
        try {
            setKey(secretVariable);
            Cipher cipherText1 = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipherText1.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipherText1.doFinal(stringToEncrypt1.getBytes("UTF-8")));

        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt1(String stringToDecrypt1, String secretVariable) {
        try {
            setKey(secretVariable);
            Cipher cipher1 = Cipher.getInstance("AES/ECB/PKCS5PADDING");
           cipher1.init(Cipher.DECRYPT_MODE,secretKey);
            return new String(cipher1.doFinal(Base64.getDecoder().decode(stringToDecrypt1)));
        }  catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

}