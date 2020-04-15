package algorithms;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Elgamal {

    BigInteger S2;
    BigInteger S1;
    modularExponention fastModularExponentiation = new modularExponention();

    public Elgamal() {

    }

    public BigInteger[] elGamalSignature(byte [] tobyte, BigInteger privateKey, BigInteger p, BigInteger g) throws NoSuchAlgorithmException, IOException {

        //byte [] tobyte = MessageDigest.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(tobyte);
        BigInteger M = new BigInteger(1, messageDigest.digest());
        //System.out.println("Value of M:" + M);
        BigInteger k = BigInteger.valueOf(13);

        while(true){
            if(privateKey.compareTo(BigInteger.ONE) > 0 && privateKey.compareTo(p.subtract(BigInteger.ONE)) < 0) {
                break;
            }
        }

        S1 = fastModularExponentiation.exponentMod(g,k,p);
        BigInteger inverseOfK = k.modInverse(p.subtract(BigInteger.ONE));
       // System.out.println("Inverse K:" + inverseOfK);
        BigInteger tempValue = M.subtract(S1.multiply(privateKey));
        S2 = (inverseOfK.multiply(tempValue)).mod(p.subtract(BigInteger.ONE));

        BigInteger SignatureArray[] = {S1,S2};
       //System.out.println("Signature:(" + S1 + "," + S2 + ")");
        return SignatureArray;
    }

    public boolean verifySignature(byte [] tobyte, BigInteger g, BigInteger p, BigInteger publicKey) throws NoSuchAlgorithmException, IOException{

        //byte [] tobyte = MessageDigest.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(tobyte);
        BigInteger M = new BigInteger(1, messageDigest.digest());
        //System.out.println("Value of M:" + M);
        BigInteger temp3,temp4;
        temp3 = fastModularExponentiation.exponentMod(g,M,p);
        temp4 = (fastModularExponentiation.exponentMod(publicKey,S1,p).multiply(fastModularExponentiation.exponentMod(S1,S2,p))).mod(p);

        //System.out.println("Temp3 Value: " + temp3);
        //System.out.println("Temp4 Value: " + temp4);

        if(temp3.equals(temp4))
        {
            System.out.println("Signature is valid(Using El Gamal verification)");
            return true;
        }
        else
        {
            System.out.println("Invalid!!!!");
            return false;
        }

    }

}
