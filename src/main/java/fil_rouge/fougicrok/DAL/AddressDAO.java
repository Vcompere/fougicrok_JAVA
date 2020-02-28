package fil_rouge.fougicrok.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class AddressDAO 
{
	private void failConnect()
    {
        Alert fail = new Alert(Alert.AlertType.ERROR);
        fail.setTitle("Erreur");
        fail.setHeaderText(null);
        fail.setContentText("Echec de la connexion à la base de données");
        fail.show();
    }
	
	public ObservableList<Address> list(int x)
    {
        ObservableList<Address> list = FXCollections.observableArrayList();

        try
        {
            String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
            Connection fr = DriverManager.getConnection(url1, "root", "");

            PreparedStatement stm = fr.prepareStatement("SELECT * FROM address WHERE adr_user_id = ?");
			stm.setInt(1, x);
            ResultSet result = stm.executeQuery();

            while(result.next())
            {
                int id = result.getInt("adr_id");
                String country = result.getString("adr_country");
                int zip = result.getInt("adr_zip");
                String street = result.getString("adr_street");
                String city = result.getString("adr_city");
                int user_id = result.getInt("adr_user_id");
     

                // creating a new object 'address' and adding it into the observableList for each row of result
                list.add(new Address(id, country, zip, street, city, user_id));
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

    public Address solo(int x)
    {
        Address adrs = new Address();

        try
        {
            String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
            Connection fr = DriverManager.getConnection(url1, "root", "");

            PreparedStatement stm = fr.prepareStatement("SELECT * FROM address WHERE adr_id = ?");
            stm.setInt(1, x);
            ResultSet result = stm.executeQuery();
            result.next();

            int id = result.getInt("adr_id");
            String country = result.getString("adr_country");
            int zip = result.getInt("adr_zip");
            String street = result.getString("adr_street");
            String city = result.getString("adr_city");
            int user_id = result.getInt("adr_user_id");

            adrs = new Address(id, country, zip, street, city, user_id);

            stm.close();
            result.close();
            fr.close();
        }
        catch (SQLException ex)
        {
            failConnect();
            System.out.println(ex.getMessage());
        }

        return adrs;
    }
}
