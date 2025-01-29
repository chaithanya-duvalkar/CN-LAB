/*Using TCP/IP sockets, write a client – server program to make the client 
send the file name and to make the server send back the contents of the 
requested file if present */

import java.io.*;
import java.net.*;

public class TCPS
{
    public static void main(String[] args) throws Exception
    {
        ServerSocket sersock=new ServerSocket(3300);
        System.out.println("Server ready for the connection");
        Socket socket=sersock.accept();     //accepts connection from the client
        System.out.println("Server connection is successful and waiting for the client request");
        InputStream istream=socket.getInputStream();
        BufferedReader fileRead=new BufferedReader(new InputStreamReader(istream));
        String fname=fileRead.readLine();
        BufferedReader contentRead=new BufferedReader(new FileReader(fname));
        OutputStream ostream=socket.getOutputStream();
        PrintWriter pwrite=new PrintWriter(ostream,true);

        String str;

        while((str=contentRead.readLine())!=null)
        {
             pwrite.println(str);
        }   
        sersock.close();
        pwrite.close();
        socket.close();
        contentRead.close();     
    }
        
}
