package algorithms;

import java.math.BigInteger;

import java.util.Random;

public class FermatLittle {

    private static final  Random myRandom = new Random();

    public static boolean isRelativelyPrime(BigInteger p, BigInteger numberOfPower){

        if(p.equals(BigInteger.ONE)){

            return  false;
        }

        for (BigInteger j = BigInteger.valueOf(0); j.compareTo(numberOfPower) < 0; j = j.add(BigInteger.ONE)){

            BigInteger a = constructRandomPrime(p);

            a = modPower(a, p.subtract(BigInteger.ONE),p);

            if(!a.equals(BigInteger.ONE)){

                System.out.println(a);
                return false;
            }
        }
        return true;
    }

    private static BigInteger modPower(BigInteger a,  BigInteger b, BigInteger p){

        BigInteger result = BigInteger.ONE;

        if(b.equals(BigInteger.ZERO)){
            return BigInteger.ONE;
        }

        if(b.equals(BigInteger.ONE)){
            return  a;
        }

        for(BigInteger i = BigInteger.valueOf(0); i.compareTo(b) < 0; i = i.add(BigInteger.ONE)){

            result = result.multiply(a);
            result = result.mod(p);
        }
        return result;

    }

    private static BigInteger constructRandomPrime(BigInteger n){

        while(true){

            final BigInteger a = new BigInteger(n.bitLength(), myRandom);

            if(BigInteger.ONE.compareTo(a) <= 0 && a.compareTo(n) < 0){

                return a;
            }
        }
    }
}
