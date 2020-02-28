package fil_rouge.fougicrok.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class CategoryDAO 
{
	private void failConnect()
	{
		Alert fail = new Alert(Alert.AlertType.ERROR);
		fail.setTitle("Erreur");
		fail.setHeaderText(null);
		fail.setContentText("Echec de la connexion à la base de données");
		fail.show();
	}

	public ObservableList<Category> list()
	{
		ObservableList<Category> list = FXCollections.observableArrayList();

		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");

			PreparedStatement stm = fr.prepareStatement("SELECT * FROM category");
			ResultSet result = stm.executeQuery();

			while(result.next())
			{
				// creating a new object 'Category' and adding it into the observableList for each row of result
				list.add(new Category(result.getInt("cat_id"), result.getString("cat_type"), result.getInt("cat_parent")));
			}

			stm.close();
			result.close();
			fr.close();
		}
		catch (SQLException ex)
		{
//            failConnect();
			System.out.println(ex.getMessage());
		}

		return list;
	}

	public ObservableList<Category> listChilds()
	{
		ObservableList<Category> list = FXCollections.observableArrayList();

		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");

			PreparedStatement stm = fr.prepareStatement("SELECT * FROM category WHERE cat_parent !=0");
			ResultSet result = stm.executeQuery();

			while(result.next())
			{
				// creating a new object 'Category' and adding it into the observableList for each row of result
				list.add(new Category(result.getInt("cat_id"), result.getString("cat_type"), result.getInt("cat_parent")));
			}

			stm.close();
			result.close();
			fr.close();
		}
		catch (SQLException ex)
		{
//            failConnect();
			System.out.println(ex.getMessage());
		}

		return list;
	}
	
	public ObservableList listFull()
	{
		ObservableList<Category> list = FXCollections.observableArrayList();
		
		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");
			
			PreparedStatement stm = fr.prepareStatement("SELECT * FROM category as x JOIN category as y ON x.cat_id = y.cat_parent");	
			ResultSet result = stm.executeQuery();
	
			while(result.next())
			{
				// creating a new object 'Category' and adding it into the observableList for each row of result
				list.add(new Category(result.getInt("y.cat_id"), result.getString("y.cat_type"), result.getInt("y.cat_parent"), result.getString("x.cat_type")));
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
	
	public void add(Category x)
	{
		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");
			
			PreparedStatement stm = fr.prepareStatement("INSERT INTO category (cat_type, cat_parent) VALUES (?, ?)");	

			stm.setString(1, x.getType());	
			stm.setInt(2, x.getIdParent());	

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
			
			PreparedStatement stm = fr.prepareStatement("DELETE FROM category WHERE cat_id = ?");

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
	
	 public void update(Category x) 
	{
		try 
		{
			String url = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url, "root", "");

			PreparedStatement stm = fr.prepareStatement("UPDATE category SET cat_type = ?, cat_parent = ? WHERE cat_id = ?");	
			stm.setString(1, x.getType());	
			stm.setInt(2, x.getIdParent());	
			stm.setInt(3, x.getId());	
	

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
