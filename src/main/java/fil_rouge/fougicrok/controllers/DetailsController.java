package fil_rouge.fougicrok.controllers;

import com.mysql.cj.exceptions.StreamingNotifiable;
import fil_rouge.fougicrok.DAL.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class DetailsController implements Initializable
{
    @FXML
    public int id, userId;
    public Date date;
    public String dAddress, fAddress;

    public VBox box;
    public Label label_numcom;
    public Label label_fAddress;
    public Label label_dAddress;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
//        for (int i=0; i<3; i++)
//        {
//            try
//            {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/orderLine.fxml"));
//                Node x = loader.load();
//                box.getChildren().add(i,x);
//
//
//                OrderLineController oLinesControl = new OrderLineController();
////                oLinesControl.go(i,12,21);
//            }
//            catch (IOException e)
//            {
//                System.out.println("init dels control -     "+e.getMessage());
//            }
//        }
    }

    public void passpass(Order ord)
    {

        AddressDAO repoAdrs = new AddressDAO();

        this.id = ord.getId();
        this.date = ord.getDate();
        this.userId = ord.getUserId();
        this.dAddress = repoAdrs.solo(ord.getdAddress()).toSlip();
        this.fAddress = repoAdrs.solo(ord.getfAddress()).toSlip();

        label_numcom.setText("Commande n° "+id);
        label_dAddress.setText(dAddress);
        label_fAddress.setText(fAddress);

        OrderLineDAO repoOL = new OrderLineDAO();
        ObservableList<OrderLine> orderLines = repoOL.list(id);

        ProductsDAO repoProd = new ProductsDAO();

        for(int i=0; i< orderLines.size(); i++)
        {
            OrderLine x = orderLines.get(i);
            Products prod = repoProd.listOrder(x.getProd_id());
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/orderLine.fxml"));
                Parent nodeOrderLine = loader.load();

                OrderLineController repo = loader.getController();
                repo.go(x.getQuantity(), x.getuPrice(), x.gettPrice(), prod);

                box.getChildren().add(i,nodeOrderLine);
            }
            catch (IOException e)
            {
                System.out.println("details control -     "+e.getMessage());
            }
        }
    }



}
