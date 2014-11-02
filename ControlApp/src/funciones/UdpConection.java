package funciones;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpConection implements Runnable {
	static String ServerIP = "0.0.0.0";
	static int Port=0;
	static String txt3="";
	public void setText(String txt){
		txt3=txt;
	}
	
	
	@Override
	public void run() {
	 try {
		 InetAddress IP=  InetAddress.getByName(ServerIP);
		  //int Port =61557;// Integer.parseInt(txt2.getText().toString());
			
		 byte []buffer = null;
		 buffer=txt3.getBytes();
		 
		 DatagramSocket socket = new DatagramSocket();
		 
		 DatagramPacket p = new DatagramPacket(buffer, buffer.length, IP, Port);
	
		 socket.send(p);
		 txt3="";
		
		 
	 }catch (Exception e){ }	}

}
	
	

