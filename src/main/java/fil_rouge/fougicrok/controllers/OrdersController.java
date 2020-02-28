package fil_rouge.fougicrok.controllers;

import fil_rouge.fougicrok.App;
import fil_rouge.fougicrok.DAL.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrdersController implements Initializable
{
	public Label spanBasket;
	public Label spanCatalog;
	@FXML
	private TableView<Order> tab;
	@FXML
	private TableColumn<?, ?> colId;
	@FXML
	private TableColumn<?, ?> colDate;
	@FXML
	private TableColumn<?, ?> colPrice;
	@FXML
	private TableColumn<?, ?> colNmb;
	@FXML
	private TableColumn<?, ?> colUser;
	@FXML
	private TableColumn<?, ?> colPromo;
	@FXML
	private TableColumn<?, ?> colState;
	@FXML
	private Group users;
	@FXML
	private Group category;
	@FXML
	private Group products;
	@FXML
	private Group discount;
	@FXML
	private ComboBox<Users> combo_user;
	@FXML
	private Label spanUser;
	@FXML
	private ComboBox<Address> combo_dAddress;
	@FXML
	private Label span_dAddress;
	@FXML
	private ComboBox<String> combo_state;
	ObservableList<String> states = FXCollections.observableArrayList("Annulée", "En préparation", "Expédiée", "Livrée");
	@FXML
	private ComboBox<Category> combo_cat;
	@FXML
	private ComboBox<Address> combo_fAddress;
	@FXML
	private Label span_fAddress;
	@FXML
	private Button ajouterBtn;
	@FXML
	private Button del;
	@FXML
	private Button clear;
	@FXML
	private ComboBox<Discount> combo_discount;
	@FXML
	private ListView<Products> catalog;
	@FXML
	private ListView<OrderLine> basket;
	ObservableList<OrderLine> listBasket = FXCollections.observableArrayList();
	@FXML
	private Text plus;
	@FXML
	private Text minus;
	@FXML
	private Text delBasket;
	@FXML
	private Label labelPrice;
	@FXML
	private Spinner<Integer> spinnerStock;
	boolean swap = true;


	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		spinnerStock.getStyleClass().add(Spinner.STYLE_CLASS_ARROWS_ON_LEFT_VERTICAL);

		//settings of tableview's collumn (string betwen cote = variable's name in the class where your object come from)
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("priceStr"));
		colNmb.setCellValueFactory(new PropertyValueFactory<>("number"));
		colUser.setCellValueFactory(new PropertyValueFactory<>("userStr"));
		colPromo.setCellValueFactory(new PropertyValueFactory<>("promo"));
		colState.setCellValueFactory(new PropertyValueFactory<>("state"));

		refresh();

		CategoryDAO repoCat = new CategoryDAO();
		combo_cat.setItems(repoCat.list());

		UsersDAO repoUser = new UsersDAO();
		combo_user.setItems(repoUser.list());
		
		combo_state.setItems(states);
		
		DiscountDAO repoDiscount = new DiscountDAO();
		combo_discount.setItems(repoDiscount.list());
		combo_discount.valueProperty().addListener(evt->{
			if(!labelPrice.getText().isEmpty())
			{
				total();
			}
		});
		
		AddressDAO repoAddress = new AddressDAO();
		combo_user.valueProperty().addListener(evt->{
			combo_dAddress.setDisable(false);
			combo_fAddress.setDisable(false);
			
			ObservableList<Address> list = repoAddress.list(combo_user.getSelectionModel().getSelectedItem().getId());
			combo_dAddress.setItems(list);
			combo_fAddress.setItems(list);
		});
		
		ProductsDAO repoProduct = new ProductsDAO();
		combo_cat.valueProperty().addListener(evt->{
			int catId = combo_cat.getSelectionModel().getSelectedItem().getId();

			if(catId == 0)
			{
				catalog.setDisable(true);
				plus.setDisable(true);
				plus.setOpacity(0.5);
				spinnerStock.setDisable(true);
				basket.setDisable(true);
				minus.setDisable(true);
				minus.setOpacity(0.5);
				delBasket.setDisable(true);
				delBasket.setOpacity(0.5);
			}
			else
			{
				catalog.setDisable(false);
				plus.setDisable(false);
				plus.setOpacity(1);
				spinnerStock.setDisable(false);
				basket.setDisable(false);
				minus.setDisable(false);
				minus.setOpacity(1);
				delBasket.setDisable(false);
				delBasket.setOpacity(1);

				if(combo_cat.getSelectionModel().getSelectedItem().getIdParent() == 0)
				{
					catalog.setItems(repoProduct.listParent(catId));
				}
				else
				{
					catalog.setItems(repoProduct.list(catId));
				}
			}
		});

	}
	private void refresh() //used to refresh my tableView
	{
		//listing every entry of my table and passing it into the tableview
		OrderDAO repo = new	OrderDAO();
		tab.setItems(repo.list());
	}
	
	private void backToStart(MouseEvent event) throws IOException
	{
		App.setRoot("views/start");
	}

	@FXML
	private void add(ActionEvent event)
	{
		FormValidation verif = new FormValidation(); // repository of my class FormValidation

		int userId;
			if(!combo_user.getSelectionModel().isEmpty())
			{
				userId = combo_user.getSelectionModel().getSelectedItem().getId();;
			}
			else
			{
				userId = 0;
			}

		int discountId;
			if(!combo_discount.getSelectionModel().isEmpty())
			{
				discountId = combo_discount.getSelectionModel().getSelectedItem().getId();;
			}
			else
			{
				discountId = 0;
			}

		int fAddress;
			if(!combo_fAddress.getSelectionModel().isEmpty())
			{
				fAddress = combo_fAddress.getSelectionModel().getSelectedItem().getId();;
			}
			else
			{
				fAddress = 0;
			}

		int dAddress;
			if(!combo_dAddress.getSelectionModel().isEmpty())
			{
				dAddress = combo_dAddress.getSelectionModel().getSelectedItem().getId();;
			}
			else
			{
				dAddress = 0;
			}

		String state;
			if(!combo_state.getSelectionModel().isEmpty())
			{
				state = combo_state.getSelectionModel().getSelectedItem();;
			}
			else
			{
				state = "En préparation";
			}

		int nbItems = listBasket.size();

		// adding the order and getting back his id
		Order order = new Order(userId, discountId, fAddress, dAddress, state);
		HashMap<String, String> span = verif.OrderVLD(order, nbItems);

		// setting spans' errors messages
		spanUser.setText(span.get("user"));
		span_fAddress.setText(span.get("fAddress"));
		span_dAddress.setText(span.get("dAddress"));
		spanBasket.setText(span.get("basket"));

		if(span.isEmpty())
		{
			OrderDAO repOrder = new OrderDAO();
			int ordId = repOrder.add(order);

			// adding order's order-lines
			OrderLineDAO repoLine = new OrderLineDAO();

			for (int i = 0; i < listBasket.size(); i++)
			{
				OrderLine item = listBasket.get(i);
				item.setOrdId(ordId);
				repoLine.add(item);

				// decrementing the product's stock
				ProductsDAO repoProd = new ProductsDAO();
				repoProd.updateStock(false, item);
			}
			refresh();
		}
	}

	@FXML
	private void del(ActionEvent event)
	{
		int ordId = tab.getSelectionModel().getSelectedItem().getId();

		Alert popup = new Alert(Alert.AlertType.CONFIRMATION);
		popup.setTitle("Confirmation de suppression");
		popup.setHeaderText("Supprimer la commande n°: "+ordId);
		popup.setContentText("Une commande ne doit être supprimée qu'en cas d'erreur de saisie.\n" +
				"Autrement, préférez passer son état à \"Annulée\".");

		// option != null.
		Optional<ButtonType> option = popup.showAndWait();

		if (option.get() == ButtonType.OK)
		{
			ProductsDAO repoProd = new ProductsDAO();

			OrderLineDAO repoLine = new OrderLineDAO();
			ObservableList<OrderLine> list = repoLine.list(ordId);
			for (int i = 0; i < list.size(); i++) {
				OrderLine line = list.get(i);
				repoProd.updateStock(true, line);
			}
			repoLine.del(ordId);

			OrderDAO repOrder = new OrderDAO();
			repOrder.del(ordId);
		}

		refresh();
	}

	@FXML
	private void clear(ActionEvent event)
	{
		combo_user.getSelectionModel().clearSelection();
		combo_state.getSelectionModel().clearSelection();
		combo_cat.getSelectionModel().clearSelection();
		combo_dAddress.getSelectionModel().clearSelection();
		combo_fAddress.getSelectionModel().clearSelection();
		combo_discount.getSelectionModel().clearSelection();

		catalog.setDisable(true);
		plus.setDisable(true);
		plus.setOpacity(0.5);
		spinnerStock.setDisable(true);
		basket.setDisable(true);
		minus.setDisable(true);
		minus.setOpacity(0.5);
		delBasket.setDisable(true);
		delBasket.setOpacity(0.5);
		basket.getItems().clear();
		catalog.getItems().clear();
		labelPrice.setText("");

	}

	@FXML
	private void users(MouseEvent event)
	{
	}

	@FXML
	private void category(MouseEvent event)
	{
	}

	@FXML
	private void products(MouseEvent event) throws IOException
	{
		App.setRoot("views/products");
	}

	@FXML
	private void discount(MouseEvent event)
	{
	}

	public static void var_dump(Object o)
	{
		Field[] fields = o.getClass().getDeclaredFields();
		System.out.println("\n"+o+" :");
		for (int i=0; i<fields.length; i++)
		{
			try
			{
				fields[i].setAccessible(true);
				System.out.println(i+1+".	"+fields[i].getType()+"   "+fields[i].getName() + " = " + fields[i].get(o));
			}
			catch (IllegalAccessException e)
			{
				System.out.println("var_dump	"+e.getMessage());
			}
		}
	}

	@FXML
	private void doubleClic(MouseEvent event)
	{
		if (event.getClickCount() == 2)
		{
			try
			{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/details.fxml"));
				Parent parent = loader.load();

				DetailsController repo = loader.getController();
				repo.passpass(tab.getSelectionModel().getSelectedItem());

				Stage stage = new Stage();
				stage.setTitle("Commande n° " + tab.getSelectionModel().getSelectedItem().getId());
				stage.setScene(new Scene(parent));
				stage.show();
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}

	@FXML
	private void addToBasket(MouseEvent event)
	{
		spanBasket.setText("");
		if(swap) // listview #catalog has focus
		{
			boolean isUnique = true;
			Products prod = catalog.getSelectionModel().getSelectedItem();
			if(prod.getStock() < 1)
			{
				spanCatalog.setText("Ce produit n'est plus en stock");
				return;
			}
			int prodId = prod.getId();
			double uPrice = prod.getPrice();
			int quantity = spinnerStock.getValue();
			if(quantity > prod.getStock())
			{
				quantity = prod.getStock();
			}

			for(int i=0; i<listBasket.size(); i++)
			{
				OrderLine item = listBasket.get(i);
				if(item.getProd_id() == prodId)
				{
					isUnique = false;
					quantity += item.getQuantity();
					if(quantity > prod.getStock())
					{
						quantity = prod.getStock();
					}
					OrderLine x = new OrderLine(prodId,uPrice,quantity,prod);
					listBasket.set(i,x);
				}
			}

			if(isUnique)
			{
				OrderLine x = new OrderLine(prodId,uPrice,quantity,prod);
				listBasket.add(x);
			}
		}
		else // listview #basket has focus
		{
			OrderLine item = basket.getSelectionModel().getSelectedItem();
			int i = listBasket.indexOf(item);
			item.setQuantity(item.getQuantity()+spinnerStock.getValue());
			item.settPrice(item.getuPrice() * item.getQuantity());
			listBasket.set(i,item);
		}
		basket.setItems(listBasket);
		total();
	}

	public void clickCatalaog(MouseEvent mouseEvent)
	{
		swap = true;
		if (!catalog.getSelectionModel().isEmpty())
		{
			int stock = catalog.getSelectionModel().getSelectedItem().getStock();
			if(stock < 1)
			{
				spanCatalog.setText("Ce produit n'est plus en stock");
			}
			else
			{
				spanCatalog.setText("");
			}
			SpinnerValueFactory<Integer> spinnerFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, stock, 1);
			spinnerStock.setValueFactory(spinnerFactory);
		}
	}

	public void clickBasket(MouseEvent mouseEvent)
	{
		swap = false;
		if (listBasket.size() != 0)
		{
			int qty = basket.getSelectionModel().getSelectedItem().getQuantity();

			spinnerStock.getValueFactory().setValue(qty);
		}
	}

	public void total()
	{
		double total = 0;
		for(int i=0; i<listBasket.size(); i++)
		{
			total += listBasket.get(i).gettPrice();
		}

		if (!combo_discount.getSelectionModel().isEmpty())
		{
			double promoV = combo_discount.getSelectionModel().getSelectedItem().getValue();
			total = total * ((100-promoV)/100);
		}
		labelPrice.setText(String.valueOf(total)+" €");
	}

	public void minusFromBasket(MouseEvent mouseEvent)
	{
		OrderLine item = basket.getSelectionModel().getSelectedItem();
		int i = listBasket.indexOf(item);
		item.setQuantity(spinnerStock.getValue());
		item.settPrice(item.getuPrice() * spinnerStock.getValue());

		listBasket.set(i,item);
		total();
	}

	public void deleteFromBasket(MouseEvent mouseEvent)
	{
		listBasket.remove(basket.getSelectionModel().getSelectedItem());
		basket.setItems(listBasket);
		total();
	}
}
