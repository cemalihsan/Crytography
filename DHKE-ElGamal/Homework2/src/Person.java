import java.io.*;
import java.math.BigInteger;
import algorithms.*;
import java.util.ArrayList;
import java.util.List;
import java.io.OutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class Person {

    public String name;
    private BigInteger privateKey;
    private BigInteger S1;
    private BigInteger S2;
    public BigInteger pK;
    public String Certificate;
    public File transferFile;
    modularExponention fastModularExponentiation = new modularExponention();

    public BigInteger getpK() {
        return pK;
    }

    public void setpK(BigInteger pK) {
        this.pK = pK;
    }

    public Person(String name, BigInteger privateKey) {
        this.name = name;
        this.privateKey = privateKey;
    }

    public BigInteger generatePublicKey(BigInteger g, BigInteger privateKey, BigInteger p) {

        BigInteger publicKey = fastModularExponentiation.exponentMod(g, privateKey, p);

        return publicKey;
    }

    public void setS1(BigInteger s1) {
        S1 = s1;
    }

    public void setS2(BigInteger s2) {
        S2 = s2;
    }

    public BigInteger getS1() {
        return S1;
    }

    public BigInteger getS2() {
        return S2;
    }

    public void setTransferFile(File transferFile) {
        this.transferFile = transferFile;
    }

    public File getTransferFile(File transferFile) {
        return transferFile;
    }

    public static char[] concat(final byte[] bytes, final String str) {
        final StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(b);
        }
        sb.append("é").append(str);
        return sb.toString().toCharArray();
    }

    public static String[] separate(String str) {

        String[] Array;
        Array = str.split("é");

        return Array;
    }

    public String getPublicKeyCertificate(String filename, BigInteger publicKey) throws IOException {

        List<String> list_file1 = null;
        BufferedReader b1 = null;
        list_file1 = new ArrayList<String>();

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String lineText = null;

        try {
            b1 = new BufferedReader(new FileReader(filename));
            while ((lineText = b1.readLine()) != null) {
                list_file1.add(lineText);

            }
            //System.out.println(list_file1);
            //System.out.println(publicKey);
           /* if (publicKey.toString().equals(list_file1.get(2))) {
                System.out.println("Public Key Verified.");

            } else {
                System.out.println("No Match!!!");
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list_file1.get(2);
    }

    public String getS1S2KeyCertificate(String filename, BigInteger s1, BigInteger s2) throws IOException {

        List<String> list_file1 = null;
        BufferedReader b1 = null;
        list_file1 = new ArrayList<String>();

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String lineText = null;

        try {
            b1 = new BufferedReader(new FileReader(filename));
            while ((lineText = b1.readLine()) != null) {
                list_file1.add(lineText);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list_file1.get(4);
    }

    public void setCertificate(String certificate) {
        Certificate = certificate;
    }

    public File obtainCertificate(byte[] txtFile,String name){
        File file = new File("Certificate_" + name + ".txt");
        try {

            OutputStream os = new FileOutputStream(file);
            os.write(txtFile);
           // printContent(file);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
   /* public static void printContent(File file) throws Exception {
        System.out.println("Print File Content");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }*/
}
