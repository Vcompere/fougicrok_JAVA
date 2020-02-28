
package fil_rouge.fougicrok.controllers;

import fil_rouge.fougicrok.App;
import fil_rouge.fougicrok.DAL.*;
import fil_rouge.fougicrok.DAL.CategoryDAO;
import fil_rouge.fougicrok.DAL.Products;
import fil_rouge.fougicrok.DAL.ProductsDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ProductsController implements Initializable
{

	@FXML
	private Group category;
	@FXML
	private Group orders;
	@FXML
	private Group discount;
	@FXML
	private Group ranks;
	@FXML
	private ImageView logo;
	@FXML
	private Button ajouterBtn;
	@FXML
	private Group users;
	@FXML
	private TableView<Products> tab;
	@FXML
	private TableColumn<?, ?> img;
	@FXML
	private TableColumn<?, ?> label;
	@FXML
	private TableColumn<?, ?> ref;
	@FXML
	private TableColumn<?, ?> description;
	@FXML
	private TableColumn<?, ?> price;
	@FXML
	private TableColumn<?, ?> stock;
	@FXML
	private TableColumn<?, ?> aDate;
	@FXML
	private TableColumn<?, ?> mDate;
	@FXML
	private TableColumn<?, ?> categoryCol;
	@FXML
	private TableColumn<?, ?> sales;
	@FXML
	private TextField inputLabel;
	@FXML
	private Label spanLabel;
	@FXML
	private TextField inputRef;
	@FXML
	private Label spanRef;
	@FXML
	private TextField inputPrice;
	@FXML
	private Label spanPrice;
	@FXML
	private DatePicker inputAdd;
	@FXML
	private Label spanAdd;
	@FXML
	private DatePicker inputUpdate;
	@FXML
	private Label spanUpdate;
	@FXML
	private TextField inputStock;
	@FXML
	private Label spanStock;
	@FXML
	private TextArea inputDescr;
	@FXML
	private Label spanDescr;
	@FXML
	private ComboBox<Category> inputCat;
	@FXML
	private Label spanCat;
	@FXML
	private ComboBox<String> inputImg;
	@FXML
	private Label spanImg;
	@FXML
	private Button del;
	@FXML
	private Button modif;
	@FXML
	private Button clear;
	
	ObservableList<String> extensions = FXCollections.observableArrayList("jpg", "jpeg", "png");
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		//settings of tableview's collumn (string betwen cote = variable's name in the class where your object come from)
		img.setCellValueFactory(new PropertyValueFactory<>("imgFull"));
		label.setCellValueFactory(new PropertyValueFactory<>("label"));
		ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
		description.setCellValueFactory(new PropertyValueFactory<>("desc"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		aDate.setCellValueFactory(new PropertyValueFactory<>("adding"));
		mDate.setCellValueFactory(new PropertyValueFactory<>("update"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("catType"));
//		sales.setCellValueFactory(new PropertyValueFactory<>("sales"));
		
		CategoryDAO repo = new CategoryDAO();
		inputCat.setItems(repo.listChilds());
		inputImg.setItems(extensions);
		
		inputAdd.setValue(dateToLocalDate(new Date()));
		inputUpdate.setValue(dateToLocalDate(new Date()));

		refresh();
	}	
	
	private void refresh() //used to refresh my tableView
	{
		//listing every entry of my table and passing it into the tableview
		ProductsDAO repo = new	ProductsDAO();
		tab.setItems(repo.list());
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

	@FXML 
	private void add(ActionEvent event) //adding a product
	{		
		FormValidation verif = new FormValidation(); // repository of my class FormValidation
		
		// values recuperation
		String label = inputLabel.getText();
		String ref = inputRef.getText();
		String descr = inputDescr.getText();
		String priceStr = inputPrice.getText();
		String stockStr = inputStock.getText();
		int cat;
		String img;
		String catType;
		
		if (!inputImg.getSelectionModel().isEmpty())
		{
			img = inputImg.getSelectionModel().getSelectedItem();
		}
		else
		{
			img = "FALSE";
		}
		
		if (!inputCat.getSelectionModel().isEmpty())
		{
			catType = inputCat.getSelectionModel().getSelectedItem().getType();
			cat = inputCat.getSelectionModel().getSelectedItem().getId();
			
		}
		else
		{
			catType="FALSE";
			cat = 0;
		}
		
		Products p1 = new Products(label, ref, descr, priceStr, stockStr, img, catType); // initializing obj Products "p1"
		HashMap<String, String> span = verif.productsVLD(p1, true); //calling the validation method with "p1" in parameter, if the form is ok, it return an empty hashmap if not, it's filled with span messages 

		// setting spans' errors messages
		spanLabel.setText(span.get("label"));
		spanRef.setText(span.get("ref"));
		spanPrice.setText(span.get("price"));
		spanStock.setText(span.get("stock"));
		spanDescr.setText(span.get("desc"));
		spanImg.setText(span.get("img"));
		spanCat.setText(span.get("cat"));
		
		
		
		if (span.isEmpty()) // if the hashmap is empty, that mean the form doesn't have any error
		{
		float price = Float.parseFloat(priceStr);
		int stock = Integer.parseInt(stockStr);
		
		Products p2 = new Products(label,ref, descr, price, stock, img, cat);
		ProductsDAO repo = new ProductsDAO();
		repo.add(p2);
		refresh();
		}
	}

	@FXML
	private void doubleClic(MouseEvent event) //when the user double click on a tableview's row, related info are being put into the form
	{
		if (event.getClickCount() == 2)
		{
			inputLabel.setText(tab.getSelectionModel().getSelectedItem().getLabel());
			inputRef.setText(tab.getSelectionModel().getSelectedItem().getRef());
			inputPrice.setText(String.valueOf(tab.getSelectionModel().getSelectedItem().getPrice()));
			inputStock.setText(String.valueOf(tab.getSelectionModel().getSelectedItem().getStock()));
			inputDescr.setText(tab.getSelectionModel().getSelectedItem().getDesc());
			inputAdd.setValue(dateToLocalDate(new java.util.Date(tab.getSelectionModel().getSelectedItem().getAdding().getTime())));
			inputUpdate.setValue(dateToLocalDate(new java.util.Date(tab.getSelectionModel().getSelectedItem().getUpdate().getTime())));
			inputImg.setValue(tab.getSelectionModel().getSelectedItem().getImg());
			
			inputCat.setValue(tab.getSelectionModel().getSelectedItem().getCatObj());
//			inputCat.getSelectionModel().select(5);
		}
	}
	
	public LocalDate dateToLocalDate(Date x) //transofmr a Date obj into a LocalDate obj
	{
		LocalDate result = x.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return result;
	}

	@FXML
	private void del(ActionEvent event) //deleting a product
	{
		int id = tab.getSelectionModel().getSelectedItem().getId();
		String label = tab.getSelectionModel().getSelectedItem().getLabel();
		String ref = tab.getSelectionModel().getSelectedItem().getRef();
		
		Alert popup = new Alert(AlertType.CONFIRMATION);
		popup.setTitle("Confirmation de suppression");
		popup.setHeaderText("Supprimer le produit: \n");
		popup.setContentText(label+"\n"
							+ref);
		
		 // option != null.
		Optional<ButtonType> option = popup.showAndWait();

		if (option.get() == ButtonType.OK) 
		{
			ProductsDAO repo = new ProductsDAO();
			repo.del(id);
		}
		refresh();
	}

	@FXML
	private void update(ActionEvent event) //updating a product
	{
		FormValidation verif = new FormValidation(); // repository of my class FormValidation
		
		// values recuperation
		String label = inputLabel.getText();
		String ref = inputRef.getText();
		String descr = inputDescr.getText();
		String priceStr = inputPrice.getText();
		String stockStr = inputStock.getText();
		int cat;
		String img;
		String catType;
		
		if (!inputImg.getSelectionModel().isEmpty())
		{
			img = inputImg.getSelectionModel().getSelectedItem();
		}
		else
		{
			img = "FALSE";
		}
		
		if (inputCat.getValue() != null)
		{
			catType = inputCat.getValue().getType();
			cat = inputCat.getValue().getId();
		}
		else
		{
			catType="FALSE";
			cat = -1;
		}
		
		Products p1 = new Products(label, ref, descr, priceStr, stockStr, img, catType); // initializing obj Products "p1"
		HashMap<String, String> span = verif.productsVLD(p1, false); //calling the validation method with "p1" in parameter, if the form is ok, it return an empty hashmap if not, it's filled with span messages 
		
		// setting spans' errors messages
		spanLabel.setText(span.get("label"));
		spanRef.setText(span.get("ref"));
		spanPrice.setText(span.get("price"));
		spanStock.setText(span.get("stock"));
		spanDescr.setText(span.get("desc"));
		spanImg.setText(span.get("img"));
		spanCat.setText(span.get("cat"));
		
		
		
		if (span.isEmpty()) // if the hashmap is empty, that mean the form doesn't have any error
		{
		float price = Float.parseFloat(priceStr);
		int stock = Integer.parseInt(stockStr);
		
		Products p2 = new Products(label,ref, descr, price, stock, img, cat);
		ProductsDAO repo = new ProductsDAO();
		repo.update(p2);
		refresh();
		}
	}

	@FXML
	private void clear(ActionEvent event)
	{
		inputLabel.clear();
		inputRef.clear();
		inputPrice.clear();
		inputCat.getSelectionModel().clearSelection();
		inputCat.setValue(null);
		inputAdd.getEditor().clear();
		inputUpdate.getEditor().clear();
		inputStock.clear();
		inputImg.getSelectionModel().clearSelection();
		inputDescr.clear();
	}

	@FXML
	private void dateReset(ActionEvent event)
	{
		inputAdd.setValue(dateToLocalDate(new Date()));
		inputUpdate.setValue(dateToLocalDate(new Date()));
	}

	@FXML
	private void verif(KeyEvent event)
	{
		FormValidation verif = new FormValidation(); // repository of my class FormValidation

		// values recuperation
		String label = inputLabel.getText();
		String ref = inputRef.getText();
		String descr = inputDescr.getText();
		String priceStr = inputPrice.getText();
		String stockStr = inputStock.getText();
		int cat;
		String img;
		String catType;

		if (!inputImg.getSelectionModel().isEmpty())
		{
			img = inputImg.getSelectionModel().getSelectedItem();
		}
		else
		{
			img = "FALSE";
		}

		if (!inputCat.getSelectionModel().isEmpty())
		{
			catType = inputCat.getSelectionModel().getSelectedItem().getType();
			cat = inputCat.getSelectionModel().getSelectedItem().getId();

		}
		else
		{
			catType="FALSE";
			cat = 0;
		}

		Products p1 = new Products(label, ref, descr, priceStr, stockStr, img, catType); // initializing obj Products "p1"
		HashMap<String, String> span = verif.productsVLD(p1, true); //calling the validation method with "p1" in parameter, if the form is ok, it return an empty hashmap if not, it's filled with span messages

		// setting spans' errors messages
		if(!"".matches(inputLabel.getText()))
		{
			spanLabel.setText(span.get("label"));
		}
		if(!"".matches(inputRef.getText()))
		{
			spanRef.setText(span.get("ref"));
		}
		if(!"".matches(inputDescr.getText()))
		{
			spanDescr.setText(span.get("desc"));
		}
		if(!"".matches(inputPrice.getText()))
		{
			spanPrice.setText(span.get("price"));
		}
		if(!"".matches(inputStock.getText()))
		{
			spanStock.setText(span.get("stock"));
		}
		if(!inputImg.getSelectionModel().isEmpty())
		{
			spanImg.setText(span.get("img"));
		}
		if(!inputCat.getSelectionModel().isEmpty())
		{
			spanCat.setText(span.get("cat"));
		}
	}
}