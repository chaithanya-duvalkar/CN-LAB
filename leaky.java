
/*Write a program for congestion control using leaky bucket algorithm and 
token bucket algorithm. */

import java.util.Scanner;

public class leaky
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the bucket capacity:");
        int bucketCapacity=sc.nextInt();
        System.out.println("Enter the output rate(packets per second):");
        int outputRate=sc.nextInt();
        System.out.println("Enter the number of packets:");
        int numPackets=sc.nextInt();
        System.out.println("Enter the packet size:");
        int[] packetSizes=new int[numPackets];
        for(int i=0;i<numPackets;i++)
        {
            packetSizes[i]=sc.nextInt();
        }

        int currentBucketSize=0;

        System.out.println("packets\t\tbucket sizes\tsent\t\tremaining\tstatus");
        for(int packetSize:packetSizes)
        {
            if(currentBucketSize+packetSize<=bucketCapacity)
            {
                currentBucketSize+=packetSize;
                System.out.println(packetSize+"\t\t"+currentBucketSize+"\t\t"+Math.min(outputRate,currentBucketSize)+"\t\t"+Math.max(0,currentBucketSize-outputRate)+"\t\tAccepted");
            }
            else
            {
                System.out.println(packetSize+"\t\t"+currentBucketSize+"\t\t"+Math.min(outputRate,currentBucketSize)+"\t\t"+Math.max(0,currentBucketSize-outputRate)+"\t\tDropped");
            }
            currentBucketSize=Math.max(0,currentBucketSize-outputRate);
        }
        sc.close();
    }
    
}
