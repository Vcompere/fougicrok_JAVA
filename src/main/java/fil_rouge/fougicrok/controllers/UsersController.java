/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fil_rouge.fougicrok.controllers;

import fil_rouge.fougicrok.App;
import fil_rouge.fougicrok.DAL.Users;
import fil_rouge.fougicrok.DAL.UsersDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class UsersController implements Initializable
{

	@FXML
	private ImageView logo;
	@FXML
	private Group products;
	@FXML
	private Group category;
	@FXML
	private Group orders;
	@FXML
	private Group discount;
	@FXML
	private Group ranks;
	@FXML
	private TableView<Users> tab;
	@FXML
	private TableColumn<?, ?> login;
	@FXML
	private TableColumn<?, ?> prenom;
	@FXML
	private TableColumn<?, ?> nom;
	@FXML
	private TableColumn<?, ?> mail;
	@FXML
	private TableColumn<?, ?> telephone;
	@FXML
	private TableColumn<?, ?> Sdate;
	@FXML
	private TableColumn<?, ?> Cdate;
	@FXML
	private TableColumn<?, ?> erreur;
	@FXML
	private TableColumn<?, ?> bloque;
	@FXML
	private TableColumn<?, ?> grade;
	@FXML
	private Label spanLogin;
	@FXML
	private TextField inputLogin;
	@FXML
	private Label spanFirstname;
	@FXML
	private TextField inputFirstname;
	@FXML
	private Label spanName;
	@FXML
	private TextField inputName;
	@FXML
	private Label spanMail;
	@FXML
	private TextField inputMail;
	@FXML
	private Label spanPhone;
	@FXML
	private TextField inputPhone;
	@FXML
	private Label spanSDate;
	@FXML
	private DatePicker inputSDate;
	@FXML
	private Label spanCDate;
	@FXML
	private DatePicker inputCDate;
	@FXML
	private Label spanTry;
	@FXML
	private TextField inputTry;
	@FXML
	private CheckBox inputBlocked;
	@FXML
	private Label spanRank;
	@FXML
	private ComboBox<String> inputRank;
	ObservableList<String> comboList = FXCollections.observableArrayList("User", "Admin", "Super Admin");
	@FXML
	private Button ajouterBtn;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		inputRank.setItems(comboList);
				
		login.setCellValueFactory(new PropertyValueFactory<>("login"));
		prenom.setCellValueFactory(new PropertyValueFactory<>("firstname"));
		nom.setCellValueFactory(new PropertyValueFactory<>("name"));
		mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		telephone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		Sdate.setCellValueFactory(new PropertyValueFactory<>("sDate"));
//		Sdate.setCellValueFactory(column -> 
//		{
//			TableCell<Users, Date> cell = new TableCell<Users, Date>()
//			{
//				private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//		
//				@Override
//				protected void updateItem(Date item, boolean empty) {
//					super.updateItem(item, empty);
//					if(empty) 
//					{
//						setText(null);
//					}
//					else 
//					{
//						this.setText(format.format(item));
//					}
//				}
//			};
//
//			return cell;
//		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//		});
		Cdate.setCellValueFactory(new PropertyValueFactory<>("cDate"));
		erreur.setCellValueFactory(new PropertyValueFactory<>("trials"));
		bloque.setCellValueFactory(new PropertyValueFactory<>("blockedShow"));
		grade.setCellValueFactory(new PropertyValueFactory<>("rankShow"));
		
		
		UsersDAO repo = new UsersDAO();
		tab.setItems(repo.list());
	}	

	@FXML
	private void backToStart(MouseEvent event) throws IOException
	{
		App.setRoot("views/start");
	}

	@FXML
	private void products(MouseEvent event) throws IOException
	{
		App.setRoot("views/products");
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
	private void doubleClic(MouseEvent event)
	{
		if (event.getClickCount() == 2)
		{
			tab.setEditable(true);
		}
	}

	@FXML
	private void add(ActionEvent event)
	{
		
	}
	
}
