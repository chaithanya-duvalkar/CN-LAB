import java.util.Scanner;

public class pgm3 
{
    public static String divide(char[] divident,char[] divisor)
    {
        int n=divident.length;
        int m=divisor.length;

      for(int i=0;i<n;i++)
      {
        if(divident[i]=='1')
        {
            if(n-i<m) break;
            for(int j=0;j<m;j++)
            {
                divident[i+j]=divisor[j]=='0'?divident[i+j]:divident[i+j]=='1'?'0':'1';
            }
        }
     }
     return new String(divident).substring(n-m+1);
    }

    public static String encode(String data,String key)
    {
        return data+divide((data+"0".repeat(key.length()-1)).toCharArray(),key.toCharArray());
    }

    public static boolean decode(String encodedData,String key)
    {
        return divide(encodedData.toCharArray(),key.toCharArray()).contains("1");
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the binary key");
        String key=sc.next();

        System.out.println("enter binary data");

        System.out.println("Encoded data:"+encode(sc.next(),key));

        System.out.println("enter the binary encoded data:");

        System.out.println(decode(sc.next(),key)?"error":"error free");
    }
    
}
