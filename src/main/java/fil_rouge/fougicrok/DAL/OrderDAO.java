/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fil_rouge.fougicrok.DAL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Date;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class OrderDAO
{
    private void failConnect()
    {
        Alert fail = new Alert(Alert.AlertType.ERROR);
        fail.setTitle("Erreur");
        fail.setHeaderText(null);
        fail.setContentText("Echec de la connexion à la base de données");
        fail.show();
    }
	
    public ObservableList<Order> list()
    {
        ObservableList<Order> list = FXCollections.observableArrayList();

        try
        {
            String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
            Connection fr = DriverManager.getConnection(url1, "root", "");

            PreparedStatement stm = fr.prepareStatement("SELECT * FROM orders JOIN users ON orders.ord_user_id = users.user_id Join discount ON orders.ord_discount = discount.disc_id");
            ResultSet result = stm.executeQuery();

            while(result.next())
            {
                int id = result.getInt("ord_id");
                Date date = result.getDate("ord_date");
                String userStr = result.getString("user_firstname")+" "+result.getString("user_name")+" - \""+result.getString("user_login")+"\"";
                String promo = result.getString("disc_code")+" (- "+String.valueOf(result.getInt("disc_value"))+" %)";
                String state = result.getString("ord_state");
				
				PreparedStatement stm1 = fr.prepareStatement("SELECT SUM(line_tPrice) as p, SUM(line_quantity) as q FROM order_line WHERE line_ord_id ="+id);
                ResultSet result1 = stm1.executeQuery();
				result1.next();
				double promoV = result.getInt("disc_value");
                double price = result1.getDouble("p") * ((100-promoV)/100);
                int number = result1.getInt("q");
				stm1.close();
				result1.close();
				
                int userId = result.getInt("ord_user_id");
                int promoId = result.getInt("ord_discount");
                int fAddress = result.getInt("ord_fAddress");
                int dAddress = result.getInt("ord_dAddress");

                // creating a new object 'Order' and adding it into the observableList for each row of result
                list.add(new Order(id, date, userStr, promo, state,  price, number, userId, promoId, fAddress, dAddress));
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

    public int add(Order x)
    {
        int ord_id = 0;

        try
        {
            String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
            Connection fr = DriverManager.getConnection(url1, "root", "");
            fr.setAutoCommit(false);
            try {

                PreparedStatement stm = fr.prepareStatement("INSERT INTO orders (ord_fAddress, ord_dAddress, ord_user_id, ord_discount, ord_date, ord_state) VALUES (?,?,?,?, now(), ?)");
                stm.setInt(1, x.getfAddress());
                stm.setInt(2, x.getdAddress());
                stm.setInt(3, x.getUserId());
                stm.setInt(4, x.getPromoId());
                stm.setString(5, x.getState());
                stm.execute();

                PreparedStatement stm1 = fr.prepareStatement("SELECT ord_id FROM orders WHERE ord_id = LAST_INSERT_ID();");

                ResultSet result = stm1.executeQuery();
                result.next();
                ord_id = result.getInt("ord_id");

                result.close();
                stm.close();
                stm1.close();
                fr.commit();
                fr.close();
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
                fr.rollback();
            }
        }
        catch (SQLException ex)
        {

            failConnect();
            System.out.println(ex.getMessage());
        }
        return ord_id;
    }

    public void del(int x)
    {
        try
        {
            String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
            Connection fr = DriverManager.getConnection(url1, "root", "");

            PreparedStatement stm = fr.prepareStatement("DELETE FROM orders WHERE ord_id = ?");
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
}
