package algorithms;

import java.math.BigInteger;

public class modularExponention{

    public BigInteger exponentMod(BigInteger base, BigInteger exponention, BigInteger modular){

        BigInteger y = BigInteger.ONE;

        if(base.equals(BigInteger.ZERO)){

            return BigInteger.ZERO;
        }
        if(exponention.equals(BigInteger.ZERO)){

            return BigInteger.ONE;
        }

        for(int i=0; i < exponention.bitLength(); i++){

            if(exponention.testBit(i))
            {
                y = y.multiply(base);
                y = y.mod(modular);

            }
            base = base.multiply(base);
            base = base.mod(modular);

        }
        return y;

    }

}
