import java.io.File;
import java.math.BigInteger;
import algorithms.*;
import algorithms.AES;

import java.security.NoSuchAlgorithmException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        BigInteger privateOfAlice = BigInteger.valueOf(5);   //PrivateKey
        BigInteger privateOfBob = BigInteger.valueOf(7); //PrivateKey
        BigInteger privateOfCA = BigInteger.valueOf(9); //PrivateKey
        BigInteger publicOfAlice;
        BigInteger publicOfBob;
        BigInteger publicOfCertificateAuthority;
        BigInteger sharedCommonKey;
        BigInteger g = BigInteger.valueOf(2);
        BigInteger p = BigInteger.valueOf(23);
        byte [] file;
        byte [] file1;
        byte [] FromCACertificateForAlice;
        byte [] FromCACertificateForBob;
        File BobCertificate;
        File AliceCertificate;
        BigInteger[] aliceMessageSignature;
        BigInteger[] AliceForSignature;
        BigInteger[] BobForSignature;
        String Message;
        String encryptedMessage;
        String decryptedMessage;

        Person Alice = new Person("Alice", privateOfAlice);
        Person Bob = new Person("Bob", privateOfBob);
        CertificateAuthority CA = new CertificateAuthority("Cemal", privateOfCA);
        CertificateAuthority CA1 = new CertificateAuthority();
        DHKE diffieHellman = new DHKE();
        Elgamal elgamal = new Elgamal();
        AES AES = new AES();

        publicOfAlice = Alice.generatePublicKey(g,privateOfAlice,p);
        publicOfBob = Bob.generatePublicKey(g,privateOfBob,p);
        publicOfCertificateAuthority = CA.generatePublicKey(g,privateOfCA,p);

        System.out.println("Alice Public Key: " + publicOfAlice);
        System.out.println("Bob Public Key: " + publicOfBob);
        System.out.println("CA Public Key: " + publicOfCertificateAuthority);

        CA1.getPublicKeyOfAlice(publicOfAlice);
        CA1.getPublicKeyOfBob(publicOfBob);

        Alice.setpK(publicOfAlice);
        Bob.setpK(publicOfBob);

        CA1.createCertificate(CA.getName(),"Alice",publicOfAlice,p,g);
        CA1.createCertificate(CA.getName(),"Bob",publicOfBob,p,g);

        Alice.setCertificate("Certificate_Alice.txt");
        Bob.setCertificate("Certificate_Bob.txt");

        file = CA1.fromFile("Certificate_Alice.txt");
        file1 = CA1.fromFile("Certificate_Bob.txt");

        System.out.println("Alice's certificate is signing by Authority...");
        AliceForSignature = elgamal.elGamalSignature(file,privateOfCA,p,g);

        System.out.println("Certificate is signed for Alice...");

        System.out.println("Writing signature inside Certificate_Alice.txt...");
        CA1.setup("Alice",AliceForSignature);

        System.out.println("Sending Certificate to Alice from CA..."); //CA send the certificate to related person
        FromCACertificateForAlice=CA1.fromFile("Certificate_Alice.txt");
        AliceCertificate = Alice.obtainCertificate(FromCACertificateForAlice,"Alice");
        System.out.println("Verifying Certificate...");
        elgamal.verifySignature(file,g,p,publicOfCertificateAuthority);

        System.out.println("Sending File to Bob...");
        Alice.setS1(AliceForSignature[0]);
        Alice.setS2(AliceForSignature[1]);
        Alice.setTransferFile(AliceCertificate);
        Bob.getTransferFile(AliceCertificate);

        System.out.println("Bob is verifying the certificate...");
        String pubAlice = Bob.getPublicKeyCertificate("Certificate_Alice.txt",publicOfAlice); //gets public key from txt file
        //System.out.println(pubAlice);
        //This check function also works in order check the signature
