import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPFileClient{
	private static final int PORT = 21820;
	public static void main(String[] args) {
		try{
			DatagramSocket socket = new DatagramSocket();
		    InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
		    Scanner scn = new Scanner(System.in);
		    System.out.println("Nhap ten file: ");
		    String fileName = scn.nextLine();
		    // gửi yêu cầu
		    String request = "READ "+fileName;
		    byte [] outputByte = request.getBytes();
		    DatagramPacket outputPack = new DatagramPacket(outputByte,outputByte.length,serverAddress,PORT);
		    socket.send(outputPack);

            // Nhận yêu cầu và lưu ra file
		    File file = new File("result");
		    FileOutputStream fos = new FileOutputStream(file);
		    byte []inputByte = new byte[60000];
		    DatagramPacket inputPack = new DatagramPacket(inputByte,inputByte.length);
		    socket.receive(inputPack);
		    fos.write(inputPack.getData(),0,inputPack.getLength());

		    System.out.println("Luu file thanh cong");



		}catch(IOException ex){
			System.out.println("Loi client : "+ex.toString());
		}
		
    }
}