/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fil_rouge.fougicrok.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class UsersDAO 
{
	
	public Users find(Users x)     
	{
		Users user = new Users();
		
		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");
			PreparedStatement stm = fr.prepareStatement("SELECT * FROM users WHERE user_login = ?");
			stm.setString(1, x.getLogin());
			
			
			ResultSet result = stm.executeQuery();
			result.next();
			
			user = new Users(result);
			
			stm.close();
			result.close();
			fr.close();
		} 
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
			
			return user;
	}
	
	public ObservableList list()
	{
		ObservableList<Users> list = FXCollections.observableArrayList();
		
		try
		{
			String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
			Connection fr = DriverManager.getConnection(url1, "root", "");
			PreparedStatement stm = fr.prepareStatement("SELECT * FROM users");
			
			ResultSet result = stm.executeQuery();
	
			while(result.next())
			{
				list.add(new Users(result));
			}
			
			stm.close();
			result.close();
			fr.close();
		} 
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}		
		
		return list;
	}

}
