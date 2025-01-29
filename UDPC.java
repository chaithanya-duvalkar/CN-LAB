/*Write a program on datagram socket for client/server to display the 
messages on client side, typed at the server side.  */

import java.io.*;
import java.net.*;

public class UDPC 
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader inFormUser=new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket=new DatagramSocket();
        InetAddress IPAddress=InetAddress.getByName("LocalHost");
        byte[] sendData=new byte[1024];
        byte[] receiveData=new byte[1024];
        System.out.println("Enter the data in Lower case such that you should receive the message in Upper case from the server");
        String sentence=inFormUser.readLine();
        sendData=sentence.getBytes();
        DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,IPAddress,5454);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence=new String(receivePacket.getData());
        System.out.println("FROM SERVER:"+modifiedSentence);
        clientSocket.close();
    }
    
}
