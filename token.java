
/*Write a program for congestion control using leaky bucket algorithm and 
token bucket algorithm. */
import java.util.Scanner;

public class token 
{
public static void main(String[] args)
{
    Scanner sc=new Scanner(System.in);

    //token generation
    System.out.println("Enter the bucket capacity(number of tokens):");
    int bucketCapacity=sc.nextInt();
    System.out.println("Enter the token generation rate:");
    int tokenRate=sc.nextInt();

    //packets generation
    System.out.println("Enter the number of packets:");
    int numPackets=sc.nextInt();
    System.out.println("Enter the packet size:");
    int[] packetSizes=new int[numPackets];
    for(int i=0;i<numPackets;i++)
    {
        packetSizes[i]=sc.nextInt();
    }

    int tokens=0;
    int sent=0;

    //packets sent using tokens
    System.out.println("Packet size\ttokens available\tsent\ttokens remaining\tstatus");
    for(int packetSize:packetSizes)
    {
        //Generate tokens 
        tokens=Math.min(tokens+tokenRate,bucketCapacity);
        if(packetSize<=tokens)
        {
            tokens-=packetSize;
            sent=packetSize;
            System.out.println(packetSize+"\t\t"+(tokens+packetSize)+"\t\t\t"+sent+"\t\t"+tokens+"\t\tAccepted");
        }
        else
        {
            sent=0;
            System.out.println(packetSize+"\t\t"+tokens+"\t\t"+sent+"\t\t"+tokens+"\t\tDropped");
        }
    }
    sc.close();
}
    
}
