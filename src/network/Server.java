package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

	List<PrintWriter> writers = new ArrayList<>();

	public Server() {
		try {
			ServerSocket server = new ServerSocket(5000);

			for (;;) {
				Socket clientSocket = server.accept();
				new Thread(new ListenClient(clientSocket)).start();
				PrintWriter temp = new PrintWriter(clientSocket.getOutputStream());
				writers.add(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toAllClients(String text) {
		for (PrintWriter temp : writers) {
			try {
				temp.println(text);
				temp.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			try {
				String text;
				while ((text = reader.nextLine()) != null) {
					System.out.println(text);
					toAllClients(text);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new Server();
	}
}
