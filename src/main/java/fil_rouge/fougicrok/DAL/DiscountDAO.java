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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DiscountDAO 
{
	private void failConnect()
    {
        Alert fail = new Alert(Alert.AlertType.ERROR);
        fail.setTitle("Erreur");
        fail.setHeaderText(null);
        fail.setContentText("Echec de la connexion à la base de données");
        fail.show();
    }
	
	public ObservableList<Discount> list()
    {
        ObservableList<Discount> list = FXCollections.observableArrayList();

        try
        {
            String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
            Connection fr = DriverManager.getConnection(url1, "root", "");

            PreparedStatement stm = fr.prepareStatement("SELECT * FROM discount");
            ResultSet result = stm.executeQuery();

            while(result.next())
            {
                int id = result.getInt("disc_id");
                String code = result.getString("disc_code");
                int value = result.getInt("disc_value");
                int uses = result.getInt("disc_uses");

                // creating a new object 'address' and adding it into the observableList for each row of result
                list.add(new Discount(id, code, value, uses));
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
}