/*
        //Checks using S1 S2 values read from the signed file and compares
        String s1Alice = String.valueOf(Alice.getS1());//gets the S1 from signed file
        String s2Alice = String.valueOf(Alice.getS2());//gets the S1 from signed file
        String s1s2 = new String(s1Alice + "\t" + s2Alice);
        //System.out.println(s1s2);
        String checkAlice = Bob.getS1S2KeyCertificate("Certificate_Alice.txt",Alice.getS1(),Alice.getS2());
        if( s1s2.equals(checkAlice)){
            System.out.println("Signature is valid");
        }
        else{
            System.out.println("Error!!!");
        }
*/

        //Using el gamal verification of the certificate comes from other user
       elgamal.verifySignature(file,g,p,publicOfCertificateAuthority);

        System.out.println("Bob's certificate is signing by Authority...");
        BobForSignature = elgamal.elGamalSignature(file1,privateOfCA,p,g);

        System.out.println("Certificate is signed for Bob...");

        System.out.println("Writing signature inside Certificate_Bob.txt...");
        CA1.setup("Bob",BobForSignature);

        System.out.println("Sending Certificate to Bob from CA...");//CA send the certificate to related person
        FromCACertificateForBob=CA1.fromFile("Certificate_Bob.txt");
        BobCertificate = Bob.obtainCertificate(FromCACertificateForBob,"Bob");
        System.out.println("Verifying Certificate...");
        elgamal.verifySignature(file1,g,p,publicOfCertificateAuthority);

        System.out.println("Sending File to Alice...");
        Bob.setS1(BobForSignature[0]);
        Bob.setS2(BobForSignature[1]);
        Bob.setTransferFile(BobCertificate);
        Alice.getTransferFile(BobCertificate);

        System.out.println("Alice is verifying the certificate...");
        String pubBob = Alice.getPublicKeyCertificate("Certificate_Bob.txt",publicOfBob);//gets public key from txt file
        //System.out.println(pubBob);

        //This check function also works in order check the signature
/*
        //Checks using S1 S2 values read from the signed file and compares
        String s1Bob = String.valueOf(Bob.getS1());//gets the S1 from signed file
        String s2Bob = String.valueOf(Bob.getS2());//gets the S1 from signed file
        String S1S2 = new String(s1Bob + "\t" + s2Bob);
        //System.out.println(S1S2);
        String checkBob = Alice.getS1S2KeyCertificate("Certificate_Bob.txt",Bob.getS1(),Bob.getS2());
        if( S1S2.equals(checkBob)){
            System.out.println("Signature is valid(Checks with comparing signatures)");
        }
        else{
            System.out.println("Error!!!");
        }
 */

        //Using el gamal verification of the certificate comes from other user
        elgamal.verifySignature(file1,g,p,publicOfCertificateAuthority);

        BigInteger publicB = new BigInteger(pubBob);
        BigInteger publicA = new BigInteger(pubAlice);

        sharedCommonKey = diffieHellman.diffieHellman(publicA, publicB,privateOfAlice,privateOfBob,p);
        System.out.println("Shared Common Key: " + sharedCommonKey);

        Message = "Message is signed by Elgamal and encrypted using AES and shared common key.";
        byte [] messageArray = Message.getBytes();
        //System.out.println("Message Array:" + messageArray);
        System.out.println("Message is signing by Alice...");
        aliceMessageSignature = elgamal.elGamalSignature(messageArray,privateOfAlice,p,g);
        //System.out.println("Message Signature:" + aliceMessageSignature[0] + "," + aliceMessageSignature[1]);

        System.out.println("Encrypting using AES..");

        char [] Array;
        Array = Alice.concat(messageArray,Message);

        String messageWithSign= new String(Array);
        System.out.println("Message with concatenation:" + messageWithSign);
        String [] decryptArray;
        byte [] decryptSignature;

        int radix = 16;// to obtain hexadecimal
        String sharedKeyString = sharedCommonKey.toString(radix);

        //System.out.println("messageWithSign:" + messageWithSign);
        encryptedMessage = AES.encrypt1(messageWithSign,sharedKeyString);
        System.out.println("Encrypted Message:" + encryptedMessage);
        decryptedMessage = AES.decrypt1(encryptedMessage,sharedKeyString);
        System.out.println("Decrypted Message:" + decryptedMessage);

        decryptArray = Bob.separate(messageWithSign);
        System.out.println("Message From Alice:" + decryptArray[1]);
        decryptSignature = decryptArray[1].getBytes();
        System.out.println("Bob is verifying the message...");
        elgamal.verifySignature(decryptSignature,g,p,publicA);

        sharedCommonKey = new BigInteger(sharedKeyString);




    }

}
