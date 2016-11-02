package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		networkConfig();
		//this.nome = "Bruno";
		this.nome = "Gabriel";

	}
	
	public void networkConfig(){
		try {
			socket = new Socket("127.0.0.1", 5000);
			writer = new PrintWriter(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@FXML
	public void handleSend(){
		writer.println(this.nome + ": " + input.getText());
		writer.flush();
		messages.appendText(this.nome + ": " + input.getText() + "\n");
		input.setText("");
		input.requestFocus();
	}

}
