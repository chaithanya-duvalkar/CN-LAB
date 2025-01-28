//Write a program to find the shortest path between vertices using bellman ford algorithm. 

import java.util.Arrays;
import java.util.Scanner;

public class bellmanford 
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n;
        System.out.print("Enter number of vertices:");
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
        System.out.println("Enter the source vertex:");
        int source=sc.nextInt();

        bellmanFord(n,graph,source-1);
        sc.close();
    }
    
    
    public static void bellmanFord(int n,int graph[][],int source)
    {
        int dist[]=new int[n];

        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source]=0;

        //to find shortest distance by n-1 iterations
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

        //finds the negative cycle in the graph
        for(int u=0;u<n;u++)
        {
            for(int v=0;v<n;v++)
            {
                if(graph[u][v]!=0&&dist[u]!=Integer.MAX_VALUE&&dist[u]+graph[u][v]<dist[v])
                {
                    System.out.println("Negative cycle detected in the graph");
                    return;
                }
            }
        }
        printSolution(dist);    
    }

    public static void printSolution(int dist[])
    {
        System.out.println("Vertices\tDistance from the source");
        for(int i=0;i<dist.length;i++)
        {
            System.out.println((i+1)+"\t\t"+(dist[i]==Integer.MAX_VALUE?"Infinity":dist[i]));
        }
    }
}
