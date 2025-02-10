import java.util.*;

public class pgm9
{
    public static void main(String[] args) {
     Scanner sc=new Scanner(System.in);
     System.out.println("Enter min threshold");
     double min=sc.nextDouble();
     System.out.println("Enter max threshold");
     double max=sc.nextDouble();
     System.out.println("Enter max drop pbbty (0-1)");
     double prob=sc.nextDouble();
     System.out.println("enter queue size");
     int size=sc.nextInt();
     System.out.println("enter no. of packets");
     int n=sc.nextInt();

     RED red=new RED(min,max,prob,size);
     for(int i=0;i<n;i++)
     {
        red.enqueue();
     }
    }

    public static class RED
    {
        private double minThreshold;
        private double maxThreshold;
        private double maxDropProb;
        private int queueSize;
        private int currentQueue;

        public RED(double min,double max,double prob,int size)
        {
            minThreshold=min;
            maxThreshold=max;
            maxDropProb=prob;
            queueSize=size;
            currentQueue=0;
        }

        public boolean enqueue()
        {
            if(currentQueue>=queueSize)
            {
                System.out.println("packets dropped q is full");
                return false;
            }

            double dropProb=calcDropProb();
            if(dropProb>0&&shouldDrop(dropProb))
            {
                System.out.println("packets dropped due to red");
                return false;
            }

            currentQueue++;
            System.out.println("Q is enqueued and current queue size is "+currentQueue);
            return true;
        }

        public Double calcDropProb()
        {
            if(currentQueue<minThreshold)
            {
                return 0.0;
            }
            else if(currentQueue>maxThreshold)
            {
                return 1.0;
            }
            else 
            {
                return maxDropProb*((currentQueue-minThreshold)/maxThreshold-minThreshold);
            }
        }

        public boolean shouldDrop(double prob)
        {
            Random random=new Random();
            return random.nextDouble()>prob;
        }
    }
}