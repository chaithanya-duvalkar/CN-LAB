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
        System.out.println("Private key:(d="+prk+"n="+mod+")");
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
        BigInteger m=new BigInteger(sc.nextLine().getBytes());
        BigInteger c=rsa.encrypt(m);
        System.out.println("Encoded data:"+c.longValue());
        BigInteger d=rsa.decrypt(c);
        System.out.println("Decrypted data:"+new String(d.toByteArray()));
        sc.close();
    }
}

//program with user input for prime number values p & q 
/* 
class RSAalg {
    BigInteger puk, prk, mod;

    void getkeys() {
        // Use fixed small prime numbers
        BigInteger p = BigInteger.valueOf(3);
        BigInteger q = BigInteger.valueOf(11);
        mod = p.multiply(q); // Compute n = p * q

        // Compute φ(n) = (p - 1) * (q - 1)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Choose public exponent (e), which must be coprime with φ(n)
        puk = BigInteger.valueOf(7); // e = 7 is valid since gcd(7, 20) = 1

        // Compute private key (d) such that (e * d) ≡ 1 (mod φ(n))
        prk = puk.modInverse(phi);

        System.out.println("Public key: (e: " + puk + ", n: " + mod + ")");
        System.out.println("Private key: (d: " + prk + ", n: " + mod + ")");
    }

    BigInteger encrypt(BigInteger m) {
        return m.modPow(puk, mod);
    }

    BigInteger decrypt(BigInteger c) {
        return c.modPow(prk, mod);
    }
}

public class pgm7 {
    public static void main(String[] args) {
        RSAalg rsa = new RSAalg();
        rsa.getkeys(); // Generate keys

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message (a number) to encrypt: ");
        BigInteger m = sc.nextBigInteger(); // Input must be numeric within range of n
        sc.close();

        // Encrypt message
        BigInteger c = rsa.encrypt(m);
        System.out.println("Encrypted Data: " + c);

        // Decrypt message
        BigInteger d = rsa.decrypt(c);
        System.out.println("Decrypted Data: " + d);
    }
}
*/
