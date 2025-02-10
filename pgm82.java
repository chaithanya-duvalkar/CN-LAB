import java.util.Scanner;

public class pgm82 {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: bucket size and token generation rate
        System.out.print("Enter bucket capacity (number of tokens): ");
        int bucketCapacity = scanner.nextInt();
        System.out.print("Enter token generation rate (tokens per second): ");
        int tokenRate = scanner.nextInt();

        // Input: number of packets and their sizes
        System.out.print("Enter the number of packets: ");
        int numPackets = scanner.nextInt();
        int[] packetSizes = new int[numPackets];
        System.out.println("Enter the packet sizes: ");
        for (int i = 0; i < numPackets; i++) {
            packetSizes[i] = scanner.nextInt();
        }

        int tokens=0;
        int sent=0;

        System.out.println("packet size\t\ttokens available\t\tsent\t\tremaining tokens\t\tstatus");
        for(int packetSize:packetSizes)
        {
            tokens=Math.min(tokens+tokenRate,bucketCapacity);
            if(packetSize<=tokens)
            {
                tokens-=packetSize;
                sent=packetSize;
                System.out.println(packetSize+""+(tokens+packetSize)+""+sent+""+tokens+" accepted");
            }
            else
            {
                sent=0;
                System.out.println(packetSize+""+tokens+""+sent+""+tokens+" accepted");
            }
        }
}
