/*Using TCP/IP sockets, write a client – server program to make the client 
send the file name and to make the server send back the contents of the 
requested file if present  */

import java.io.*;
import java.net.*;

public class TCPC
{
    public static void main(String[] args) throws Exception
     {
        Socket socket=new Socket("127.0.0.1",3300);
        System.out.println("Enter the file name:");
        BufferedReader keyRead=new BufferedReader(new InputStreamReader(System.in));
        String fname=keyRead.readLine();                       //reads the client file 
        OutputStream ostream=socket.getOutputStream();         //sends the file to the server side by sending request to the server
        PrintWriter pwrite=new PrintWriter(ostream,true);      
        pwrite.println(fname);                                 //writes the file content into the server side
        InputStream istream=socket.getInputStream();           //input from the server side
        BufferedReader socketRead=new BufferedReader(new InputStreamReader(istream));      //reads the socket from the server side

        String str;

        while((str=socketRead.readLine())!=null)
        {
            System.out.println(str);
        }
        socketRead.close();
        pwrite.close();
        keyRead.close();
    }
    
}
