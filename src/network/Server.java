package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public Server() {
		try {
			ServerSocket server = new ServerSocket(5000);

			for (;;) {
				Socket clientSocket = server.accept();
				new Thread(new ListenClient(clientSocket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ListenClient implements Runnable {

		Scanner reader;

		public ListenClient(Socket clientSocket) {
			try {
				reader = new Scanner(clientSocket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try{
				String text;
				while((text = reader.nextLine()) != null){
					System.out.println(text);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new Server();
	}
}
