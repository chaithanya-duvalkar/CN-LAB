import java.util.*;

class sort
{
    //initialisation
    static class Frame
    {
        int fnum;
        String content;

    Frame(int n,String s)
    {
        this.fnum=n;
        this.content=s;
    }
}

    //to swap the frames
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

    //read the frame contents

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of frames:");
        int n=sc.nextInt();
       
        System.out.println("Enter frame details");
        Frame[] F=new Frame[n];
        for(int i=0;i<n;i++)
        {
            System.out.println("Enter frame number:");
            int a=sc.nextInt();

            System.out.println("Enter frame content");
            String b=sc.next();
            F[i]=new Frame(a,b);
        }

        //shuffle frames
        List<Frame> frameList=new ArrayList<>(Arrays.asList(F));
        Collections.shuffle(frameList);
        F=frameList.toArray(new Frame[0]);

        //before sort
        System.out.println("Frames before sorting:");
        for(int i=0;i<n;i++)
        {
            System.out.print(F[i].content+" ");
        }
        System.out.println();
       
        bubblesort(F,n);
        //after sort
        System.out.println("Frames after sorting:");
        for(int i=0;i<n;i++)
        {
            System.out.print(F[i].content+" ");
        }
        System.out.println();
        sc.close();
    }



}