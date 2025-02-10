import java.util.Scanner;

public class pgm81 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter bucket capacity");
        int bucketCapacity=sc.nextInt();
        System.out.println("enter output rate");
        int outputRate=sc.nextInt();
        System.out.println("enter no. of packets");
        int numPackets=sc.nextInt();
        int[] packetSizes=new int[numPackets];
        for(int i=0;i<numPackets;i++)
        {
            packetSizes[i]=sc.nextInt();
        }

        int currentBucketSize=0;

        System.out.println("packetsize\t\tbucket size\tsent\t\tremaining\tstatus");
        for(int packetSize:packetSizes)
        {
            if(currentBucketSize+packetSize<=bucketCapacity)
            {
                currentBucketSize+=packetSize;
                System.out.println(packetSize+"\t"+currentBucketSize+"\t\t"+Math.min(outputRate,currentBucketSize)+"\t\t"+Math.max(0,currentBucketSize-outputRate)+"\t Accepted");
            }
            else
            {
                System.out.println(packetSize+"\t"+currentBucketSize+"\t\t"+Math.min(outputRate,currentBucketSize)+"\t\t"+Math.max(0,currentBucketSize-outputRate)+"\t Dropped");
           
            }
            currentBucketSize=Math.max(0,currentBucketSize-outputRate);
        }
    }
}
