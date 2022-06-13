import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPChatClient{
    private static final int PORT = 0;
    private DatagramSocket socket;
    private InetAddress inetaddress;
    byte[] inputByte = new byte[60000];
    
    public UDPChatClient (DatagramSocket socket, InetAddress inetaddress) {
        this.socket = socket;
        this.inetaddress = inetaddress;
    }
    
    public void gui_va_nhan_client(){
        Scanner sc = new Scanner(System.in);
        while(true){
            try{
                System.out.print("Gui message tu Client den server : ");
                String message_gui = sc.nextLine();
                inputByte = message_gui.getBytes();
                DatagramPacket packet = new DatagramPacket(inputByte , inputByte.length , inetaddress , 21820);
                socket.send(packet);
                socket.receive(packet);
                
                String message_from_server = new String(packet.getData() , 0 , packet.getLength());
                System.out.println("Message tu server : " + message_from_server);
            }catch(IOException ioex){
                System.out.println(ioex);
            }
        }
    }
    
    public static void main(String[] args) throws SocketException, UnknownHostException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress inetaddress = InetAddress.getByName("localhost");
        UDPChatClient client = new UDPChatClient(socket , inetaddress);
        client.gui_va_nhan_client();
    }
}