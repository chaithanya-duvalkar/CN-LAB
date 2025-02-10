import java.util.*;

public class pgm2 
{
    public static class Frame{
        int fnum;
        String content;

        Frame(int n,String s)
        {
            this.fnum=n;
            this.content=s;
        }
    }

    public static void bubblesort(Frame[] F,int n)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n-i-1;j++)
            {
                if(F[j].fnum>F[j+1].fnum)
                {
                    String s1=F[j].content,s2=F[j+1].content;
                    int a=F[j].fnum,b=F[j+1].fnum;

                    F[j].fnum=b;
                    F[j+1].fnum=a;
                    F[j].content=s2;
                    F[j+1].content=s1;

                }
            }
        }

    }
    
    public static void main(String[] args) {
     Scanner sc=new Scanner(System.in);
     System.out.println("enter no. of frames");
     int n=sc.nextInt();
     System.out.println("enter frame details:");
     Frame[] F=new Frame[n];
     for(int i=0;i<n;i++)
     {
        System.out.println("enter frame number");
        int a=sc.nextInt();

        System.out.println("enter frame content");
        String b=sc.next();
        F[i]=new Frame(a,b);
     }   

     List<Frame> frameList=new ArrayList<>(Arrays.asList(F));
     Collections.shuffle(frameList);
     F=frameList.toArray(new Frame[0]);

     System.out.println("before sort");
     for(int i=0;i<n;i++)
     {
        System.out.println(F[i].fnum+" :"+F[i].content+" ");
     }

     bubblesort(F,n);

     System.out.println("after sort");
     for(int i=0;i<n;i++)
     {
        System.out.println(F[i].fnum+" :"+F[i].content+" ");
     }
    }
    
}
