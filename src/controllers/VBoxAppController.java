package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class VBoxAppController implements Initializable {

	@FXML
	private TextArea messages;
	@FXML
	private TextField input;
	@FXML
	private Button send;
	@FXML
	private AnchorPane anchorPane;

	private String nome;
	private Socket socket;
	private PrintWriter writer;
	private Scanner reader;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		networkConfig();
		this.nome = "Bruno";
		this.nome = "Leco";

	}

	public void networkConfig() {
		try {
			socket = new Socket("192.168.1.32", 5000);
			writer = new PrintWriter(socket.getOutputStream());
			reader = new Scanner(socket.getInputStream());
			new Thread(new ListenServer()).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void onMouseClick(){
		sendText();
	}
	
	@FXML
	public void handleEnterPressed(KeyEvent event){
	    if (event.getCode() == KeyCode.ENTER) {
	        sendText();
	    }
	}
	
	
	public void sendText() {
		writer.println(this.nome + ": " + input.getText());
		writer.flush();
		input.setText("");
		input.requestFocus();
	}

	public class ListenServer implements Runnable {

		@Override
		public void run() {
			try {
				String text;
				while ((text = reader.nextLine()) != null) {
					messages.appendText(text  + "\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
