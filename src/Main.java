import java.math.BigInteger;
import algorithms.*;
import algorithms.AES;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Main {

    public static void main(String[] args) {

        FermatLittle fermatLittle = new FermatLittle();
        EuclidAlgorithm euclidAlgorithm = new EuclidAlgorithm();
        modularExponention modularExponention = new modularExponention();
        AES AES = new AES();

        System.out.println("Test Cases for each module.");
        System.out.println("");
        System.out.println("Test extended Euclidean algorithm:");

        BigInteger A = BigInteger.valueOf(12345);
        BigInteger B = BigInteger.valueOf(11111);
        System.out.println("Values : " + A + "," + B);
        System.out.println("Result: " + EuclidAlgorithm.extendedEuclidean(A, B));
        System.out.println("");

        System.out.println("Test GCD algorithm:");

        BigInteger A2 = BigInteger.valueOf(19);
        BigInteger B2 = BigInteger.valueOf(23);
        System.out.println("Values : " + A2 + "," + B2);
        System.out.println("Result: " + EuclidAlgorithm.gcd(A2, B2));
        System.out.println("");

        System.out.println("Test the multiplicative inverse:");

        BigInteger Num1 = new BigInteger("7");
        BigInteger Num2 = new BigInteger("11");

        System.out.println("Values :" + Num1 + "," + Num2);
        System.out.println("Result:" + EuclidAlgorithm.multiplicativeInverse(Num1, Num2));
        System.out.println("");

        System.out.println("Test the relatively prime condition");

        BigInteger RP1 = new BigInteger("17");
        BigInteger RP2 = new BigInteger("12345");

        System.out.println("Values : " + RP1 + "," + RP2);
        System.out.println("Result:" + EuclidAlgorithm.isRelativePrime(RP1, RP2));
        System.out.println("");

        System.out.println("Test the Fermat Little Theorem");
        BigInteger base = BigInteger.valueOf(3);
        BigInteger power = BigInteger.valueOf(11);
        System.out.println("Values : " + base + "," + power);
        System.out.println("Result: " + FermatLittle.isRelativelyPrime(base, power));
        System.out.println("");

        BigInteger base1 = BigInteger.valueOf(24);
        BigInteger power1 = BigInteger.valueOf(221);
        System.out.println("Values : " + base1 + "," + power1);
        System.out.println("Result: " + FermatLittle.isRelativelyPrime(base1, power1));
        System.out.println("");

        System.out.println("Test the Fast Modular Exponentiation");
        BigInteger me1 = BigInteger.valueOf(5);
        BigInteger me2 = BigInteger.valueOf(2);
        BigInteger me3 = BigInteger.valueOf(3);
        System.out.println("Values : " + me1 + "," + me2 + "," + me3);
        System.out.println("Result: " + modularExponention.exponentMod(me1, me2, me3));

        System.out.println("");

        System.out.println("Test the Fast Modular Exponentiation");
        BigInteger me4 = BigInteger.valueOf(15);
        BigInteger me5 = BigInteger.valueOf(10);
        BigInteger me6 = BigInteger.valueOf(21);
        System.out.println("Values : " + me4 + "," + me5 + "," + me6);
        System.out.println("Result: " + modularExponention.exponentMod(me4, me5, me6));

        System.out.println("");

        System.out.println("Test of AES Encryption And Decryption");
        String secretKey = "stringkeyisgenerateddifferently";

        String originalString = "cryptographyisamazing";
        String encryptedString = AES.encrypt1(originalString, secretKey) ;
        String decryptedString = AES.decrypt1(encryptedString, secretKey) ;

        System.out.println("");

        System.out.println("Original String: " + originalString);
        System.out.println("Encrypted with Specific Key: " + encryptedString);
        System.out.println("Decrypted with Specific Key: " + decryptedString);

    }

}
