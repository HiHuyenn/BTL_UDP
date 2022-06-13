import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPChatServer{
    private static final int PORT = 21820;
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(PORT);
            Scanner scn = new Scanner(System.in);
            byte []inputByte = new byte[60000];
            while(true){
                // Nhan du lieu
                DatagramPacket inputPack = new DatagramPacket(inputByte,inputByte.length);
                socket.receive(inputPack);
                String inputStr = new String(inputPack.getData(),0,inputPack.getLength());
                System.out.println("Hang xom noi : "+inputStr);
                // Gui du lieu
                System.out.print(" Ban noi : "+inputStr);
                String outputStr = scn.nextLine();
                DatagramPacket outputPack = new DatagramPacket(outputStr.getBytes(),outputStr.length(),inputPack.getAddress(),inputPack.getPort());
                socket.send(outputPack);
            }
        }catch(IOException ex){
            System.out.println("Loi Server : "+ex.toString());
        }
        
    }
}




