/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fil_rouge.fougicrok.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ProductsDAO 
{
	private void failConnect()
	{
		Alert fail = new Alert(Alert.AlertType.ERROR);
		fail.setTitle("Erreur");
		fail.setHeaderText(null);
		fail.setContentText("Echec de la connexion à la base de données");
		fail.show();
	}
	
	public ObservableList list()
	{
		ObservableList<Products> list = FXCollections.observableArrayList();
		
		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");
			PreparedStatement stm = fr.prepareStatement("SELECT * FROM products JOIN category ON category.cat_id = products.prod_cat_id");
			
			ResultSet result = stm.executeQuery();
	
			while(result.next())
			{
				int id = result.getInt("prod_id");
				String label = result.getString("prod_name");
				String ref = result.getString("prod_ref");
				String desc = result.getString("prod_description");
				float price = result.getFloat("prod_price");
				String img = result.getString("prod_img");
				int stock = result.getInt("prod_stock");
				Date adding = result.getDate("prod_adding");
				Date update = result.getDate("prod_update");
				int cat = result.getInt("cat_id");
				String imgFull = ref+"."+img;
				String catType = result.getString("cat_type");
				int catParent = result.getInt("cat_parent");
				Category catObj = new Category(result.getInt("cat_id"), result.getString("cat_type"), result.getInt("cat_parent"));

				list.add(new Products(id, label, ref, desc, price, stock, img, adding, update, cat, catType, catParent, imgFull, catObj));
			}
			
			stm.close();
			result.close(); 
			fr.close();
		} 
		catch (SQLException ex)
		{
			failConnect();
			System.out.println(ex.getMessage());
		}		
		
		return list;
	}

	public Products listOrder(int x)
	{
		Products prod = new Products();

		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");
			PreparedStatement stm = fr.prepareStatement("SELECT * FROM products JOIN category ON category.cat_id = products.prod_cat_id WHERE prod_id = ?");
			stm.setInt(1, x);
			ResultSet result = stm.executeQuery();

			while(result.next())
			{
				int id = result.getInt("prod_id");
				String label = result.getString("prod_name");
				String ref = result.getString("prod_ref");
				String desc = result.getString("prod_description");
				float price = result.getFloat("prod_price");
				String img = result.getString("prod_img");
				int stock = result.getInt("prod_stock");
				Date adding = result.getDate("prod_adding");
				Date update = result.getDate("prod_update");
				int cat = result.getInt("cat_id");
				String imgFull = ref+"."+img;
				String catType = result.getString("cat_type");
				int catParent = result.getInt("cat_parent");
				Category catObj = new Category(result.getInt("cat_id"), result.getString("cat_type"), result.getInt("cat_parent"));

				prod = new Products(id, label, ref, desc, price, stock, img, adding, update, cat, catType, catParent, imgFull, catObj);
			}

			stm.close();
			result.close();
			fr.close();
		}
		catch (SQLException ex)
		{
			failConnect();
			System.out.println(ex.getMessage());
		}

		return prod;
	}
	
	public ObservableList list(int x)
	{
		ObservableList<Products> list = FXCollections.observableArrayList();
		
		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");
			PreparedStatement stm = fr.prepareStatement("SELECT * FROM products JOIN category ON category.cat_id = products.prod_cat_id WHERE prod_cat_id = ? ");
			stm.setInt(1, x);
			ResultSet result = stm.executeQuery();
	
			while(result.next())
			{
				int id = result.getInt("prod_id");
				String label = result.getString("prod_name");
				String ref = result.getString("prod_ref");
				String desc = result.getString("prod_description");
				float price = result.getFloat("prod_price");
				String img = result.getString("prod_img");
				int stock = result.getInt("prod_stock");
				Date adding = result.getDate("prod_adding");
				Date update = result.getDate("prod_update");
				int cat = result.getInt("cat_id");
				String imgFull = ref+"."+img;
				String catType = result.getString("cat_type");
				int catParent = result.getInt("cat_parent");
				Category catObj = new Category(result.getInt("cat_id"), result.getString("cat_type"), result.getInt("cat_parent"));

				list.add(new Products(id, label, ref, desc, price, stock, img, adding, update, cat, catType, catParent, imgFull, catObj));
			}
			
			stm.close();
			result.close(); 
			fr.close();
		} 
		catch (SQLException ex)
		{
			failConnect();
			System.out.println(ex.getMessage());
		}		
		
		return list;
	}

	public ObservableList listParent(int x)
	{
		ObservableList<Products> list = FXCollections.observableArrayList();

		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");
			PreparedStatement stm = fr.prepareStatement("SELECT * FROM products JOIN category ON category.cat_id = products.prod_cat_id WHERE cat_parent = ?");
			stm.setInt(1, x);
			ResultSet result = stm.executeQuery();

			while(result.next())
			{
				int id = result.getInt("prod_id");
				String label = result.getString("prod_name");
				String ref = result.getString("prod_ref");
				String desc = result.getString("prod_description");
				float price = result.getFloat("prod_price");
				String img = result.getString("prod_img");
				int stock = result.getInt("prod_stock");
				Date adding = result.getDate("prod_adding");
				Date update = result.getDate("prod_update");
				int cat = result.getInt("cat_id");
				String imgFull = ref+"."+img;
				String catType = result.getString("cat_type");
				int catParent = result.getInt("cat_parent");
				Category catObj = new Category(result.getInt("cat_id"), result.getString("cat_type"), result.getInt("cat_parent"));

				list.add(new Products(id, label, ref, desc, price, stock, img, adding, update, cat, catType, catParent, imgFull, catObj));
			}

			stm.close();
			result.close();
			fr.close();
		}
		catch (SQLException ex)
		{
			failConnect();
			System.out.println(ex.getMessage());
		}

		return list;
	}
	
	public void add(Products x)
	{
		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");
			
			PreparedStatement stm = fr.prepareStatement("INSERT INTO products (prod_name, prod_ref, prod_description, prod_price, prod_stock, prod_img, prod_cat_id, prod_adding) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

			stm.setString(1, x.getLabel());	
			stm.setString(2, x.getRef());	
			stm.setString(3, x.getDesc());	
			stm.setFloat(4, x.getPrice());	
			stm.setInt(5, x.getStock());	
			stm.setString(6, x.getImg());	
			stm.setInt(7, x.getCat());		
			stm.setDate(8, new java.sql.Date(new Date().getTime()));	

			stm.execute();
			stm.close();	
			fr.close();
		} 
		catch (SQLException ex)
		{
			failConnect();
			System.out.println(ex.getMessage());
		}
	}
	
	public void del(int x)
	{
		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");
			
			PreparedStatement stm = fr.prepareStatement("DELETE FROM products WHERE prod_id = ?");

			stm.setInt(1, x);	


			stm.execute();
			stm.close();	
			fr.close();
		} 
		catch (SQLException ex)
		{
			failConnect();
			System.out.println(ex.getMessage());
		}
	}
	
	 public void update(Products x) 
	{
		try 
		{
			String url = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url, "root", "");

			PreparedStatement stm = fr.prepareStatement("UPDATE products SET prod_name = ?, prod_ref = ?, prod_description = ?, prod_price = ?, prod_stock = ?, prod_img=?, prod_cat_id=?, prod_update=? WHERE prod_ref = ?");
			stm.setString(1, x.getLabel());	
			stm.setString(2, x.getRef());	
			stm.setString(3, x.getDesc());	
			stm.setDouble(4, x.getPrice());	
			stm.setInt(5, x.getStock());	
			stm.setString(6, x.getImg());	
			stm.setInt(7, x.getCat());	
			stm.setDate(8, new java.sql.Date(new Date().getTime()));
			stm.setString(9, x.getRef());

			stm.execute();
			stm.close();	
			fr.close();

		} 
		catch (Exception e) 
		{
			failConnect();
			 System.out.println(e.getMessage());
		}
	}

	public void updateStock(Boolean b, OrderLine x)
	{
		if(b) //incrementing
		{
			try
			{
				String url = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
				Connection fr = DriverManager.getConnection(url, "root", "");

				PreparedStatement stm = fr.prepareStatement("UPDATE products SET prod_stock = prod_stock + ? WHERE prod_id = ?");
				stm.setInt(1, x.getQuantity());
				stm.setInt(2, x.getProd_id());

				stm.execute();
				stm.close();
				fr.close();

			}
			catch (Exception e)
			{
				failConnect();
				System.out.println(e.getMessage());
			}
		}
		else // decrementing
		{
			try
			{
				String url = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
				Connection fr = DriverManager.getConnection(url, "root", "");

				PreparedStatement stm = fr.prepareStatement("UPDATE products SET prod_stock = prod_stock - ? WHERE prod_id = ?");
				stm.setInt(1, x.getQuantity());
				stm.setInt(2, x.getProd_id());

				stm.execute();
				stm.close();
				fr.close();

			}
			catch (Exception e)
			{
				failConnect();
				System.out.println(e.getMessage());
			}
		}
	}
}
