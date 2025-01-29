
/*Write a program to implement random early detection (RED) congestion 
control algorithm. */

import java.util.*;

public class RED
{
    public static class RandomEarlyDetection
    {
        private double minThreshold;
        private double maxThreshold;
        private double maxDropProb;
        private int queueSize;
        private int currentQueue;

        RandomEarlyDetection(double min,double max,double prob,int size)
        {
            minThreshold=min;
            maxThreshold=max;
            maxDropProb=prob;
            queueSize=size;
            currentQueue=0;
        }

        //enqueue the packets
        public boolean enqueue()
        {

            //packets dropped due to fullQ
            if(currentQueue>=queueSize)
            {
                System.out.println("Packets dropped,Queue if full");
                return false;
            }

            //packets dropped by random early detection
            double dropProb=calcDropProb();
            if(dropProb>0&&shouldDrop(dropProb))
            {
                System.out.println("packets are dropped by RED");
                return false;
            }

            //queue is enqueued
            currentQueue++;
            System.out.println("packets are enqueued and current queue size:"+currentQueue);
            return true;
        }   

        //packet drop probbability
        public double calcDropProb()
        {

            //no drop of packets
            if(currentQueue<minThreshold)
            {
                return 0.0;
            }

            //100% pbbty of packets drop
            else if(currentQueue>maxThreshold)
            {
                return 1.0;
            }

            //between min and max threshold
            else
            {
                return maxDropProb*((currentQueue-minThreshold)/(maxThreshold-minThreshold));
            }
        }

         //packets dropped by random decide 
         public boolean shouldDrop(double prob)
         {
            Random random=new Random();
            return random.nextDouble()<prob;
         }
    }
    
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the minimum threshold");
        double min=sc.nextDouble();
        System.out.println("Enter the maximum threshold");
        double max=sc.nextDouble();
        System.out.println("Enter the maximum probability(0-1)");
        double prob=sc.nextDouble();
        System.out.println("Enter queue size");
        int size=sc.nextInt();
        System.out.println("Enter the number of packets");
        int n=sc.nextInt();

        RandomEarlyDetection red=new RandomEarlyDetection(min, max, prob, size);
        for(int i=0;i<n;i++)
        {
            red.enqueue();
        }

    }

}
