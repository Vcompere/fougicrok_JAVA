package fil_rouge.fougicrok.DAL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Date;

public class OrderLineDAO
{
    private void failConnect()
    {
        Alert fail = new Alert(Alert.AlertType.ERROR);
        fail.setTitle("Erreur");
        fail.setHeaderText(null);
        fail.setContentText("Echec de la connexion à la base de données");
        fail.show();
    }

    public ObservableList<OrderLine> list(int x)
    {
        ObservableList<OrderLine> list = FXCollections.observableArrayList();

        try
        {
            String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
            Connection fr = DriverManager.getConnection(url1, "root", "");

            PreparedStatement stm = fr.prepareStatement("SELECT * FROM order_line WHERE line_ord_id = ?");
            stm.setInt(1, x);
            ResultSet result = stm.executeQuery();

            while(result.next())
            {
                int id = result.getInt("line_id");
                int quantity = result.getInt("line_quantity");
                int prodId = result.getInt("line_prod_id");
                double uPrice = result.getDouble("line_uPrice");
                double tPrice = result.getDouble("line_tPrice");


                // creating a new object 'OrderLine' and adding it into the observableList for each row of result
                list.add(new OrderLine(id, prodId, quantity, uPrice, tPrice));
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

    public void add(OrderLine x)
    {
        try
        {
            String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
            Connection fr = DriverManager.getConnection(url1, "root", "");

            PreparedStatement stm = fr.prepareStatement("INSERT INTO order_line (line_uPrice, line_tPrice, line_quantity, line_prod_id, line_ord_id) VALUES (?, ?, ?, ?, ?);");

            stm.setDouble(1, x.getuPrice());
            stm.setDouble(2, x.gettPrice());
            stm.setInt(3, x.getQuantity());
            stm.setInt(4, x.getProd_id());
            stm.setInt(5, x.getOrdId());

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

            PreparedStatement stm = fr.prepareStatement("DELETE FROM order_line WHERE line_ord_id = ?");
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
