/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fil_rouge.fougicrok.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import fil_rouge.fougicrok.App;
import fil_rouge.fougicrok.DAL.Users;
import fil_rouge.fougicrok.DAL.UsersDAO;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class LoginController implements Initializable 
{

	@FXML
	private ImageView logo;
	@FXML
	private TextField input_login;
	@FXML
	private Label span_login;
	@FXML
	private PasswordField input_pwd;
	@FXML
	private Label span_pwd;
	@FXML
	private Circle btn;

	

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		// TODO
	}	

	@FXML
	private void back(MouseEvent event) throws IOException 
	{
		App.setRoot("views/start");
	}

	@FXML
	private void submit(MouseEvent event) 
	{
		Users user = new Users(input_login.getText());
		UsersDAO repo = new UsersDAO();
		user = repo.find(user);
		
//		if (BCrypt.checkpw(input_pwd.getText(), user.getPwd()))
//		{
//			
//			span_login.setText(user.getMail());
//		}
//		else
//		{
//				
//			span_login.setText("BHGA NON");
//		}
	}
	
}
