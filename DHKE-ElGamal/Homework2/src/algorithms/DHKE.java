package algorithms;
import java.math.BigInteger;

public class DHKE {

    modularExponention fastModularExponentiation = new modularExponention();

    public BigInteger diffieHellman(BigInteger publicKeyOfAlice, BigInteger publicKeyOfBob, BigInteger privateKeyAlice, BigInteger privateKeyBob, BigInteger modular){

        BigInteger AlicePublic;
        BigInteger BobPublic;
        BigInteger SharedCommonKeyAlice;
        BigInteger SharedCommonKeyBob;

        AlicePublic = publicKeyOfAlice;
        BobPublic = publicKeyOfBob;

        SharedCommonKeyAlice = fastModularExponentiation.exponentMod(BobPublic, privateKeyAlice,modular);
        SharedCommonKeyBob = fastModularExponentiation.exponentMod(AlicePublic,privateKeyBob,modular);

        if(SharedCommonKeyAlice == SharedCommonKeyBob){
            System.out.println("Shared key obtained.");
        }

        return  SharedCommonKeyAlice;
    }
}
