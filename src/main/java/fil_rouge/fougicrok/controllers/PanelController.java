/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fil_rouge.fougicrok.controllers;

import fil_rouge.fougicrok.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class PanelController implements Initializable
{

	@FXML
	private ImageView logo;
	@FXML
	private Group products;
	@FXML
	private Group users;
	@FXML
	private Group catgeory;
	@FXML
	private Group orders;
	@FXML
	private Group discount;
	@FXML
	private Group ranks;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		// TODO
	}	

	@FXML
	private void products(MouseEvent event) throws IOException
	{
		App.setRoot("views/products");
	}

	@FXML
	private void users(MouseEvent event) throws IOException
	{
		App.setRoot("views/users");
	}

	@FXML
	private void category(MouseEvent event) throws IOException
	{
		App.setRoot("views/category");
	}

	@FXML
	private void orders(MouseEvent event) throws IOException
	{
		App.setRoot("views/orders");
	}

	@FXML
	private void discount(MouseEvent event) throws IOException
	{
		App.setRoot("views/discount");
	}

	@FXML
	private void ranks(MouseEvent event) throws IOException
	{
		App.setRoot("views/ranks");
	}

	@FXML
	private void backToStart(MouseEvent event) throws IOException
	{
		App.setRoot("views/start");
	}
	
}
