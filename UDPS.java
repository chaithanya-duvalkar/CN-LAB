/*Write a program on datagram socket for client/server to display the 
messages on client side, typed at the server side.  */

import java.net.*;

public class UDPS
{
    public static void main(String[] args) throws Exception
    {
        DatagramSocket serverSocket=new DatagramSocket(5454);
        System.out.println("Server is ready for client");
        byte[] receiveData=new byte[1024];
        byte[] sendData=new byte[1024];
        while (true) 
        {
            DatagramPacket receivedPacket=new DatagramPacket(receiveData,receiveData.length);
            serverSocket.receive(receivedPacket);
            String sentence=new String(receivedPacket.getData());
            System.out.println("RECEIVED:"+sentence);
            InetAddress IPAddress=receivedPacket.getAddress();
            int Port=receivedPacket.getPort();
            String capitalised=sentence.toUpperCase();
            sendData=capitalised.getBytes();
            DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,IPAddress,Port);
            serverSocket.send(sendPacket);
        }
        
    }
    
}
