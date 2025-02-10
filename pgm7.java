import java.math.BigInteger;
import java.util.*;

class RSA{
    BigInteger prk,puk,mod;

    void getkeys(int bitlen)
    {
    Random r=new Random();
    BigInteger p=BigInteger.probablePrime(bitlen,r);
    BigInteger q=BigInteger.probablePrime(bitlen,r);
    mod=p.multiply(q);
    BigInteger phi=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    puk=BigInteger.probablePrime(bitlen/2,r);
    while(!phi.gcd(puk).equals(BigInteger.ONE)&&puk.compareTo(phi)>=0)
    {
        puk=BigInteger.probablePrime(bitlen/2,r);
    }
    prk=puk.modInverse(phi);
    System.out.println("public key: (e= "+puk+ ",n= " +mod+ ")");
    System.out.println("private key: (d= "+prk+ ",n= " +mod+ ")");

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
    public class pgm7 
    {
        public static void main(String[] args) {
            RSA rsa=new RSA();
            rsa.getkeys(512);
    
            Scanner sc=new Scanner(System.in);
    
            System.out.println("enter the msg to enrypt");
            BigInteger m=new BigInteger(sc.next().getBytes());
            BigInteger c=rsa.encrypt(m);
            System.out.println("encrypted msg:"+c.longValue());
            BigInteger d=rsa.decrypt(c);
            System.out.println("decrypted msg:"+new String(d.toByteArray()));
        }
    
}
