//Write a program for simple RSA algorithm to encrypt and decrypt the data. 

import java.math.BigInteger;
import java.util.*;

 class RSAalgorithm
{
    BigInteger puk,prk,mod;

    void getkeys(int bitlen)
    {
        Random r=new Random();
        BigInteger p=BigInteger.probablePrime(bitlen,r);
        BigInteger q=BigInteger.probablePrime(bitlen,r);
        mod=p.multiply(q);
        BigInteger phi=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        puk=BigInteger.probablePrime(bitlen/2,r);
        while(!phi.gcd(puk).equals(BigInteger.ONE)||puk.compareTo(mod)>=0)
        {
            puk=BigInteger.probablePrime(bitlen/2,r);
        }
        prk=puk.modInverse(phi);
        System.out.println("Public key:(e="+puk+"n="+mod+")");
        System.out.println("Private key:(e="+prk+"n="+mod+")");
    }

    BigInteger encrypt(BigInteger m)
    {
        return m.modPow(puk,mod);
    }

    BigInteger decrypt(BigInteger c)
    {
        return c.modPow(prk,mod);
    }  
}

class RSA
{
    public static void main(String[] args)
    {
        RSAalgorithm rsa=new RSAalgorithm();
        rsa.getkeys(512);
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the msg to encrypt:");
        BigInteger m=new BigInteger(sc.next().getBytes());
        BigInteger c=rsa.encrypt(m);
        System.out.println("Encoded data:"+c.longValue());
        BigInteger d=rsa.decrypt(c);
        System.out.println("Decrypted data:"+new String(d.toByteArray()));
        sc.close();
    }
}
