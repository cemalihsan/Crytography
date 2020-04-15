import java.io.*;
import java.math.BigInteger;
import algorithms.*;

public class CertificateAuthority {

    public BigInteger publicKey;
    public String name ;
    private BigInteger privateKey;
    public BigInteger AlicePublicKey;
    public BigInteger BobPublicKey;
    public BigInteger s1s2;
    public BigInteger p, g;

    modularExponention fastModularExponentiation = new modularExponention();

    public CertificateAuthority(String name, BigInteger privateKey) {
        this.name = name;
        this.privateKey = privateKey;
    }

    public CertificateAuthority() throws IOException {
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }

    public BigInteger generatePublicKey(BigInteger g, BigInteger privateKey, BigInteger p){

        BigInteger publicKey = fastModularExponentiation.exponentMod(g,privateKey,p);

        return publicKey;
    }

   public BigInteger getPublicKeyOfAlice(BigInteger publicKey1){

        AlicePublicKey = publicKey1;
        return AlicePublicKey;
    }
    public BigInteger getPublicKeyOfBob(BigInteger publicKey2){

        BobPublicKey = publicKey2;
        return BobPublicKey;
    }

    public void createCertificate(String CA, String name, BigInteger publicKey, BigInteger p, BigInteger g) {
        try {
            FileWriter writer = new FileWriter( "Certificate_"+ name +".txt");
            writer.write(CA);
            writer.write("\r\n");   // write new line
            writer.write(name);
            writer.write("\r\n");   // write new line
            writer.write(String.valueOf(publicKey));
            writer.write("\r\n");   // write new line
            writer.write(String.valueOf(p) + "\t");
            writer.write(String.valueOf(g) + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte[] fromFile(String filename){

        byte[] file_data = null;

        try{
            File file = new File(filename);
            FileInputStream data = new FileInputStream(file);
            file_data = new byte[(int)file.length()];
            data.read(file_data);
            data.close();
            return file_data;

        }
        catch(FileNotFoundException e){
            System.out.println("\nFile not found!");
        }

        catch(IOException e){
            System.out.println("\nIO Error!");
        }
        return file_data;

    }

    public void setup(String name, BigInteger[] s1s2) {

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Certificate_" + name + ".txt", true)));
                out.println(s1s2[0] + "\t" + s1s2[1]);
                out.flush();
                out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
