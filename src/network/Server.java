package network;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	
	public Server(){
		try {
			ServerSocket server = new ServerSocket(5000);
			
			for(;;){
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
