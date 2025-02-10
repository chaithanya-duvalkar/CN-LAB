import java.util.Arrays;
import java.util.Scanner;

public class pgm4
{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n;
        System.out.print("ENter number of vertices");
        n=sc.nextInt();

        System.out.println("Enter the vertices");
        int[][] graph=new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
        {
            graph[i][j]=sc.nextInt();

        }      
      }
      System.out.println("Enter the source");
      int source=sc.nextInt();

      bellmanFord(n,graph,source-1);
    }

    public static void bellmanFord(int n,int graph[][],int source)
    {
        int dist[]=new int[n];

        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source]=0;

        for(int i=0;i<n-1;i++)
        {
            for(int u=0;u<n;u++)
            {
                for(int v=0;v<n;v++)
                {
                    if(graph[u][v]!=0&&dist[u]!=Integer.MAX_VALUE&&dist[u]+graph[u][v]<dist[v])
                    {
                        dist[v]=dist[u]+graph[u][v];
                    }
                }
            }
        }

        for(int u=0;u<n;u++)
        {
            for(int v=0;v<n;v++)
            {
                if(graph[u][v]!=0&&dist[u]!=Integer.MAX_VALUE&&dist[u]+graph[u][v]<dist[v])
                {
                    System.out.println("Negative cycle is detected");
                    return;
                }
            }
        }

        printSolution(dist);

    }

    public static void printSolution(int dist[])
    {
        System.out.println("vertex\tdistance from source");
        for(int i=0;i<dist.length;i++)
        {
            System.out.println((i+1)+"\t\t"+(dist[i]==Integer.MAX_VALUE?"Infinity":dist[i]));
        }
    }
}