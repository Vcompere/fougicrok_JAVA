/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fil_rouge.fougicrok.controllers;

import fil_rouge.fougicrok.App;
import fil_rouge.fougicrok.DAL.Category;
import fil_rouge.fougicrok.DAL.CategoryDAO;
import fil_rouge.fougicrok.DAL.FormValidation;
import fil_rouge.fougicrok.DAL.CategoryDAO;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class CategoryController implements Initializable
{

	@FXML
	private TableView<Category> tab;
	@FXML
	private Group users;
	@FXML
	private Group products;
	@FXML
	private Group orders;
	@FXML
	private Group discount;
	@FXML
	private Group ranks;
	@FXML
	private Button modif;
	@FXML
	private Button ajouterBtn;
	@FXML
	private Button del;
	@FXML
	private Button clear;
	@FXML
	private ImageView logo;
	@FXML
	private TableColumn<?, ?> catLabel;
	@FXML
	private TableColumn<?, ?> catParent;
	@FXML
	private TextField input;
	@FXML
	private Label spanInput;
	@FXML
	private ComboBox<Category> combo;
	@FXML
	private Label spanCombo;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		//settings of tableview's collumn (string betwen cote = variable's name in the class where your object come from)
		catLabel.setCellValueFactory(new PropertyValueFactory<>("type"));
		catParent.setCellValueFactory(new PropertyValueFactory<>("parentStr"));
		
		refresh();
	}	

	private void refresh() //used to refresh my tableView
	{
		//listing every entry of my table and passing it into the tableview
		CategoryDAO repo = new	CategoryDAO();
		tab.setItems(repo.listFull());
		
		//refreshing combobox's list
		combo.setItems(repo.list());
	}
	
	@FXML
	private void doubleClic(MouseEvent event) //when the user double click on a tableview's row, related info are being put into the form
	{
		if (event.getClickCount() == 2)
		{
			Category selected = tab.getSelectionModel().getSelectedItem();
			
			input.setText(selected.getType());
			Category parent = comboSelect(combo.getItems(), selected.getIdParent());
			combo.setValue(parent);
		}
	}
	
	private Category comboSelect(ObservableList<Category> list, int x)
	{
		for (Category y : list)
		{
			if(y.getId() == x)
			{
				return y;
			}
		}
		return null;
	}

	@FXML
	private void users(MouseEvent event)
	{
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
	private void orders(MouseEvent event) throws IOException
	{
		App.setRoot("views/orders");
	}

	@FXML
	private void discount(MouseEvent event)
	{
	}

	@FXML
	private void ranks(MouseEvent event)
	{
	}

	@FXML
	private void verif(KeyEvent event)
	{
		FormValidation verif = new FormValidation(); // repository of my class FormValidation
		
		// values recuperation
		String label = input.getText();
		int idParent;
		
		
		
		if (!combo.getSelectionModel().isEmpty())
		{
			idParent = combo.getSelectionModel().getSelectedItem().getId();
		}
		else
		{
			idParent = 0;
		}
		
		Category c1 = new Category(label, idParent); // initializing obj Category "c1"
		HashMap<String, String> span = verif.categoryVLD(c1, true); //calling the validation method with "c1" in parameter, if the form is ok, it return an empty hashmap if not, it's filled with span messages 
		
		// setting spans' errors messages
		spanInput.setText(span.get("input"));
	}

	@FXML
	private void update(ActionEvent event)
	{
		FormValidation verif = new FormValidation(); // repository of my class FormValidation
		
		// values recuperation
		int id = tab.getSelectionModel().getSelectedItem().getId();
		String label = input.getText();
		int idParent;
		
		
		
		if (!combo.getSelectionModel().isEmpty())
		{
			idParent = combo.getSelectionModel().getSelectedItem().getId();
		}
		else
		{
			idParent = 0;
		}
		
		Category c1 = new Category(id, label, idParent); // initializing obj Category "c1"
		HashMap<String, String> span = verif.categoryVLD(c1, false); //calling the validation method with "c1" in parameter, if the form is ok, it return an empty hashmap if not, it's filled with span messages 
		
		// setting spans' errors messages
		spanInput.setText(span.get("input"));
		
		if (span.isEmpty()) // if the hashmap is empty, that mean the form doesn't have any error
		{
			CategoryDAO repo = new CategoryDAO();
			repo.update(c1);
		}
		refresh();
	}

	@FXML
	private void add(ActionEvent event)
	{
		FormValidation verif = new FormValidation(); // repository of my class FormValidation
		
		// values recuperation
		String label = input.getText();
		int idParent;
		
		
		
		if (!combo.getSelectionModel().isEmpty())
		{
			idParent = combo.getSelectionModel().getSelectedItem().getId();
		}
		else
		{
			idParent = 0;
		}
		
		Category c1 = new Category(label, idParent); // initializing obj Category "c1"
		HashMap<String, String> span = verif.categoryVLD(c1, true); //calling the validation method with "c1" in parameter, if the form is ok, it return an empty hashmap if not, it's filled with span messages 
		
		// setting spans' errors messages
		spanInput.setText(span.get("input"));
		
		if (span.isEmpty()) // if the hashmap is empty, that mean the form doesn't have any error
		{
			CategoryDAO repo = new CategoryDAO();
			repo.add(c1);
		}
		refresh();
	}

	@FXML
	private void del(ActionEvent event)
	{
		int id = tab.getSelectionModel().getSelectedItem().getId();
		String label = tab.getSelectionModel().getSelectedItem().getType();
		
		Alert popup = new Alert(AlertType.CONFIRMATION);
		popup.setTitle("Confirmation de suppression");
		popup.setHeaderText("Supprimer la catégorie: \n");
		popup.setContentText(label+"\n");
		
		 // option != null.
		Optional<ButtonType> option = popup.showAndWait();

		if (option.get() == ButtonType.OK) 
		{
			CategoryDAO repo = new CategoryDAO();
			repo.del(id);
		}
		refresh();
	}

	@FXML
	private void clear(ActionEvent event)
	{
		input.clear();
		combo.setValue(null);
	}
	

	
}
