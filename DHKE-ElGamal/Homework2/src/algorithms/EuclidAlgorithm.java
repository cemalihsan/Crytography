package algorithms;

import java.math.BigInteger;
import java.util.ArrayList;


public class EuclidAlgorithm {

    public static ArrayList<BigInteger> extendedEuclidean(BigInteger a, BigInteger b) {

        BigInteger x = BigInteger.ZERO;
        BigInteger y = BigInteger.ONE;
        BigInteger x1 = BigInteger.ONE;
        BigInteger y1 = BigInteger.ZERO;
        BigInteger q = BigInteger.ZERO;

        while (!(b.equals(BigInteger.ZERO))) {

            q = a.divide(b);
            BigInteger r = a.mod(b);

            a = b;
            b = r;

            BigInteger temp = x.subtract(q.multiply(x1));

            x = x1;
            x1 = temp;

            BigInteger temp1 = y.subtract(q.multiply(y1));
            y = y1;
            y1 = temp1;

        }

        ArrayList<BigInteger> result = new ArrayList<BigInteger>();

        result.add(b);
        result.add(x);
        result.add(y);
        result.add(a);

       /* System.out.println(b);
        System.out.println(x);
        System.out.println(y);
        System.out.println(q);*/

        return result;
    }

    public static boolean isRelativePrime(BigInteger a, BigInteger b) {
       // the equation ax+by=1 if it is equal one they are relatively prime
        if (extendedEuclidean(a, b).get(0).equals(BigInteger.ONE)) {
            return true;
        }
        return false;
    }

    public static BigInteger multiplicativeInverse(BigInteger a, BigInteger b) {

        ArrayList<BigInteger> result = extendedEuclidean(a, b);

        if (result.get(0).equals(BigInteger.ONE)) {

            return result.get(1);
        } else {

            System.out.println("No inverse!!!");
        }

        return null;
    }

    public static BigInteger gcd(BigInteger A, BigInteger B){

            if(B.equals(BigInteger.ZERO))
            {
                return A;
            }
            else{

                return gcd(B, A.mod(B));
            }



    }

}