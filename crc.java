//3.Write a program for error detecting code using CRC-CCITT (16- bits). 

import java.util.*;

public class crc {
    
    //divide function
    public static String divide(char[] divident,char[] divisor)
    {
         int n=divident.length,m=divisor.length;

         for(int i=0;i<n;i++)
         {
            if(divident[i]=='1')
            {
                if(n-i<m) break;
                for(int j=0;j<m;j++)
                {
                    divident[i+j]=divisor[j]=='0'?divident[i+j]:divident[i+j]=='0'?'0':'1';
                }
            }
         }
         return new String(divident).substring(n-m+1);
    }

    //encode the data
    //data+remainder

    public static String encode(String data,String key)
    {
        return data+divide((data+"0".repeat(key.length()-1)).toCharArray(),key.toCharArray());
    }

    //decode the data
    //returns true if error free
    public static boolean decode(String encodedData,String key)
    {
      return divide(encodedData.toCharArray(),key.toCharArray()).contains("1");
    }
    
    //main function
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the binary key:");
        String key=sc.next();

        System.out.println("Enter the binary data:");

        //encode function call
        System.out.println("Encoded data:"+encode(sc.next(),key));

        //decode function call
         //error check
        System.out.println("Enter the encoded binary data:");
        System.out.println(decode(sc.next(),key)?"Error in data":"Error free data");
        System.out.println();
    }

}
