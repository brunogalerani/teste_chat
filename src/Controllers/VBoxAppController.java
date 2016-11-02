package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class VBoxAppController implements Initializable{

	@FXML
	private TextArea messages;
	@FXML
	private TextField input;
	@FXML
	private Button send;
	@FXML 
	private AnchorPane anchorPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
